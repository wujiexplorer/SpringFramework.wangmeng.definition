package com.lx.benefits.bean.dto.jdOrder.api;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/16.
 */
public class JDOrderItem implements Serializable {

    private static final long serialVersionUID = 587812788996752914L;

    private Long skuId;

    private Integer num;

    private Long category;

    private Double price;

    private Integer type;

    private Long oid;

    private String name;

    private Double tax;

    private Double taxPrice;

    private Double nakedPrice;

    private Double  remoteRegionFreight;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(Double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public Double getNakedPrice() {
        return nakedPrice;
    }

    public void setNakedPrice(Double nakedPrice) {
        this.nakedPrice = nakedPrice;
    }

    public Double getRemoteRegionFreight() {
        return remoteRegionFreight;
    }

    public void setRemoteRegionFreight(Double remoteRegionFreight) {
        this.remoteRegionFreight = remoteRegionFreight;
    }
}
