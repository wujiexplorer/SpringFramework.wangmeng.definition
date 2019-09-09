package com.lx.benefits.bean.dto.jd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 15:35
 */
@Data
@ApiModel("京东商品")
public class PrdJdBaseItemReq {

    @ApiModelProperty(value = "SKU")
    private String jdSku;

    @ApiModelProperty(value = "名称")
    private String jdName;

    @ApiModelProperty(value = "品牌")
    private String jdBrandName;

    @ApiModelProperty(value = "毛利率")
    private Double jdRate;

    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize = 10;

}
