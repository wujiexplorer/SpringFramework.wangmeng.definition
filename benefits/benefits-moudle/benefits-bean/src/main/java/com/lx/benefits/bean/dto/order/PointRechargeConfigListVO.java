package com.lx.benefits.bean.dto.order;


import com.lx.benefits.bean.base.dto.BaseVO;
import com.lx.benefits.bean.to.order.PointRechargeConfigTO;

import java.util.List;

public class PointRechargeConfigListVO implements BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3928167538223798761L;
	
	/**用户手机号*/
	private String mobile;
	/**福利币余额*/
	private String userPoint;
	
	private List<PointRechargeConfigTO> list;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(String userPoint) {
		this.userPoint = userPoint;
	}

	public List<PointRechargeConfigTO> getList() {
		return list;
	}

	public void setList(List<PointRechargeConfigTO> list) {
		this.list = list;
	}
	
}
