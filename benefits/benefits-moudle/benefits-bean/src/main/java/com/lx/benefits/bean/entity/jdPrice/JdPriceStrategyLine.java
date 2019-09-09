package com.lx.benefits.bean.entity.jdPrice;

import java.util.Date;

public class JdPriceStrategyLine {
    private Long id;

    private Long strategyId;

    private Double frome;

    private Double toe;

    private Double priceRate;

    private Byte offline;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Double getFrome() {
        return frome;
    }

    public void setFrome(Double frome) {
        this.frome = frome;
    }

    public Double getToe() {
        return toe;
    }

    public void setToe(Double toe) {
        this.toe = toe;
    }

    public Double getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(Double priceRate) {
        this.priceRate = priceRate;
    }

    public Byte getOffline() {
        return offline;
    }

    public void setOffline(Byte offline) {
        this.offline = offline;
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