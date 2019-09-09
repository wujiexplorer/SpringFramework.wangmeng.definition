package com.lx.benefits.bean.enums;

/**
 * 积分类型{1: 普通积分; 2: 节日礼金; 3: 认可激励积分}
 * Created by lidongri on 2019/1/6.
 */
public enum  CreditType {

    normal(1),

    festive(2),

    encourage(3);

    private int code;


    CreditType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
