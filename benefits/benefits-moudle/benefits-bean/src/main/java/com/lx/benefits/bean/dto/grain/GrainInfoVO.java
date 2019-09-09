package com.lx.benefits.bean.dto.grain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User:wangmeng
 * Date:2019/5/27
 * Time:18:12
 * Verision:2.x
 * Description:TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "grainInfoVO",description = "颗粒号复合信息")
public class GrainInfoVO extends GrainInfo {

    private Integer count;
}
