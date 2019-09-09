package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/3.
 */
public class JDItemImage implements Serializable {

    private static final long serialVersionUID = 9102303527652726925L;

    private String path;

    private Integer isPrimary;

    private Integer orderSort;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Integer isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Integer getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(Integer orderSort) {
        this.orderSort = orderSort;
    }


}
