package com.lx.benefits.bean.entity.entercreditinfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;

public class CreditChekingRecorder implements Serializable {
    private String cashNumber;

    private Long enterprId;

    private Integer creditType;

    private BigDecimal cashAmount;

    private String applyRemark;

    private Integer applyType;

    private Date applyTime;

    private String applyUser;

    private Date checkTime;

    private String checkUser;

    private Integer checkStatus;

    private String checkRemark;

    private String payVoucher;

    private Date payTime;

    private BigDecimal payAmount;

    private static final long serialVersionUID = 1L;

    public String getCashNumber() {
        return cashNumber;
    }

    public void setCashNumber(String cashNumber) {
        this.cashNumber = cashNumber == null ? null : cashNumber.trim();
    }

    public Long getEnterprId() {
        return enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

    public Integer getCreditType() {
        return creditType;
    }

    public void setCreditType(Integer creditType) {
        this.creditType = creditType;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark == null ? null : applyRemark.trim();
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser == null ? null : applyUser.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser == null ? null : checkUser.trim();
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark == null ? null : checkRemark.trim();
    }

    public String getPayVoucher() {
        return payVoucher;
    }

    public void setPayVoucher(String payVoucher) {
        this.payVoucher = payVoucher == null ? null : payVoucher.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
    
    @Override
    public String toString() {
    	return "CreditChekingRecorder"+JSON.toJSONString(this);
    }
}