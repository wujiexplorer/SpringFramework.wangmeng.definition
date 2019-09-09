package com.lx.benefits.bean.util;

import java.math.BigDecimal;

/**
 * User: fan
 * Date: 2019/03/11
 * Time: 15:37
 */
public class CalRateUtil {

    public static final BigDecimal calRate(BigDecimal jdPrice, BigDecimal salePrice, BigDecimal protocolPrice) {
        jdPrice = jdPrice == null ? new BigDecimal(0) : jdPrice;
        protocolPrice = protocolPrice == null ? new BigDecimal(0) : protocolPrice;
        BigDecimal price = protocolPrice;
        if (jdPrice.compareTo(new BigDecimal(0)) <= 0) {
            return new BigDecimal(0);
        }
        return BigDecimalUtil.divide(BigDecimalUtil.subtract(jdPrice, price), jdPrice);
    }

    /**
     * 
     * @param jdPrice 销售价
     * @param protocolPrice 协议价
     * @return
     */
    public static final Double calRate(Double jdPrice, Double protocolPrice) {
        jdPrice = jdPrice == null ? 0D : jdPrice;
        protocolPrice = protocolPrice == null ? 0D : protocolPrice;
        Double price = protocolPrice;
        if (jdPrice == 0 || jdPrice < 0) {
            return 0D;
        }
        return BigDecimalUtil.divide(jdPrice - price, jdPrice).doubleValue();
    }

}
