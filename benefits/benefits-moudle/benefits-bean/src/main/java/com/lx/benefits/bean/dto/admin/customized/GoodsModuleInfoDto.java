package com.lx.benefits.bean.dto.admin.customized;

import com.lx.benefits.bean.entity.product.BrandEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author unknow on 2018-12-06 10:43.
 */
@Data
@ApiModel("企业客户定制商品模块信息")
public class GoodsModuleInfoDto {
    @ApiModelProperty(value = "定制商品模块id")
    private Long customId;
    @ApiModelProperty(value = "企业id")
    private Long enterprId;
    @ApiModelProperty(value = "热门商品id集合")
    private List<Long> hotGoodsIdList;
    @ApiModelProperty(value = "需要过滤的专题id集合")
    private List<Long> topicIdsList;
    @ApiModelProperty(value = "需要过滤的供应商id集合")
    private List<Long> supplierIdsList;
    @ApiModelProperty(value = "需要过滤的品牌id集合")
    private List<Long> brandIdsList;
    @ApiModelProperty(value = "需要过滤的类目id集合")
    private List<Long> categoryIdsList;

    @ApiModelProperty(value = "需要过滤的品牌信息")
    private List<BrandEntity> brandEntityList;

	// 最低价配置
	private BigDecimal lowestPrice;
}
