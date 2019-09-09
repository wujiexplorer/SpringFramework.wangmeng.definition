package com.lx.benefits.bean.enums;

/**
 * User:wangmeng
 * Date:2019/8/9
 * Time:13:56
 * Version:2.x
 * Description:TODO
 **/
public enum VoucherEnum {

    FULLRANGE(0,"全场商品"),
    CATEGORY(1,"类目"),
    BRAND(2,"品牌"),
    TOPIC(3,"专题"),
    PRODUCT(4,"商品");

    private Integer code;
    private String desc;

    VoucherEnum(Integer code,String desc){
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
