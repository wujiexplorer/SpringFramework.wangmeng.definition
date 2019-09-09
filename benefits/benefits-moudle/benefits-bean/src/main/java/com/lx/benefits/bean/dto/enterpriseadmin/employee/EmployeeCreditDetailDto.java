package com.lx.benefits.bean.dto.enterpriseadmin.employee;

import java.math.BigDecimal;

import lombok.Data;


@Data
public class EmployeeCreditDetailDto {
	private Long employeeId; 
    private String employeeNo;
    private String employeeName;
	/*** 普通积分*/
    private BigDecimal ordCredit = BigDecimal.ZERO;
    /*** 节日积分*/
    private BigDecimal festCredit = BigDecimal.ZERO;
}
