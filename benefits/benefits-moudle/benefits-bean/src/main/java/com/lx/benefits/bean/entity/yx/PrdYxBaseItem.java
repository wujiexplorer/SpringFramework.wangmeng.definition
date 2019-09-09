package com.lx.benefits.bean.entity.yx;

import java.math.BigDecimal;
import java.util.Date;

public class PrdYxBaseItem {
    private Long id;

    private String skuCode;

    private Integer brandId;

    private String brandName;

    private Integer categoryId;

    private String categoryName;

    private Integer categoryId2;

    private String categoryName2;

    private Integer categoryId3;

    private String categoryName3;

    private Integer goodsType;

    private Integer isCross;

    private Integer supplierId;

    private String supplierName;

    private String goodsName;

    private String goodsNameEn;

    private String goodsBody;

    private String goodsImage;

    private String goodsImages;

    private String goodsSpecname;

    private Integer placeOriginid;

    private String placeOrigin;

    private BigDecimal goodsFreight;

    private String createdUser;

    private Date createdTime;

    private String updatedUser;

    private Date updatedTime;

    private Boolean sex;

    private String countrysize;

    private Date gjvalidendtime;

    private Date gjvalidstarttime;

    private BigDecimal gjdiscount;

    private Byte iseffect;

    private String detailHtml;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(Integer categoryId2) {
        this.categoryId2 = categoryId2;
    }

    public String getCategoryName2() {
        return categoryName2;
    }

    public void setCategoryName2(String categoryName2) {
        this.categoryName2 = categoryName2 == null ? null : categoryName2.trim();
    }

    public Integer getCategoryId3() {
        return categoryId3;
    }

    public void setCategoryId3(Integer categoryId3) {
        this.categoryId3 = categoryId3;
    }

    public String getCategoryName3() {
        return categoryName3;
    }

    public void setCategoryName3(String categoryName3) {
        this.categoryName3 = categoryName3 == null ? null : categoryName3.trim();
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getIsCross() {
        return isCross;
    }

    public void setIsCross(Integer isCross) {
        this.isCross = isCross;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsNameEn() {
        return goodsNameEn;
    }

    public void setGoodsNameEn(String goodsNameEn) {
        this.goodsNameEn = goodsNameEn == null ? null : goodsNameEn.trim();
    }

    public String getGoodsBody() {
        return goodsBody;
    }

    public void setGoodsBody(String goodsBody) {
        this.goodsBody = goodsBody == null ? null : goodsBody.trim();
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage == null ? null : goodsImage.trim();
    }

    public String getGoodsSpecname() {
        return goodsSpecname;
    }

    public void setGoodsSpecname(String goodsSpecname) {
        this.goodsSpecname = goodsSpecname == null ? null : goodsSpecname.trim();
    }

    public Integer getPlaceOriginid() {
        return placeOriginid;
    }

    public void setPlaceOriginid(Integer placeOriginid) {
        this.placeOriginid = placeOriginid;
    }

    public String getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin == null ? null : placeOrigin.trim();
    }

    public BigDecimal getGoodsFreight() {
        return goodsFreight;
    }

    public void setGoodsFreight(BigDecimal goodsFreight) {
        this.goodsFreight = goodsFreight;
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getCountrysize() {
        return countrysize;
    }

    public void setCountrysize(String countrysize) {
        this.countrysize = countrysize == null ? null : countrysize.trim();
    }

    public Date getGjvalidendtime() {
        return gjvalidendtime;
    }

    public void setGjvalidendtime(Date gjvalidendtime) {
        this.gjvalidendtime = gjvalidendtime;
    }

    public Date getGjvalidstarttime() {
        return gjvalidstarttime;
    }

    public void setGjvalidstarttime(Date gjvalidstarttime) {
        this.gjvalidstarttime = gjvalidstarttime;
    }

    public BigDecimal getGjdiscount() {
        return gjdiscount;
    }

    public void setGjdiscount(BigDecimal gjdiscount) {
        this.gjdiscount = gjdiscount;
    }

    public Byte getIseffect() {
        return iseffect;
    }

    public void setIseffect(Byte iseffect) {
        this.iseffect = iseffect;
    }

    public String getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(String goodsImages) {
        this.goodsImages = goodsImages;
    }

    public String getDetailHtml() {
        return detailHtml;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml;
    }
}