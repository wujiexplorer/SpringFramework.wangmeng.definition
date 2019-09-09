package com.lx.benefits.config.bean;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class GeetestValidBean {
	@NotEmpty(message = "参数错误")
	private String challenge;
	@NotEmpty(message = "参数错误")
	private String validate;
	@NotEmpty(message = "参数错误")
	private String seccode;

}
