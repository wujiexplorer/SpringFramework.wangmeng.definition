package com.lx.benefits.bean.vo.cart;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @ClassName: CartVO
* @Description:
* @author wind
* @date 2019-2-11
*/
@Data
public class CartVO implements Serializable {

    /**
     * 小计售价
     */
    private BigDecimal totalPrice;
    /**
     * 小计邮费
     */
    private BigDecimal totalFreight;
    /**
     * 商家维度列表
     */
    private List<CartSellerVO> list;
    @Data
    public static class CartSellerVO{
        /**
         *卖家id
         */
        private Long sellerId;
        /**
         * 供应商名称
         */
        private String sellerName;
        /**
         * 小计售价
         */
        private BigDecimal totalPrice;
        /**
         * 小计邮费
         */
        private BigDecimal totalFreight;
        /**
         * 商家商品列表
         */
        private List<CartProductExtVO> skuList;
    }


}
