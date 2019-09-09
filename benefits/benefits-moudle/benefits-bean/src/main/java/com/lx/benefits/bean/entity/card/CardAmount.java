package com.lx.benefits.bean.entity.card;

import java.io.Serializable;
import java.util.Date;

public class CardAmount implements Serializable {
    private Integer amountId;

    private Integer amount;

    private Integer sort;

    private Date createTime;

    private Date udpateTime;

    private static final long serialVersionUID = 1L;

    public Integer getAmountId() {
        return amountId;
    }

    public void setAmountId(Integer amountId) {
        this.amountId = amountId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUdpateTime() {
        return udpateTime;
    }

    public void setUdpateTime(Date udpateTime) {
        this.udpateTime = udpateTime;
    }
}