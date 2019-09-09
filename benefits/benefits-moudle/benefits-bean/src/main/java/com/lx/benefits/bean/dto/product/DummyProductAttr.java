package com.lx.benefits.bean.dto.product;


import com.lx.benefits.bean.base.dto.BaseVO;

import java.util.List;

/**
 * Created by ldr on 2016/10/13.
 */
public class DummyProductAttr implements BaseVO {

    private static final long serialVersionUID = -6833287987294431876L;

   private String name;

    private List<String> cols;

    public DummyProductAttr() {
    }

    public DummyProductAttr(String name, List<String> cols) {
        this.name = name;
        this.cols = cols;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCols() {
        return cols;
    }

    public void setCols(List<String> cols) {
        this.cols = cols;
    }
}
