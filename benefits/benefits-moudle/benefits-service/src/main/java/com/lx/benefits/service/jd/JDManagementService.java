package com.lx.benefits.service.jd;

import java.util.List;

import com.lx.benefits.bean.bo.product.TempProduct;

public interface JDManagementService {

	/**
	 * 获取京东商品信息
	 * 
	 * @param skuId
	 *            京东商品skuId
	 * @param withSimilar
	 *            是否获取同类的其他商品(相同的spu)
	 * @return
	 */
	List<TempProduct> getSkuDetails(String skuId, boolean withSimilar);

	/**
	 * 京东商品导入
	 * 
	 * @param skuCode
	 *            京东商品skuId
	 * @param withSimilar
	 *            是否导入同类的其他商品(相同的spu)
	 * @return 导入的sku个数
	 */
	int goodsImport(String skuCode, boolean withSimilar);

}
