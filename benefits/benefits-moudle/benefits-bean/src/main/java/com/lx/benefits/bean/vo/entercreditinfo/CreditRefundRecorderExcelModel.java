package com.lx.benefits.bean.vo.entercreditinfo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.lx.benefits.bean.enums.CreditType;

/**
 * 退款记录表格
 * 
 * @author qixian
 *
 */
public class CreditRefundRecorderExcelModel extends BaseRowModel {
	@ExcelProperty(index = 1, value = "退款编号")
	private String cashNumber;
	@ExcelProperty(index = 1, value = "企业名称")
	private String enterprName;
	private Integer creditType;
	@ExcelProperty(index = 1, value = "退款类型")
	private String creditTypeName;
	@ExcelProperty(index = 1, value = "退款金额")
	private BigDecimal cashAmount;
	@ExcelProperty(index = 1, value = "审批人")
	private String checkUser;
	@ExcelProperty(index = 1, value = "退款时间")
	private Date checkTime;
	@ExcelProperty(index = 1, value = "打款凭证")
	private String payVoucher;

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

	public String getPayVoucher() {
		return payVoucher;
	}

	public void setPayVoucher(String payVoucher) {
		this.payVoucher = payVoucher;
	}
}
