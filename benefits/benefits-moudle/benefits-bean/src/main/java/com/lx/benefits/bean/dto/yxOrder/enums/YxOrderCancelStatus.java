package com.lx.benefits.bean.dto.yxOrder.enums;

/**
 * Created by ldr on 2017/10/10.
 */
public enum YxOrderCancelStatus {

    NO("不允许取消"), //不允许取消

    YES("允许取消"),//允许取消

    AUDITING("待审核") ;//待审核

    private String desc;

    YxOrderCancelStatus(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
