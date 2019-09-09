package com.lx.benefits.bean.dto.spec;

import java.math.BigDecimal;
import java.util.List;

import com.lx.benefits.bean.entity.voucher.Voucher;
import lombok.Data;

@Data
public class ProductBean {

	private Integer goodsType;
	private Integer isCross;
	private Long placeOriginId;
	private String placeOrigin;// 产地
	private Integer sex;
	private String countrySize;// 国别
	private Long spuCode;
	private String goodsName;
	private String goodsNameEn;
	private String goodsImage;// 商品图片
	private List<String> imgUrlList;// 商品轮播图
	private String season;// 季节
	private String sexName;// 性别
	private String introduction;// 商品详情
	private String introductionEn;// 商品详情
	private Long brandId;// 品牌名称
	private String brandName;// 品牌名称
	private Long supplierId;// 供应商ID
	private String supplierName;// 供应商名称
	private BigDecimal goodsFreight;// 运费
	private Integer goodsState;// 商品状态
	private List<SkuBean> skuList;// sku列表
	private List<ProductSpecValueBean> skuSpecList;// 规格信息
	
	private Long categoryId;
	private Long categoryId2;
	private Long categoryId3;
	private String categoryName;
	private String categoryName2;
	private String categoryName3;
	private List<SkuSpectValueBean> skuSpectValueList;// sku+sku规格信息（运营接口专用）
	private BigDecimal goodsPrice;
	//关联Spu的优惠卷
	private List<Voucher> vouchers;
}
