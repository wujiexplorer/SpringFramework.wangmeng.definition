package com.lx.benefits.bean.constants;

/**
 * 代理商账号状态(1正常、2冻结)
 * 
 * @author qixian
 *
 */
public enum AgentWalletStatus {
	NORMAL(1), // 正常
	FREEZE(2), // 冻结
	;

	private Integer status;

	private AgentWalletStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public static boolean check(Integer status) {
		if (status == null) {
			return false;
		}
		for (AgentWalletStatus item : AgentWalletStatus.values()) {
			if (item.getStatus().equals(status)) {
				return true;
			}
		}
		return false;
	}
}
