package com.lx.benefits.bean.dto.order;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class QueryFreightVO {
	private Long provinceId;
    private Long cityId;
    private Long districtId;
    private Long streetId;
    private BigDecimal price;//商品总价
    private Integer num;
    private Long supplierId;
    private String sku;
}
