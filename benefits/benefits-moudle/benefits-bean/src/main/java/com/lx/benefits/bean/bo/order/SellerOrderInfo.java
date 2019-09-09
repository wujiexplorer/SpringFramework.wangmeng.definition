package com.lx.benefits.bean.bo.order;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class SellerOrderInfo {
	private String orderSellerNumber;
	private BigDecimal totalGoodsPoints;
	private BigDecimal totalShipExpense;
	private List<ProductOrderInfo> productOrders;
}
