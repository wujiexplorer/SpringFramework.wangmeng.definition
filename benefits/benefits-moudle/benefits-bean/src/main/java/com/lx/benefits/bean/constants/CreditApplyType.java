package com.lx.benefits.bean.constants;

/**
 * 积分申请类型
 * 
 * @author qixian
 *
 */
public enum CreditApplyType {
	RECHARGE(1), // 充值
	REFUND(2), // 退款
	;

	private Integer type;

	private CreditApplyType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

	public static boolean check(Integer type) {
		if (type == null) {
			return false;
		}
		for (CreditApplyType item : CreditApplyType.values()) {
			if (item.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
}
