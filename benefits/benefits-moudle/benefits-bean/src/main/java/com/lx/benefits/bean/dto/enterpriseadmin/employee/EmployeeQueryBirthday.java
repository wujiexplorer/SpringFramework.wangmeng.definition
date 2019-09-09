package com.lx.benefits.bean.dto.enterpriseadmin.employee;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class EmployeeQueryBirthday {
	private Long enterprId;
    @ApiModelProperty(value = "员工工号")
    private String employeeNo;
    @ApiModelProperty(value = "员工姓名")
    private String employeeName;
    @ApiModelProperty(value = "生日起始时间")
    private String startTime;
    @ApiModelProperty(value = "生日结束时间")
    private String endTime;
    
    @ApiModelProperty(value = "生日提醒状态 0关闭 1开启")
    private Integer birthdayRemind;
    @ApiModelProperty(value = "当前时间  格式：月-日")
    private String currentTime;
    
    @ApiModelProperty(value = "页码")
    private Integer page;
    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize;

}
