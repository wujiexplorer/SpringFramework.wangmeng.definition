package com.lx.benefits.bean.dto.yx;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 15:35
 */
@Data
@ApiModel("严选商品")
public class PrdYxBaseItemReq {

    @ApiModelProperty(value = "SKU")
    private String skuCode;

    @ApiModelProperty(value = "名称")
    private String goodsName;

    @ApiModelProperty(value = "品牌")
    private String brandName;

    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize = 10;

}
