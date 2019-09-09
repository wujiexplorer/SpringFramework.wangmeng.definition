package com.lx.benefits.bean.entity.agent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AgentWalletCheckingRecorder implements Serializable {
    private Integer id;

    private Integer agentId;

    private BigDecimal cashTotal;

    private BigDecimal historyCashTotal;

    private Date checkTime;

    private Integer status;

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

    public BigDecimal getCashTotal() {
        return cashTotal;
    }

    public void setCashTotal(BigDecimal cashTotal) {
        this.cashTotal = cashTotal;
    }

    public BigDecimal getHistoryCashTotal() {
        return historyCashTotal;
    }

    public void setHistoryCashTotal(BigDecimal historyCashTotal) {
        this.historyCashTotal = historyCashTotal;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}