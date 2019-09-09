package com.lx.benefits.bean.dto.admin.order;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * Created by softw on 2019/1/15.
 */
public class OrderExcelDto extends BaseRowModel {

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
