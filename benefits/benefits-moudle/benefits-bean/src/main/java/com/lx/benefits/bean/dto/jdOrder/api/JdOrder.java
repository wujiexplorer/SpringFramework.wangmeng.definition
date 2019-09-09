package com.lx.benefits.bean.dto.jdOrder.api;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/16.
 */
public class JdOrder implements Serializable {

    private static final long serialVersionUID = -656712254436917499L;

    private JdOrderParentOrder parentOrder;

    private JDOrderSubOrder subOrder;

    private Integer type;

    public JdOrderParentOrder getParentOrder() {
        return parentOrder;
    }

    public void setParentOrder(JdOrderParentOrder parentOrder) {
        this.parentOrder = parentOrder;
    }

    public JDOrderSubOrder getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(JDOrderSubOrder subOrder) {
        this.subOrder = subOrder;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
