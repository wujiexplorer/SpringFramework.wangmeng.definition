package com.lx.benefits.bean.entity.card;

import java.io.Serializable;
import java.util.Date;

public class CardBatchRecorder implements Serializable {
    private Integer batchId;

    private Integer customerBatchId;

    private Long customerId;

    private Integer type;

    private Integer status;

    private Integer saleOrderId;

    private String makeUser;

    private Date makeTime;

    private String storeUser;

    private Date storeTime;

    private String deliverUser;

    private Date deliverTime;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getCustomerBatchId() {
        return customerBatchId;
    }

    public void setCustomerBatchId(Integer customerBatchId) {
        this.customerBatchId = customerBatchId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(Integer saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public String getMakeUser() {
        return makeUser;
    }

    public void setMakeUser(String makeUser) {
        this.makeUser = makeUser == null ? null : makeUser.trim();
    }

    public Date getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Date makeTime) {
        this.makeTime = makeTime;
    }

    public String getStoreUser() {
        return storeUser;
    }

    public void setStoreUser(String storeUser) {
        this.storeUser = storeUser == null ? null : storeUser.trim();
    }

    public Date getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Date storeTime) {
        this.storeTime = storeTime;
    }

    public String getDeliverUser() {
        return deliverUser;
    }

    public void setDeliverUser(String deliverUser) {
        this.deliverUser = deliverUser == null ? null : deliverUser.trim();
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}