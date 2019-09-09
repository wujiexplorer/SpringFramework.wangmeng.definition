package com.lx.benefits.bean.vo.order;

import lombok.Data;

@Data
public class ProductMsg {
	/** 商品skuId */
	private String skuId;
	/** 库存状态描述 */
	private String stockStateDesc;

}
