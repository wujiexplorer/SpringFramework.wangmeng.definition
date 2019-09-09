package com.lx.benefits.bean.enums;

/**
 * @author unknow on 2018-12-21 23:40.
 */
public enum SexEnum {
    S(0, "保密"),
    M(1, "男"),
    F(2, "女"),
    L(3, "不涉及"),
    C(4, "儿童");

    private Integer value;
    
    private String desc;
    
    SexEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }
    
    public String getDesc() {
        return desc;
    }

    public static SexEnum getSexEnum(Integer value, SexEnum nullOfDefault) {
        if (null == value) {
            return nullOfDefault;
        }
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getValue().equals(value)) {
                return sexEnum;
            }
        }
        return nullOfDefault;
    }

    public static SexEnum getSexEnum(String name, SexEnum nullOfDefault) {
        if (null == name) {
            return nullOfDefault;
        }
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.name().equals(name)) {
                return sexEnum;
            }
        }
        return nullOfDefault;
    }
    
    public static boolean isVaildSexEnum(Integer value) {
        return null != getSexEnum(value, null);
    }

    public static boolean isVaildSexEnum(String name) {
        return null != getSexEnum(name, null);
    }
}