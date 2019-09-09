package com.lx.benefits.bean.vo.card;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCardSearchBean {

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deliverStartTime;// 发卡范围-开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deliverEndTime;// 发卡范围-结束时间
	private Long customerId;// 客户ID
	private String cardNumber;// 会员卡卡号
	private Long enterprId;// 会员卡绑定的企业ID
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date usedStartTime;// 用户激活使用范围-开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date usedEndTime;// 用户激活使用范围-结束时间
	private Integer status;// 会员卡状态
}
