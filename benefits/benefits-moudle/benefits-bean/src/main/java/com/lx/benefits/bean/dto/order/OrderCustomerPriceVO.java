package com.lx.benefits.bean.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCustomerPriceVO {

    /**
     * 企业id
     */
    private Long entrprId;

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
}
