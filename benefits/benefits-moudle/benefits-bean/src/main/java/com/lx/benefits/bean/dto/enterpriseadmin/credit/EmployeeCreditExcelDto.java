package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author slong
 */
@Data
public class EmployeeCreditExcelDto extends BaseRowModel {
    /**
     * 员工工号
     */
    @ExcelProperty(value = "员工工号", index = 0)
    private String employeeNo;

    /**
     * 员工姓名
     */
    @ExcelProperty(value = "员工姓名", index = 1)
    private String employeeName;

    /**
     * 积分数额
     */
    @ExcelProperty(value = "分配积分数", index = 2)
    private String credit;

    /**
     * 积分剩余
     */
    @ExcelProperty(value = "积分剩余", index = 3)
    private String surplus;

    /**
     * 积分类型
     */
    @ExcelProperty(value = "分配积分类型", index = 4)
    private String creditType;


}
