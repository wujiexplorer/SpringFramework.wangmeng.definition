package com.lx.benefits.bean.vo.entercreditinfo;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.entity.entercreditinfo.CreditChekingRecorder;

public class CreditChekingRecorderBean extends CreditChekingRecorder {

	private static final long serialVersionUID = 1L;
	private String agentName;
	private String enterprName;

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getEnterprName() {
		return enterprName;
	}

	public void setEnterprName(String enterprName) {
		this.enterprName = enterprName;
	}

	@Override
	public String toString() {
		return "CreditChekingRecorderBean" + JSON.toJSONString(this);
	}

}
