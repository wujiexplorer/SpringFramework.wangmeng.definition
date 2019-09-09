package com.lx.benefits.bean.entity.membercenterinfo;

import java.util.Date;

public class MemberCenterInfo {
    private Long id;

    /**
     * 用户手机号码
     */
    private String mobile;

    private String password;

    /**
     * 密码加盐
     */
    private String salt;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 创建人
     */
    private Long create_user_id;

    /**
     * 修改时间
     */
    private Date modify_time;

    /**
     * 修改人
     */
    private Long modify_user_id;

    private String source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(Long create_user_id) {
        this.create_user_id = create_user_id;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public Long getModify_user_id() {
        return modify_user_id;
    }

    public void setModify_user_id(Long modify_user_id) {
        this.modify_user_id = modify_user_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}