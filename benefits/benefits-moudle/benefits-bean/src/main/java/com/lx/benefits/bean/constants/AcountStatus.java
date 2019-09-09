package com.lx.benefits.bean.constants;

/**
 * 代理商钱包状态(1正常、2冻结)
 * 
 * @author qixian
 *
 */
public enum AcountStatus {
	NORMAL(1), // 正常
	FREEZE(2), // 冻结
	;

	private Integer status;

	private AcountStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public static boolean check(Integer status) {
		if (status == null) {
			return false;
		}
		for (AcountStatus item : AcountStatus.values()) {
			if (item.getStatus().equals(status)) {
				return true;
			}
		}
		return false;
	}
}
