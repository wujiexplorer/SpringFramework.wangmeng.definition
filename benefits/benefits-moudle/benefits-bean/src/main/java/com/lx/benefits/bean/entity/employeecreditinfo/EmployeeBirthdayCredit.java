package com.lx.benefits.bean.entity.employeecreditinfo;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Data;

@Data
public class EmployeeBirthdayCredit {
	
	/** 生日分配积分 */
	private BigDecimal credit;
	
	/** 是否自动发放 0 关闭 1开启*/
	private Integer status;
	
	/** 员工id */
	private BigInteger employeeId;
}
