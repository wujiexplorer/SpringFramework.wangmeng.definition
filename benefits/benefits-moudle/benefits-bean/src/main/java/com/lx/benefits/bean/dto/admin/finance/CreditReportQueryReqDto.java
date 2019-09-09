package com.lx.benefits.bean.dto.admin.finance;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-06 03:03.
 */
@ApiModel("企业积分报表列表查询参数")
public class CreditReportQueryReqDto extends FLPageDto {
    @ApiModelProperty(value = "供应商id")
    private Long supplierId;
    @ApiModelProperty(value = "订单id")
    private Long orderId;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "CreditReportQueryReqDto{" +
                "supplierId=" + supplierId +
                ", orderId=" + orderId +
                ", enterpriseId=" + enterpriseId +
                ", settlementStatus='" + settlementStatus + '\'' +
                ", settlementType='" + settlementType + '\'' +
                ", reportStart='" + reportStart + '\'' +
                ", reportEnd='" + reportEnd + '\'' +
                "} " + super.toString();
    }
}
