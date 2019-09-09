package com.lx.benefits.bean.vo.agent;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.lx.benefits.bean.constants.AgentIncomeType;

public class AgentIncomeRecorderExcelModel extends BaseRowModel {
	@ExcelProperty(index = 0, value = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private Integer type;
	@ExcelProperty(index = 1, value = "项目")
	private String typeName;
	@ExcelProperty(index = 2, value = "凭证号")
	private String voucherNumber;
	@ExcelProperty(index = 3, value = "收支")
	private String incomeExpenditure;
	@ExcelProperty(index = 4, value = "金额")
	private BigDecimal cashAmount;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
		if (type != null) {
			this.setTypeName(AgentIncomeType.getDescriptionByType(type));
		}
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public String getIncomeExpenditure() {
		return incomeExpenditure;
	}

	public void setIncomeExpenditure(String incomeExpenditure) {
		this.incomeExpenditure = incomeExpenditure;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		if (cashAmount != null) {
			if (cashAmount.doubleValue() >= 0) {
				this.setIncomeExpenditure("收入");
				this.cashAmount = cashAmount;
			} else {
				this.setIncomeExpenditure("支出");
				this.cashAmount = cashAmount.multiply(BigDecimal.valueOf(-1));
			}
		}
	}

}
