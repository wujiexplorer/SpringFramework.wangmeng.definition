package com.lx.benefits.bean.bo.order;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductOrderInfo {
	private String orderProductNumber;
	private Long spuId;
	private Long skuId;
	private String goodsName;
	private Integer goodsType;
	private String goodsSpec;
	private BigDecimal goodsPrice;
	private Integer quantity;
	private BigDecimal shipExpense;
	private Integer categoryId;
	private String categoryName;
	private Integer categoryId2;
	private String categoryName2;
	private Integer categoryId3;
	private String categoryName3;
	private Integer status;
}