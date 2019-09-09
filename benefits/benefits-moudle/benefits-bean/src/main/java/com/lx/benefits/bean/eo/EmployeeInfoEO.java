package com.lx.benefits.bean.eo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.apollo.common.annotation.ExcelAnnotation;
import com.apollo.common.annotation.ExcelStringConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@ExcelStringConfig(fileName="员工信息")
public class EmployeeInfoEO implements Serializable {
	
	@ExcelAnnotation(header = "姓名", cell = 0)
    private String employeeName;
    @ExcelAnnotation(header = "工号", cell = 1)
    private String employeeNo;
    @ExcelAnnotation(header = "手机号", cell = 2)
    private String mobile;
    @ExcelAnnotation(header = "邮箱", cell = 3)
    private String email;
    @ExcelAnnotation(header = "生日", cell = 4)
    private String birthday;
    @ExcelAnnotation(header = "职级", cell = 5)
    private String position;
    @ExcelAnnotation(header = "入职时间", cell = 6)
    private String inductionTime;
    @ExcelAnnotation(header = "登录账号", cell = 7)
    private String loginName;
    @ExcelAnnotation(header = "离职情况(N:在职 Y:离职)", cell = 8)
    private String leaveStatus;
    @ExcelAnnotation(header = "普通积分", cell = 9)
    private BigDecimal ordCredit;
    @ExcelAnnotation(header = "节日积分", cell = 10)
    private BigDecimal festCredit;
}
