package com.lx.benefits.service.sale.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.bo.order.OrderSaveBO;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.sale.AdvanceSaleExample;
import com.lx.benefits.bean.entity.sale.AdvanceSaleItem;
import com.lx.benefits.bean.param.order.OrderSubmitParam;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.product.SkuPriceBean;
import com.lx.benefits.dao.memconsigneeaddress.ConsignessAdressDao;
import com.lx.benefits.mapper.creditoperateinfo.CreditOperateInfoMapper;
import com.lx.benefits.mapper.employeecreditinfo.EmployeeCreditInfoMapper;
import com.lx.benefits.mapper.product.ProductMapper;
import com.lx.benefits.mapper.product.SkuMapper;
import com.lx.benefits.mapper.sale.AdvanceSaleItemMapper;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.enterprfestival.EnterprFestivalService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.service.sale.AdvanceSaleItemService;
import com.lx.benefits.support.order.OrderSupport;

/**
 * @author unknow on 2018-12-26 17:08.
 */
@Service
public class AdvanceSaleServiceImpl implements AdvanceSaleItemService {

    private final static Logger logger = LoggerFactory.getLogger(AdvanceSaleServiceImpl.class);

    @Autowired
    private ConsignessAdressDao adressDao;

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;
    
    @Autowired
    private AdvanceSaleItemMapper advanceSaleItemMapper;

    @Resource
    private OrderSupport orderSupport;

    @Resource
    private SkuService skuService;

    @Autowired
    private EmployeeCreditInfoMapper employeeCreditInfoMapper;

    @Autowired
    private CreditOperateInfoMapper creditOperateInfoMapper;

    @Autowired
    private EmployeeUserInfoService employeeUserInfoService;
    @Autowired
    private EnterprFestivalService enterprFestivalService;


    @Override
    public Long insertSelective(AdvanceSaleItem record) {
        return advanceSaleItemMapper.insertSelective(record);
    }


    @Override
    public Integer update(AdvanceSaleItem item, AdvanceSaleExample example) {
        return advanceSaleItemMapper.updateByExample(item, example);
    }

    @Override
    public List<AdvanceSaleItem> selectListByCampaignId(Long campaignId) {
        AdvanceSaleExample example = new AdvanceSaleExample();
        example.createCriteria().andCampaignIdEqualTo(campaignId);
        return advanceSaleItemMapper.selectByExample(example);
    }

    @Override
    public JSONObject submitOrder(Long campaignId) {
		enterprFestivalService.getById(campaignId);//判断时间是否在有效期内和是否存在，该判断已在被调用方法中存在
        try {
            AdvanceSaleExample example = new AdvanceSaleExample();
            example.createCriteria().andCampaignIdEqualTo(campaignId).andFlagNotEqualTo(2);
            example.setPage(0);
            example.setPageSize(10000);
            List<AdvanceSaleItem> list = advanceSaleItemMapper.selectByExample(example);
            if (null == list || list.size() < 1) {
                return Response.fail("无代下单数据");
            }
            int[] temp = {0, 0};
            CountDownLatch countDownLatch = new CountDownLatch(20);
            for (AdvanceSaleItem advanceSaleItem : list) {
                logger.info("预售代下单开始,请求参数:{}", JSON.toJSONString(advanceSaleItem));
                CompletableFuture.supplyAsync(() -> creatOrder(advanceSaleItem,countDownLatch,temp),taskExecutor);
            }
        } catch (Exception e) {
            logger.error("预售代下单异常 {}",e.getMessage());
            return Response.fail("解析代下单文件异常");
        }

        return Response.succ("导入成功");
    }

    @Override
    public List<AdvanceSaleItem> selectDownExcel(Long campaignId) {
        AdvanceSaleItem advanceSaleItem = new AdvanceSaleItem();
        advanceSaleItem.setCampaignId(campaignId);
        return advanceSaleItemMapper.selectDownExcel(advanceSaleItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject submitAdvance(Map<String, Object> params) {
        Long enterpriseId =  SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
        if (null == enterpriseId || enterpriseId < 1) {
            return Response.fail("登录失效，请重新登录");
        }
        Long skuId = Long.parseLong(params.get("skuId").toString());
        if (skuId == null || skuId < 1) {
            return Response.fail("skuId不能为空");
        }
        Long campaignId = Long.parseLong(params.get("campaignId").toString());
        if (campaignId == null || campaignId < 1) {
            return Response.fail("预售活动id不能为空");
        }
        Long tid = Long.parseLong(params.get("tid").toString());
        Integer count = Integer.parseInt(params.get("count").toString());
        if (null == count || count < 1) {
            count = 1;
        }
        // 查询商品价格
        SkuEntity skuEntity = skuService.queryBySkuId(skuId);
        if (null == skuEntity || skuEntity.getGoodsState().equals(0)) {
            return Response.fail("商品不存在或已下架");
        }
        List<Long> skuIds  = new ArrayList<>();
        skuIds.add(skuId);
		Map<Long, SkuPriceBean> map = skuService.singleCustomPrice(skuIds, enterpriseId);
        BigDecimal goodsPrice  = map.get(skuId).getGoodsPrice();
        Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        // 用户预售额度
        EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoMapper.selectEmployeeCreditInfo(employeeId,campaignId);
        if (null == employeeCreditInfo) {
            return Response.fail("用户无额度购买");
        }
        if (employeeCreditInfo.getCredit().doubleValue() < goodsPrice.doubleValue()*count) {
            return Response.fail("额度不足，无法购买");
        }
        // 提交预售订单
        AdvanceSaleItem advanceSaleItem = new AdvanceSaleItem();
        advanceSaleItem.setCampaignId(campaignId);
        advanceSaleItem.setUserid(employeeId);
        advanceSaleItem.setCount(count);
        advanceSaleItem.setTid(tid);
        advanceSaleItem.setSku(skuId);
        advanceSaleItem.setGoodsPrice(goodsPrice);
        Long insert = advanceSaleItemMapper.insertSelective(advanceSaleItem);
        if (insert > 0) {
            //扣除额度
            EmployeeCreditInfo info = new EmployeeCreditInfo();
            info.setEmployeeId(employeeId);
            info.setCampaignId(campaignId);
            info.setCredit(goodsPrice);
            info.setUpdated((int) DateUtil.getNowTimestamp10());
            int update = employeeCreditInfoMapper.updateEmployeeCredit(info);
            if (update < 1) {
                throw new RuntimeException("预售购买失败");
            }
            CreditOperateInfo creditOperateInfo = new CreditOperateInfo();
            creditOperateInfo.setCreditType(4);
            creditOperateInfo.setOwnerUserId(employeeId);
            creditOperateInfo.setOptUserId(employeeId);
            creditOperateInfo.setCredit(goodsPrice);
            creditOperateInfo.setOptTime(DateUtil.getNowTimestamp10());
            creditOperateInfo.setCampaignId(campaignId);
            creditOperateInfo.setOptType(11);
            creditOperateInfo.setCreated(DateUtil.getNowTimestamp10());
            creditOperateInfo.setUpdated(DateUtil.getNowTimestamp10());
            creditOperateInfo.setRemark("预售购买扣除额度");
            int credit = creditOperateInfoMapper.insertSelective(creditOperateInfo);
            if (credit < 1) {
                throw new RuntimeException("预售购买失败");
            }
        }
        return Response.succ("预售购买成功");
    }

    private String creatOrder(AdvanceSaleItem advanceSaleItem, CountDownLatch countDownLatch,int[] temp) {
        OrderSubmitParam orderSubmitParam = new OrderSubmitParam();
        try {
            ConsigneeAdress consigneeAdress = adressDao.getUserDefaultAddress(advanceSaleItem.getUserid());
            if (consigneeAdress != null) {
            	EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(advanceSaleItem.getUserid(),false);
                SkuEntity skuEntity = skuMapper.queryBySkuId(advanceSaleItem.getSku());
                ProductEntity productEntity = productMapper.selectById(skuEntity.getSpuCode());

                OrderSubmitParam.SkuParam skuParam = new OrderSubmitParam.SkuParam();
                skuParam.setSkuId(advanceSaleItem.getSku().intValue());
                skuParam.setQuantity(advanceSaleItem.getCount().intValue());

                OrderSubmitParam.SellerOrderParam sellerOrderParam = new OrderSubmitParam.SellerOrderParam();
                sellerOrderParam.setSellerId(productEntity.getSupplierId());
                sellerOrderParam.setBuyerComment("");
                sellerOrderParam.setItemList(Collections.singletonList(skuParam));

                orderSubmitParam.setUserReceiveAddrId(consigneeAdress.getId());
                orderSubmitParam.setCampaginId(advanceSaleItem.getCampaignId());
                orderSubmitParam.setTopicId(advanceSaleItem.getTid());
                orderSubmitParam.setOnlyPointPay(true);
                orderSubmitParam.setSource(1);
                orderSubmitParam.setSellerOrderList(Collections.singletonList(sellerOrderParam));
                orderSubmitParam.setAccountId(advanceSaleItem.getUserid());
                orderSubmitParam.setPlatform(0);
                orderSubmitParam.setEnterprId(employeeInfoDto.getEnterprId());
                logger.info("创建手工单成功,参数信息：{}", JSON.toJSONString(orderSubmitParam));
                OrderSaveBO orderSaveBO = orderSupport.assembleOrder(orderSubmitParam);
                orderSupport.saveOrder(orderSaveBO,null);
                temp[0] += 1;
                advanceSaleItem.setOrderMsg("成功");
                advanceSaleItem.setFlag(2);
                advanceSaleItem.setStatus(0);
                advanceSaleItemMapper.updateSelective(advanceSaleItem);
            } else {
                temp[1] += 1;
                advanceSaleItem.setStatus(1);
                advanceSaleItem.setFlag(1);
                advanceSaleItem.setOrderMsg("没有收货地址");
                advanceSaleItemMapper.updateSelective(advanceSaleItem);
            }

        } catch (Exception e) {
            temp[1] += 1;
            AdvanceSaleItem recorder=new AdvanceSaleItem();
            recorder.setId(advanceSaleItem.getId());
            recorder.setOrderMsg(e.getMessage());
            recorder.setStatus(1);
            recorder.setFlag(1);
            advanceSaleItemMapper.updateByPrimaryKeySelective(recorder);
            logger.info("预售代下单异常 {} 参数信息：{}",e.getMessage(),JSON.toJSONString(orderSubmitParam));
        } finally {
            countDownLatch.countDown();
        }
        return "";
    }

}
