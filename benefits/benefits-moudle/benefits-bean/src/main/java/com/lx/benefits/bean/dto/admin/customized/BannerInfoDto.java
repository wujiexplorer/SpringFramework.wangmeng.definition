package com.lx.benefits.bean.dto.admin.customized;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author unknow on 2018-12-06 21:41.
 */
@Data
@ApiModel("企业客户定制欢迎页模块banner信息")
public class BannerInfoDto {
    @ApiModelProperty(value = "bannerId 新增时不需要")
    private Long bannerId;
    @ApiModelProperty(value = "banner图片路径")
    private String bannerPath;
    @ApiModelProperty(value = "banner链接地址")
    private String linkUrl;
    @ApiModelProperty(value = "banner图片title")
    private String bannerTitle;
    @ApiModelProperty(value = "banner图片title")
    private String bannerTitleEn;
    @ApiModelProperty(value = "banner图片排序")
    private Integer bannerSort;
    @ApiModelProperty(value = "展示开始时间，无限制设置为空，格式：yyyy-MM-dd HH:mm:ss")
    private String startTime;
    @ApiModelProperty(value = "展示结束时间，无限制设置为空，格式：yyyy-MM-dd HH:mm:ss")
    private String endTime;
    @ApiModelProperty(value = "是否展示当前banner单元")
    private Boolean isShow;
    @ApiModelProperty(value = "是否删除 编辑时可指定")
    private Boolean isDeleted;

    private Integer type;

}
