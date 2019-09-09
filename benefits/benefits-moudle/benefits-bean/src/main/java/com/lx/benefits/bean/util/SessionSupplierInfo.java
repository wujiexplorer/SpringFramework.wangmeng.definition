package com.lx.benefits.bean.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("供应商Session信息")
@Data
public class SessionSupplierInfo implements SessionInfo{
    
    @ApiModelProperty(value = "供应商Id")
    private Long supplierId;

    @ApiModelProperty(value = "供应商登录用户名")
    private String loginName;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

}