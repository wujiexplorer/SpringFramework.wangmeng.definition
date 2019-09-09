package com.lx.benefits.bean.to.order;


import com.lx.benefits.bean.base.dto.BaseTO;

public class PointRechargeConfigTO  implements BaseTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4840452692840303519L;

    private Long id;

    /**
     * 售价 [数据类型DOUBLE]
     */
    private String price;

    /**
     * 充值福利币数量 [数据类型BIGINT]
     */
    private String point;
    
    /**
     * 折扣力度
     */
    private String discount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
    
}
