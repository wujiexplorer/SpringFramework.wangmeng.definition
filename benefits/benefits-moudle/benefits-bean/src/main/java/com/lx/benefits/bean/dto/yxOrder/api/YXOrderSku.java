package com.lx.benefits.bean.dto.yxOrder.api;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ldr on 2017/6/7.
 */
@Data
public class YXOrderSku implements Serializable {

    private static final long serialVersionUID = -4964785988582264530L;
    /**
     * SKU ID
     */
    private String skuId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品数量
     */
    private int saleCount;

    /**
     * 商品单价
     */
    private Double originPrice;

    /**
     * 金额小计
     */
    private Double subtotalAmount;

    /**
     * 优惠卷金额
     */
    private Double couponTotalAmount;

    /**
     * 活动优惠金额
     */
    private Double activityTotalAmount;

}
