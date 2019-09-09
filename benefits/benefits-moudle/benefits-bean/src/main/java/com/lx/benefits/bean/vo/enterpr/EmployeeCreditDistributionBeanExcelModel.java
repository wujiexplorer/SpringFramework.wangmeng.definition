package com.lx.benefits.bean.vo.enterpr;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeCreditDistributionBeanExcelModel extends BaseRowModel{
	@ExcelProperty(index = 0, value = "操作时间")
	private String handleTime;
	@ExcelProperty(index = 1, value = "操作类型")
	private String handleType;
	private Integer creditType;
	@ExcelProperty(index = 2, value = "积分类型")
	private String creditTypeDesc;
	@ExcelProperty(index = 3, value = "员工姓名")
	private String employeeName;
	@ExcelProperty(index = 4, value = "员工号")
	private String employeeNo;
	@ExcelProperty(index = 5, value = "积分数量")
	private BigDecimal creditCount;
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
