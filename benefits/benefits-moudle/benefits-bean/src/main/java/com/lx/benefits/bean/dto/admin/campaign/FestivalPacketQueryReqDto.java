package com.lx.benefits.bean.dto.admin.campaign;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

/**
 * @author unknow on 2018-12-26 18:17.
 */
@Data
public class FestivalPacketQueryReqDto extends FLPageDto {

    @ApiModelProperty(value = "活动id")
    private Long campaignId;

    @ApiModelProperty(value = "活动名称")
    private String campaignName;

    @ApiModelProperty(value = "企业名称ID")
    private Long enterprId;

    private Integer type;

}
