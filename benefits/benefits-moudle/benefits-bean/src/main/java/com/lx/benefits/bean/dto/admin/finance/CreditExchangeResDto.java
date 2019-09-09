package com.lx.benefits.bean.dto.admin.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-06 02:36.
 */
@ApiModel("运营后台企业积分充值OR退款信息")
public class CreditExchangeResDto {
    @ApiModelProperty(value = "积分变更id")
    private Long optId;
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;
    @ApiModelProperty(value = "企业名字")
    private String enterpriseName;
    @ApiModelProperty(value = "积分类型 {PUTONG, JIERILIJIN, RENKEJILI}")
    private String creditType;
    @ApiModelProperty(value = "积分变更类型 {DISTRIBUTION, RECOVERY}")
    private String optType;
    @ApiModelProperty(value = "积分变更金额")
    private String exchangeAmount;
    @ApiModelProperty(value = "积分变更状态 {CREATED, UNPAID, COMPLETE}")
    private String exchangeStatus;
    @ApiModelProperty(value = "日志摘要")
    private String logSummary;
    @ApiModelProperty(value = "财务编号")
    private String financeNo;
    @ApiModelProperty(value = "积分创建时间，新增编辑无需此参数，格式：yyyy-MM-dd HH:mm:ss")
    private String created;
    @ApiModelProperty(value = "积分更新时间，新增编辑无需此参数，格式：yyyy-MM-dd HH:mm:ss")
    private String updated;

    private Integer optUserId;

    private String optUserName;

    private String optTime;

    private Integer auditUserId;

    private String auditUserName;

    private String auditTime;

    public Integer getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(Integer optUserId) {
        this.optUserId = optUserId;
    }

    public String getOptUserName() {
        return optUserName;
    }

    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName;
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }

    public Integer getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public Long getOptId() {
        return optId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setOptId(Long optId) {
        this.optId = optId;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getExchangeAmount() {
        return exchangeAmount;
    }

    public void setExchangeAmount(String exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    public String getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(String exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public String getLogSummary() {
        return logSummary;
    }

    public void setLogSummary(String logSummary) {
        this.logSummary = logSummary;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getFinanceNo() {
        return financeNo;
    }

    public void setFinanceNo(String financeNo) {
        this.financeNo = financeNo;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "CreditExchangeResDto{" +
                "optId=" + optId +
                ", enterpriseId=" + enterpriseId +
                ", creditType='" + creditType + '\'' +
                ", optType='" + optType + '\'' +
                ", exchangeAmount='" + exchangeAmount + '\'' +
                ", exchangeStatus='" + exchangeStatus + '\'' +
                ", logSummary='" + logSummary + '\'' +
                ", financeNo='" + financeNo + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}