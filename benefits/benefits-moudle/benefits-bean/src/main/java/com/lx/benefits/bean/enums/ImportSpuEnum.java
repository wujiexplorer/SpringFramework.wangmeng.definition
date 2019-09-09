package com.lx.benefits.bean.enums;

/**
 * @author unknow on 2018-12-21 23:40.
 */
public enum ImportSpuEnum {
    IS_CROSS_FALSE(0, "非跨境商品"),
    IS_CROSS_TRUE(1, "跨境商品"),
    GOODS_DUMMY(0,"虚拟商品"),
    GOODS_SPU(1,"实物商品");

    private Integer value;

    private String desc;

    ImportSpuEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }
    
    public String getDesc() {
        return desc;
    }

    public static ImportSpuEnum getImportEnum(Integer value, ImportSpuEnum nullOfDefault) {
        if (null == value) {
            return nullOfDefault;
        }
        for (ImportSpuEnum sexEnum : ImportSpuEnum.values()) {
            if (sexEnum.getValue().equals(value)) {
                return sexEnum;
            }
        }
        return nullOfDefault;
    }

    public static ImportSpuEnum getImportEnum(String name, ImportSpuEnum nullOfDefault) {
        if (null == name) {
            return nullOfDefault;
        }
        for (ImportSpuEnum sexEnum : ImportSpuEnum.values()) {
            if (sexEnum.name().equals(name)) {
                return sexEnum;
            }
        }
        return nullOfDefault;
    }
    
    public static boolean isVaildSexEnum(Integer value) {
        return null != getImportEnum(value, null);
    }

    public static boolean isVaildSexEnum(String name) {
        return null != getImportEnum(name, null);
    }
}