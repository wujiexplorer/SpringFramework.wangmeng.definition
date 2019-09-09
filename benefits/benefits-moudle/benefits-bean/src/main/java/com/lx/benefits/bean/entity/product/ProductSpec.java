package com.lx.benefits.bean.entity.product;

import java.io.Serializable;

public class ProductSpec implements Serializable {
    private Integer specId;

    private String specName;

    private Integer supplierId;

    private static final long serialVersionUID = 1L;

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}