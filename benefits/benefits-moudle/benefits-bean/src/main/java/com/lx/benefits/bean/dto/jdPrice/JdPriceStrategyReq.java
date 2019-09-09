package com.lx.benefits.bean.dto.jdPrice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User: fan
 * Date: 2019/03/04
 * Time: 23:15
 */
@Data
@ApiModel("京东调价策略")
public class JdPriceStrategyReq {

    @ApiModelProperty(value = "模版名称")
    private String name;

    @ApiModelProperty(value = "模版类型")
    private Integer type;

    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize = 10;

}
