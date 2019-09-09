package com.lx.benefits.bean.base.dto;

import com.lx.benefits.bean.dto.admin.customized.BannerInfoDto;
import com.lx.benefits.bean.dto.admin.customized.EnterprNoticeDto;
import com.lx.benefits.bean.dto.admin.customized.ModuleOptionInfoDto;
import com.lx.benefits.bean.entity.enterprmenuinfo.EnterprMenuInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author unknow on 2018-12-06 19:48.
 */
@Data
@ApiModel("企业客户定制欢迎页模块信息")
public class WelcomeModuleInfoDto {
    @ApiModelProperty(value = "定制模块id")
    private Long customId;
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;
    @ApiModelProperty(value = "企业名称")
    private String enterprName;
    @ApiModelProperty(value = "Logo图片地址")
    private String logoPath;
    @ApiModelProperty(value = "Banner列表信息")
    private List<BannerInfoDto> bannerInfoDtoList;
    @ApiModelProperty(value = "网页背景色")
    private String pageBackground;
    @ApiModelProperty(value = "导航栏背景色")
    private String navBackground;
    @ApiModelProperty(value = "企业联系电话")
    private String contactPhone;
    @ApiModelProperty(value = "企业联系Email")
    private String contactEmail;
    @ApiModelProperty(value = "欢迎页模块设置列表信息")
    private List<ModuleOptionInfoDto> moduleOptionInfoDtoList;
    private EnterprNoticeDto enterprNoticeDto;
    private List<EnterprMenuInfo> enterprMenuInfoList;
    /**
     * 类型  1：pc  2：手机端
     */
    private Integer type ;
    /**
     * 0:隐藏  1：显示
     */
    private Boolean isShowSearchBar;
    /**
     * 0:关闭  1：打开
     */
    private Integer wxacode;//小程序码

}