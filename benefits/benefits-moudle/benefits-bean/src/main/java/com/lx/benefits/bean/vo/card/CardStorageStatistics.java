package com.lx.benefits.bean.vo.card;

import java.util.List;

import lombok.Data;

@Data
public class CardStorageStatistics {
	private Integer totalCount;
	private Long totalAmount;
	private Long usedAmount;
	private List<CardAmountStorage> cardInfo;
}
