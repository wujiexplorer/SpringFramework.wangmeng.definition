package com.lx.benefits.bean.vo.card;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CardVerifyBean {

	@NotNull(message = "审核状态不能为空")
	private Boolean isPassed;
	@Size(max = 255, message = "备注最长255个字符")
	private String verifyInfo;
}
