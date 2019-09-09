package com.lx.benefits.bean.dto.admin.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-20 02:05.
 */
@ApiModel("管理员用户信息")
public class AdminAccountInfoDto {
    @ApiModelProperty(value = "运营后台管理员id")
    private Long adminId;
    @ApiModelProperty(value = "登录名")
    private String loginName;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "登录salt")
    private String salt;
    @ApiModelProperty(value = "所属部门id")
    private Long departmentId;
    @ApiModelProperty(value = "所属角色id")
    private Long roleId;
    @ApiModelProperty(value = "用户手机号")
    private String mobile;
    @ApiModelProperty(value = "用户email")
    private String email;
    @ApiModelProperty(value = "管理员状态")
    private String status;
    @ApiModelProperty(value = "最后一次登录时间")
    private String lastLoginTime;
    @ApiModelProperty(value = "最后一次登录ip")
    private String lastLoginIp;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @Override
    public String toString() {
        return "AdminAccountInfoDto{" +
                "adminId=" + adminId +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", departmentId=" + departmentId +
                ", roleId=" + roleId +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                '}';
    }
}
