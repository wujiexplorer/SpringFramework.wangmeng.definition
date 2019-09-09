package com.lx.benefits.bean.dto.yx;

import java.io.Serializable;
import java.util.List;

/**
 * 商品信息
 * Created by ldr on 2017/5/27.
 */
public class YXItem implements Serializable {

    private static final long serialVersionUID = -3408649332252511632L;

    /**
     * 商品的标识
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 主skuId
     * @deprecated 已作废
     */
    private Long primarySkuId;

    /**
     * 商品描述
     */
    private String simpleDesc;

    /**
     * 分类
     */
    private List<List<YXCategory>> categoryPathList;

    /**
     * 列表页图片的URL
     */
    private String listPicUrl;

    /**
     * 商品主图的URL
     * @deprecated  已作废
     */
    private String primaryPicUrl;

    /**
     * 商品下的sku列表
     */
    private List<YXItemSKU> skuList;

    /**
     * 详情
     */
    private YXItemDetail itemDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSimpleDesc() {
        return simpleDesc;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }

    public List<List<YXCategory>> getCategoryPathList() {
        return categoryPathList;
    }

    public void setCategoryPathList(List<List<YXCategory>> categoryPathList) {
        this.categoryPathList = categoryPathList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListPicUrl() {
        return listPicUrl;
    }

    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    @Deprecated
    public Long getPrimarySkuId() {
        return primarySkuId;
    }

    @Deprecated
    public void setPrimarySkuId(Long primarySkuId) {
        this.primarySkuId = primarySkuId;
    }

    @Deprecated
    public String getPrimaryPicUrl() {
        return primaryPicUrl;
    }

    @Deprecated
    public void setPrimaryPicUrl(String primaryPicUrl) {
        this.primaryPicUrl = primaryPicUrl;
    }

    public List<YXItemSKU> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<YXItemSKU> skuList) {
        this.skuList = skuList;
    }

    public YXItemDetail getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(YXItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }
}
