package com.lx.benefits.bean.vo.entercreditinfo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.lx.benefits.bean.enums.CreditType;

public class CreditRechargeRecorderExcelModel extends BaseRowModel {
	@ExcelProperty(index = 0, value = "充值编号")
	private String cashNumber;
	@ExcelProperty(index = 1, value = "企业名称")
	private String enterprName;
	private Integer creditType;
	@ExcelProperty(index = 2, value = "充值类型")
	private String creditTypeName;
	@ExcelProperty(index = 3, value = "充值金额")
	private BigDecimal cashAmount;
	@ExcelProperty(index = 4, value = "审批人")
	private String checkUser;
	@ExcelProperty(index = 5, value = "充值时间", format = "yyyy-MM-dd HH:mm:ss")
	private Date checkTime;
	@ExcelProperty(index = 6, value = "所属代理商")
	private String agentName;
	private BigDecimal profitProportion;
	@ExcelProperty(index = 7, value = "代理商返点率")
	private String profitProportionString;
	@ExcelProperty(index = 8, value = "返点金额")
	private BigDecimal rebateAmount;
	@ExcelProperty(index = 9, value = "实收金额")
	private BigDecimal actualReceiveAmount;
	@ExcelProperty(index = 10, value = "收款凭证")
	private String payVoucher;
	@ExcelProperty(index = 11, value = "到账日期", format = "yyyy-MM-dd")
	private Date payTime;
	@ExcelProperty(index = 12, value = "到账金额")
	private BigDecimal payAmount;

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
		if (creditType != null) {
			if (CreditType.encourage.getCode() == creditType) {
				this.setCreditTypeName("认可激励积分");
			} else if (CreditType.normal.getCode() == creditType) {
				this.setCreditTypeName("普通积分");
			} else if (CreditType.festive.getCode() == creditType) {
				this.setCreditTypeName("节日积分");
			}
		}

	}

	public String getCreditTypeName() {
		return creditTypeName;
	}

	public void setCreditTypeName(String creditTypeName) {
		this.creditTypeName = creditTypeName;
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

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public BigDecimal getProfitProportion() {
		return profitProportion;
	}

	public void setProfitProportion(BigDecimal profitProportion) {
		this.profitProportion = profitProportion;
		if (profitProportion != null) {
			this.setProfitProportionString(profitProportion.toPlainString() + "%");
		}
	}

	public String getProfitProportionString() {
		return profitProportionString;
	}

	public void setProfitProportionString(String profitProportionString) {
		this.profitProportionString = profitProportionString;
	}

	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	public BigDecimal getActualReceiveAmount() {
		return actualReceiveAmount;
	}

	public void setActualReceiveAmount(BigDecimal actualReceiveAmount) {
		this.actualReceiveAmount = actualReceiveAmount;
	}

	public String getPayVoucher() {
		return payVoucher;
	}

	public void setPayVoucher(String payVoucher) {
		this.payVoucher = payVoucher;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

}
