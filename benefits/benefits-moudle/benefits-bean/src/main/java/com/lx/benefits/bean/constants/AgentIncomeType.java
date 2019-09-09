package com.lx.benefits.bean.constants;

/**
 * 代理商收入类型（1订单返佣、2异常订单扣除、3充值返点、4提现）
 * 
 * @author qixian
 *
 */
public enum AgentIncomeType {
	SALE_ORDER(1, "销售返佣"), //
	ABNORMAL_ORDER(2, "异常订单扣除"), //
	RECHARGE(3, "充值返点"), // 充值返点
	WITHDRAW(4, "提现结算"), // 提现
	PROFIT_ORDER(5, "利润返佣"),// 利润返佣
	INTRODUCER_AWARD(6,"奖励"),//介绍人奖励
	;

	private Integer type;
	private String description;

	private AgentIncomeType(Integer type, String description) {
		this.type = type;
		this.description = description;
	}

	public Integer getType() {
		return this.type;
	}

	public String getDescription() {
		return this.description;
	}

	public static boolean check(Integer type) {
		if (type == null) {
			return false;
		}
		for (AgentIncomeType item : AgentIncomeType.values()) {
			if (item.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}

	public static String getDescriptionByType(Integer type) {
		for (AgentIncomeType item : AgentIncomeType.values()) {
			if (item.getType().equals(type)) {
				return item.getDescription();
			}
		}
		return "";
	}
}
