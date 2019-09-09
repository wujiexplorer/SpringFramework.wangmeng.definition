package com.lx.benefits.bean.constants;

/**
 * 审核状态(1未审核、2审核通过、3审核不通过)
 * 
 * @author qixian
 *
 */
public enum CheckStatus {
	INIT(1), // 未审核
	PASSED(2), // 审核通过
	UNPASSED(3),// 审核不通过
	;

	private Integer status;

	private CheckStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
