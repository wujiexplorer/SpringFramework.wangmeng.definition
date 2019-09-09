package com.lx.benefits.bean.bo.order;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;

public class OrderOverview {
	private Long enterprId;
	private Integer orderCount;
	private BigDecimal priceTotal;
	private BigDecimal pointTotal;

	public Long getEnterprId() {
		return enterprId;
	}

	public void setEnterprId(Long enterprId) {
		this.enterprId = enterprId;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public BigDecimal getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(BigDecimal priceTotal) {
		this.priceTotal = priceTotal;
	}

	public BigDecimal getPointTotal() {
		return pointTotal;
	}

	public void setPointTotal(BigDecimal pointTotal) {
		this.pointTotal = pointTotal;
	}

	@Override
	public String toString() {
		return "OrderOverview" + JSON.toJSONString(this);
	}
}
