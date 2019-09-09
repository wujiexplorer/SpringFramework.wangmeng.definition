package com.lx.benefits.bean.param.cart;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;


/**
 * 购物车加购参数
 */
@Data
public class CartSaveParam {
    /**
     * 来源 0：加购，1：更新购物车数量
     */
    private int source = 0;
    /**
     *商品id
     */
    @NotNull(message = "商品不能为空")
    private Long skuId;
    /**
     *购物车商品数量
     */
    @NotNull(message = "请选择商品数量")
    @Range(min = -1, max = 1000, message = "购物车加购数量1~1000件")
    private Integer count;
    /**
     * 活动ID
     */
    private long activityId;
    /**
     *是否选中，0：未选中，1：选中
     */
    private int checked;
}
