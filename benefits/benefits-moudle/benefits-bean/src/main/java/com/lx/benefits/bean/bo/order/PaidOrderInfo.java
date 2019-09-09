package com.lx.benefits.bean.bo.order;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class PaidOrderInfo {
	private String orderPayNumber;
	private BigDecimal points;
	private Integer orderStatus;
	private Date createTime;
}
