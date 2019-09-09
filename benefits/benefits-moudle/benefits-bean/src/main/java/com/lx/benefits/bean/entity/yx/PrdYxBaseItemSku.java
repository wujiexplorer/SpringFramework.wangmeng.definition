package com.lx.benefits.bean.entity.yx;

import java.math.BigDecimal;
import java.util.Date;

public class PrdYxBaseItemSku {
    private Integer id;

    private Long spuCode;

    private String skuCode;

    private String goodsSpec;

    private Integer goodsStorge;

    private String goodsImage;

    private String createdUser;

    private Date createdTime;

    private String updatedUser;

    private Date updatedTime;

    private Integer payStorge;

    private BigDecimal goodsCostprice;

    private BigDecimal goodsMarkprice;

    private BigDecimal goodsPrice;

    private BigDecimal goodsDiscount;

    private String itemNumber;

    private Integer seasonid;

    private String season;

    private String goodsAttr;

    private String color;

    private BigDecimal length;

    private BigDecimal width;

    private BigDecimal height;

    private String itemSkuSpecValueList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(Long spuCode) {
        this.spuCode = spuCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec == null ? null : goodsSpec.trim();
    }

    public Integer getGoodsStorge() {
        return goodsStorge;
    }

    public void setGoodsStorge(Integer goodsStorge) {
        this.goodsStorge = goodsStorge;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage == null ? null : goodsImage.trim();
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser == null ? null : createdUser.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser == null ? null : updatedUser.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getPayStorge() {
        return payStorge;
    }

    public void setPayStorge(Integer payStorge) {
        this.payStorge = payStorge;
    }

    public BigDecimal getGoodsCostprice() {
        return goodsCostprice;
    }

    public void setGoodsCostprice(BigDecimal goodsCostprice) {
        this.goodsCostprice = goodsCostprice;
    }

    public BigDecimal getGoodsMarkprice() {
        return goodsMarkprice;
    }

    public void setGoodsMarkprice(BigDecimal goodsMarkprice) {
        this.goodsMarkprice = goodsMarkprice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(BigDecimal goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber == null ? null : itemNumber.trim();
    }

    public Integer getSeasonid() {
        return seasonid;
    }

    public void setSeasonid(Integer seasonid) {
        this.seasonid = seasonid;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr == null ? null : goodsAttr.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getItemSkuSpecValueList() {
        return itemSkuSpecValueList;
    }

    public void setItemSkuSpecValueList(String itemSkuSpecValueList) {
        this.itemSkuSpecValueList = itemSkuSpecValueList;
    }
}