package com.lx.benefits.bean.enums;

/**
 * User:wangmeng
 * Date:2019/8/20
 * Time:15:24
 * Version:2.x
 * Description:TODO
 **/
public enum SeckillEnum {

    NO_START(0,"未开始"),
    STARTING(1,"进行中"),
    STOPED(2,"已结束");

    private Integer code;
    private String desc;

    SeckillEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
