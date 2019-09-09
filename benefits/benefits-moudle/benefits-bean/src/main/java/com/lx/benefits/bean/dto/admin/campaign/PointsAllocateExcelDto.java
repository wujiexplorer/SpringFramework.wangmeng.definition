package com.lx.benefits.bean.dto.admin.campaign;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @author by yingcai on 2018/12/26.
 */
public class PointsAllocateExcelDto extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String employeeNo;

    @ExcelProperty(index = 1)
    private String employeeName;

    @ExcelProperty(index = 2)
    private String points;

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

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
