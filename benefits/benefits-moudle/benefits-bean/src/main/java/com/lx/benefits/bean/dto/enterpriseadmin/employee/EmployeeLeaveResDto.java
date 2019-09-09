package com.lx.benefits.bean.dto.enterpriseadmin.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-05 17:38.
 */
@ApiModel("企业员工离职信息")
public class EmployeeLeaveResDto {
    private Long leaveId;
    @ApiModelProperty(value = "员工唯一标示id")
    private Long employeeId;
    @ApiModelProperty(value = "员工工号")
    private String employeeNo;
    @ApiModelProperty(value = "员工离职状态{Y: 离职; N: 在职}")
    private String leaveStatus;
    @ApiModelProperty(value = "员工姓名")
    private String employeeName;
    @ApiModelProperty(value = "员工离职时间 格式：yyyy-MM-dd HH:mm:ss")
    private String leaveTime;
    
    public Long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNo() {
        return this.employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getLeaveTime() {
        return this.leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    @Override
    public String toString() {
        return "EmployeeLeaveResDto{" +
                "leaveId=" + leaveId +
                ", employeeId=" + employeeId +
                ", employeeNo='" + employeeNo + '\'' +
                ", leaveStatus='" + leaveStatus + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", leaveTime='" + leaveTime + '\'' +
                '}';
    }
}
