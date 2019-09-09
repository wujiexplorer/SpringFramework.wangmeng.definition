package com.lx.benefits.bean.vo.enterpr;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprBindRecorder;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;

public class EnterprUserInfoBean extends EnterprUserInfo {

	private static final long serialVersionUID = 1L;

	private String subEnterprName;
	private String agentName;
	private String checkUser;
	private Date checkTime;
	private String addUser;
	private Date addTime;
	private List<EnterprBindRecorder> bindRecorders;
	private BigDecimal costTotal;
	private Integer orderCount;

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public List<EnterprBindRecorder> getBindRecorders() {
		return bindRecorders;
	}

	public void setBindRecorders(List<EnterprBindRecorder> bindRecorders) {
		this.bindRecorders = bindRecorders;
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

	public String getSubEnterprName() {
		return subEnterprName;
	}

	public void setSubEnterprName(String subEnterprName) {
		this.subEnterprName = subEnterprName;
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

	public BigDecimal getCostTotal() {
		return costTotal;
	}

	public void setCostTotal(BigDecimal costTotal) {
		this.costTotal = costTotal;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	@Override
	public String toString() {
		return "EnterprUserInfoBean" + JSON.toJSONString(this);
	}
}
