package com.lx.benefits.bean.sdk.jd;

/**
 * Created by ldr on 2017/3/20.
 */
public enum JDOrderType {

    PARENT(1,"父单"),
    SUB(2,"子单");

    private int code;

    private String desc;

    JDOrderType(int code, String desc){
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
