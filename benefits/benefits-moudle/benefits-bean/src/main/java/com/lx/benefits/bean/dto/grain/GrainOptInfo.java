package com.lx.benefits.bean.dto.grain;

import com.lx.benefits.bean.base.dto.BaseVO;
import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User:wangmeng
 * Date:2019/5/17
 * Time:16:02
 * Verision:2.x
 * Description:TODO
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "grainOptInfo",description = "企业颗粒号有关颗粒值操作日志")
public class GrainOptInfo implements BaseVO {
    @ApiModelProperty(value = "颗粒号操作ID")
    private Long optId;
    @ApiModelProperty(value = "颗粒号父操作ID（开始时为0）")
    private Long parentOptId;
    @ApiModelProperty(value = "颗粒号")
    private Long grainId;
    @ApiModelProperty(value = "企业ID")
    private Long enterprId;
    @ApiModelProperty(value = "企业名称")
    private String enterprName;
    @ApiModelProperty(value = "转入时间")
    private Date receiveTime;
    @ApiModelProperty(value = "转入积分（普通积分）")
    private Double receiveCreditValue;
    @ApiModelProperty(value = "创建时间")
    private Integer created;
    @ApiModelProperty(value = "更新时间")
    private Integer updated;
}
