package com.lx.benefits.bean.vo.card;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MemberCardBindRequest {
	private String loginName;
	private String password;
	private String code;
	@NotNull(message = "参数错误")
	private Integer bindType;
	@NotEmpty(message = "参数错误")
	private String verifyNumber;
	@NotEmpty(message = "参数错误")
	private String mobile;
	private String wxOpenId;
}
