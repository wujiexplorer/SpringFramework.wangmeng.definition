package com.lx.benefits.bean.vo.enterpr;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprBindRecorder;

public class EnterprBindRecorderBean extends EnterprBindRecorder {

	private static final long serialVersionUID = 3033284161703936630L;
	private Date firstBindTime;
	private String enterprName;

	public Date getFirstBindTime() {
		return firstBindTime;
	}

	public void setFirstBindTime(Date firstBindTime) {
		this.firstBindTime = firstBindTime;
	}

	public String getEnterprName() {
		return enterprName;
	}

	public void setEnterprName(String enterprName) {
		this.enterprName = enterprName;
	}

	@Override
	public String toString() {
		return "EnterprBindRecorderBean" + JSON.toJSONString(this);
	}
}
