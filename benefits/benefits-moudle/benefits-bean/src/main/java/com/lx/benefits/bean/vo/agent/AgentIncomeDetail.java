package com.lx.benefits.bean.vo.agent;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;

public class AgentIncomeDetail {

	// 公共参数
	private Integer type;
	private String voucherNumber;
	private BigDecimal cashAmount;

	// 企业充值相关
	private String enterprName;
	private Integer creditType;
	// 提现相关
	private Date applyTime;
	private Integer checkStatus;
	private String payVoucher;
	private Date checkTime;
	private String checkRemark;
	// 订单收益相关
	private String employeeName;
	private String employeeNo;
	private String productName;
	private Integer quantity;
	private Date createTime;
	private Integer orderStatus;

	public AgentIncomeDetail() {
	}

	public AgentIncomeDetail(Integer type, String voucherNumber, BigDecimal cashAmount) {
		this.type = type;
		this.voucherNumber = voucherNumber;
		this.cashAmount = cashAmount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
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

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getPayVoucher() {
		return payVoucher;
	}

	public void setPayVoucher(String payVoucher) {
		this.payVoucher = payVoucher;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "AgentIncomeDetail" + JSON.toJSONString(this);
	}

}
