package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;

/**
 * 京东库存信息 订单详情页、下单使用
 * Created by ldr on 2017/3/14.
 */
public class JDNewStock implements Serializable {

    private static final long serialVersionUID = -4044952524624312143L;

    private String areaId;

    private Long skuId;

    /**
     * 库存状态编号 33,39,40,36,34
     */
    private Integer stockStateId;

    /**
     *  库存状态描述
     33 有货 现货-下单立即发货
     39 有货 在途-正在内部配货，预计2~6天到达本仓库
     40 有货 可配货-下单后从有货仓库配货
     36 预订
     34 无货
     */
    private String stockStateDesc;

    private Integer remainNum;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getStockStateId() {
        return stockStateId;
    }

    public void setStockStateId(Integer stockStateId) {
        this.stockStateId = stockStateId;
    }

    public String getStockStateDesc() {
        return stockStateDesc;
    }

    public void setStockStateDesc(String stockStateDesc) {
        this.stockStateDesc = stockStateDesc;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }
}
