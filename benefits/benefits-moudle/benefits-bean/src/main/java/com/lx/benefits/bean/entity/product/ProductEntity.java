package com.lx.benefits.bean.entity.product;

import com.lx.benefits.bean.dto.spec.ProductSpecValueBean;
import com.lx.benefits.bean.entity.operation.TopicEntity;
import com.lx.benefits.bean.entity.voucher.Voucher;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品基本 【spu】表 product
 *
 * @author ruoyi
 * @date 2019-02-12
 */
@Data
public class ProductEntity{

	/** spu编码 */
	private Long spuCode;

	private String skuCode;

	/** 品牌id */
	private Long brandId;
	/** 品牌名称 */
	private String brandName;
	/** 一级分类id */
	private Long categoryId;
	/** 一级分类名称 */
	private String categoryName;
	/** 二级分类id */
	private Long categoryId2;
	/** 二级分类名称 */
	private String categoryName2;
	/** 三级分类id */
	private Long categoryId3;
	/** 三级分类名称 */
	private String categoryName3;
	/** 商品类型 1虚拟商品，0实物商品，默认为0 */
	private Integer goodsType;
	/** 是否跨境 1:跨境0非跨境 */
	private Integer isCross;
	/** 商品属性 */
	private String goodsAttr;
	/** 颜色 */
	private String color;
	/** 供应商id */
	private Long supplierId;
	/** 供应商名称 */
	private String supplierName;
	/** 商品名称 */
	private String goodsName;
	/** 英文名称 */
	private String goodsNameEn;
	/** 商品描述 */
	private String goodsBody;
	/** 商品主图 */
	private String goodsImage;
	/** 轮播图 */
	private String goodsImages;
	/** 规格序列化 */
	private String goodsSpecname;
	/** 产地 */
	private Long placeOriginId;
	private String placeOrigin;

	/** 折扣 */
	private BigDecimal goodsDiscount;

	/** sku 折扣*/
	private BigDecimal skuDiscount;
	/** 原厂货号 */
	private String itemNumber;
	/** 季节 */
	private Long seasonId;
	private String season;
	/** 商品状态 商品状态 0下架，1正常，2审核中 ，3审核通过，10违规（禁售） */
	private Integer goodsState;
	/** 运费 费 0为免运费 */
	private BigDecimal goodsFreight;
	/** 创建人 */
	private String createdUser;
	/** 创建时间 */
	private Date createdTime;
	/**上架时间*/
	private Date statedTime;
	/** 更新人 */
	private String updatedUser;
	/** 更新时间 */
	private Date updatedTime;
	/** 性别 */
	private Integer sex;
	/** 国别*/
	private String countrySize;
	/** 库存 */
	private Integer stock;
	/** 改价生效开始时间*/
	private Date gjValidStarttime;
	/** 改价生效结束时间*/
	private Date gjValidEndtime;
	/**改价折扣*/
	private BigDecimal gjDiscount;

	/**是否一直生效 0：否  1：是*/
	private Integer isEffect;
	/**专题名称*/
	private String topicName;
	private Long topicId;

	private Long skuId;

	private Integer sortNum;

    /** 商品列表页展示价格 */
	private BigDecimal goodsPrice;

	private BigDecimal goodsMarkprice;

	private BigDecimal goodsCostprice;
	/**
	 * 介绍
	 */
	private String introduction;

	private String introductionEn;

	private List<SkuEntity> skuList;

	private String saleUnit;

	// 空查所有 1 有库存 0 无存在
	private Integer isStock;

	private Integer goodsStorge;

	// 专题
	private List<TopicEntity> topicList;

	private String skuImage;

	private List<Long> spuCodeList;

	private String voucherIds;

	private List<Voucher> vouchers;

	private BigDecimal minGoodsPrice;

	private List<ProductSpecValueBean> skuSpecList;// 规格信息

	private Date sortDate;
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
		if (StringUtils.isNotBlank(introduction)) {
			this.goodsBody = introduction;
		}

	}

	public void setGoodsBody(String goodsBody) {
		this.goodsBody = goodsBody;
		if (StringUtils.isNotBlank(goodsBody)) {
			this.introduction = goodsBody;
		}
	}


	public void setGoodsDiscount(BigDecimal goodsDiscount) {
	}

	public void setSkuDiscount(BigDecimal skuDiscount) {
		this.skuDiscount = skuDiscount;
		this.goodsDiscount = skuDiscount;
	}
}
