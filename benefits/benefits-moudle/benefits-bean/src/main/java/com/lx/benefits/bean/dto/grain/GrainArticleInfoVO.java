package com.lx.benefits.bean.dto.grain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User:wangmeng
 * Date:2019/5/29
 * Time:17:24
 * Verision:2.x
 * Description:TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "grainArticleInfo",description = "颗粒文章复合信息")
public class GrainArticleInfoVO extends GrainArticleInfo{
    @ApiModelProperty(value = "企业名称")
    private String enterprName;
}
