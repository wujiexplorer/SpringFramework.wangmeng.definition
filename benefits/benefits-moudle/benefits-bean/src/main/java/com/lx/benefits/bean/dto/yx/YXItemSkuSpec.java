package com.lx.benefits.bean.dto.yx;

import java.io.Serializable;

/**
 * @Author: mickey
 * @Date: 2018/8/131549
 * @Description:
 */
public class YXItemSkuSpec implements Serializable{

    private Long id;

    private YXSpecGroup skuSpec;

    private YXSpec skuSpecValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public YXSpecGroup getSkuSpec() {
        return skuSpec;
    }

    public void setSkuSpec(YXSpecGroup skuSpec) {
        this.skuSpec = skuSpec;
    }

    public YXSpec getSkuSpecValue() {
        return skuSpecValue;
    }

    public void setSkuSpecValue(YXSpec skuSpecValue) {
        this.skuSpecValue = skuSpecValue;
    }
}
