package com.lx.benefits.bean.vo.agent;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.entity.agent.AgentAccountCheckingRecorder;

public class AgentCheckingBean extends AgentAccountCheckingRecorder {
	private static final long serialVersionUID = 1L;

	private String password;
	private String parentAgentName;
	private List<String> certifyImages;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getParentAgentName() {
		return parentAgentName;
	}

	public void setParentAgentName(String parentAgentName) {
		this.parentAgentName = parentAgentName;
	}

	public List<String> getCertifyImages() {
		return certifyImages;
	}

	public void setCertifyImages(List<String> certifyImages) {
		this.certifyImages = certifyImages;
	}

	@Override
	public String toString() {
		return "AgentCheckingBean" + JSON.toJSONString(this);
	}
}
