package com.lx.benefits.bean.dto.admin.customized;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author unknow on 2018-12-06 18:44.
 */
@ApiModel("企业客户定制价格搜索请求参数")
@Data
public class PriceQueryReqDto extends FLPageDto {
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;
    @ApiModelProperty(value = "商品编号")
    private Long goodsId;


}