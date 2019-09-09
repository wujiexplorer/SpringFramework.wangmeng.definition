package com.lx.benefits.bean.dto.jdOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User: fan
 * Date: 2019/03/03
 * Time: 15:24
 */
@Data
@ApiModel("京东订单查询")
public class JdMerchantOrderReq {

    @ApiModelProperty(value = "我方子订单号")
    private Long subOrderCode;

    @ApiModelProperty(value = "京东订单号")
    private String merchantOrderId;

    @ApiModelProperty(value = "京东父单号")
    private String merchantParentOrderId;

    @ApiModelProperty(value = "用户ID")
    private Long memberId;

    @ApiModelProperty(value = "物流状态 0是新建/1是妥投/2是拒收")
    private Boolean state;

    @ApiModelProperty(value = "订单类型 1是父订单/2是子订单")
    private Boolean type;

    @ApiModelProperty(value = "订单状态 0为取消订单/1为有效")
    private Boolean orderState;

    @ApiModelProperty(value = "订单确认状态 0为取消订单/1为有效")
    private Boolean submitState;

    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize = 10;

}
