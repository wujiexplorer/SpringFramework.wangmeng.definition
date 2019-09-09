package com.lx.benefits.bean.vo.agent;

import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.JSON;

public class EnterpriseUniqueInfoBean {

	@NotEmpty(message = "信息不能为空!")
	private String info;

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