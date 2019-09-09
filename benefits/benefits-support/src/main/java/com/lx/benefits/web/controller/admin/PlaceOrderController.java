package com.lx.benefits.web.controller.admin;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apollo.common.utils.bean.BeanUtil;
import com.lx.benefits.bean.bo.order.OrderSaveBO;
import com.lx.benefits.bean.dto.admin.enterprise.EnterpriseQueryReqDto;
import com.lx.benefits.bean.dto.admin.order.*;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.orderimportinfo.OrderImportInfo;
import com.lx.benefits.bean.entity.orderimportitem.OrderImportItem;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.param.order.OrderSubmitParam;
import com.lx.benefits.bean.util.EasyExcelUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionFuliInfo;
import com.lx.benefits.bean.vo.product.SkuPriceBean;
import com.lx.benefits.dao.memconsigneeaddress.ConsignessAdressDao;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.orderimport.OrderImportInfoService;
import com.lx.benefits.service.orderimportitem.OrderImportItemService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.support.order.OrderSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * Created by softw on 2019/1/15.
 */
@Api(tags = "运营后台-订单模块")
@RestController("placeOrderController")
@RequestMapping("/admin/placeOrder")
public class PlaceOrderController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private String order_url;

    @Autowired
    private OrderImportInfoService orderImportInfoService;

    @Autowired
    private OrderImportItemService orderImportItemService;

    @Autowired
    private EmployeeUserInfoService employeeUserInfoService;

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;
    @Resource
    private OrderSupport orderSupport;
    @Autowired
    private ConsignessAdressDao adressDao;
    @Resource
    private SkuService skuService;
    @Resource
    private ProductService productService;

    @Autowired
    public PlaceOrderController(Environment env) {
        order_url =  env.getProperty("fuli.service.api");
    }

    @ApiOperation(value = "订单代下单", response = Boolean.class)
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public JSONObject submit(@ApiParam(value = "Request", name = "reqDto") @RequestBody OrderReqDto reqDto) {
        if (null == reqDto) {
            return Response.fail("代下单参数不能为空!");
        }
        String orderListFile = reqDto.getOrderListFile();
        if (null == orderListFile || orderListFile.isEmpty()) {
            return Response.fail("代下单文件不能为空!");
        }
        try {
            Long enterprId = 0L;
            SessionFuliInfo sessionFuliInfo = SessionContextHolder.getSessionFuliInfo();
            // 记录导入记录
            OrderImportInfo orderImportInfo = new OrderImportInfo();
            orderImportInfo.setOptUserId(sessionFuliInfo.getAdminId());
            orderImportInfo.setImportCount(0L);
            orderImportInfo.setEnterprId(enterprId);
            orderImportInfo.setFileUrl(orderListFile);
            orderImportInfo.setImportErr(0L);
            orderImportInfo.setImportSuc(0L);

            UrlResource resource = new UrlResource(orderListFile);
            List<Object> data = EasyExcelUtil.readExcelWithModel(resource.getInputStream(), OrderInfoDto.class, ExcelTypeEnum.XLSX);
            //10. 记录导入记录
            Long importId =  orderImportInfoService.insertSelective(orderImportInfo);
            orderImportInfo.setImportId(importId);
            if (importId < 1) {
                return Response.fail("导入失败");
            }
            int line = 2;
            long importNum = 0;
            int latch = data.size() >20 ?20 : data.size();
            CountDownLatch countDownLatch = new CountDownLatch(latch);
            int[] temp = {0, 0};
            for (Object orderInfo : data) {
                importNum++;
                OrderInfoDto orderInfoDto = (OrderInfoDto) orderInfo;
                if (null == orderInfoDto) {
                    countDownLatch.countDown();
                    return Response.fail(String.format("第 %s 代下单信息不能为空!", line));
                }
                String loginName = orderInfoDto.getLoginName();
                if (null == loginName || StringUtils.isEmpty(loginName)) {
                    countDownLatch.countDown();
                    return Response.fail(String.format("第 %s 代下单信息用户名不能为空!", line));
                }
                EmployeeUserInfo userInfo = employeeUserInfoService.findByLoginName(loginName);
                if (null == userInfo) {
                    countDownLatch.countDown();
                    return Response.fail(String.format("第 %s 用户名不存在!", line));
                }
                orderInfoDto.setUserid(userInfo.getEmployeeId());
                enterprId = userInfo.getEnterprId();
                logger.info("创建手工单,请求参数:{}", JSON.toJSONString(orderInfo));
                CompletableFuture.supplyAsync(() -> creatOrder(orderInfoDto,countDownLatch,temp,importId),taskExecutor);
                line++;
            }

            OrderImportInfo updateOrder = new OrderImportInfo();
            updateOrder.setImportCount(importNum);
            updateOrder.setImportId(importId);
            updateOrder.setEnterprId(enterprId);
            orderImportInfoService.update(updateOrder);
        } catch (Exception e) {
            logger.error("代下单 {}",e.getMessage());
            return Response.fail("解析代下单文件异常");
        }

        return Response.succ("导入成功");
    }

    @ApiOperation(value = "代下单导入记录列表", response = OrderImportResDto.class)
    @RequestMapping(value = "/importlist", method = RequestMethod.GET)
    public JSONObject importList(@ApiParam(value = "Request", name = "req") @ModelAttribute EnterpriseQueryReqDto req) {
        return orderImportInfoService.selectByExample(req);
    }

    @ApiOperation(value = "订单导入详情列表", response = OrderImportItemResDto.class)
    @RequestMapping(value = "/importitem", method = RequestMethod.GET)
    public JSONObject importitem(@ApiParam(value = "Request", name = "req") @ModelAttribute OrderImportItemQueryDto req) {
        return orderImportItemService.selectByExample(req);
    }


    private String creatOrder(OrderInfoDto orderInfoDto,CountDownLatch countDownLatch,int[] temp,Long importId) {
        OrderImportItem orderImportItem = BeanUtil.copySpring(orderInfoDto,OrderImportItem.class);
        OrderSubmitParam orderSubmitParam = new OrderSubmitParam();
        try {
            logger.info("请求下单接口参数 {}  {}",order_url, JSON.toJSONString(orderInfoDto));
            EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(orderImportItem.getUserid(),false);
            ConsigneeAdress consigneeAdress = adressDao.getUserDefaultAddress(orderImportItem.getUserid());
            if (consigneeAdress != null) {
                SkuEntity skuEntity = skuService.getBySkuId(orderInfoDto.getSku());
                ProductEntity productEntity = productService.selectById(skuEntity.getSpuCode());


                OrderSubmitParam.SkuParam skuParam = new OrderSubmitParam.SkuParam();
                skuParam.setSkuId(orderInfoDto.getSku().intValue());
                skuParam.setQuantity(orderInfoDto.getCount().intValue());

                OrderSubmitParam.SellerOrderParam sellerOrderParam = new OrderSubmitParam.SellerOrderParam();
                sellerOrderParam.setSellerId(productEntity.getSupplierId());
                sellerOrderParam.setBuyerComment("");
                sellerOrderParam.setItemList(Collections.singletonList(skuParam));

                orderSubmitParam.setUserReceiveAddrId(consigneeAdress.getId());
                orderSubmitParam.setTopicId(Objects.isNull(orderInfoDto.getTid())?0L:orderInfoDto.getTid());
                orderSubmitParam.setCampaginId(Objects.isNull(orderInfoDto.getCampaignId())?0L:orderInfoDto.getCampaignId());
                orderSubmitParam.setOnlyPointPay(true);
                orderSubmitParam.setSource(1);
                orderSubmitParam.setSellerOrderList(Collections.singletonList(sellerOrderParam));
                orderSubmitParam.setAccountId(orderImportItem.getUserid());
                orderSubmitParam.setPlatform(0);
                orderSubmitParam.setEnterprId(employeeInfoDto.getEnterprId());
                // 获取商品价格
                Map<Long, SkuPriceBean> singleCustomPrice = skuService.singleCustomPrice(Arrays.asList(orderInfoDto.getSku()), employeeInfoDto.getEnterprId());
                BigDecimal goodsPrice = singleCustomPrice.get(orderInfoDto.getSku()).getGoodsPrice();
                orderImportItem.setGoodsPrice(goodsPrice);
                OrderSaveBO orderSaveBO = orderSupport.assembleOrder(orderSubmitParam);
				orderSupport.saveOrder(orderSaveBO, null);

                orderImportItem.setOrderMsg("代下单成功");
                orderImportItem.setStatus(0);
                temp[0] += 1;
                logger.info("创建手工单成功,参数信息：{}",JSON.toJSONString(orderSubmitParam));
            } else {
                temp[1] += 1;
                orderImportItem.setOrderMsg("无代下单地址");
                orderImportItem.setStatus(1);
            }

        } catch (Exception e) {
            logger.info("代下单 creatOrder 异常 {} 参数信息：{}",e.getMessage(),JSON.toJSONString(orderSubmitParam));
            temp[1] += 1;
            orderImportItem.setStatus(1);
            orderImportItem.setOrderMsg(e.getMessage());
        }  finally {
            logger.info("代下单temp",temp.toString());
            // 更新导入状态
            OrderImportInfo orderImportInfo = new OrderImportInfo();
            orderImportInfo.setImportErr((long)temp[1]);
            orderImportInfo.setImportSuc((long)temp[0]);
            orderImportInfo.setImportId(importId);
            Integer update = orderImportInfoService.update(orderImportInfo);
            logger.info("更新导入记录 {} update={} ", JSON.toJSONString(orderImportInfo),update);

            // 订单详情
            try {
                orderImportItem.setImportId(importId);
                Long insert = orderImportItemService.insertSelective(orderImportItem);
                logger.info("详情导入记录 {} update={} ", JSON.toJSONString(orderImportItem),insert);
            } catch (Exception e) {
                logger.info("createOrder 导入明细异常 {}",e.getMessage());
            }

            countDownLatch.countDown();
        }
        return "";
    }


}
