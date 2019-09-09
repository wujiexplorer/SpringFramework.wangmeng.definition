package com.lx.benefits.bean.vo.agent;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;

public class AgentUniqueInfoBean {

	@NotNull(message = "类型不能为空!")
	private Integer agentType;
	@NotEmpty(message = "信息不能为空!")
	private String info;

	public Integer getAgentType() {
		return agentType;
	}

	public void setAgentType(Integer agentType) {
		this.agentType = agentType;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "AgentUniqueInfoBean" + JSON.toJSONString(this);
	}

}