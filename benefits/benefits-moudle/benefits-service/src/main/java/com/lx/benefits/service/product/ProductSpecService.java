package com.lx.benefits.service.product;

import java.util.List;

import com.lx.benefits.bean.entity.product.ProductSpec;

public interface ProductSpecService {

	/**
	 * 获取商品规格名称（基本+供应商）
	 * 
	 * @param supplierId
	 *            供应商ID
	 * @return
	 */
	List<ProductSpec> getBaseProductSpecName(Integer supplierId);

	/**
	 * 根据规格名称获取规格信息
	 * 
	 * @param specName
	 *            规格名称
	 * @param supplierId
	 *            供应商ID
	 * @return
	 */
	ProductSpec getProductSpecByName(String specName, Integer supplierId);

	/**
	 * 添加规格名称
	 * 
	 * @param specName
	 *            规格名称
	 * @param supplierId
	 *            供应商ID
	 * @return
	 */
	ProductSpec addtProductSpecByName(String specName, Integer supplierId);

}
