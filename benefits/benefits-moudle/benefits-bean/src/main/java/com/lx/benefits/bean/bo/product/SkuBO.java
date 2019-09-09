package com.lx.benefits.bean.bo.product;

import lombok.Data;

/**
 * 商品 【sku】商品表 sku
 * 
 * @author ruoyi
 * @date 2019-02-12
 */
@Data
public class SkuBO {
	private Long productId;
	/** id */
	private Long id;
	/** spu编码 */
	private Long spuCode;
	/** sku编码 */
	private String skuCode;
	/** 商品规格 */
	private String goodsSpec;
	/** 市场价 */
	private Long goodsMarketprice;
	/** 商品库存 */
	private Integer goodsStorge;
	/** 待支付库存 */
	private Integer payStorge;
	/** 商品主图 */
	private String goodsImage;
	/** 购买数量 */
	private Integer quantity;
	/*******************************/
	/** 品牌名称 */
	private String brandName;
	/** 商品类型 1虚拟商品，0实物商品，默认为0 */
	private Integer goodsType;
	/** 是否跨境 1:跨境0非跨境 */
	private Integer isCross;
	/** 商品名称 */
	private String goodsName;
	/** 英文名称 */
	private String goodsNameEn;
	/** 商品状态 商品状态 0下架，1正常，10违规（禁售） */
	private Integer goodsState;
	/** 运费 费 0为免运费 */
	private Long goodsFreight;
	/** 商品成本价(购买价)，单位分 */
	private Long costPrice;
	/** 销售价 */
	private Long goodsPrice;
	/** 供应商id */
	private Long sellerId;
	/** 供应商名称 */
	private String sellerName;
	//秒杀ID
	private Long seckillId;

}
