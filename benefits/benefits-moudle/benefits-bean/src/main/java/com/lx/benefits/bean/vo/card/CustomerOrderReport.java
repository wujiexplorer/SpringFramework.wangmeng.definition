package com.lx.benefits.bean.vo.card;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CustomerOrderReport {
	private Long customerId;// 客户ID
	private String customerName;// 客户名称
	private Long totalAmount;// 已制作金额
	private Integer totalCount;// 会员卡总数
	private BigDecimal sumPayable;// 应收账款
	private BigDecimal paidAmount;// 回款金额
	private BigDecimal unpaidAmount;// 未回款金额
	private BigDecimal usedAmount;// 用户已激活金额
}
