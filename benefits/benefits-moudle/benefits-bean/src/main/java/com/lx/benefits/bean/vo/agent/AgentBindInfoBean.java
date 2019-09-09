package com.lx.benefits.bean.vo.agent;

import javax.validation.constraints.NotNull;

public class AgentBindInfoBean {

	@NotNull(message = "父级代理不能为空")
	private Integer parentAgentId;

	private Integer password;
	@NotNull(message = "备注信息不能为空")
	private String remark;

	public Integer getParentAgentId() {
		return parentAgentId;
	}

	public void setParentAgentId(Integer parentAgentId) {
		this.parentAgentId = parentAgentId;
	}

	public Integer getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
