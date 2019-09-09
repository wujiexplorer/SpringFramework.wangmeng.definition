package com.lx.benefits.bean.enums.jdapi;

/**
 * Created by ldr on 2017/3/16.
 */
public enum JDMessageType {

    ORDER_SPLIT(1,"拆单"),

    ITEM_PRICE_CHANGE(2, "价格变更"),

    ITEM_STATUS_CHANGE(4, "商品上下架变更消息"),

    ORDER_FINISHED(5,"代表该订单已妥投"),

    ITEM_POOL_CHANGE(6,"添加、删除商品池内商品"),

    ORDER_CANCEL(10,"订单取消（不区分取消原因）"),

    ORDER_PACKAGED(12 ,"代表配送单生成（打包完成后推送，仅提供给买卖宝类型客户）"),

    ORDER_CHANGE_NEW(13,"换新订单生成（换新单下单后推送，仅提供给买卖宝类型客户）"),

    PAY_FAILED(14,"支付失败消息"),

    ORDER_CANCEL_NO_PAY(15,"7天未支付取消消息/未确认取消（cancelType, 1: 7天未支付取消消息; 2: 未确认取消）"),

    ITEM_DETAIL_CHANGE(16,"商品介绍及规格参数变更消息"),

    ITEM_GIFT_CHANGE(17,"赠品促销变更消息"),

    ORDER_NEW(25,"新订单消息"),

    AREA_CHANGE(50,"京东地址变更消息推送");


    private int code;

    private String desc;

    JDMessageType(int code, String desc) {
        this.code = code;

        this.desc = desc;
    }

    public static JDMessageType getByCode(Integer code){
        if (code == null) {
            return null;
        }
        for( JDMessageType type: JDMessageType.values()) {
            if(type.getCode() == code){ return type;}
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
