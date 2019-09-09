package com.lx.benefits.bean.dto.admin.supplierinfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: fan
 * Date: 2019/02/24
 * Time: 01:02
 */
@Data
public class SupplierInfoPageRes {

    @ApiModelProperty(value = "供应商ID")
    private Long id;

    @ApiModelProperty(value = "供应商编号")
    private String code;

    @ApiModelProperty(value = "供应商名称")
    private String name;

    @ApiModelProperty(value = "供应商英文名")
    private String alias;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String linkman;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    public void setUpdateTime(Date updateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.updateTime = sdf.format(updateTime);
    }
}
