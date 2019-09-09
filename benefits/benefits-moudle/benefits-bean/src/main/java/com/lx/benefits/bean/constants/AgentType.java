package com.lx.benefits.bean.constants;

/**
 * 代理类型(1公司、2个人)
 * 
 * @author qixian
 *
 */
public enum AgentType {
	ENTERPRISE(1), // 公司
	PERSONAL(2), // 个人
	;

	private Integer type;

	private AgentType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

	public static boolean check(Integer type) {
		if (type == null) {
			return false;
		}
		for (AgentType item : AgentType.values()) {
			if (item.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
}
