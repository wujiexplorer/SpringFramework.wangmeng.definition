package com.lx.benefits.bean.dto.order;


import com.lx.benefits.bean.base.dto.BaseVO;

public class PointRechargeOrderVO implements BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6214059870075265430L;
	
	/**福利币充值单号*/
	private Long orderCode;

	public Long getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}
	
}
