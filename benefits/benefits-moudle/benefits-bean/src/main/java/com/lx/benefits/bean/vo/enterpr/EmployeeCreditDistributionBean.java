package com.lx.benefits.bean.vo.enterpr;

import java.math.BigDecimal;
import java.math.BigInteger;


public class EmployeeCreditDistributionBean {
	
	private BigInteger parentOptId;
	private BigInteger optId;
	private String handleTime;
	private String handleType;
	private Integer creditType;
	private String creditTypeDesc;
	private String employeeName;
	private String employeeNo;
	private BigDecimal creditCount;
	private Integer optType;
	
	public BigInteger getParentOptId() {
		return parentOptId;
	}
	public void setParentOptId(BigInteger parentOptId) {
		this.parentOptId = parentOptId;
	}
	public BigInteger getOptId() {
		return optId;
	}
	public void setOptId(BigInteger optId) {
		this.optId = optId;
	}
	public Integer getOptType() {
		return optType;
	}
	public void setOptType(Integer optType) {
		this.optType = optType;
	}
	public String getCreditTypeDesc() {
		return creditTypeDesc;
	}
	public void setCreditTypeDesc(String creditTypeDesc) {
		this.creditTypeDesc = creditTypeDesc;
	}
	public void setCreditType(Integer creditType) {
		this.creditType = creditType;
	}
	
	public String getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}
	public String getHandleType() {
		return handleType;
	}
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}
	public Integer getCreditType() {
		return creditType;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public BigDecimal getCreditCount() {
		return creditCount;
	}
	public void setCreditCount(BigDecimal creditCount) {
		this.creditCount = creditCount;
	}
	
}
