package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/8.
 */
public class JDPrice implements Serializable {

	private static final long serialVersionUID = 3331322329998796979L;

	private String skuId;

	private Double jdPrice;// 京东销售价格

	private Double price;// 协议价格

	private Double marketPrice;// 市场价格

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Double getJdPrice() {
		return jdPrice;
	}

	public void setJdPrice(Double jdPrice) {
		this.jdPrice = jdPrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

}
