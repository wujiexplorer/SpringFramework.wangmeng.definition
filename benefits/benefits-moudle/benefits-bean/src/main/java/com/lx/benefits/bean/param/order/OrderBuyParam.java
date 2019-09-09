package com.lx.benefits.bean.param.order;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 订单参数
 */
@Data
public class OrderBuyParam {

	private List<SkuParam> jdSkuList;

	private List<SkuParam> yxSkuList;

	/**
	 * 收货地址ID
	 */
	@NotNull(message = "收货地址不能为空")
	private Long userReceiveAddrId;

}
