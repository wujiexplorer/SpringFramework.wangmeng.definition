package com.lx.benefits.bean.dto.admin.finance;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author slong
 */
@ApiModel(value = "企业积分分配AND回收变更列表 reqDto")
@Data
public class CreditExchangeReqDto extends FLPageDto {

    @ApiModelProperty(value = "操作类型 {HR_DISTRIBUTION_REDUCE（分配）, HR_RECOVERY_ADD（回收）}")
    private String optType;

    private Long parentOptId;

}
