package com.lx.benefits.bean.vo.agent;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;

public class EnterpriseBindInfoBean {

	@NotNull(message = "代理商信息不能为空")
	private Integer agentId;

	private Integer password;
	@NotNull(message = "备注信息不能为空")
	private String remark;

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
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

	@Override
	public String toString() {
		return "EnterpriseBindInfoBean" + JSON.toJSONString(this);
	}
}
