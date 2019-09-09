package com.lx.benefits.bean.dto.spec;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SkuSpectValueBean {

	private Long skuId;
	private String specLinkeId;

	// 规格1
	private Integer specId1;
	private String specName1;
	private Integer specValueId1;
	private String specImage1;
	private String specValue1;
	// 规格2
	private Integer specId2;
	private String specName2;
	private Integer specValueId2;
	private String specValue2;
	private String specImage2;

	private BigDecimal goodsMarkprice;// 市场价
	private BigDecimal goodsPrice;// 销售价
	private BigDecimal goodsCostprice;// 成本价
	private Integer goodsStorge;// 商品库存
	private String itemNumber;// 原厂货号

}
