package com.lx.benefits.bean.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by softw on 2019/3/6.
 */
public class ProductUtil {

    /**
     *
     * @param isEffect 是否一直生效 0：否  1：是
     * @param startTime 改价生效时间
     * @param endTime 改价结束时间
     * @param goodsPrice 原销售价
     * @return
     */
    public static BigDecimal gjGoodsPrice(Integer isEffect,BigDecimal gjDiscount, Date startTime ,
                                          Date endTime,BigDecimal goodsPrice) {
        if (isEffect.equals(1)) {
            return getGoodsPrice(gjDiscount,goodsPrice);
        } else {
            if (null != startTime && null != endTime) {
                if (DateUtil.isInRange(startTime,endTime)){
                    return getGoodsPrice(gjDiscount,goodsPrice);
                }
            }
        }
        return goodsPrice;
    }

    private static BigDecimal getGoodsPrice(BigDecimal gjDiscount,BigDecimal goodsPrice) {
        // 改价折扣
        BigDecimal discountSku =new BigDecimal(gjDiscount.toString());
        // 折扣率
        BigDecimal discount =discountSku.divide(new BigDecimal(100));
        // 销售价 * （折扣率）
        return goodsPrice.multiply(discount);
    }
}
