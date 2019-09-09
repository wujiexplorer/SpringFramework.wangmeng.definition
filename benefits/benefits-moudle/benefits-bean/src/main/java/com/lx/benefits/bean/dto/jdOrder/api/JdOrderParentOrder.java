package com.lx.benefits.bean.dto.jdOrder.api;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldr on 2017/3/16.
 */
public class JdOrderParentOrder implements Serializable {

    private static final long serialVersionUID = -4437145328050619832L;

    private JDOrderParentOrderSnap pOrder;

    private List<JDOrderSubOrder> cOrder;

    /**
     * 订单状态  0为取消订单  1为有效
     */
    private Integer orderState;

    /**
     * 0为未确认下单订单   1为确认下单订单
     */
    private Integer submitState;

    private Double orderNakedPrice;

    /**
     * 订单类型   1是父订单   2是子订单
     */
    private Integer type;

    private Double orderTaxPrice;

    /**
     * 物流状态 0 是新建  1是妥投   2是拒收
     */
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public JDOrderParentOrderSnap getpOrder() {
        return pOrder;
    }

    public void setpOrder(JDOrderParentOrderSnap pOrder) {
        this.pOrder = pOrder;
    }

    public List<JDOrderSubOrder> getcOrder() {
        return cOrder;
    }

    public void setcOrder(List<JDOrderSubOrder> cOrder) {
        this.cOrder = cOrder;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getSubmitState() {
        return submitState;
    }

    public void setSubmitState(Integer submitState) {
        this.submitState = submitState;
    }

    public Double getOrderNakedPrice() {
        return orderNakedPrice;
    }

    public void setOrderNakedPrice(Double orderNakedPrice) {
        this.orderNakedPrice = orderNakedPrice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getOrderTaxPrice() {
        return orderTaxPrice;
    }

    public void setOrderTaxPrice(Double orderTaxPrice) {
        this.orderTaxPrice = orderTaxPrice;
    }
}
