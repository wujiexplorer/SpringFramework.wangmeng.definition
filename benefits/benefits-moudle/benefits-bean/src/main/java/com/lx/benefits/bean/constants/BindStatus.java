package com.lx.benefits.bean.constants;

/**
 * 代理商钱包状态(1正常、2冻结)
 * 
 * @author qixian
 *
 */
public enum BindStatus {
	BIND(1), // 绑定
	UNBIND(0), // 解绑
	;

	private Integer status;

	private BindStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public static boolean check(Integer status) {
		if (status == null) {
			return false;
		}
		for (BindStatus item : BindStatus.values()) {
			if (item.getStatus().equals(status)) {
				return true;
			}
		}
		return false;
	}
}
