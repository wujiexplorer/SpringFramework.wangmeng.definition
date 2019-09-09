package com.lx.benefits.bean.dto.yxOrder.enums;

/**
 * 严选 包裹状态
 * Created by ldr on 2017/6/8.
 */
public enum YXOrderPackageStatus {

    WAITING_DELIVERY("等待发货"),

    START_DELIVERY("已发货(等待收货)"),

    WAITING_COMMENT("已收货(等待评论)"),

    COMMENTED("已评价"),

    SYS_CANCEL("系统取消"),

    USER_CANCEL("用户取消"),

    KF_CANCEL("客服取消"),

    USERPAYEDCANCEL("用户付款后取消");

    private String desc;

    YXOrderPackageStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }


    public static String getByName(String name){
        if(name == null || name.isEmpty()) return "";
        for(YXOrderPackageStatus status: YXOrderPackageStatus.values()){
            if(status.name().equalsIgnoreCase(name)){
                return  status.getDesc();
            }
        }
        return  name;
    }
}
