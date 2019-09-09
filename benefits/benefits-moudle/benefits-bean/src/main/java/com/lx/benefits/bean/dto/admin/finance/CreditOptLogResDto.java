package com.lx.benefits.bean.dto.admin.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-10 02:28.
 */
@ApiModel("充值OR退款OR审核通过OR审核拒绝操作记录信息")
public class CreditOptLogResDto
{
    @ApiModelProperty(value = "充值OR退款OR审核通过OR审核拒绝操作id")
    private Long optId;
    @ApiModelProperty(value = "操作类型 {DISTRIBUTION RECOVERY HR_DISTRIBUTION_REDUCE HR_RECOVERY_ADD HR_DISTRIBUTION_USER_ADD HR_RECOVERY_USER_REDUCE USER_ORDER_REDUCE USER_REFUND_ADD DISTRIBUTION_TO_EMPLOYEE ADMIN_DISTRIBUTION_USER_ADD}")
    private String optType;
    @ApiModelProperty(value = "操作人id")
    private String optUserId;
    @ApiModelProperty(value = "操作人")
    private String optUserName;
    @ApiModelProperty(value = "充值OR退款OR审核通过OR审核拒绝备注")
    private String optRemarks;
    @ApiModelProperty(value = "操作时间，新增编辑无需此参数，格式：yyyy-MM-dd HH:mm:ss")
    private String optTime;
}
