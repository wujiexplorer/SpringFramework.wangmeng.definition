package com.lx.benefits.bean.vo.enterpr;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprCheckingRecorder;

public class EnterpriseCheckingBean extends EnterprCheckingRecorder {
	private static final long serialVersionUID = 1L;

	private String password;
	private String agentName;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@Override
	public String toString() {
		return "AgentCheckingBean" + JSON.toJSONString(this);
	}
}
