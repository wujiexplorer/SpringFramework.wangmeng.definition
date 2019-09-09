package com.lx.benefits.bean.dto.admin.campaign;

import java.util.List;

import lombok.Data;

@Data
public class FestivalPacketInfoBean {
	private Long campaignId;
	private String campaignCode;
	private String bannerPath;
	private String campaignName;
	private String campaignNameEn;
	private Integer type;
	private String creditType;
	private Integer thirdPay;
	private List<TopicInfoPageBean> topicList;

}
