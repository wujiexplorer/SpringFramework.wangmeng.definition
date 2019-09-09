package com.lx.benefits.bean.dto.admin.finance;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-06 02:28.
 */
@ApiModel("企业积分充值AND退款列表查询参数")
public class CreditQueryReqDto extends FLPageDto {
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;
    @ApiModelProperty(value = "积分状态 {CREATED, UNPAID, COMPLETE}")
    private String creditStatus;

    @ApiModelProperty(value = "类型 {DISTRIBUTION, RECOVERY}")
    private String optType;

    @ApiModelProperty(value = "积分创建开始时间，格式：yyyy-MM-dd HH:mm:ss")
    private String createdStart;
    @ApiModelProperty(value = "积分创建结束时间，格式：yyyy-MM-dd HH:mm:ss")
    private String createdEnd;

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public String getCreatedStart() {
        return createdStart;
    }

    public void setCreatedStart(String createdStart) {
        this.createdStart = createdStart;
    }

    public String getCreatedEnd() {
        return createdEnd;
    }

    public void setCreatedEnd(String createdEnd) {
        this.createdEnd = createdEnd;
    }

    @Override
    public String toString() {
        return "CreditQueryReqDto{" +
                "enterpriseId=" + enterpriseId +
                ", creditStatus='" + creditStatus + '\'' +
                ", createdStart='" + createdStart + '\'' +
                ", createdEnd='" + createdEnd + '\'' +
                "} " + super.toString();
    }
}
