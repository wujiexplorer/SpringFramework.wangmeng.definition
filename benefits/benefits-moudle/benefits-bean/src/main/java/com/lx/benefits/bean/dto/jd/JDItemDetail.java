package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/3.
 */
public class JDItemDetail implements Serializable{

    private static final long serialVersionUID = -1131074009186631248L;

    private String sku;

    private String weight;

    private String imagePath;

    private String state;

    private String brandName;

    private String name;

    private String productArea;

    private String upc;

    private String saleUnit;

    private String category;

    private String eleGift;


    private String skuType;

    private String introduction;

    private String param;

    private String appintroduce;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductArea() {
        return productArea;
    }

    public void setProductArea(String productArea) {
        this.productArea = productArea;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEleGift() {
        return eleGift;
    }

    public void setEleGift(String eleGift) {
        this.eleGift = eleGift;
    }

    public String getSkuType() {
        return skuType;
    }

    public void setSkuType(String skuType) {
        this.skuType = skuType;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getAppintroduce() {
        return appintroduce;
    }

    public void setAppintroduce(String appintroduce) {
        this.appintroduce = appintroduce;
    }
}
