package com.lx.benefits.bean.bo.product;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class EsProductBean {

	private Long spuCode;
	private String goodsName;
	private Long brandId;
	private String brandName;
	private Long categoryId;
	private String categoryName;
	private Long categoryId2;
	private String categoryName2;
	private Long categoryId3;
	private String categoryName3;
	private Long supplierId;
	private String supplierName;
	private String keywords;
	private String goodsSpecname;
	private Double lowPrice;
	private Date statedTime;
}
