package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/14.
 */
public class JDSSkuNums implements Serializable {

    private static final long serialVersionUID = 3314236421287185101L;

    private String skuId;

    private Integer num;

    public JDSSkuNums() {
    }

    public JDSSkuNums(String skuId, Integer num) {
        this.skuId = skuId;
        this.num = num;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
