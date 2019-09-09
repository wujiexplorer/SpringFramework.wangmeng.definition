package com.lx.benefits.bean.dto.yx;

import java.io.Serializable;

/**
 * @Author: mickey
 * @Date: 2018/8/131600
 * @Description:
 */
public class YXSpec implements Serializable{

    private Long id;

    /**
     * 所属规格对应ID
     */
    private Long skuSpecId;

    /**
     * 规格值
     */
    private String value;

    /**
     * 规格图片
     */
    private String picUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuSpecId() {
        return skuSpecId;
    }

    public void setSkuSpecId(Long skuSpecId) {
        this.skuSpecId = skuSpecId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
