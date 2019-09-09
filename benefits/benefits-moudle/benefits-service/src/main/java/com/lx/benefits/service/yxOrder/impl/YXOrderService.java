package com.lx.benefits.service.yxOrder.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.apollo.common.utils.money.MoneyUtil;
import com.lx.benefits.bean.dto.jd.utils.DateUtil;
import com.lx.benefits.bean.dto.yx.result.YXResult;
import com.lx.benefits.bean.dto.yxOrder.api.YXChannelOrderOut;
import com.lx.benefits.bean.dto.yxOrder.api.YXOrderCancelResult;
import com.lx.benefits.bean.dto.yxOrder.api.YXOrderSku;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.order.OrderShip;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.sdk.yanxuan.YXOrder;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.vo.order.ItemOrderVO;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.order.OrderShipService;
import com.lx.benefits.service.order.ProductOrderExService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.service.yx.YXBaseService;
import com.lx.benefits.service.yxOrder.IYXOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


@Service
public class YXOrderService extends YXBaseService implements IYXOrderService {

    private static final Logger logger = LoggerFactory.getLogger(YXOrderService.class);
    @Resource
    private OrderService orderService;
    @Resource
    private OrderShipService orderShipService;
    @Resource
    private ProductOrderExService productOrderExService;
    @Resource
    private SkuService skuService;

    @Override
    public YXChannelOrderOut submit(Long sellerOrderNumber) {

        List<Order> sellerOrderList = orderService.listByNumberAndLevel(sellerOrderNumber,2);
        if (CollectionUtils.isEmpty(sellerOrderList)){
            throw new BusinessException("订单不存在");
        }
        Order sellerOrder = sellerOrderList.get(0);
        Long totalPrice = sellerOrder.getPrice()+sellerOrder.getPointAmount()+sellerOrder.getShipExpense()+sellerOrder.getShipExpensePointAmount();
        Integer freight = sellerOrder.getShipExpense()+sellerOrder.getShipExpensePointAmount();
        YXOrder yxOrder = new YXOrder();
        yxOrder.setOrderId(sellerOrderNumber.toString());
        yxOrder.setSubmitTime(DateUtil.format(new Date(), DateUtil.NEW_FORMAT));
        yxOrder.setPayTime(DateUtil.format(new Date(), DateUtil.NEW_FORMAT));
        yxOrder.setRealPrice(MoneyUtil.changeF2Y(totalPrice.intValue()).doubleValue());
        // yxOrder.setRealPrice(0D);
        yxOrder.setExpFee(MoneyUtil.changeF2Y(freight).doubleValue());
        yxOrder.setPayMethod("支付宝");


        OrderShip orderShip = orderShipService.getByOrderNumber(sellerOrderNumber);
        if (orderShip == null) {
            logger.error("YX_SUBMIT_ORDER_ERROR_ORDER_CONSIGNEE_IS_EMPTY_SUB_ORDER_CODE:" + sellerOrderNumber);
            throw new BusinessException("获取用户收货地址错误");
        }

        yxOrder.setReceiverName(orderShip.getShipToName());
        yxOrder.setReceiverMobile(orderShip.getShipToMobile());
        yxOrder.setReceiverPhone(orderShip.getShipToMobile());
        yxOrder.setReceiverProvinceName(orderShip.getShipToProvince());
        yxOrder.setReceiverCityName(orderShip.getShipToCity());
        yxOrder.setReceiverDistrictName(orderShip.getShipToDistrict());
        yxOrder.setReceiverAddressDetail(orderShip.getShipToAddress());

        List<YXOrderSku> yxOrderSkus = new ArrayList<>();

        List<ItemOrderVO> itemOrderExList = productOrderExService.selectUserItemOrderList(sellerOrder.getBuyerUserId(),Collections.singletonList(sellerOrderNumber),null);

        List<Integer> skuIdList = CollectionExtUtil.getPropertyList(itemOrderExList,ItemOrderVO::getSkuId);
        List<SkuEntity> skuEntityList =skuService.listByIdList(skuIdList);
        Map<Long,String> skuIdCodeMap = CollectionExtUtil.toMap(skuEntityList,SkuEntity::getId,SkuEntity::getSkuCode);
        List<Order> itemOrderList = orderService.listByParentNumberAndLevel(sellerOrderNumber,3);
        Map<Long,Order> itemOrderMap = CollectionExtUtil.toMap(itemOrderList,Order::getNumber);

        for (ItemOrderVO orderItemEx : itemOrderExList) {
            Order itemOrder = itemOrderMap.get(orderItemEx.getItemOrderNumber());
            Long totalGoodsPrice = itemOrder.getPrice()+itemOrder.getPointAmount();
            YXOrderSku yxOrderSku = new YXOrderSku();

            yxOrderSku.setSkuId(skuIdCodeMap.get(orderItemEx.getSkuId().longValue()));
            yxOrderSku.setProductName(orderItemEx.getTitle());
            yxOrderSku.setSaleCount(orderItemEx.getQuantity());
            yxOrderSku.setOriginPrice(MoneyUtil.changeF2Y(orderItemEx.getGoodsPrice().intValue()).doubleValue());
            yxOrderSku.setSubtotalAmount(MoneyUtil.changeF2Y(totalGoodsPrice.intValue()).doubleValue());
            yxOrderSkus.add(yxOrderSku);
        }

        yxOrder.setOrderSkus(yxOrderSkus);

        Map<String, String> params = new HashMap<>();
        params.put("order", JsonUtil.toString(yxOrder));
        String res = handle(params, getOrderPayed());

        logger.info("YX_ORDER_SUBMIT_RESULT:" + res);

        YXResult<String> yxResult = JsonUtil.parseObject(res, new TypeReference<YXResult<String>>() {
        });
        if (yxResult == null || !yxResult.success()) {
            logger.error("YX_ORDER_SUBMIT_ERROR_RES:" + res);
            logger.error("YX_ORDER_SUBMIT_ERROR_PARAM:" + JsonUtil.convertObjToStr(yxOrder));
            throw new BusinessException(yxResult.getCode().toString(),"下单失败");
        }

        return JsonUtil.parseObject(yxResult.getResult(), YXChannelOrderOut.class);
    }

    @Override
    public YXOrderCancelResult cancel(String orderId) {
        Map<String,String> param = new HashMap<>();
        param.put("orderId",orderId);
        String res  = handle(param,getOrderCancel());

        YXResult<String> yxResult = JSON.parseObject(res,new TypeReference<YXResult<String>>(){});
        if( yxResult == null || !yxResult.success()){
            throw new BusinessException(yxResult.getCode().toString(),yxResult.getMsg());
        }

        return JSON.parseObject(yxResult.getResult(),YXOrderCancelResult.class);
    }

    @Override
    public YXResult confirm(String orderId, String packageId) {

        Map<String,String> param = new HashMap<>();
        param.put("orderId",orderId);
        param.put("packageId",packageId);
        param.put("confirmTime", DateUtil.format(new Date(),DateUtil.NEW_FORMAT));

        String res = handle(param,getOrderConfirm());
        logger.info("YX_ORDER_CONFIRM_RESULT:"+res);

        YXResult result = JSON.parseObject(res,YXResult.class);

        if(result == null || !result.success()){
            logger.error("YX_CONFIRM_ORDER_ERROR_RESULT:"+ res);
        }

        return result;
    }

    @Override
    public YXResult<YXChannelOrderOut> query(String orderId) {
        Map<String,String> param = new HashMap<>();
        param.put("orderId", orderId);

        String res = handle(param,getOrderGet());
        logger.info("YX_ORDER_QUERY_RESULT:"+res);
        System.out.println(res);
        YXResult<String> yxResult = JSON.parseObject(res, new TypeReference<YXResult<String>>() {
        });
        if (!yxResult.success()) {
            logger.error("YX_ORDER_QUERY_ERROR_RESULT:" + res);
            logger.error("YX_ORDER_QUERY_ERROR_PARAM:" + JSON.toJSON(orderId));
            return new YXResult<>(yxResult.getCode(), yxResult.getMsg());
        }
        YXChannelOrderOut yxChannelOrderOut = JSON.parseObject(yxResult.getResult(), YXChannelOrderOut.class);

        return new YXResult<>(yxChannelOrderOut);
    }


    @Override
    public Integer freight(Long totalGoodsPrice) {
        /**严选订单88包邮，不满88每单收取10元运费*/
        if(totalGoodsPrice>=8800L){
            return 0;
        }else {
            return 1000;
        }
    }
}
