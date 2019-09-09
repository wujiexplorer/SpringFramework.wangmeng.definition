package com.lx.benefits.service.order;

import java.util.List;

import com.lx.benefits.bean.param.order.OrderBuyParam;
import com.lx.benefits.bean.vo.order.JDAddress;
import com.lx.benefits.bean.vo.order.ProductValidateResult;

/**
 * User:wangmeng Date:2019/4/23 Time:15:53 Verision:2.x Description:TODO
 **/
public interface OrderInfoValidateService {

	public ProductValidateResult validateOrderInfo(OrderBuyParam orderBuyParam, Long userReceiveAddrId) throws Exception;

	/**
	 * 获取区域受限的商品
	 * 
	 * @param skuIds
	 * @param jdAddress
	 * @return
	 */
	public List<String> getAreaRestrictSkus(List<String> skuIds, JDAddress jdAddress);
}
