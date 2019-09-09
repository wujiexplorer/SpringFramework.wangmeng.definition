package com.lx.benefits.bean.dto.enterprise;

import java.math.BigDecimal;

/**
 * 员工积分
 *
 * @author luojie
 */
public class EmployeeCreditRes {

    private Long creditId;

    private Long employeeId;

    private Integer creditType;

    private Long campaignId;

    private BigDecimal credit;

    private Integer status;

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    private Integer isDeleted;

    private String created;

    private Integer updated;

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getCreditType() {
        return creditType;
    }

    public void setCreditType(Integer creditType) {
        this.creditType = creditType;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "EmployeeCreditInfo{" +
                "creditId=" + creditId +
                ", employeeId=" + employeeId +
                ", creditType=" + creditType +
                ", campaignId=" + campaignId +
                ", credit=" + credit +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}