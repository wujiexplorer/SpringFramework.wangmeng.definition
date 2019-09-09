package com.lx.benefits.bean.dto.admin.address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author unknow on 2018-12-23 02:19.
 */
@ApiModel("代下单导入记录信息")
@Data
public class AddressImportResDto {
    @ApiModelProperty(value = "导入记录id")
    private Long importId;
    @ApiModelProperty(value = "导入的文件路径")
    private String fileUrl;
    @ApiModelProperty(value = "操作人")
    private String optUserName;
    @ApiModelProperty(value = "导入订单数")
    private Long importCount;
    @ApiModelProperty(value = "导入成功数")
    private Long importSuc;
    @ApiModelProperty(value = "导入失败数")
    private Long importErr;
    @ApiModelProperty(value = "员工导入时间")
    private String importTime;
    @ApiModelProperty(value = "企业名称")
    private String enterprName;

}
