package com.lx.benefits.bean.enums;

public enum CreditSetEnum {
	CLOSE(0,"关闭"),
	OPEN(1,"开启");
	private Integer value;
    private String desc;
	private CreditSetEnum(Integer value, String desc) {
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
