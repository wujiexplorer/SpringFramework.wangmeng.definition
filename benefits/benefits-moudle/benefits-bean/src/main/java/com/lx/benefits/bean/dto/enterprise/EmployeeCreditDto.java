package com.lx.benefits.bean.dto.enterprise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author unknow on 2018-12-27 03:31.
 */
@ApiModel("员工积分基础信息")
@Data
public class EmployeeCreditDto {
    @ApiModelProperty(value = "积分类型 {PUTONG, JIERILIJIN, RENKEJILI}")
    private String creditType;
    @ApiModelProperty(value = "积分所属活动{0: 总积分; 其它值: 所属的具体活动}")
    private Long campaignId;
    @ApiModelProperty(value = "员工积分")
    private BigDecimal credit;

}
