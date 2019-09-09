package com.lx.benefits.bean.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author unknow on 2018-12-06 02:18.
 */
@ApiModel("操作记录查询参数")
public class OptLogQueryReqDto extends FLPageDto {

    @ApiModelProperty(value = "业务标示信息")
    @NotNull(message = "bizId参数为空")
    private Long bizId;

    @ApiModelProperty(value = "操作类型,目前支持 {HR_DISTRIBUTION_USER_ADD, HR_RECOVERY_USER_REDUCE, RECHARGE, REFUND, PASS, REFUSE}")
    @NotNull(message = "optType参数为空")
    private String optType;

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    @Override
    public String toString() {
        return "OptLogQueryReqDto{" +
                "bizId=" + bizId +
                ", optType='" + optType + '\'' +
                "} " + super.toString();
    }
}
