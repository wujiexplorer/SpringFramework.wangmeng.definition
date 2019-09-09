package com.lx.benefits.bean.vo.pay;

import lombok.Data;

@Data
public class PayChargeVO {
    /**
     * 支付渠道
     *
     * @see
     */
    private int payChannel;

    /**
     * 如果是是支付宝, 返回的支付信息;如果是微信返回的是code_url
     */
    private String payStr;

    /*****************************
     * 微信支付需要的信息
     * 参考: https://developers.weixin.qq.com/miniprogram/dev/api/api-pay.html#wxrequestpaymentobject
     */
    private String appId;
    /**
     * 随机字符串，长度为32个字符以下
     */
    private String nonceStr;
    /**
     * 统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=*
     * package
     */
    private String pack;
    /**
     * 签名算法，暂支持 MD5
     */
    private String signType;
    /**
     * 时间戳从1970年1月1日00:00:00至今的秒数,即当前的时间
     */
    private String timeStamp;
    /**
     * 签名
     */
    private String paySign;

    /**
     * 支付单号
     */
    private Long payOrderNumber;
}
