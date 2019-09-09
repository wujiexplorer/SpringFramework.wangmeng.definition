package com.lx.benefits.bean.dto.enterprise;

import java.math.BigDecimal;

public class EnterprCreditInfoBean {

	private Integer creditType;
	private BigDecimal validCredit;
	private BigDecimal lockCredit;
	private BigDecimal historyTotalCredit;

	public EnterprCreditInfoBean(Integer creditType, BigDecimal validCredit, BigDecimal lockCredit, BigDecimal historyTotalCredit) {
		this.creditType = creditType;
		this.validCredit = validCredit;
		this.lockCredit = lockCredit;
		this.historyTotalCredit = historyTotalCredit;
	}

	public EnterprCreditInfoBean(Integer creditType) {
		this.creditType = creditType;
		this.validCredit = BigDecimal.valueOf(0);
		this.lockCredit = BigDecimal.valueOf(0);
		this.historyTotalCredit = BigDecimal.valueOf(0);
	}

	public EnterprCreditInfoBean() {
	}

	public Integer getCreditType() {
		return creditType;
	}

	public void setCreditType(Integer creditType) {
		this.creditType = creditType;
	}

	public BigDecimal getValidCredit() {
		return validCredit;
	}

	public void setValidCredit(BigDecimal validCredit) {
		this.validCredit = validCredit;
	}

	public BigDecimal getLockCredit() {
		return lockCredit;
	}

	public void setLockCredit(BigDecimal lockCredit) {
		this.lockCredit = lockCredit;
	}

	public BigDecimal getHistoryTotalCredit() {
		return historyTotalCredit;
	}

	public void setHistoryTotalCredit(BigDecimal historyTotalCredit) {
		this.historyTotalCredit = historyTotalCredit;
	}

}
