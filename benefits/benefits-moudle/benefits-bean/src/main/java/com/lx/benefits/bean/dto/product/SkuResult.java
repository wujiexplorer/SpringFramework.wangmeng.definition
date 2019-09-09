package com.lx.benefits.bean.dto.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SkuResult {
	private String Skus ;
	private BigDecimal marketPrice;//京东市场价
	private BigDecimal jDPrices ;//京东价格
	private BigDecimal prices ;//协议价格
	private BigDecimal goodsRate;//毛利率
	private BigDecimal goodsDiscount;//折扣
}
