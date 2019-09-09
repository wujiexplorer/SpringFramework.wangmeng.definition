package com.lx.benefits.bean.vo.card;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CardStasticReport {
	private Integer status;// 会员卡状态
	private Long totalAmount;// 制作总额（累计制作多少面额总数）
	private Long usedAmount;// 已激活总额（通过使用激活充入账户总数）
	private Long unUsedAmount;// 未激活总额（发卡交付后未被用户激活（不包含未发卡数量））
	private BigDecimal totalPayAmount;// 已回款金额（所有客户回款总额）
}
