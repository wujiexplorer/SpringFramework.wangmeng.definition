package com.lx.benefits.bean.dto.yianapi.client;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/12/4.
 */
public class ClientOrderUpdateResp  implements Serializable {

    private String oid;

    private String order_number;

    private String remark;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }
}
