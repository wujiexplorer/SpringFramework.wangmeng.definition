package com.lx.benefits.bean.dto.admin.customized;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author unknow on 2018-12-06 11:22.
 */
@Data
@ApiModel("企业客户商品定制价格详细信息")
public class PriceDetailResDto {
    @ApiModelProperty(value = "定制价格模块id")
    private Long customId;
    @ApiModelProperty(value = "企业id")
    private Long enterprId;
    @ApiModelProperty(value = "企业名称")
    private String enterprName;
    @ApiModelProperty(value = "商品id")
    private Long goodsId;
    @ApiModelProperty(value = "商品编号")
    private String goodsNo;
    @ApiModelProperty(value = "商品标题")
    private String goodsTitle;
    @ApiModelProperty(value = "定制单价")
    private Double goodsPrice;
    @ApiModelProperty(value = "添加时间，新增编辑无需此参数，格式：yyyy-MM-dd HH:mm:ss")
    private String created;
    @ApiModelProperty(value = "修改时间，新增编辑无需此参数，格式：yyyy-MM-dd HH:mm:ss")
    private String updated;
    private String supplierName;
    private Long supplierId;
    private String createdUser;
    private Double price;

}
