package com.lx.benefits.bean.bo.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProductOrderDetails {
	private String orderPayNumber;
	private BigDecimal totalPoints;
	private Date createTime;
	private List<SellerOrderInfo> sellerOrders;
	private ReceiveAddressInfo receiveAddressInfo;
}
