package com.lx.benefits.bean.param.employee;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EmployeeUserInfoParam {
	 /**
     * 员工id,自增主键ID
     */
    private Long employeeId;

    /**
     * 员工所属企业客户id
     */
    private Long enterprId;

    /**
     * 员工登录用户名
     */
    private String loginName;

    /**
     * 员工登录密码
     */
    private String password;

    /**
     * 员工登录密码secret
     */
    private String secret;

    /**
     * 员工工号
     */
    private String employeeNo;

    /**
     * 员工姓名
     */
    private String employeeName;

    /**
     * 员工性别{0 :保密; 1: 男; 2: 女; 3: 变性人}
     */
    private Integer sex;

    /**
     * 员工状态{0: 在职; 1: 离职}
     */
    private Integer leaveStatus;

    /**
     * 员工手机号
     */
    private String mobile;

    /**
     * 员工Email
     */
    private String email;

    /**
     * 员工生日
     */
    private String birthday;
    
    /**
     * 员工生日自动发放积分提醒
     */
    private Integer birthdayRemind;
    /**
     * 员工入职时间
     */
    
    private Integer inductionTime;

    /**
     * 员工职级
     */
    private String position;

    /**
     * 是否删除 {1:已删除; 0:未删除}
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private Integer created;

    /**
     * 更新时间
     */
    private Integer updated;

    private Integer leaveTime;

    /**
     * 员工积分
     */
    private BigDecimal credit;

}
