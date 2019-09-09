package com.lx.benefits.support.order;

import com.apollo.common.annotation.RedisLock;
import com.apollo.common.exception.BusinessException;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.lx.benefits.bean.bo.order.OrderPayBO;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.order.OrderPayRecord;
import com.lx.benefits.bean.entity.pay.PayAccount;
import com.lx.benefits.bean.enums.PayChannelEnum;
import com.lx.benefits.bean.vo.pay.PayChargeVO;
import com.lx.benefits.config.properties.PayProperties;
import com.lx.benefits.service.order.OrderPayRecordService;
import com.lx.benefits.service.pay.PayAccountService;
import com.lx.benefits.service.user.WxUserOpenIdService;
import com.lx.benefits.utils.CommonUtil;
import com.lx.benefits.utils.DateTimeUtils;
import com.lx.benefits.utils.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component
public class OrderPaySupport {

    @Resource
    private PayProperties payProperties;
    @Resource
    private OrderPayRecordService orderPayRecordService;
    @Resource
    private PayAccountService payAccountService;
    @Resource
    private WxUserOpenIdService wxUserOpenIdService;
    @Resource
    private OrderAliPaySupport orderAliPaySupport;
    @Autowired
    private WxPayService wxPayService;

    /**
     * 订单支付
     * @param payOrder
     * @param payChannel
     * @return
     */
    @RedisLock(name = "orderPay",keys = {"#payOrder.buyerUserId"},keepMills = 20000L)
    @Transactional(rollbackFor = Exception.class)
    public PayChargeVO pay(Order payOrder, int payChannel,boolean fromWeb,String ip, String wxOpenId) {

        log.info("order pay, payOrderNumber:{}, payChannel:{}, accountId:{} ", payOrder.getNumber(), payChannel, payOrder.getBuyerUserId());
        String payMark = CommonUtil.generateUUID();
        PayAccount payAccount = payAccountService.getById(payProperties.getAlipay().getSellerPayId());

        OrderPayRecord orderPayRecord = new OrderPayRecord();
        orderPayRecord.setPayOrderNumber(payOrder.getNumber());
        orderPayRecord.setPayChannel(payChannel);
        orderPayRecord.setPayMark(payMark);
        orderPayRecord.setSellerPayId(payAccount.getId());
        orderPayRecordService.doAddRecord(orderPayRecord);

        OrderPayBO orderPayBO = new OrderPayBO();
        orderPayBO.setPayMark(payMark);
        orderPayBO.setPayOrderNumber(payOrder.getNumber());
        orderPayBO.setRealPrice(payOrder.getPrice()+payOrder.getShipExpense());
        orderPayBO.setPayChannel(payChannel);
        orderPayBO.setIp(ip);
        orderPayBO.setOpenId(wxOpenId);
        orderPayBO.setFromWeb(fromWeb);
        return payByChannel(orderPayBO);
    }


    /**
     * 支付
     * @param orderPayBO
     * @return
     */
    private PayChargeVO payByChannel(OrderPayBO orderPayBO) {
        PayChargeVO vo = new PayChargeVO();
        vo.setPayOrderNumber(orderPayBO.getPayOrderNumber());
        vo.setPayChannel(orderPayBO.getPayChannel());
        if (orderPayBO.getPayChannel() == PayChannelEnum.ALIPAY.getCode()) {
            vo.setPayStr(orderAliPaySupport.pay(orderPayBO));
            return vo;
        }
        if (orderPayBO.getPayChannel() == PayChannelEnum.WEIXINPAY.getCode()) {

			PayProperties.WxPay wxPay = payProperties.getWxPay();
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            if (orderPayBO.isFromWeb()) {
                orderRequest.setTradeType(WxPayConstants.TradeType.NATIVE);
            }else {
                orderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
            }
            //  支付标题
            orderRequest.setBody("福粒订单");
            orderRequest.setOutTradeNo(orderPayBO.getPayMark());
            // 注意单位是分
            orderRequest.setTotalFee(orderPayBO.getRealPrice().intValue());
            orderRequest.setProductId(orderPayBO.getPayMark());
            orderRequest.setOpenid(orderPayBO.getOpenId());
            orderRequest.setSpbillCreateIp(orderPayBO.getIp());
            Date createTime = new Date();
            Date expireTime = DateTimeUtils.addMins(createTime, 10);
            orderRequest.setTimeStart(LocalDateTimeUtil.toSimpleString(LocalDateTimeUtil.trans2LocalDateTime(createTime)));
            orderRequest.setTimeExpire(LocalDateTimeUtil.toSimpleString(LocalDateTimeUtil.trans2LocalDateTime(expireTime)));
            orderRequest.setNotifyUrl(wxPay.getNotifyUrl());

            try {

                if (orderPayBO.isFromWeb()) {
                    WxPayNativeOrderResult result = wxPayService.createOrder(orderRequest);
                    vo.setPayStr(result.getCodeUrl());
                }else {
                    WxPayMpOrderResult result = wxPayService.createOrder(orderRequest);
                    vo.setAppId(result.getAppId());
                    vo.setTimeStamp(result.getTimeStamp());
                    vo.setNonceStr(result.getNonceStr());
                    vo.setPack(result.getPackageValue());
                    vo.setSignType(result.getSignType());
                    vo.setPaySign(result.getPaySign());
                }

                vo.setPayChannel(orderPayBO.getPayChannel());
                return vo;
            } catch (WxPayException e) {
                log.error("生成微信支付信息失败", e);
                throw new BusinessException("生成微信支付信息失败, 请稍后再试");
            }
        }
        return null;
    }
}






