package com.lx.benefits.bean.vo.card;

import lombok.Data;

@Data
public class CardAmountStorage {
	private Integer amountId;// 面值ID
	private Integer amount;// 面值
	private Integer num;// 数量

	public CardAmountStorage() {
	}

	public CardAmountStorage(Integer amount, Integer num) {
		this.amount = amount;
		this.num = num;
	}

	public CardAmountStorage(Integer amountId, Integer amount, Integer num) {
		this.amountId = amountId;
		this.amount = amount;
		this.num = num;
	}
}
