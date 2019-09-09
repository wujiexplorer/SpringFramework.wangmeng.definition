package com.lx.benefits.bean.dto.supplieradmin.order;

import java.util.List;

/**
 * @author by yingcai on 2018/12/23.
 */
public class OrderInfoListResDto {

    private List<OrderInfoResDto> list;

    private Integer count;

    public List<OrderInfoResDto> getList() {
        return list;
    }

    public void setList(List<OrderInfoResDto> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
