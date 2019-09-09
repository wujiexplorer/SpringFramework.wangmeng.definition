package com.lx.benefits.bean.dto.yx;

import java.io.Serializable;

/**
 * 库存信息
 * Created by ldr on 2017/6/7.
 */
public class YXItemInventoryDto implements Serializable {

    private static final long serialVersionUID = 8722279700898424364L;

    private String skuId;

    private Long inventory;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }
}
