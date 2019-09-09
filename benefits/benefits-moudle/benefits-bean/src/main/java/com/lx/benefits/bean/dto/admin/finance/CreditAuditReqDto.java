package com.lx.benefits.bean.dto.admin.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-06 02:45.
 */
@ApiModel("企业积分审核操作参数")
public class CreditAuditReqDto {
    @ApiModelProperty(value = "积分id")
    private Long optId;
    @ApiModelProperty(value = "积分审核动作{CREATED, UNPAID, COMPLETE}")
    private String auditAction;


    public String getAuditAction() {
        return auditAction;
    }

    public void setAuditAction(String auditAction) {
        this.auditAction = auditAction;
    }

    public Long getOptId() {
        return optId;
    }

    public void setOptId(Long optId) {
        this.optId = optId;
    }

    @Override
    public String toString() {
        return "CreditAuditReqDto{" +
                "optId=" + optId +
                ", auditAction='" + auditAction + '\'' +
                '}';
    }
}
