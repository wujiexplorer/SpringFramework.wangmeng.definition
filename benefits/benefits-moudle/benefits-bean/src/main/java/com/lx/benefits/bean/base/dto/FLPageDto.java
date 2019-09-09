package com.lx.benefits.bean.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-06 02:10.
 */
@ApiModel("列表分页通用参数")
public class FLPageDto {
    @ApiModelProperty(value = "页码")
    private Integer page;
    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize;

    public Integer getPage() {
        return null == this.page || this.page < 1 ? 1 : this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return null == this.pageSize || this.pageSize < 1 ? 1 : this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "FLPageDto{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}