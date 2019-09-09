package com.lx.benefits.bean.dto.yianapi.client;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lidongri on 2018/12/3.
 */
public class ClientOrderReq extends ClientBaseReq implements Serializable {

    private String oid;

    private String org_code;

    private String ee_no;

    private Double shipping_fee;

    private List<String> pay_channels;

    private List<ClientOrderItemReq> sku_items;

    private Double price;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getEe_no() {
        return ee_no;
    }

    public void setEe_no(String ee_no) {
        this.ee_no = ee_no;
    }

    public Double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(Double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public List<String> getPay_channels() {
        return pay_channels;
    }

    public void setPay_channels(List<String> pay_channels) {
        this.pay_channels = pay_channels;
    }

    public List<ClientOrderItemReq> getSku_items() {
        return sku_items;
    }

    public void setSku_items(List<ClientOrderItemReq> sku_items) {
        this.sku_items = sku_items;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
