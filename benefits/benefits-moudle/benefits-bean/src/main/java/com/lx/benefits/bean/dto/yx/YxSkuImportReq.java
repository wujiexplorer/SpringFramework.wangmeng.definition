package com.lx.benefits.bean.dto.yx;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * User: fan
 * Date: 2019/02/26
 * Time: 23:35
 */
@Data
@ApiModel("严选商品导入")
public class YxSkuImportReq {

    @ApiModelProperty(value = "SKU列表")
    List<String> sku;

}
