package com.lx.benefits.bean.entity.product;

import java.io.Serializable;
import java.util.Date;

public class SkuStorageRecorder implements Serializable {
    private Integer id;

    private Long skuId;

    private Integer goodsStorge;

    private String operator;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getGoodsStorge() {
        return goodsStorge;
    }

    public void setGoodsStorge(Integer goodsStorge) {
        this.goodsStorge = goodsStorge;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}