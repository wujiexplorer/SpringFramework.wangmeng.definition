package com.lx.benefits.bean.enums.card;

public enum MemberCardStatus {

	INIT(0, "初始"), //
	STORED(1, "入库"), //
	INVALID(2, "作废"), //
	ACTIVE(3, "已激活"), //
	USED(4, "已使用"),//
	;

	public Integer status;
	public String desc;

	MemberCardStatus(Integer status, String desc) {
		this.status = status;
		this.desc = desc;
	}

}
