package com.lx.benefits.bean.dto.spec;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.product.SkuSpecValue;

import lombok.Data;

@Data
public class ProductRequestBean {

	private Long supplierId;
	@NotNull(message = "商品分类不能为空")
	private Integer categoryId3;// 三级分类id
	@NotNull(message = "商品类型不能为空")
	private Integer goodsType;// 1虚拟商品，0实物商品，默认为0
	private Integer isCross;// 是否跨境 1:跨境0非跨境
	@NotEmpty(message = "商品名称不能为空")
	private String goodsName;// 商品名称
	private String goodsNameEn;// 英文名称
	@NotNull(message = "品牌不能为空")
	private Long brandId;// 品牌id
	@NotEmpty(message = "商品图片不能为空")
	private List<String> goodsImageList;// 商品图片
	private Long placeOriginId;
	private String placeOrigin;// 产地
	private String season;// 季节
	private Integer sex;// 性别，1男、2女、3不涉及、4儿童
	private String countrySize;// 国别
	@NotNull(message = "商品详情不能为空")
	private String introduction;// 中文详情
	private String introductionEn;// 英文详情
	@NotNull(message = "运费不能为空")
	private BigDecimal goodsFreight;// 运费，默认0
	@NotEmpty(message = "规格信息不能为空")
	private List<SpecBean> specList;
	@NotEmpty(message = "sku信息不能为空")
	private List<SkuEntity> skuList;

	@Data
	public static class SpecBean {
		private String specName;
		private Integer specId;
		private List<SkuSpecValue> specValues;
	}
}
