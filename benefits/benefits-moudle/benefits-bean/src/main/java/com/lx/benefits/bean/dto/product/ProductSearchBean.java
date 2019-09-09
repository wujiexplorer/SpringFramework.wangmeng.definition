package com.lx.benefits.bean.dto.product;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ProductSearchBean {

	private Long enterprId;
	private Integer categoryId;
	private Integer categoryId2;
	private Integer categoryId3;
	private String key;
	private Integer sort;
	private Integer brandId;
	private Integer countryId;
	private Integer topicId;
	private boolean isHot = false;

	private List<Long> hotGoodsIdList;

	private List<Long> excludeSupplierIds;
	private List<Long> excludeCategoryIds;
	private List<Long> excludeCategoryId2s;
	private List<Long> excludeCategoryId3s;
	private List<Long> excludeBrandIds;
	private List<Long> excludeTopicIds;

	private List<Long> spuCodes;
	
	// 价格筛选范围
	private BigDecimal startPrice;
	private BigDecimal endPrice;

	// 最低价配置
	private BigDecimal lowestPrice;
}
