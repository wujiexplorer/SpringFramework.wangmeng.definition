package com.lx.benefits.bean.vo.card;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CustomerInfo {
	private Long customerId;
	@NotEmpty(message = "客户名称不能为空")
	private String customerName;
	@NotEmpty(message = "联系电话不能为空")
	private String mobile;

	public CustomerInfo() {
	}

	public CustomerInfo(Long customerId, String customerName) {
		this.customerId = customerId;
		this.customerName = customerName;
	}
}
