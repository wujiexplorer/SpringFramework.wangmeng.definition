package com.lx.benefits.bean.dto.enterpriseadmin.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-23 02:20.
 */
@ApiModel("企业员工导入记录明细信息")
public class EmployeeImportDetailResDto {
    @ApiModelProperty(value = "员工导入记录明细id")
    private Long detailId;
    @ApiModelProperty(value = "所属导入批次id")
    private Long importId;
    @ApiModelProperty(value = "员工工号")
    private String employeeNo;
    @ApiModelProperty(value = "员工姓名")
    private String employeeName;
    @ApiModelProperty(value = "导入时间")
    private String importTime;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
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

    public String getImportTime() {
        return importTime;
    }

    public void setImportTime(String importTime) {
        this.importTime = importTime;
    }

    @Override
    public String toString() {
        return "EmployeeImportDetailResDto{" +
                "detailId=" + detailId +
                ", importId=" + importId +
                ", employeeNo='" + employeeNo + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", importTime='" + importTime + '\'' +
                '}';
    }
}
