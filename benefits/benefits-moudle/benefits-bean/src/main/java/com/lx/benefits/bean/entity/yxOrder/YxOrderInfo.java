package com.lx.benefits.bean.entity.yxOrder;

import java.util.Date;

public class YxOrderInfo {
    private Long id;

    private Long subOrderCode;

    private String yxOrderStatus;

    private String yxPackageId;

    private Double realPrice;

    private Double expFee;

    private Long userId;

    private String userName;

    private Date orderTime;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubOrderCode() {
        return subOrderCode;
    }

    public void setSubOrderCode(Long subOrderCode) {
        this.subOrderCode = subOrderCode;
    }

    public String getYxOrderStatus() {
        return yxOrderStatus;
    }

    public void setYxOrderStatus(String yxOrderStatus) {
        this.yxOrderStatus = yxOrderStatus == null ? null : yxOrderStatus.trim();
    }

    public String getYxPackageId() {
        return yxPackageId;
    }

    public void setYxPackageId(String yxPackageId) {
        this.yxPackageId = yxPackageId == null ? null : yxPackageId.trim();
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Double getExpFee() {
        return expFee;
    }

    public void setExpFee(Double expFee) {
        this.expFee = expFee;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}