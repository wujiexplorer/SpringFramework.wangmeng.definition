package com.lx.benefits.sdk.wxpay;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.lx.benefits.config.properties.PayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class PayConfig {

    @Resource
    private PayProperties payProperties;

    @Bean
    public WxPayConfig wxPayConfig() {
        PayProperties.WxPay wxPay = payProperties.getWxPay();

        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(wxPay.getAppId());
        payConfig.setMchId(wxPay.getMchId());
        payConfig.setMchKey(wxPay.getMchKey());
        payConfig.setNotifyUrl(wxPay.getNotifyUrl());
        payConfig.setTradeType("JSAPI");
        return payConfig;
    }

    @Bean
    public WxPayService wxPayService(WxPayConfig wxPayConfig) {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        return wxPayService;
    }
}
