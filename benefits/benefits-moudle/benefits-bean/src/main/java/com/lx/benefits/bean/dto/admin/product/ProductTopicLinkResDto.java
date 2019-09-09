package com.lx.benefits.bean.dto.admin.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品基本 【spu】表 product
 * 
 * @author ruoyi
 * @date 2019-02-12
 */
@Data
public class ProductTopicLinkResDto {

	/** spu编码 */
	private Long spuCode;
	/** 品牌id */
	private Long brandId;
	/** 品牌名称 */
	private String brandName;
	/** 商品类型 1虚拟商品，0实物商品，默认为0 */
	private Integer goodsType;
	/** 是否跨境 1:跨境0非跨境 */
	private Integer isCross;
	/** 颜色 */
	private String colour;
	/** 库存 */
	private Integer stock;
	/** 供应商id */
	private Long supplierId;
	private String supplierName;
	/** 商品名称 */
	private String goodsName;
	/** 英文名称 */
	private String goodsNameEn;
	/** 商品描述 */
	private String goodsBody;
	/** 商品主图 */
	private String goodsImage;
	private List<String> goodsImageList;
	/** 产地 */
	private String placeOrigin;
	/** 成本价 */
	private BigDecimal goodsCostprice;
	/** 市场价 */
	private BigDecimal goodsMarkprice;
	/** 销售价 */
	private BigDecimal goodsPrice;

	private BigDecimal goodsDiscount;

	private Long skuId;

	private Integer sortNum;

	private Integer goodsState;

	private String skuImage;

}
