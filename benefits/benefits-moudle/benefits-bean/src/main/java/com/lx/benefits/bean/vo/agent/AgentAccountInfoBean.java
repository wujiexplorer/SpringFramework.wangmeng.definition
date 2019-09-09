package com.lx.benefits.bean.vo.agent;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.entity.agent.AgentAccountInfo;

public class AgentAccountInfoBean extends AgentAccountInfo {
	private static final long serialVersionUID = 1L;

	private List<String> certifyImages;
	private String parentAgentName;
	private String loginName;
	private Integer enterpriseCount;
	private Integer subAgentCount;
	private String agentLevelName;
	private Integer employeeCount;
	private String addUser;
	private Date addTime;
	private String checkUser;
	private Date checkTime;
	private Integer rebateType;

	public Integer getRebateType() {
		return rebateType;
	}

	public void setRebateType(Integer rebateType) {
		this.rebateType = rebateType;
	}

	public List<String> getCertifyImages() {
		return certifyImages;
	}

	public void setCertifyImages(List<String> certifyImages) {
		this.certifyImages = certifyImages;
	}

	public String getParentAgentName() {
		return parentAgentName;
	}

	public void setParentAgentName(String parentAgentName) {
		this.parentAgentName = parentAgentName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getEnterpriseCount() {
		return enterpriseCount;
	}

	public void setEnterpriseCount(Integer enterpriseCount) {
		this.enterpriseCount = enterpriseCount;
	}

	public Integer getSubAgentCount() {
		return subAgentCount;
	}

	public void setSubAgentCount(Integer subAgentCount) {
		this.subAgentCount = subAgentCount;
	}

	public String getAgentLevelName() {
		return agentLevelName;
	}

	public void setAgentLevelName(String agentLevelName) {
		this.agentLevelName = agentLevelName;
	}

	public Integer getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(Integer employeeCount) {
		this.employeeCount = employeeCount;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
