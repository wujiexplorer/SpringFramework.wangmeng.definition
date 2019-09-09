package com.lx.benefits.bean.enums;

/**
 * 是否选项枚举
 */
public enum YNEnum {
    Y(1),
    N(0);
    
    private Integer isDeleted;
    YNEnum(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer val() {
        return this.isDeleted;
    }

    public static String getName(Integer val, YNEnum nullOfDefault) {
        for (YNEnum ynEnum : YNEnum.values()) {
            if (ynEnum.val().equals(val)) {
                return ynEnum.name();
            }
        }
        return null == nullOfDefault ? null : nullOfDefault.name();
    }
    
    public static Integer getValue(String name, YNEnum nullOfDefault) {
        for (YNEnum ynEnum : YNEnum.values()) {
            if (ynEnum.name().equals(name)) {
                return ynEnum.val();
            }
        }
        return null == nullOfDefault ? null : nullOfDefault.val();
    }
}
