package com.lx.benefits.bean.dto.enterpriseadmin.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Positive;

/**
 * @author unknow on 2018-12-29 17:35.
 */
@ApiModel("企业员工离职OR入职操作请求信息")
public class EmployeeLeaveOptReqDto {
    @ApiModelProperty(value = "员工唯一标示id")
    @Positive(message = "无效的员工id")
    private Long employeeId;
    @ApiModelProperty(value = "员工状态 {N:在职; Y:离职}")
    private String leaveStatus;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    @Override
    public String toString() {
        return "EmployeeLeaveOptReqDto{" +
                "employeeId=" + employeeId +
                ", leaveStatus='" + leaveStatus + '\'' +
                '}';
    }
}
