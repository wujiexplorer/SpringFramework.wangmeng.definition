package com.lx.benefits.bean.dto.supplieradmin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author by yingcai on 2018/12/3.
 */
@ApiModel("供应商请求类")
public class SupplierUserByIdReq {

    @ApiModelProperty(value = "id的值")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
