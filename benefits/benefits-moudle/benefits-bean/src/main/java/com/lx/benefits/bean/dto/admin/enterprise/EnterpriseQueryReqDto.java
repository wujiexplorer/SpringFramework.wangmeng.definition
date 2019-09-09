package com.lx.benefits.bean.dto.admin.enterprise;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author unknow on 2018-12-06 01:50.
 */
@ApiModel("企业列表查询参数")
@Data
public class EnterpriseQueryReqDto extends FLPageDto {
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;

}