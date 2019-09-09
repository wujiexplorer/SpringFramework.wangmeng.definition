package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/3.
 */
public class JDItemState implements Serializable {

    private static final long serialVersionUID = 5316114859391273054L;

    private String sku;

    private Integer state;

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
