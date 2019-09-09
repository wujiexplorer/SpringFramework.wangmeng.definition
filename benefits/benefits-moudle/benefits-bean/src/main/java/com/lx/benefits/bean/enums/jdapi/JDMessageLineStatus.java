package com.lx.benefits.bean.enums.jdapi;

/**
 * Created by ldr on 2017/3/17.
 */
public enum JDMessageLineStatus {

    LINED(0,"未处理"),

    PROCESSING(1,"处理中"),

    PROCESSED(2,"处理完成"),

    ABORTED(3,"忽略");

    private int code;
    private String desc;

    JDMessageLineStatus(int code, String desc){
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
