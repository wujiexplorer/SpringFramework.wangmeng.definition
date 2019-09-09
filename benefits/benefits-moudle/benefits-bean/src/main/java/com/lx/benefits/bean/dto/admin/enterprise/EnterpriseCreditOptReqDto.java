package com.lx.benefits.bean.dto.admin.enterprise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-10 01:38.
 */
@ApiModel("企业客户积分充值OR退款参数")
public class EnterpriseCreditOptReqDto
{
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;
    @ApiModelProperty(value = "积分金额")
    private Double creditAmount;
    @ApiModelProperty(value = "财务编号")
    private String financeNo;
    @ApiModelProperty(value = "积分类型 {PUTONG, JIERILIJIN, RENKEJILI}")
    private String creditType;
    @ApiModelProperty(value = "积分操作类型 {DISTRIBUTION, RECOVERY}")
    private String optType;
    @ApiModelProperty(value = "积分充值OR退款备注")
    private String remarks;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getFinanceNo() {
        return financeNo;
    }

    public void setFinanceNo(String financeNo) {
        this.financeNo = financeNo;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "EnterpriseCreditOptReqDto{" +
                "enterpriseId=" + enterpriseId +
                ", creditAmount=" + creditAmount +
                ", financeNo='" + financeNo + '\'' +
                ", creditType='" + creditType + '\'' +
                ", optType='" + optType + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}