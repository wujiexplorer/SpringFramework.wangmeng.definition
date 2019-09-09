package com.lx.benefits.bean.enums;

public enum DeleteStatus {

	NO((byte) 0, "未删除"), //
	YES((byte) 1, "已删除"), //
	;

	public Byte status;
	public String desc;

	DeleteStatus(Byte status, String desc) {
		this.status = status;
		this.desc = desc;
	}

}
