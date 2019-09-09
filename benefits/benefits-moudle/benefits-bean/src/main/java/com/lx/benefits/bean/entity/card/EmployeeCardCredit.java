package com.lx.benefits.bean.entity.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmployeeCardCredit implements Serializable {
    private Integer id;

    private Integer employeeId;

    private BigDecimal cardCredit;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getCardCredit() {
        return cardCredit;
    }

    public void setCardCredit(BigDecimal cardCredit) {
        this.cardCredit = cardCredit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}