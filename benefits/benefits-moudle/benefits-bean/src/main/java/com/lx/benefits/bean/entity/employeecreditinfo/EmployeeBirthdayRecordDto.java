package com.lx.benefits.bean.entity.employeecreditinfo;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Data;

@Data
public class EmployeeBirthdayRecordDto {
	/** 自增主键 */
	private Integer id;
	/** 员工id */
	private BigInteger employeeId;
	/** 生日分配积分 */
	private BigDecimal credit;
	/** 分配状态 0挂起 1已发放 */
	private Integer status;
	/** 分配状态描述 */
	private String statusDesc;
	/** 积分类型 1普通 2节日 3认可激励*/
	private Integer creditType;
	/** 积分类型描述*/
	private String creditTypeDesc;
	/** 创建时间 */
	private String createTime;
	/** 更新时间 */
	private String updateTime;
}
