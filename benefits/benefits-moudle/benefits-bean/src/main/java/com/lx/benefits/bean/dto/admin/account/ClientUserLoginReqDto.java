package com.lx.benefits.bean.dto.admin.account;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ClientUserLoginReqDto {

	@NotEmpty(message = "token不能为空")
	private String token;
	@NotEmpty(message = "orgCode不能为空")
	private String orgCode;
}
