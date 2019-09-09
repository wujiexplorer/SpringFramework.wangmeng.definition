package com.lx.benefits.bean.dto.admin.customized;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author unknow on 2018-12-06 21:45.
 */
@Data
@ApiModel("企业客户定制欢迎页模块选项信息")
public class ModuleOptionInfoDto {
    @ApiModelProperty(value = "moduleId 新增时不需要")
    private Long moduleId;
    @ApiModelProperty(value = "module名称")
    private String moduleName;
    @ApiModelProperty(value = "module英文名称")
    private String moduleNameEn;
    @ApiModelProperty(value = "module链接地址")
    private String moduleLink;
    @ApiModelProperty(value = "module图片地址")
    private String modulePic;
    @ApiModelProperty(value = "module背景色")
    private String background;
    @ApiModelProperty(value = "模块选中背景色")
    private String selectedBackground;
    @ApiModelProperty(value = "module展示顺序")
    private Integer moduleSort;
    @ApiModelProperty(value = "展示开始时间，无限制设置为空，格式：yyyy-MM-dd HH:mm:ss")
    private String startTime;
    @ApiModelProperty(value = "展示结束时间，无限制设置为空，格式：yyyy-MM-dd HH:mm:ss")
    private String endTime;
    @ApiModelProperty(value = "是否展示当前module单元")
    private Boolean isShow;
    @ApiModelProperty(value = "是否删除 编辑时可指定")
    private Boolean isDeleted;

    private Integer type;

}