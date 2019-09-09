package com.lx.benefits.bean.dto.grain;

import com.lx.benefits.bean.base.dto.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User:wangmeng
 * Date:2019/5/17
 * Time:15:51
 * Verision:2.x
 * Description:TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "grainArticleInfo",description = "颗粒文章基本信息")
public class GrainArticleInfo implements BaseVO {
    @ApiModelProperty(value = "文章ID")
    private Long articleId;
    @ApiModelProperty(value = "颗粒号ID")
    private Long grainId;
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;
    @ApiModelProperty(value = "文章内容")
    private String articleContent;
    @ApiModelProperty(value = "发布时间")
    private Date publishTime;
    @ApiModelProperty(value = "更新文章时间")
    private Date updateTime;
    @ApiModelProperty(value = "发布人")
    private String publishPerson;
    @ApiModelProperty(value = "发布人ID")
    private Long publishPersonId;
    @ApiModelProperty(value = "发布企业")
    private String publishEnterprise;
    @ApiModelProperty(value = "单次奖励颗粒值")
    private Double singleGrainAwardValue;
    @ApiModelProperty(value = "是否自主创作")
    private Integer isCustom;
    @ApiModelProperty(value = "文章是否热门")
    private Integer isHot;
    @ApiModelProperty(value = "文章状态（1：暂停奖励；2：开启奖励；3：未查看；4：已查看；5：已屏蔽；6：待定")
    private Integer status;
    @ApiModelProperty(value = "文章审核状态（1：未查看；2：已查看；3：已屏蔽")
    private Integer verifyStatus;
    @ApiModelProperty(value = "文章状态值（1：暂停奖励；2：开启奖励；3：未查看；4：已查看；5：已屏蔽；6：待定")
    private String statusValue;
    @ApiModelProperty(value = "文章阅读时间")
    private Integer articleReadTime;
    @ApiModelProperty(value = "文章阅读量")
    private Integer readQuantity;
    @ApiModelProperty(value = "累计颗粒奖励")
    private Double cumulationAwardValue;
    @ApiModelProperty(value = "图片路径")
    private String imagePath;
    @ApiModelProperty(value = "创建时间")
    private Integer created;
    @ApiModelProperty(value = "更新时间")
    private Integer updated;
    @ApiModelProperty(value = "企业名称")
    private String enterprName;
    @ApiModelProperty(value = "是否已奖励标志")
    private Boolean flag;
    @ApiModelProperty(value = "是否已屏蔽标志（0：未屏蔽；1：已屏蔽）")
    private Integer isScreen;
    @ApiModelProperty(value = "是否已阅读标志（0：未读；1：已读）")
    private Integer isRead;
}
