package com.lx.benefits.bean.dto.admin.customized;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author unknow on 2018-12-06 10:57.
 */
@Data
@ApiModel("企业客户定制价格模块信息")
public class PriceModuleInfoDto {
    @ApiModelProperty(value = "定制价格模块id, 编辑时此参数不能为空")
    private Long customId;
    @ApiModelProperty(value = "企业id")
    private Long enterprId;
    @ApiModelProperty(value = "商品id")
    private Long goodsId;
    @ApiModelProperty(value = "定制单价")
    private Double goodsPrice;
    @ApiModelProperty(value = "企业定制价格文件")
    private String filePath;
}
