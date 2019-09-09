package com.lx.benefits.bean.dto.enterpriseadmin.employee;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-04 22:48.
 */
@ApiModel("企业员工列表查询参数")
public class EmployeeQueryReqDto extends FLPageDto {
    /**
     * 员工所属企业id
     */
    private Long enterprId;
    @ApiModelProperty(value = "员工工号")
    private String employeeNo;
    @ApiModelProperty(value = "员工姓名")
    private String employeeName;
    @ApiModelProperty(value = "员工手机号")
    private String employeeMobile;
    @ApiModelProperty(value = "员工Email")
    private String employeeEmail;

    public Long getEnterprId() {
        return enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeMobile() {
        return employeeMobile;
    }

    public void setEmployeeMobile(String employeeMobile) {
        this.employeeMobile = employeeMobile;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    @Override
    public String toString() {
        return "EmployeeQueryReqDto{" +
                "enterprId=" + enterprId +
                ", employeeNo='" + employeeNo + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeMobile='" + employeeMobile + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                "} " + super.toString();
    }
}
