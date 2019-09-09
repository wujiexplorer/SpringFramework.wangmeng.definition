package com.lx.benefits.bean.dto.grain;

import com.lx.benefits.bean.base.dto.BaseVO;
import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User:wangmeng
 * Date:2019/5/17
 * Time:16:10
 * Verision:2.x
 * Description:TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "grainSpecialPerson",description = "有关特殊员工颗粒奖励基本信息")
public class GrainSpecialPerson implements BaseVO{
    @ApiModelProperty(value = "某个公司的特殊员工ID")
    private Long specialPersonId;
    @ApiModelProperty(value = "员工ID")
    private Long employeeId;
    @ApiModelProperty(value = "员工名称")
    private String employeeName;
    @ApiModelProperty(value = "员工工号")
    private String employeeNo;
    @ApiModelProperty(value = "单次奖励颗粒值")
    private Double singleAwardGrainValue;
    @ApiModelProperty(value = "企业ID")
    private Long enterprId;
    @ApiModelProperty(value = "创建时间")
    private Integer created;
    @ApiModelProperty(value = "更新时间")
    private Integer updated;
}
