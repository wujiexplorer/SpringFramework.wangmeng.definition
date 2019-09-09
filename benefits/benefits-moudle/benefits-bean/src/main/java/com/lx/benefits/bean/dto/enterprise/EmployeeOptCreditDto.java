package com.lx.benefits.bean.dto.enterprise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-27 03:23.
 */
@ApiModel("员工积分操作信息")
public class EmployeeOptCreditDto {
    @ApiModelProperty(value = "积分操作id")
    private Long optId;
    @ApiModelProperty(value = "积分类型{PUTONG, JIERILIJIN, RENKEJILI}")
    private String creditType;
    @ApiModelProperty(value = "礼金活动id")
    private Long campaignId;
    @ApiModelProperty(value = "积分金额")
    private Double credit;
    @ApiModelProperty(value = "积分操作者")
    private Long optUserId;
    @ApiModelProperty(value = "积分操作时间")
    private String optTime;
    @ApiModelProperty(value = "积分操作类型")
    private String optType;
    @ApiModelProperty(value = "积分操作备注信息")
    private String remark;

    public Long getOptId() {
        return optId;
    }

    public void setOptId(Long optId) {
        this.optId = optId;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Long getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(Long optUserId) {
        this.optUserId = optUserId;
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "EmployeeOptCreditDto{" +
                "optId=" + optId +
                ", creditType='" + creditType + '\'' +
                ", campaignId=" + campaignId +
                ", credit=" + credit +
                ", optUserId=" + optUserId +
                ", optTime='" + optTime + '\'' +
                ", optType='" + optType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
