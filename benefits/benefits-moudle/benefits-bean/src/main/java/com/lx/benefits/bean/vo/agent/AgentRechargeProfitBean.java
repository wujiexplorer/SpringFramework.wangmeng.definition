package com.lx.benefits.bean.vo.agent;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;

public class AgentRechargeProfitBean {

	private Long enterprId;
	private String enterprName;
	private Integer creditType;
	private BigDecimal cashAmount;
	private BigDecimal incomeAmount;
	private Integer checkStatus;
	private Date createTime;

	public Long getEnterprId() {
		return enterprId;
	}

	public void setEnterprId(Long enterprId) {
		this.enterprId = enterprId;
	}

	public String getEnterprName() {
		return enterprName;
	}

	public void setEnterprName(String enterprName) {
		this.enterprName = enterprName;
	}

	public Integer getCreditType() {
		return creditType;
	}

	public void setCreditType(Integer creditType) {
		this.creditType = creditType;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AgentRechargeProfitBean" + JSON.toJSONString(this);
	}
}
