package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @author luojie
 */
public class CreditRecoveryExcelDto extends BaseRowModel {


    /**
     * 员工工号
     */
    @ExcelProperty(value = "回收员工工号", index = 0)
    private String employeeNo;

    /**
     * 积分数额
     */
//    @ExcelProperty(value = "回收积分数额 此项可不填目前是回收用户当前剩余所有积分", index = 1)
    private String credit;

    /**
     * 积分类型
     */
    @ExcelProperty(value = "回收积分类型", index = 2)
    private String creditType;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

}
