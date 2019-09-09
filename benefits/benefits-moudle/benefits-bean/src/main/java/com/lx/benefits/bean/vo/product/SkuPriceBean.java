package com.lx.benefits.bean.vo.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SkuPriceBean {

	private BigDecimal goodsPrice;
	private boolean isCustomer;

	public SkuPriceBean(BigDecimal goodsPrice, boolean isCustomer) {
		this.goodsPrice = goodsPrice;
		this.isCustomer = isCustomer;
	}

}
