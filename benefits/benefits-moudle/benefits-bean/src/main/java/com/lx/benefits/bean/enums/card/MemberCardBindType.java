package com.lx.benefits.bean.enums.card;

public enum MemberCardBindType {
	LOGIN(1, "登录"), //
	REGISTER(2, "注册"),//
	;

	public Integer type;
	public String desc;

	MemberCardBindType(Integer type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public static MemberCardBindType getByType(Integer type) {
		if (type != null) {
			MemberCardBindType[] values = MemberCardBindType.values();
			for (MemberCardBindType item : values) {
				if (type.equals(item.type)) {
					return item;
				}
			}
		}
		return null;
	}
}
