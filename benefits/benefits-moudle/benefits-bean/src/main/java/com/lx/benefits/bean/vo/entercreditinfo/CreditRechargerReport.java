package com.lx.benefits.bean.vo.entercreditinfo;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;

public class CreditRechargerReport {

	private BigDecimal totalRecharge;// 累计充值金额
	private BigDecimal totalRebate;// 累计充值返点
	private BigDecimal totalRefund;// 累计退款金额

	public BigDecimal getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(BigDecimal totalRecharge) {
		this.totalRecharge = totalRecharge;
	}

	public BigDecimal getTotalRebate() {
		return totalRebate;
	}

	public void setTotalRebate(BigDecimal totalRebate) {
		this.totalRebate = totalRebate;
	}

	public BigDecimal getTotalRefund() {
		return totalRefund;
	}

	public void setTotalRefund(BigDecimal totalRefund) {
		this.totalRefund = totalRefund;
	}

	@Override
	public String toString() {
		return "CreditRechargerReport" + JSON.toJSONString(this);
	}
}
