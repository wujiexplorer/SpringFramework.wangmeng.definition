package com.lx.benefits.bean.dto.enterprise;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class EnterprMoneyCreditDto {
	private BigDecimal totleArrivalAmount = BigDecimal.ZERO; //到账总金额 
	private BigDecimal totleConsumeCredit = BigDecimal.ZERO; //消耗总积分
	private BigDecimal totleEmployeeCredit = BigDecimal.ZERO;//员工总积分
	private BigDecimal totleEmployeeGeneralCredit = BigDecimal.ZERO;//员工普通总积分
	private BigDecimal totleEmployeeFestivalCredit = BigDecimal.ZERO;//员工节日总积分
	private BigDecimal totleRecycleCredit = BigDecimal.ZERO; //回收总积分
}
