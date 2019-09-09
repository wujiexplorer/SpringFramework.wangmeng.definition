package com.lx.benefits.bean.enums.card;

public enum MemberCardBatchStatus {
	INIT(0, "未制卡"), //
	MAKING(1, "制卡中"), //
	STROED(2, "已入库"), //
	DELIVERED(3, "已发卡")//
	;

	public Integer status;
	public String desc;

	MemberCardBatchStatus(Integer status, String desc) {
		this.status = status;
		this.desc = desc;
	}

}
