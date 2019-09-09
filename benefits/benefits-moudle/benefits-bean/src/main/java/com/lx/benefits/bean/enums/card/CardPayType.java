package com.lx.benefits.bean.enums.card;

/**
 * 会员卡订单付款方式
 *
 */
public enum CardPayType {

	CURRENT_CASH(0), // 现款现结
	PERIODIC_PAYMENT(1), // 周期结款
	;

	public Integer type;

	private CardPayType(Integer type) {
		this.type = type;
	}

	public static boolean check(Integer type) {
		if (type == null) {
			return false;
		}
		for (CardPayType item : CardPayType.values()) {
			if (item.type.equals(type)) {
				return true;
			}
		}
		return false;
	}

}
