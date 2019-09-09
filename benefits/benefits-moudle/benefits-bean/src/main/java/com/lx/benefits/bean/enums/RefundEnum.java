package com.lx.benefits.bean.enums;

public class RefundEnum {

    public enum STATUS{

        APPLY(1, "申请中","退款中"),
        PENDING_RETURN(2, "待退货","退款中"),
        PENDING_REFUND(3, "待退款","退款中"),
        REFUND_REJECTION(4, "退款/货拒绝","退款拒绝"),
        REFUND_SUCC(5, "退款/货成功","退款成功"),
        CANCEL(6, "退款取消","退款取消"),
        ;
        private int code;
        private String desc;
        private String frontDesc;

        STATUS(int code, String desc,String frontDesc){
            this.code = code;
            this.desc = desc;
            this.frontDesc = frontDesc;
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

        public static String getFrontDescByCode(int code){
            for(STATUS status : STATUS.values()){
                if(status.getCode() == code){
                    return status.getFrontDesc();
                }
            }
            return "";
        }
        public static String getDescByCode(int code){
            for(STATUS status : STATUS.values()){
                if(status.getCode() == code){
                    return status.getDesc();
                }
            }
            return "";
        }

        public static STATUS getEnumByCode(int code){
            for(STATUS status : STATUS.values()){
                if(status.getCode() == code){
                    return status;
                }
            }
            return null;
        }
    }
}
