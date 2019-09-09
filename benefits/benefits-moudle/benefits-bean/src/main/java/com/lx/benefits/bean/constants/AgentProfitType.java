package com.lx.benefits.bean.constants;

/**
 * 收益方式（1佣金、2返点）
 * 
 * @author qixian
 *
 */
public enum AgentProfitType {
	MONEY(1), // 佣金
	POINTS(2), // 返点
	;

	private Integer type;

	private AgentProfitType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

	public static boolean check(Integer type) {
		if (type == null) {
			return false;
		}
		for (AgentProfitType item : AgentProfitType.values()) {
			if (item.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
}
