package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author unknow on 2018-12-05 21:31.
 */
@Data
@ApiModel("企业后台积分充值OR退款信息")
public class CreditExchangeResDto {
    @ApiModelProperty(value = "积分变更id")
    private Long exchangeId;
    @ApiModelProperty(value = "积分类型 {PUTONG, JIERILIJIN, RENKEJILI}")
    private String creditType;
    @ApiModelProperty(value = "类型描述 {PUTONG, JIERILIJIN, RENKEJILI}")
    private String creditTypeDesc;
    @ApiModelProperty(value = "类型描述")
    private String optType;
    @ApiModelProperty(value = "类型描述")
    private String optTypeDesc;
    @ApiModelProperty(value = "积分变更金额")
    private String exchangeAmount;
    @ApiModelProperty(value = "积分变更状态 {CREATED, UNPAID, COMPLETE}")
    private String exchangeStatus;
    @ApiModelProperty(value = "积分创建时间，格式：yyyy-MM-dd HH:mm:ss")
    private String created;
    @ApiModelProperty(value = "员工数")
    private Integer employeeNum;

}
