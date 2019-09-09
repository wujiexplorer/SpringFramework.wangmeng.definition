package com.lx.benefits.bean.enums.card;

public enum EmployeeCardStatus {

	INIT(0, "未激活"), //
	ACTIVE(1, "已激活"), //
	;

	public Integer status;
	public String desc;

	EmployeeCardStatus(Integer status, String desc) {
		this.status = status;
		this.desc = desc;
	}

}
