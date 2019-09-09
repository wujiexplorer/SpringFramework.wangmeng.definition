package com.lx.benefits.bean.dto.supplieradmin.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-05 23:46.
 */
@ApiModel("供应商订单信息")
public class OrderInfoResDto
{
    @ApiModelProperty(value = "订单id")
    private String orderId;
    @ApiModelProperty(value = "子订单编号")
    private String orderCode;
    @ApiModelProperty(value = "父订单编号")
    private String parentOrderCode;
    @ApiModelProperty(value = "订单类型")
    private String orderType;
    @ApiModelProperty(value = "订单支付时间，格式：yyyy-MM-dd HH:mm:ss")
    private String payTime;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "收货人")
    private String receiveName;
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderListInfoResDto{" +
                "orderId='" + orderId + '\'' +
                "orderCode='" + orderCode + '\'' +
                "parentOrderCode='" + parentOrderCode + '\'' +
                ", orderType='" + orderType + '\'' +
                ", payTime='" + payTime + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", receiveName='" + receiveName + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }

    public String getParentOrderCode() {
        return parentOrderCode;
    }

    public void setParentOrderCode(String parentOrderCode) {
        this.parentOrderCode = parentOrderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
