package com.lx.benefits.bean.entity.seckill;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User:wangmeng
 * Date:2019/8/23
 * Time:11:53
 * Version:2.x
 * Description:TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeckillOrderReq {

    private Long number;

    private Long seckillId;

    private Integer offset;

    private Integer limit;

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
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (this.getPage() - 1) * this.getPageSize();
    }

}
