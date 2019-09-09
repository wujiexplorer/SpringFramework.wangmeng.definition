package com.lx.benefits.bean.vo.enterpr;

import com.alibaba.fastjson.JSON;

public class EnterpriseOverview {

	private Integer totalCount;
	private Integer leaveCount;
	private Integer workingCount;
	private Integer leaveCredit;

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(Integer leaveCount) {
		this.leaveCount = leaveCount;
	}

	public Integer getWorkingCount() {
		return workingCount;
	}

	public void setWorkingCount(Integer workingCount) {
		this.workingCount = workingCount;
	}

	public Integer getLeaveCredit() {
		return leaveCredit;
	}

	public void setLeaveCredit(Integer leaveCredit) {
		this.leaveCredit = leaveCredit;
	}

	@Override
	public String toString() {
		return "EnterpriseOverview" + JSON.toJSONString(this);
	}
}
