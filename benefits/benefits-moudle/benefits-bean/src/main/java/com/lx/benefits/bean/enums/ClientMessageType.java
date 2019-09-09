package com.lx.benefits.bean.enums;

public enum ClientMessageType {

	ORDER_SUCCESS(1, "下单成功"), //
	ORDER_FAILED(2, "下单失败"),//
	;

	public int type;
	public String desc;

	ClientMessageType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
}
