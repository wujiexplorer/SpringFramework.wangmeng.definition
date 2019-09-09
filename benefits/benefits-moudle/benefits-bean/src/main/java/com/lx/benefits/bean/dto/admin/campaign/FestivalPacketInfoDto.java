package com.lx.benefits.bean.dto.admin.campaign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author unknow on 2018-12-11 00:32.
 */
@Data
@ApiModel("企业节日礼金信息")
public class FestivalPacketInfoDto {
    @ApiModelProperty(value = "节日礼金活动id")
    private Long campaignId;
    @ApiModelProperty(value = "所属企业id")
    private Long enterpriseId;
    @ApiModelProperty(value = "所属企业Name")
    private String enterpriseName;
    @ApiModelProperty(value = "活动名称")
    private String campaignName;
    @ApiModelProperty(value = "活动英文名称")
    private String campaignNameEn;
    @ApiModelProperty(value = "活动类型 {1：节日, 2:生日, 3：周年庆 , 4:预售}")
    private Integer type;
    @ApiModelProperty(value = "可用积分类型 {PUTONG, JIERILIJIN, RENKEJILI,ADVANCESALE}")
    private String creditType;
    @ApiModelProperty(value = "礼金剩余积分")
    private Double surplus;
    @ApiModelProperty(value = "活动所属主题")
    private Long campaignThemeId;
    @ApiModelProperty(value = "活动所属主题集合")
    private List<Long> campaignThemeIdList;
    @ApiModelProperty(value = "活动状态 {UNDERWAY, OVER}")
    private String campaignStatus;
    @ApiModelProperty(value = "活动开始时间，格式：yyyy-MM-dd HH:mm:ss")
    private String startTime;
    @ApiModelProperty(value = "活动结束时间，格式：yyyy-MM-dd HH:mm:ss")
    private String endTime;
    @ApiModelProperty(value = "积分人员分配文件路径")
    private String creditListFile;
    @ApiModelProperty(value = "活动商品id集合")
    private List<Long> goodsIdList;
    @ApiModelProperty(value = "活动banner路径")
    private String bannerPath;
    @ApiModelProperty(value = "用户未下单时默认下单产品")
    private Long defaultGoodsId;
    @ApiModelProperty(value = "活动备注信息")
    private String remark;
    @ApiModelProperty(value = "添加活动时间，添加编辑无需此参数，格式：yyyy-MM-dd HH:mm:ss")
    private String created;
    @ApiModelProperty(value = "修改活动时间，添加编辑无需此参数，格式：yyyy-MM-dd HH:mm:ss")
    private String updated;
    @ApiModelProperty(value = "活动编码")
    private String campaignCode;
    @ApiModelProperty(value = "第三方支付  0：否  1：是")
    private Integer thirdPay;
    @ApiModelProperty(value = "邮件提醒  0：否  1：是")
    private Integer isEmail;
    @ApiModelProperty(value = "邮件提醒模板")
    private String remindEmail;
    @ApiModelProperty(value = "短信提醒  0：否  1：是")
    private Integer isSms;
    @ApiModelProperty(value = "短信提醒模板")
    private String remindSms;
    @ApiModelProperty(value = "白名单")
    private Boolean isWhitelist;

    // 总积分
    private Double creditTotal;

}
