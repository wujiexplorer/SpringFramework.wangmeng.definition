package com.lx.benefits.bean.bo.product;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class TempProduct {

    private String skuId;

    private String spuId;

    private Integer supplierId;

    private String saleUnit;

    private Double weight;

    private String productArea;

    private String imagePath;

    private String param;

    private Integer state;

    private String brandName;

    private String goodsName;

    private String introduction;

    private BigDecimal costPrice;

    private BigDecimal goodsPrice;

    private BigDecimal marketPrice;

    private BigDecimal profitRate;

    private BigDecimal goodsDiscount;

    private Long catogoryId3;

    private String goodsSpec;

    private String skuImages;

    private Integer storage;

    private Date updateTime;

    private Long catogoryId2;

    private Long catogoryId;

    private Integer xgCategoryId;

    private String upc;

}