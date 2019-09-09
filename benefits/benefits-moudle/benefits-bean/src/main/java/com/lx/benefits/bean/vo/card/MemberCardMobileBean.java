package com.lx.benefits.bean.vo.card;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MemberCardMobileBean {
	@NotEmpty(message = "参数错误")
	private String verifyNumber;
	@NotEmpty(message = "手机号码不能为空")
	@Size(max = 15, message = "手机号码错误")
	private String mobile;
	private String smsCode;
	// 图片验证码
	private String codeKey;
	private String imageCode;
}
