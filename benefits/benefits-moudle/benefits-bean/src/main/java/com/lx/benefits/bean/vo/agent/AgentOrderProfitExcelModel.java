package com.lx.benefits.bean.vo.agent;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.enums.OrderEnum;

public class AgentOrderProfitExcelModel extends BaseRowModel {
	@ExcelProperty(index = 0, value = "订单号")
	private String orderNumber;
	@ExcelProperty(index = 1, value = "所属公司")
	private String enterprName;
	@ExcelProperty(index = 2, value = "员工姓名")
	private String employeeName;
	@ExcelProperty(index = 3, value = "员工编号")
	private String employeeNo;
	@ExcelProperty(index = 4, value = "商品名称")
	private String productName;
	@ExcelProperty(index = 5, value = "数量")
	private Integer quantity;
	@ExcelProperty(index = 6, value = "下单时间", format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private Integer orderStatus;
	@ExcelProperty(index = 7, value = "订单状态")
	private String orderStatusName;
	@ExcelProperty(index = 8, value = "支付金额")
	private BigDecimal saleAmount;
	@ExcelProperty(index = 9, value = "预计佣金")
	private BigDecimal incomeAmount;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getEnterprName() {
		return enterprName;
	}

	public void setEnterprName(String enterprName) {
		this.enterprName = enterprName;
	}

	public BigDecimal getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
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
		if (orderStatus != null) {
			this.setOrderStatusName(OrderEnum.STATUS.getDescByCode(orderStatus));
		}
	}

	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	@Override
	public String toString() {
		return "AgentOrderProfitBean" + JSON.toJSONString(this);
	}
}
