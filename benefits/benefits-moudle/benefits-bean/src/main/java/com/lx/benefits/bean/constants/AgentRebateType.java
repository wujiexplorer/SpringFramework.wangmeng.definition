package com.lx.benefits.bean.constants;

/**
 * 返点模式（1充值返点、2销售额返点、3利润额返点）
 * 
 * @author qixian
 *
 */
public enum AgentRebateType {
	RECHARGE(1), // 充值
	SALES(2), // 销售额
	PROFIT(3),// 利润额(新)
	OLDPROFIT(4),// 利润额(旧)
	;

	private Integer type;

	private AgentRebateType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

	public static boolean check(Integer type) {
		if (type == null) {
			return false;
		}
		for (AgentRebateType item : AgentRebateType.values()) {
			if (item.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
}
