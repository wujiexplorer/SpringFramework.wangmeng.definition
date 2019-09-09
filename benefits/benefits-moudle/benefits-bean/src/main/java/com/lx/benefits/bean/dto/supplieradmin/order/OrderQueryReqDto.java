package com.lx.benefits.bean.dto.supplieradmin.order;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-04 02:11.
 */
@ApiModel("订单列表查询参数")
public class OrderQueryReqDto extends FLPageDto {
    @ApiModelProperty(value = "订单编号")
    private Long orderId;
    @ApiModelProperty(value = "收货人姓名")
    private String receiveName;
    @ApiModelProperty(value = "收货人手机号")
    private String receiveMobile;
    @ApiModelProperty(value = "订单状态 {CREATED, CANCELLED, PAID, SHIPPED, RECEIVED, REFUND_COMPLETED}")
    private String orderStatus;
    @ApiModelProperty(value = "订单搜索下单开始时间，格式：yyyy-MM-dd HH:mm:ss")
    private String createdStart;
    @ApiModelProperty(value = "订单搜索下单结束时间，格式：yyyy-MM-dd HH:mm:ss")
    private String createdEnd;
    
    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getReceiveName() {
        return this.receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveMobile() {
        return this.receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreatedStart() {
        return this.createdStart;
    }

    public void setCreatedStart(String createdStart) {
        this.createdStart = createdStart;
    }

    public String getCreatedEnd() {
        return this.createdEnd;
    }

    public void setCreatedEnd(String createdEnd) {
        this.createdEnd = createdEnd;
    }

    @Override
    public String toString() {
        return "OrderQueryReqDto{" +
                "orderId=" + orderId +
                ", receiveName='" + receiveName + '\'' +
                ", receiveMobile='" + receiveMobile + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", createdStart='" + createdStart + '\'' +
                ", createdEnd='" + createdEnd + '\'' +
                "} " + super.toString();
    }
}
