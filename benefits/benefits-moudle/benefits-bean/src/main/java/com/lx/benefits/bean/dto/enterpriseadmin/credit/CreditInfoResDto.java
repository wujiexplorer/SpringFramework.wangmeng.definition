package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author unknow on 2018-12-05 23:11.
 */
@ApiModel("企业积分信息")
@Data
public class CreditInfoResDto {
    @ApiModelProperty(value = "生效积分")
    private BigDecimal validCredit;
    @ApiModelProperty(value = "未生效积分")
    private BigDecimal invalidCredit;
    @ApiModelProperty(value = "锁定积分")
    private BigDecimal lockCredit;
    @ApiModelProperty(value = "分配积分")
    private BigDecimal distCredit;
    @ApiModelProperty(value = "积分类型 {PUTONG, JIERILIJIN, RENKEJILI}")
    private String creditType;
}