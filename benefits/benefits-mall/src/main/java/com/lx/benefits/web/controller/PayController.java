package com.lx.benefits.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.lx.benefits.bean.enums.PayChannelEnum;
import com.lx.benefits.config.properties.PayProperties;
import com.lx.benefits.utils.HttpHelper;
import com.lx.benefits.utils.TraceLogUtil;
import com.lx.benefits.support.pay.PaySupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 支付
 **/
@Slf4j
@RestController
@RequestMapping("/pay")
public class PayController {
    @Resource
    private PayProperties payProperties;

    @Autowired
    private WxPayService wxPayService;
    @Resource
    private PaySupport paySupport;

    @RequestMapping("/alipayCallback")
    public String alipayCallback(HttpServletRequest request) throws Exception{
        String payMark = request.getParameter("out_trade_no");
        String payTid = request.getParameter("trade_no");
        String tradeStatus = request.getParameter("trade_status");
        log.info("处理支付宝回调开始，payMark={}, payTid={}, tradeStatus={}", payMark, payTid, tradeStatus);

        Map<String, String> params = HttpHelper.getRequestParamtersMap(request);
        if (log.isDebugEnabled()) {
            log.debug("notify params: {}", JSONObject.toJSON(params));
        }
        // 验证签名
        if (!isVerify(params)) {
            return ("fail");
        }
        //处理你的业务逻辑，更细订单状态等
        if("TRADE_SUCCESS".equals(tradeStatus)) {
            if (paySupport.payCallback(payMark, payTid, PayChannelEnum.ALIPAY.getCode())) {
                log.info("处理支付宝回调成功，payMark={}", payMark);
                return ("success");
            } else {
                log.info("处理支付宝回调失败，payMark={}", payMark);
                return ("fail");
            }
        }else {
            return ("fail");
        }
    }


    private boolean isVerify(Map<String, String> params) throws AlipayApiException {
        PayProperties.Alipay alipay = payProperties.getAlipay();
        // 验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipay.getAlipayPublicKey(), alipay.getInputCharset(), alipay.getSignType());
        log.info("notify signVerified: {}", signVerified);
        return signVerified;
    }

    @RequestMapping("/wxPayCallback")
    public String wxPayCallback(HttpServletRequest request, HttpServletResponse response) throws Exception{
        TraceLogUtil.startTrace();
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

            log.info("微信回调开始:"+xmlResult);
            // 内部会检测是否合法, 能解析成功, 即是合法
            WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlResult);
            String payMark = result.getOutTradeNo();
            String payTid = result.getTransactionId();

            log.info("微信回调开始，payMark={}, payTid={}, tradeStatus={}", payMark, payTid);

            //TODO
            if (!paySupport.payCallback(payMark, payTid, PayChannelEnum.WEIXINPAY.getCode())) {
                log.info("处理微信回调失败，payMark={}", payMark);
                return WxPayNotifyResponse.fail("处理失败");
            }
            log.info("处理微信回调成功，payMark={}", payMark);
            return WxPayNotifyResponse.success("处理成功");
        } catch (WxPayException e) {
            log.warn("处理微信回调失败", e);
            return WxPayNotifyResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error("处理微信回调失败", e);
            return WxPayNotifyResponse.fail(e.getMessage());
        } finally {
            TraceLogUtil.stopTrace();
        }
    }



}