package com.lx.benefits.bean.enums;

public enum CardKeyStatusEnum {

	STORED(1, "库存中"), 
	DELIVERED(2, "已发货"), 
	COMPLETED(3, "已完成"), 
	EXPIRED(4, "已失效"),
	;
	public Integer status;
	public String desc;
	private CardKeyStatusEnum(Integer status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	
	public Integer getStatus() {
		return status;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDescByCode(Integer status){
        for(CardKeyStatusEnum cardKeyStatusEnum : CardKeyStatusEnum.values()){
            if(cardKeyStatusEnum.getStatus().equals(status)){
                return cardKeyStatusEnum.getDesc();
            }
        }
        return "";
    }
}
