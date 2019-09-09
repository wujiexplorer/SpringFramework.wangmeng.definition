package com.lx.benefits.bean.vo.agent;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class WithdrawRequestBean {

	@NotNull(message = "提现金额不能为空!")
	@Positive(message = "提现金额必须大于零!")
	private BigDecimal cashAmount;// 提现金额
	@NotEmpty(message = "开户银行不能为空")
	private String bankName;// 开户银行
	@NotEmpty(message = "银行账号不能为空")
	private String bankCardNumber;// 银行账号
}
