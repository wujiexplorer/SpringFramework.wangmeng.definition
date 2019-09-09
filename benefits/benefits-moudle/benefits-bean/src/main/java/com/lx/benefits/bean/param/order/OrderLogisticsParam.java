package com.lx.benefits.bean.param.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class OrderLogisticsParam {
    @ApiModelProperty(value = "快递公司")
    private String logisticsChannel;
    @ApiModelProperty(value = "快递单号")
    private String logisticsNumber;
    @ApiModelProperty(value = "备注")
    private String remark;
}
