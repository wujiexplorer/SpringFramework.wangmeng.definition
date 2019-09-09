package com.lx.benefits.bean.sdk.jd;

/**
 * Created by ldr on 2017/3/23.
 */
public enum JDOrderLogType {

    ORDER_SUBMIT_FALED(1,"订单提交失败"),

    ORDER_CONFIRM_FAILED(2,"订单确认失败"),

    ORDER_PAY_FAILED(3,"订单支付失败");

    private int code;

    private String desc;

    JDOrderLogType(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
