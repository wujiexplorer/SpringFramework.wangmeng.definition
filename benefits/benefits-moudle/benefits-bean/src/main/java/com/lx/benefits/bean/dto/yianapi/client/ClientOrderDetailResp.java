package com.lx.benefits.bean.dto.yianapi.client;

/**
 * Created by lidongri on 2018/12/6.
 */
public class ClientOrderDetailResp   {

    private String order_number;

    private Integer status;

    private String remark;


    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
