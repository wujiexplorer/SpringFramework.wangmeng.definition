package com.lx.benefits.bean.dto.admin.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SkuQueryParam {
	private Long supplierId;
	private Long spuCode;
	private String skuCode;
	private Long skuId;
	private String itemNumber;
	private String goodsName;
	private String goodsNameEn;
	private Integer status;
	private Integer storageStatus;
	private Integer saleRateStatus;
	private BigDecimal goodsRate;
	private Integer goodsType;
	private Long brandId;
	private Long categoryId;
	private Long categoryId2;
	private Long categoryId3;
	private Integer goodsRateSort;//0降序 1升序
	private Integer goodsPriceSort;//0降序 1升序
	private Integer goodsStorgeSort;//0降序 1升序
}
