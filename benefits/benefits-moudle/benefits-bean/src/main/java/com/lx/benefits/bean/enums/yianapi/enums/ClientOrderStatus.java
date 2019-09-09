package com.lx.benefits.bean.enums.yianapi.enums;

/**
 * Created by lidongri on 2018/12/2.
 */
public enum ClientOrderStatus {

    	//99：未支付	1：已支付	 2：处理中	4：已发货	 5：已完成	6：已退货	 7：已撤销 	8：已部分发货

    PAYING(99,"未支付"),

    PAYED(1,"已支付"),

    PROCESSING(2,"处理中"),

    DISPATCH(4,"已发货"),

    FINISHED(5,"已完成"),

    REFUNDED(6,"已退货"),

    CANCELED(7,"已撤销"),

    PART_DISPATCH(8,"已部分发货");


    private int code;
    private String desc;

    ClientOrderStatus(int code,String desc) {
        this.desc = desc;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
