package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/8.
 */
public class JDItemPriceVO implements Serializable {

    private static final long serialVersionUID = -2634731972878610165L;

    private String skuId;

    private Double jdPrice;

    private Double price;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Double getJdPrice() {
        return jdPrice;
    }

    public void setJdPrice(Double jdPrice) {
        this.jdPrice = jdPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
