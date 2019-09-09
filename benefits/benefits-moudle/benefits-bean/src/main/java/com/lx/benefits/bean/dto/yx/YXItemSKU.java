package com.lx.benefits.bean.dto.yx;

import java.io.Serializable;
import java.util.List;

/**
 * 商品中Sku信息
 * Created by ldr on 2017/5/27.
 */
public class YXItemSKU implements Serializable {

    private static final long serialVersionUID = -4238295586776480375L;

    /**
     * Sku的标识
     */
    private Long id;

    /**
     * 严选基准售价
     * 成本价
     * @deprecated
     */
    private Double yanxuanPrice;

    /**
     * 渠道售价
     */
    private Double channelPrice;


    /**
     * sku规格文字信息
     */
    private String displayString;

    /**
     * sku规格图片的URL
     */
    private String picUrl;

    /**
     * sku下的规格及规格值列表
     */
    private List<YXItemSkuSpec> itemSkuSpecValueList;


    /**
     * sku详情信息
     */
    private YXItemSkuDetail skuDetailTO;


    /**
     * 新品标记 0：不是；1：是
     */
    private Integer newFlag;

    /**
     * 渠道选品对应分级信息
     */
    private String classification;


    public List<YXItemSkuSpec> getItemSkuSpecValueList() {
        return itemSkuSpecValueList;
    }

    public void setItemSkuSpecValueList(List<YXItemSkuSpec> itemSkuSpecValueList) {
        this.itemSkuSpecValueList = itemSkuSpecValueList;
    }

    public YXItemSkuDetail getSkuDetailTO() {
        return skuDetailTO;
    }

    public void setSkuDetailTO(YXItemSkuDetail skuDetailTO) {
        this.skuDetailTO = skuDetailTO;
    }

    public Integer getNewFlag() {
        return newFlag;
    }

    public void setNewFlag(Integer newFlag) {
        this.newFlag = newFlag;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Double getYanxuanPrice() {
        return yanxuanPrice;
    }

    public void setYanxuanPrice(Double yanxuanPrice) {
        this.yanxuanPrice = yanxuanPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Double getChannelPrice() {
        return channelPrice;
    }

    public void setChannelPrice(Double channelPrice) {
        this.channelPrice = channelPrice;
    }
}
