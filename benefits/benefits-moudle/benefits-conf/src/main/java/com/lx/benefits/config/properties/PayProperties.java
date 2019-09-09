package com.lx.benefits.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 项目变量配置
 */
@Component
@ConfigurationProperties(prefix = "pay")
@Lazy(false)
@Data
public class PayProperties {


    private Alipay alipay = new Alipay();

    private WxPay wxPay = new WxPay();

    @Data
    public class Alipay {
        private String appId;
        private String merchantId;
        private String secret;
        private int sellerPayId;
        private String partner;
        private String privateKey;
        private String publicKey;
        private String alipayPublicKey;
        private String notifyUrl;
        private String returnUrl;
        private String logPath;
        private String inputCharset;
        private String signType;
    }

    /**
     * 微信支付
     */
    @Data
    public class WxPay {
        private int sellerPayId;
        /**
         * 公众号appid.
         */
        private String appId;
        /**
         * 商户号
         */
        private String mchId;

        /**
         * 商户密钥.
         */
        private String mchKey;

        /**
         * 微信支付异步回掉地址，通知url必须为直接可访问的url，不能携带参数.
         */
        private String notifyUrl;
        /**
         * 证书地址
         */
        private String keyPath;
    }
}
