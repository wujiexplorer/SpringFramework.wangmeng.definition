package com.lx.benefits.bean.dto.grain;

import com.lx.benefits.bean.base.dto.BaseVO;
import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User:wangmeng
 * Date:2019/5/17
 * Time:15:41
 * Verision:2.x
 * Description:TODO
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "grainArticleAward",description = "员工看广告颗粒奖励基本信息")
public class GrainArticleAward implements BaseVO{
    @ApiModelProperty(value = "奖励ID")
    private Long awardId;
    @ApiModelProperty(value = "员工ID")
    private Long employeeId;
    @ApiModelProperty(value = "员工名称")
    private String employeeName;
    @ApiModelProperty(value = "员工奖励颗粒值")
    private Double awardGrainValue;
    @ApiModelProperty(value = "奖励获取时间")
    private Date awardGrainTime;
    @ApiModelProperty(value = "文章ID")
    private Long articleId;
    @ApiModelProperty(value = "创建时间")
    private Integer created;
    @ApiModelProperty(value = "更新时间")
    private Integer updated;

}
