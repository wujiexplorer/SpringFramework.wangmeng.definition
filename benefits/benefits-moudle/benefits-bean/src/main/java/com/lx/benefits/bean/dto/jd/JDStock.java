package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;

/**
 * 库存 商品列表页使用
 * Created by ldr on 2017/3/14.
 */
public class JDStock implements Serializable {

    private static final long serialVersionUID = -7236457397747490492L;

    private String area;

    private String desc;

    private  String sku;

    /**
     * 33 有货 现货-下单立即发货
     39 有货 在途-正在内部配货，预计2~6天到达本仓库
     40 有货 可配货-下单后从有货仓库配货
     36 预订
     34 无货
     */
    private Integer state;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
