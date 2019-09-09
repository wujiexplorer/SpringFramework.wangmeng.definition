package com.lx.benefits.bean.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author unknow on 2018-12-06 02:10.
 */
@ApiModel("列表分页通用参数")
public class FLPageResDto<T> {
    @ApiModelProperty(value = "页码")
    private Integer page;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize;

    @ApiModelProperty(value = "总共条数")
    private Integer count;

    private List<T> result;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "FLPageResDto{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", count=" + count +
                ", result=" + result +
                '}';
    }
}