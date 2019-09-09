package com.lx.benefits.bean.entity.userinfo;

import com.lx.benefits.bean.dto.power.SysRoleRes;

import java.util.Date;
import java.util.List;

public class UserInfo {
    private Long id;

    /**
     * 登录名
     */
    private String login_name;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录盐
     */
    private String salt;

    /**
     * 部门id
     */
    private Long department_id;

    /**
     * 角色id
     */
    private Long role_id;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户email
     */
    private String email;

    /**
     * 用户状态
     */
    private Boolean status;

    /**
     * 创建人id
     */
    private String create_user;

    /**
     * 修改人id
     */
    private String update_user;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 最后一次登录时间
     */
    private Date last_login_time;

    /**
     * 最后一次登录ip
     */
    private String last_login_ip;

    private List<SysRoleRes> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name == null ? null : login_name.trim();
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user == null ? null : create_user.trim();
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user == null ? null : update_user.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip == null ? null : last_login_ip.trim();
    }

    public List<SysRoleRes> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRoleRes> roleList) {
        this.roleList = roleList;
    }
}