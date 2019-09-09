package com.lx.benefits.bean.entity.agent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AgentIncomeRecorder implements Serializable {
    private Integer id;

    private Integer agentId;

    private String voucherNumber;

    private String payOrderNumber;

    private BigDecimal cashAmount;

    private BigDecimal profitProportion;

    private Integer type;

    private Long enterprId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber == null ? null : voucherNumber.trim();
    }

    public String getPayOrderNumber() {
        return payOrderNumber;
    }

    public void setPayOrderNumber(String payOrderNumber) {
        this.payOrderNumber = payOrderNumber == null ? null : payOrderNumber.trim();
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getProfitProportion() {
        return profitProportion;
    }

    public void setProfitProportion(BigDecimal profitProportion) {
        this.profitProportion = profitProportion;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getEnterprId() {
        return enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}