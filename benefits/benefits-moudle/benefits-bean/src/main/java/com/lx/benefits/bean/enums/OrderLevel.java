package com.lx.benefits.bean.enums;

public enum OrderLevel {
	PAY_LEVEL(1, "支付级订单"), //
	SELLER_LEVEL(2, "商家级订单"), //
	PRODUCT_LEVEL(3, "商品级订单"),//
	;
	public Integer code;
	public String desc;

	private OrderLevel(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}
