package com.lx.benefits.bean.dto.jdOrder.api;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldr on 2017/3/16.
 */
public class JDOrderParentOrderSnap implements Serializable {

    private static final long serialVersionUID = -8028258645457123559L;

    private Long jdOrderId;

    private Double orderPrice;

    private Double freight;

    private List<JDOrderItem> sku;

    public Long getJdOrderId() {
        return jdOrderId;
    }

    public void setJdOrderId(Long jdOrderId) {
        this.jdOrderId = jdOrderId;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public List<JDOrderItem> getSku() {
        return sku;
    }

    public void setSku(List<JDOrderItem> sku) {
        this.sku = sku;
    }
}
