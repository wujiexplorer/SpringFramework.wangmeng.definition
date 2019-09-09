package com.lx.benefits.bean.entity.enterprfestivalpacket;


import com.lx.benefits.bean.entity.Entity;
import com.lx.benefits.bean.enums.CreditTypeEnum;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EnterprFestivalPacket extends Entity {
	
	public  static final EnterprFestivalPacket ZERO_ENTERPR_FESTIVAL_PACKET;
	static {
		ZERO_ENTERPR_FESTIVAL_PACKET = new EnterprFestivalPacket() {
			{
				super.setCampaignId(0L);
				super.setCreditType(CreditTypeEnum.PUTONG.getValue());
			}

			@Override
			public void setEnterprId(Long enterprId) {
				throw new RuntimeException("不支持修改");
			}

			@Override
			public void setCreditType(Integer creditType) {
				throw new RuntimeException("不支持修改");
			}

			@Override
			public void setCampaignId(Long campaignId) {
				throw new RuntimeException("不支持修改");
			}
		};
	}

    /**
     * 节日礼金活动id,自增主键ID
     */
    private Long campaignId;

    /**
     * 员工所属企业客户id
     */
    private Long enterprId;

    /**
     * 活动名称
     */
    private String campaignName;

    /**
     * 活动名称
     */
    private String campaignNameEn;

    /**
     * 可用积分类型 {0: 普通积分, 1: 节日礼金}
     */
    private Integer creditType;

    /**
     *  活动类型 {1：节日, 2:生日, 3：周年庆}
     */
    private Integer type;

    /**
     * 礼金总积分
     */
    private BigDecimal credit;
    /**
     * 礼金剩余积分
     */
    private BigDecimal surplus;

    /**
     * 活动所属主题
     */
    private Integer campaignThemeId;
    /**
     * 活动所属主题集合
     */
    private String campaignThemeIdList;
    /**
     * 活动状态 {0: 有效, 1: 无效}
     */
    private Integer campaignStatus;

    /**
     * 活动开始时间，秒级时间戳
     */
    private Integer startTime;

    /**
     * 活动结束时间, 秒级时间戳
     */
    private Integer endTime;

    /**
     * 积分人员分配文件路径,积分累加
     */
    private String creditListFile;

    /**
     * 活动商品id集合
     */
    private String goodsIdList;

    /**
     * 活动banner路径
     */
    private String bannerPath;

    /**
     * 用户未下单时默认下单产品
     */
    private Long defaultGoodsId;

    /**
     * 活动备注信息
     */
    private String remark;
    /**
     * 活动编码
     */
    private String campaignCode;
    /**
     * 第三方支付  0：否  1：是
     */
    private Integer thirdPay;
    /**
     * 邮件提醒  0：否  1：是
     */
    private Integer isEmail;
    /**
     * 邮件提醒模板
     */
    private String remindEmail;
    /**
     * 短信提醒  0：否  1：是
     */
    private Integer isSms;
    /**
     * 短信提醒模板
     */
    private String remindSms;

    /**
     * 商品白名单
     */
    private Boolean isWhitelist;

}