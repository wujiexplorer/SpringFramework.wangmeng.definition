package com.lx.benefits.web.ao;

import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.constants.DAOConstant;
import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import com.lx.benefits.bean.dto.jd.utils.DateUtil;
import com.lx.benefits.bean.dto.order.LogisticVO;
import com.lx.benefits.bean.dto.yianapi.YiAnOrderDetails;
import com.lx.benefits.bean.dto.yianapi.YianOrderDetailVO;
import com.lx.benefits.bean.entity.ent.YianOrderInfo;
import com.lx.benefits.bean.entity.ent.YianOrderItem;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.enums.yianapi.model.YiAnResult;
import com.lx.benefits.bean.enums.yianapi.model.YiAnUserInfoResult;
import com.lx.benefits.bean.enums.yianapi.model.kuaidi100query.Kuaidi100Line;
import com.lx.benefits.bean.enums.yianapi.model.kuaidi100query.Kuaidi100Result;
import com.lx.benefits.bean.to.order.LogDetailTO;
import com.lx.benefits.bean.to.order.OrderLineTO;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.service.yianapi.IKuaidi100QueryService;
import com.lx.benefits.service.yianapi.IYiAnUserInfoService;
import com.lx.benefits.service.yianapi.YianOrderInfoService;
import com.lx.benefits.service.yianapi.YianOrderItemService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by lidongri on 2018/11/12.
 */

@Service
public class YiAnOrderAO {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private YianOrderInfoService yianOrderInfoService;

    @Autowired
    private YianOrderItemService yianOrderItemService;

    @Autowired
    private IYiAnUserInfoService yiAnUserInfoService;

    @Autowired
    private IKuaidi100QueryService kuaidi100QeryService;

    @Resource
    private SkuService skuService;

    public MResultVO details(YiAnOrderDetails orderDetails) {
        try {
            YianOrderInfo infoq = new YianOrderInfo();
            infoq.setOrderCode(orderDetails.getOid());

            ResultInfo<YianOrderInfo> resultInfo = yianOrderInfoService.queryUniqueByObject(infoq);
            if (!resultInfo.isSuccess()) {
                return new MResultVO(resultInfo.getMsg().getMessage(), resultInfo.getMsg().getCode());
            }

            YianOrderInfo yianOrder = resultInfo.getData();

            if (yianOrder == null) return new MResultVO(MResultInfo.SUCCESS);

            //验证用户
            YiAnResult<YiAnUserInfoResult> result = yiAnUserInfoService.getUserInfo(orderDetails.getCode());

            if (result == null || result.getResult() == null) {
                return new MResultVO("用户信息错误");
            }
            YiAnUserInfoResult us = result.getResult();
            if (!(yianOrder.getEeNo().equalsIgnoreCase(us.getEe_no()) &&(yianOrder.getOrgCode().equalsIgnoreCase(us.getOrg_code())||
            yianOrder.getOrgCode().equalsIgnoreCase(us.getRoot_org_code())))) {
                return new MResultVO("用户信息错误");
            }


            YianOrderItem itemq = new YianOrderItem();
            itemq.setOrderCode(orderDetails.getOid());

            ResultInfo<List<YianOrderItem>> itemRes = yianOrderItemService.queryByObject(itemq);

            if (!itemRes.isSuccess()) {
                return new MResultVO(itemRes.getMsg().getMessage(), itemRes.getMsg().getCode());
            }

            List<YianOrderItem> yianOrderItems = itemRes.getData();

            List<String> skus = yianOrderItems.stream().map(YianOrderItem::getSku).collect(Collectors.toList());

            Map<String, Object> params = new HashMap<>();
            params.put("skus",skus);
            List<SkuEntity> itemSkus = skuService.queryByParam(params);
            Map<Long, SkuEntity> itemSkuMap = itemSkus.stream().collect(Collectors.toMap(SkuEntity::getId, Function.identity()));

            List<Long> itemIds = itemSkus.stream().map(SkuEntity::getId).collect(Collectors.toList());
            Map<String, Object> params2 = new HashMap<>();
            List<DAOConstant.WHERE_ENTRY> whereList2 = new ArrayList<DAOConstant.WHERE_ENTRY>();
            whereList2.add(new DAOConstant.WHERE_ENTRY("item_id", DAOConstant.MYBATIS_SPECIAL_STRING.INLIST, itemIds));
            params2.put(DAOConstant.MYBATIS_SPECIAL_STRING.WHERE.name(), whereList2);

            YianOrderDetailVO detailVO = buildYianOrderDetailVO(yianOrder);

            List<OrderLineTO> lineTOS = buildOrderLineTOS(yianOrderItems, itemSkuMap);

            detailVO.setLines(lineTOS);

            return new MResultVO(MResultInfo.SUCCESS, detailVO);
        } catch (ServiceException se) {
            logger.error("M_YIAN_ORDER_ERROR", se);
            return new MResultVO(se.getErrorCode().toString(), se.getMessage());
        } catch (Exception e) {
            logger.error("M_YIAN_ORDER_ERROR", e);
            return new MResultVO(MResultInfo.SYSTEM_ERROR);
        }
    }


    public MResultVO<LogisticVO> track(YiAnOrderDetails vo) {

        LogisticVO lv = new LogisticVO();
        lv.setLogcode(vo.getNu());
        lv.setCompany(vo.getNam());
        try {
            Kuaidi100Result<Kuaidi100Line> result = kuaidi100QeryService.query(vo.getCom(), vo.getNu());
            if (null != result) {
                List<Kuaidi100Line> expressLogInfoList = result.getData();
                if (CollectionUtils.isNotEmpty(expressLogInfoList)) {
                    List<LogDetailTO> loglist = new ArrayList<>();
                    for (Kuaidi100Line ei : expressLogInfoList) {
                        loglist.add(new LogDetailTO(ei.getContext(), ei.getTime()));
                    }
                    lv.setLoglist(loglist);
                }
            }
            return new MResultVO<LogisticVO>(MResultInfo.SUCCESS, lv);

        } catch (Exception e) {
            logger.error("M_YIAN_TRACK_ERROR", e);
            return new MResultVO(MResultInfo.SUCCESS, lv);
        }

    }

    private List<OrderLineTO> buildOrderLineTOS(List<YianOrderItem> yianOrderItems, Map<Long, SkuEntity> itemSkuMap) {
        List<OrderLineTO> lineTOS = new ArrayList<>();

        for (YianOrderItem item : yianOrderItems) {
            OrderLineTO to = new OrderLineTO();
            to.setSku(item.getSku());
            to.setName(item.getItemName());
            to.setCount(StringUtil.getStrByObj(item.getCount()));
            to.setPrice(StringUtil.getStrByObj(item.getPrice()));
            to.setLineprice(StringUtil.getStrByObj(item.getTotal()));
            SkuEntity itemSku = itemSkuMap.get(item.getSku());
            to.setImgurl(itemSku.getGoodsImage());
            lineTOS.add(to);
        }
        return lineTOS;
    }

    private YianOrderDetailVO buildYianOrderDetailVO(YianOrderInfo yianOrder) {
        YianOrderDetailVO detailVO = new YianOrderDetailVO();
        detailVO.setOrdercode(yianOrder.getOrderCode());
        detailVO.setOrdertype(StringUtil.getStrByObj(yianOrder.getOrderType()));
        detailVO.setOrderprice(StringUtil.getStrByObj(yianOrder.getTotal()));
        detailVO.setAddress(yianOrder.getReceiverAddress());
        detailVO.setName(yianOrder.getReceiverName());
        detailVO.setTel(yianOrder.getReceiverMobile());
        detailVO.setOrdertime(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", yianOrder.getCreateTime()));
        detailVO.setPaytime(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", yianOrder.getCreateTime()));
        detailVO.setStatus(StringUtil.getStrByObj(yianOrder.getOrderStatus()));
        detailVO.setVno(StringUtil.getStrByObj(yianOrder.getVirtualCardNo()));
        detailVO.setVpwd(yianOrder.getVirtualPassword());
        detailVO.setVintro(StringUtil.getStrByObj(yianOrder.getVirtualIntro()));
        detailVO.setDeliverycode(StringUtil.getStrByObj(yianOrder.getDeliveryCode()));
        detailVO.setDeliverycompanycode(StringUtil.getStrByObj(yianOrder.getDeliveryCompanyCode()));
        detailVO.setDeliveryname(StringUtil.getStrByObj(yianOrder.getDeliveryCompany()));
        detailVO.setDiscountamount("0");
        detailVO.setFreight("0");
        detailVO.setTaxes("0");
        detailVO.setDisprice("0");
        detailVO.setTotalpoint("0");
        detailVO.setItemprice(StringUtil.getStrByObj(yianOrder.getTotal()));
        detailVO.setBaseprice(StringUtil.getStrByObj(yianOrder.getTotal()));
        return detailVO;
    }


}
