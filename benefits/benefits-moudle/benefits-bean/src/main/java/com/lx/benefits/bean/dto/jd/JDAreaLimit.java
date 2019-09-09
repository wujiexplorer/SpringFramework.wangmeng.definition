package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/3.
 */
public class JDAreaLimit implements Serializable {

    private static final long serialVersionUID = -69001789693358017L;

    private String skuId;

    private Boolean isAreaRestrict;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Boolean getIsAreaRestrict() {
        return isAreaRestrict;
    }

    public void setIsAreaRestrict(Boolean areaRestrict) {
        isAreaRestrict = areaRestrict;
    }
}
