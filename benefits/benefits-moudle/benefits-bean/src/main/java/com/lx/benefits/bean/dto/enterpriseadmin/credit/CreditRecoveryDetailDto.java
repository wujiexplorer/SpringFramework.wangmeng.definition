package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import java.math.BigDecimal;

/**
 * @author luojie
 */
public class CreditRecoveryDetailDto {


    /**
     * 员工工号
     */
    private String employeeNo;

    /**
     * 积分数额  目前不适用后期看情况支持
     */
    private BigDecimal credit;

    /**
     * 积分类型
     */
    private Integer creditType;

    /**
     * 操作者id
     */
    private Long optUserId;

    /**
     * 操作备注
     */
    private String remark;

    /**
     * 企业客户id
     */
    private Long enterprId;

    /**
     * 员工积分id
     */
    private Long creditId;

    /**
     * 员工id
     */
    private Long employeeId;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Integer getCreditType() {
        return creditType;
    }

    public void setCreditType(Integer creditType) {
        this.creditType = creditType;
    }

    public Long getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(Long optUserId) {
        this.optUserId = optUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getEnterprId() {
        return enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

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
}
