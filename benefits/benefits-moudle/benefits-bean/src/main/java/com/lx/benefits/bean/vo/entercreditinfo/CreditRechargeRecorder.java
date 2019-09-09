package com.lx.benefits.bean.vo.entercreditinfo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CreditRechargeRecorder {
	private String cashNumber;
	private String enterprName;
	private Integer creditType;
	private BigDecimal cashAmount;
	private String checkUser;
	private Date checkTime;
	private String payVoucher;
	private String agentName;
	private BigDecimal rebateAmount;
	private BigDecimal profitProportion;
	private BigDecimal actualReceiveAmount;
	private BigDecimal payAmount;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date payTime;

	public String getCashNumber() {
		return cashNumber;
	}

	public void setCashNumber(String cashNumber) {
		this.cashNumber = cashNumber;
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

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getPayVoucher() {
		return payVoucher;
	}

	public void setPayVoucher(String payVoucher) {
		this.payVoucher = payVoucher;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	public BigDecimal getProfitProportion() {
		return profitProportion;
	}

	public void setProfitProportion(BigDecimal profitProportion) {
		this.profitProportion = profitProportion;
	}

	public BigDecimal getActualReceiveAmount() {
		return actualReceiveAmount;
	}

	public void setActualReceiveAmount(BigDecimal actualReceiveAmount) {
		this.actualReceiveAmount = actualReceiveAmount;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Override
	public String toString() {
		return "CreditRechargeRecorder" + JSON.toJSONString(this);
	}
}
