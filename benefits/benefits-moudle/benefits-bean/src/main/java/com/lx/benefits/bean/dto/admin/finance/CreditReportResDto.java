package com.lx.benefits.bean.dto.admin.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-06 03:14.
 */
@ApiModel("企业积分报表信息")
public class CreditReportResDto {
    @ApiModelProperty(value = "积分报表id")
    private Long reportId;
    @ApiModelProperty(value = "供应商id")
    private Long supplierId;
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;
    @ApiModelProperty(value = "报告结算状态")
    private String settlementStatus;
    @ApiModelProperty(value = "报告结算类型")
    private String settlementType;
    @ApiModelProperty(value = "报告日期开始日期，格式：yyyy-MM-dd")
    private String reportStart;
    @ApiModelProperty(value = "报告日期结束日期，格式：yyyy-MM-dd")
    private String reportEnd;
    @ApiModelProperty(value = "供应商净收入")
    private Double supplierNetIncome;
    @ApiModelProperty(value = "供应商收入")
    private Double supplierIncome;
    @ApiModelProperty(value = "供应商退款")
    private Double supplierRefund;
    @ApiModelProperty(value = "附加物流费用")
    private Double expressExtraCosts;
    @ApiModelProperty(value = "发起人userId")
    private Long originatorUserId;
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public String getReportStart() {
        return reportStart;
    }

    public void setReportStart(String reportStart) {
        this.reportStart = reportStart;
    }

    public String getReportEnd() {
        return reportEnd;
    }

    public void setReportEnd(String reportEnd) {
        this.reportEnd = reportEnd;
    }

    public Double getSupplierNetIncome() {
        return supplierNetIncome;
    }

    public void setSupplierNetIncome(Double supplierNetIncome) {
        this.supplierNetIncome = supplierNetIncome;
    }

    public Double getSupplierIncome() {
        return supplierIncome;
    }

    public void setSupplierIncome(Double supplierIncome) {
        this.supplierIncome = supplierIncome;
    }

    public Double getSupplierRefund() {
        return supplierRefund;
    }

    public void setSupplierRefund(Double supplierRefund) {
        this.supplierRefund = supplierRefund;
    }

    public Double getExpressExtraCosts() {
        return expressExtraCosts;
    }

    public void setExpressExtraCosts(Double expressExtraCosts) {
        this.expressExtraCosts = expressExtraCosts;
    }

    public Long getOriginatorUserId() {
        return originatorUserId;
    }

    public void setOriginatorUserId(Long originatorUserId) {
        this.originatorUserId = originatorUserId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "CreditReportResDto{" +
                "reportId=" + reportId +
                ", supplierId=" + supplierId +
                ", enterpriseId=" + enterpriseId +
                ", settlementStatus='" + settlementStatus + '\'' +
                ", settlementType='" + settlementType + '\'' +
                ", reportStart='" + reportStart + '\'' +
                ", reportEnd='" + reportEnd + '\'' +
                ", supplierNetIncome=" + supplierNetIncome +
                ", supplierIncome=" + supplierIncome +
                ", supplierRefund=" + supplierRefund +
                ", expressExtraCosts=" + expressExtraCosts +
                ", originatorUserId=" + originatorUserId +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
