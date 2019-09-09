package com.lx.benefits.support.order;

import com.alibaba.fastjson.JSON;
import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.money.MoneyUtil;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.lx.benefits.bean.bo.order.OrderRefundBO;
import com.lx.benefits.config.properties.PayProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

@Slf4j
@Component
public class OrderWxPaySupport {

    @Resource
    private PayProperties payProperties;

    @Autowired
    private WxPayService wxPayService;

    public void wxRefund(OrderRefundBO refundBO){

        String nonceStr = UUID.randomUUID().toString().substring(0,16);
        PayProperties.WxPay wxPay = payProperties.getWxPay();
        WxPayRefundRequest request = new WxPayRefundRequest();
        request.setMchId(wxPay.getMchId());
        request.setAppid(wxPay.getAppId());
        request.setOutTradeNo(refundBO.getPayMark());
        request.setRefundFee(MoneyUtil.changeY2F(refundBO.getRefundAmount()));
        request.setOutRefundNo(refundBO.getOutRequestNo());
        request.setRefundDesc(refundBO.getRefundReason());
        request.setTotalFee(MoneyUtil.changeY2F(refundBO.getTotalFee()));
        request.setNonceStr(nonceStr);
        request.setOpUserId(refundBO.getOperatorId());
        request.setNotifyUrl(wxPay.getNotifyUrl());
        request.setSignType(WxPayConstants.SignType.MD5);
        request.setSign(getSign(wxPay, request));

        try {
            WxPayConfig payConfig = new WxPayConfig();
            payConfig.setAppId(StringUtils.trimToNull(wxPay.getAppId()));
            payConfig.setMchId(StringUtils.trimToNull(wxPay.getMchId()));
            payConfig.setMchKey(StringUtils.trimToNull(wxPay.getMchKey()));
//        payConfig.setSubAppId(StringUtils.trimToNull(wxPay.getSubAppId()));
//        payConfig.setSubMchId(StringUtils.trimToNull(wxPay.getSubMchId()));
            payConfig.setKeyPath(wxPay.getKeyPath());
            wxPayService.setConfig(payConfig);

            WxPayRefundResult refundResult = wxPayService.refund(request);
            if(refundResult.getReturnCode().equals(WxPayConstants.RefundStatus.SUCCESS)){
                log.info("退款打款成功 退款编号：{} ,微信退款参数:{},返回结果:{}",refundBO.getRefundApplyNumber(), JSON.toJSONString(refundBO),JSON.toJSONString(refundResult));
            }else {
                log.error("退款打款失败 退款编号：{} ,微信退款参数:{},返回结果:{}",refundBO.getRefundApplyNumber(), JSON.toJSONString(refundBO),JSON.toJSONString(refundResult));
                throw new BusinessException(refundResult.getErrCode(),refundResult.getReturnMsg());
            }

        } catch (WxPayException e) {
            log.error("退款打款异常 退款编号：{} ,微信退款参数:{}",refundBO.getRefundApplyNumber(), JSON.toJSONString(refundBO));
            log.error("退款打款异常 退款编号："+refundBO.getRefundApplyNumber(),e);
            throw new BusinessException(e.getErrCode(),e.getReturnMsg());
        }
    }

    private String getSign(PayProperties.WxPay wxPay, WxPayRefundRequest request) {
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", request.getAppid()); // 微信公众号的appid
        packageParams.put("mch_id", request.getMchId()); // 商务号
        packageParams.put("nonce_str", request.getNonceStr()); // 随机生成后数字，保证安全性
        packageParams.put("out_trade_no", request.getOutTradeNo());
        packageParams.put("out_refund_no", request.getOutRefundNo());
        packageParams.put("total_fee", request.getTotalFee().toString());
        packageParams.put("refund_fee", request.getRefundFee().toString());

        return SignUtils.createSign(packageParams,request.getSignType(),wxPay.getMchKey(),null);
    }


}






