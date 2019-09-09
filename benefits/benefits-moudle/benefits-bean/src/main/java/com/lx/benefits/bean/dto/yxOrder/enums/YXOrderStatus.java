package com.lx.benefits.bean.dto.yxOrder.enums;

/**
 * 严选 订单状态
 * Created by ldr on 2017/6/8.
 */
public enum YXOrderStatus {

    WAITING_PAY("未付款"),

    PAYED("已付款"),

    SYS_CANCEL("系统取消"),

    USER_CANCEL("用户取消"),

    KF_CANCEL("客服取消"),

    CANCELLING("取消待审核"),

    USERPAYEDCANCEL("用户付款后取消");

    private String desc;

    YXOrderStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }


    public static boolean isCanceled(String status){
        if(status == null || status.isEmpty()) return false;

        if(status.equals(SYS_CANCEL.name()) || status.equals(USER_CANCEL.name())|| status.equals(KF_CANCEL.name()) || status.equals(USERPAYEDCANCEL.name())){
            return true;
        }
        return false;

    }



}
