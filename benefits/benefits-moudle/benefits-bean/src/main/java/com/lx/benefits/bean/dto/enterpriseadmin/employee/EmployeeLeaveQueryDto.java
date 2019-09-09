package com.lx.benefits.bean.dto.enterpriseadmin.employee;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-29 01:25.
 */
@ApiModel("员工离职信息查询参数")
public class EmployeeLeaveQueryDto extends FLPageDto {

    @ApiModelProperty(value = "员工唯一标示id")
    private Long employeeId;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "EmployeeLeaveQueryDto{" +
                "employeeId=" + employeeId +
                "} " + super.toString();
    }
}