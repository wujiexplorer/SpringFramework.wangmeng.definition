package com.lx.benefits.bean.enums;

/**
 * 支付渠道
 */
public enum PayChannelEnum {

    ALIPAY(1, "支付宝"),
    WEIXINPAY(2, "微信");

    private int code;
    private String desc;

    PayChannelEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(int code){
        for(PayChannelEnum payChannelEnum : PayChannelEnum.values()){
            if(payChannelEnum.getCode() == code){
                return payChannelEnum.getDesc();
            }
        }
        return "";
    }
}
