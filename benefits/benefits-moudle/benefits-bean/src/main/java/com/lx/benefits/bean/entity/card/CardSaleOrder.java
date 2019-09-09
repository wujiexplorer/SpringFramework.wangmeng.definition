package com.lx.benefits.bean.entity.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CardSaleOrder implements Serializable {
    private Integer saleOrderId;

    private Long customerId;

    private Long bindEnterprId;

    private Byte isCustomCard;

    private Integer payType;

    private BigDecimal discountOnSale;

    private BigDecimal sumPayable;

    private BigDecimal paidAmount;

    private String createUser;

    private Date createTime;

    private Integer status;

    private String verifyUser;

    private Date verifyTime;

    private String verifyInfo;

    private Boolean deleted;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(Integer saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBindEnterprId() {
        return bindEnterprId;
    }

    public void setBindEnterprId(Long bindEnterprId) {
        this.bindEnterprId = bindEnterprId;
    }

    public Byte getIsCustomCard() {
        return isCustomCard;
    }

    public void setIsCustomCard(Byte isCustomCard) {
        this.isCustomCard = isCustomCard;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getDiscountOnSale() {
        return discountOnSale;
    }

    public void setDiscountOnSale(BigDecimal discountOnSale) {
        this.discountOnSale = discountOnSale;
    }

    public BigDecimal getSumPayable() {
        return sumPayable;
    }

    public void setSumPayable(BigDecimal sumPayable) {
        this.sumPayable = sumPayable;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVerifyUser() {
        return verifyUser;
    }

    public void setVerifyUser(String verifyUser) {
        this.verifyUser = verifyUser == null ? null : verifyUser.trim();
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo == null ? null : verifyInfo.trim();
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}