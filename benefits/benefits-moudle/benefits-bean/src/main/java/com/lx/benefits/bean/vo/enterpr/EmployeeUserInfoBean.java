package com.lx.benefits.bean.vo.enterpr;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;

public class EmployeeUserInfoBean {
	private String employeeNo;
	private String employeeName;
	private String loginName;
	private Date createTime;
	private Integer leaveStatus;
	private List<EmployeeCreditInfo> creditinfo;

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(Integer leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public List<EmployeeCreditInfo> getCreditinfo() {
		return creditinfo;
	}

	public void setCreditinfo(List<EmployeeCreditInfo> creditinfo) {
		this.creditinfo = creditinfo;
	}

	@Override
	public String toString() {
		return "EmployeeUserInfoBean" + JSON.toJSONString(this);
	}

}
