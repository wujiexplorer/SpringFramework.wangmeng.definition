package com.lx.benefits.bean.dto.admin.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author unknow on 2018-12-23 02:19.
 */
@ApiModel("代下单导入记录信息")
@Data
public class OrderImportItemResDto {

    private Long detailId;
    /**
     * 导入记录id,自增主键ID
     */
    private Long importId;
    /**
     * 所属员工id
     */
    private Long userid;

    private Long aid;

    private Long tid;

    private Long sku;

    @ApiModelProperty(value = "数量")
    private Long count;

    @ApiModelProperty(value = "活动id")
    private Long campaignId;

    @ApiModelProperty(value = "区域信息")
    private String regcode;

    @ApiModelProperty(value = "省份信息")
    private String provcode;

    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "描述")
    private String orderMsg;

    @ApiModelProperty(value = "创建时间")
    private String created;

    @ApiModelProperty(value = "更新时间")
    private String updated;

    @ApiModelProperty(value = "员工姓名")
    private String employeeName;
}
