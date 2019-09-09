package com.lx.benefits.bean.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-04 03:10.
 */
@ApiModel("运营后台Session信息")
public class SessionFuliInfo implements SessionInfo{
    @ApiModelProperty(value = "管理员id")
    private Long adminId;
    @ApiModelProperty(value = "管理员登录用户名")
    private String loginName;
    @ApiModelProperty(value = "所属部门id")
    private Long departmentId;
    @ApiModelProperty(value = "所属角色id")
    private Long roleId;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SessionFuliInfo{" +
                "adminId=" + adminId +
                ", loginName='" + loginName + '\'' +
                ", departmentId=" + departmentId +
                ", roleId=" + roleId +
                '}';
    }
}