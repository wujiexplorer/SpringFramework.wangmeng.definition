package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author unknow on 2018-12-05 22:06.
 */
@ApiModel("企业积分回收请求参数")
@Data
public class CreditRecoveryReqDto {
    @ApiModelProperty(value = "企业积分类型 {PUTONG, JIERILIJIN, RENKEJILI}")
    private String creditType;

    @ApiModelProperty(value = "企业积分回收范围 {PART, ALL}")
    private String recoveryRange;

    @ApiModelProperty(value = "企业回收员工积分明细文件")
    private String filePath;

    @ApiModelProperty(value = "企业积分回收备注")
    private String remarks;
    /**
     * 节日礼金id
     */
    private Long campaignId;
}