package com.lx.benefits.bean.enums;

public enum GoodsStateEnum {
	SOLDOUT(0, "下架"),
	RACKING(1, "上架"),
	INREVIEW(2, "审核中"),
	APPROVE(3, "审核通过"),
	;
	
	private Integer value;
    private String desc;
    
	private GoodsStateEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
    
    
}
