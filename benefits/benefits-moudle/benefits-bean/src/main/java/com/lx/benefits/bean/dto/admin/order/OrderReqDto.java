package com.lx.benefits.bean.dto.admin.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by softw on 2019/1/15.
 */
@ApiModel("代下单操作参数")
public class OrderReqDto {

    @ApiModelProperty(value = "下单文件地址")
    private String orderListFile;

    public String getOrderListFile() {
        return orderListFile;
    }

    public void setOrderListFile(String orderListFile) {
        this.orderListFile = orderListFile;
    }

    @Override
    public String toString() {
        return "OrderReqDto{" +
                "orderListFile='" + orderListFile + '\'' +
                '}';
    }
}
