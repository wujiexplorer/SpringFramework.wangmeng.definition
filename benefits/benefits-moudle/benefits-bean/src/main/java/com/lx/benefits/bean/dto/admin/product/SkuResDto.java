package com.lx.benefits.bean.dto.admin.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品 【sku】商品表 sku
 * 
 * @author ruoyi
 * @date 2019-02-12
 */
@Data
public class SkuResDto {
	
	/** id */
	private Long id;
	/** spu编码 */
	private Long spuCode;
	/** sku编码 */
	private String skuCode;
	/** 商品规格 */
	private String goodsSpec;
	/** 商品库存 */
	private Integer goodsStorge;
	/** 待支付库存 */
	private Integer payStorge;
	/** 商品主图 */
	private String goodsImage;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;
	/** 更新时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updatedTime;
	/** 供应商名称*/
	private String supplierName;
	/** 商品名称 */
	private String goodsName;
	/** 销售价 */
	private BigDecimal goodsPrice;
	/** 毛利率 */
	private BigDecimal goodsRate;
	/** 原厂货号 */
	private String itemNumber;
	/** 季节 */
	private String season;

	private String brandName;

	private String color;

	private BigDecimal length;

	private BigDecimal width;

	private BigDecimal height;

	private String goodsNameEn;
	private String categoryName;
	private String categoryName2;
	private String categoryName3;
	private Integer sex;
	private Integer isCross;
	private Integer goodsType;
	private BigDecimal goodsFreight;
	private BigDecimal goodsCostprice;
	private BigDecimal goodsMarkprice;
	private String topicName;

}
