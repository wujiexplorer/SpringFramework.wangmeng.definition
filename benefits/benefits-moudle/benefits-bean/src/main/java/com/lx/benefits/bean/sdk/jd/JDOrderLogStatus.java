package com.lx.benefits.bean.sdk.jd;

/**
 * Created by ldr on 2017/3/23.
 */
public enum JDOrderLogStatus {

    UN_PROCESS(0,"待处理"),

    PROCESSING(1,"处理中"),

    PROCESSED(2,"已处理"),

    FAILED(3,"处理失败");

    private int code;

    private String desc;

    JDOrderLogStatus(int code, String desc){
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
