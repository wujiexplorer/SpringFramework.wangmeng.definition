package com.lx.benefits.bean.dto.yx;

import java.io.Serializable;

/**
 * @Author: mickey
 * @Date: 2018/8/131605
 * @Description:
 */
public class YXItemSkuDetail implements Serializable{

    /**
     * 商品长度	length	Double	商品长度cm，可能为null，表示没有该属性
     */
    private Double length;

    /**
     * 商品宽度	width	Double	商品宽度cm，可能为null，表示没有该属性
     */
    private Double width;

    /**
     * 商品高度	height	Double	商品高度cm，可能为null，表示没有该属性
     */
    private Double height;

    /**
     * 商品毛重	weight	Double	商品毛重kg，可能为null，表示没有该属性
     */
    private Double weight;

    /**
     * 商品净重	netWeight	Double	商品净重kg，可能为null，表示没有该属性
     */
    private Double netWeight;

    /**
     * 保质期	shelfLife	Integer	保质期天，可能为null，表示没有该属性
     */
    private Integer shelfLife;


    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }
}
