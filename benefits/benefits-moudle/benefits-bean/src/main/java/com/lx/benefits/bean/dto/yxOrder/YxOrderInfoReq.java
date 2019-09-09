package com.lx.benefits.bean.dto.yxOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User: fan
 * Date: 2019/03/03
 * Time: 22:33
 */
@Data
@ApiModel("严选订单查询")
public class YxOrderInfoReq {

    @ApiModelProperty(value = "子订单号")
    private Long subOrderCode;

    @ApiModelProperty(value = "用户id")
    private Long userId;


    @ApiModelProperty(value = "订单状态 WAITING_PAY/未付款;PAYED/已付款;SYS_CANCEL/系统取消;USER_CANCEL/用户取消;KF_CANCEL/客服取消;CANCELLING/取消待审核;USERPAYEDCANCEL/用户付款后取消;")
    private String yxOrderStatus;

    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize = 10;
}
