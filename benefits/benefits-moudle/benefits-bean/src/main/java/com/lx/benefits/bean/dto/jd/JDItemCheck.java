package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/14.
 */
public class JDItemCheck implements Serializable {

    private static final long serialVersionUID = 122587673713984174L;

    private Long skuId;

    private String name;

    private Integer saleState;//是否可售，1：是，0：否

    private Integer isCanVAT;//是否可开增票，1：支持，0：不支持

    private Integer is7ToReturn;//是否支持7天无理由退货，1：是，0：否

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSaleState() {
        return saleState;
    }

    public void setSaleState(Integer saleState) {
        this.saleState = saleState;
    }

    public Integer getIsCanVAT() {
        return isCanVAT;
    }

    public void setIsCanVAT(Integer isCanVAT) {
        this.isCanVAT = isCanVAT;
    }

    public Integer getIs7ToReturn() {
        return is7ToReturn;
    }

    public void setIs7ToReturn(Integer is7ToReturn) {
        this.is7ToReturn = is7ToReturn;
    }
}
