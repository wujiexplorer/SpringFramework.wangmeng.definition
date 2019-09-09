package com.lx.benefits.bean.dto.yianapi.client;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/12/3.
 */
public class ClientOrderItemReq implements Serializable {

    private String partner_sku;

    private Integer quantity;

    private Double unit_price;

    public String getPartner_sku() {
        return partner_sku;
    }

    public void setPartner_sku(String partner_sku) {
        this.partner_sku = partner_sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }
}
