package com.lx.benefits.bean.enums;

public class OrderEnum {

    public enum STATUS{
        INIT(0, "订单待支付"),
        CANCEL(1, "订单已取消"),
        PAID(2, "订单已支付"),
        SHIPPED(3, "订单已发货"),
        RECEIVED(4, "订单已收货"),
        CLOSED(5, "订单已关闭"),
        COMPLETED(6, "订单已完成"),
        ;
        private int code;
        private String desc;

        STATUS(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescByCode(int code){
            for(STATUS status : STATUS.values()){
                if(status.getCode() == code){
                    return status.getDesc();
                }
            }
            return "";
        }
    }

    public enum REVERSE_STATUS{
        INIT(0, "未进入退货/款流程",""),
        APPLY(1, "进入退货/款流程","退款中"),
        END(2, "退货/款流程完成","退款成功"),
        END_WHITOUT_SUCC(3, "退货/款流程完结，未发生过成功的售后行为",""),
        END_SUCC(4, "退货/款流程完结，发生过成功的售后行为","退款成功"),
        ;
        private int code;
        private String desc;
        private String frontDesc;

        REVERSE_STATUS(int code, String desc,String frontDesc){
            this.code = code;
            this.desc = desc;
            this.frontDesc=frontDesc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public String getFrontDesc() {
            return frontDesc;
        }

        public static String getDescByCode(int code){
            for(REVERSE_STATUS status : REVERSE_STATUS.values()){
                if(status.getCode() == code){
                    return status.getDesc();
                }
            }
            return "";
        }

        public static String getFrontDescByCode(int code){
            for(REVERSE_STATUS status : REVERSE_STATUS.values()){
                if(status.getCode() == code){
                    return status.getFrontDesc();
                }
            }
            return "";
        }
    }
}
