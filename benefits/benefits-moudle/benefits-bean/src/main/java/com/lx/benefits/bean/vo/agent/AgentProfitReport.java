package com.lx.benefits.bean.vo.agent;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;

public class AgentProfitReport {

	private Integer agentId;
	private Integer enterpriseCount; // 企业总数
	private Integer employeeCount; // 企业员工总数
	private Integer rebateType;
	private BigDecimal totalRecharge;// 企业充值总额
	private BigDecimal totalIncome; // 代理商返点总额
	private Integer orderCount; // 企业员工下单总数
	private Integer rechargeCount; // 企业充值次数
	private Integer type;

	private BigDecimal totalOrderIncome;// 订单利润总额
	private BigDecimal totalOrderSaleAmount;// 订单营业总额
	private BigDecimal totalOrderCostAmount;// 订单成本总额
	private BigDecimal rechargeRebate;// 充值返点
	private BigDecimal orderIncomeRebate;// 利润额返点
	private BigDecimal orderSaleAmountRebate;// 营业额返点
	private BigDecimal accountBalance;// 代理商帐户余额

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getEnterpriseCount() {
		return enterpriseCount;
	}

	public void setEnterpriseCount(Integer enterpriseCount) {
		this.enterpriseCount = enterpriseCount;
	}

	public Integer getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(Integer employeeCount) {
		this.employeeCount = employeeCount;
	}

	public Integer getRebateType() {
		return rebateType;
	}

	public void setRebateType(Integer rebateType) {
		this.rebateType = rebateType;
	}

	public BigDecimal getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(BigDecimal totalRecharge) {
		this.totalRecharge = totalRecharge;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getRechargeCount() {
		return rechargeCount;
	}

	public void setRechargeCount(Integer rechargeCount) {
		this.rechargeCount = rechargeCount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getTotalOrderIncome() {
		return totalOrderIncome;
	}

	public void setTotalOrderIncome(BigDecimal totalOrderIncome) {
		this.totalOrderIncome = totalOrderIncome;
	}

	public BigDecimal getTotalOrderSaleAmount() {
		return totalOrderSaleAmount;
	}

	public void setTotalOrderSaleAmount(BigDecimal totalOrderSaleAmount) {
		this.totalOrderSaleAmount = totalOrderSaleAmount;
	}

	public BigDecimal getRechargeRebate() {
		return rechargeRebate;
	}

	public void setRechargeRebate(BigDecimal rechargeRebate) {
		this.rechargeRebate = rechargeRebate;
	}

	public BigDecimal getOrderIncomeRebate() {
		return orderIncomeRebate;
	}

	public void setOrderIncomeRebate(BigDecimal orderIncomeRebate) {
		this.orderIncomeRebate = orderIncomeRebate;
	}

	public BigDecimal getOrderSaleAmountRebate() {
		return orderSaleAmountRebate;
	}

	public void setOrderSaleAmountRebate(BigDecimal orderSaleAmountRebate) {
		this.orderSaleAmountRebate = orderSaleAmountRebate;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public BigDecimal getTotalOrderCostAmount() {
		return totalOrderCostAmount;
	}

	public void setTotalOrderCostAmount(BigDecimal totalOrderCostAmount) {
		this.totalOrderCostAmount = totalOrderCostAmount;
	}

	@Override
	public String toString() {
		return "AgentProfitReport" + JSON.toJSONString(this);
	}
}
