package com.lx.benefits.bean.dto.admin.supplierinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User: fan
 * Date: 2019/02/24
 * Time: 01:01
 */
@Data
@ApiModel("供应商基础信息查询")
public class SupplierInfoReq {

    @ApiModelProperty(value = "供应商名")
    private String name;

    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize = 10;

}
