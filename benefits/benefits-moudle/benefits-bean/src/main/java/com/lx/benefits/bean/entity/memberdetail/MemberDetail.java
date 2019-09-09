package com.lx.benefits.bean.entity.memberdetail;

import java.util.Date;

public class MemberDetail {
    private Long id;

    /**
     * 用户ID
     */
    private Long uid;

    private Date birthday;

    /**
     * 用户真实姓名
     */
    private String true_name;

    /**
     * 头像url
     */
    private String avatar_url;

    /**
     * 实名验证照片a
     */
    private String pic_a;

    /**
     * 实名验证照片b
     */
    private String pic_b;

    /**
     * 是否实名验证
     */
    private Integer is_certificate_check;

    /**
     * 实名验证审核状态
     */
    private Integer verify_status;

    /**
     * 注册平台
     */
    private Integer registry_platform;

    /**
     * 目前常住地址
     */
    private String address;

    /**
     * 宝宝出生医院
     */
    private String baby_birth_hospital;

    /**
     * 证件类型
     */
    private Integer certificate_type;

    /**
     * 证件号
     */
    private String certificate_value;

    private Date create_time;

    private Date modify_time;

    private Date delete_time;

    /**
     * 是否删除
     */
    private Integer is_delete;

    /**
     * 最后登录时间
     */
    private Date last_login_time;

    /**
     * 最后登录ip
     */
    private String last_login_ip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name == null ? null : true_name.trim();
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url == null ? null : avatar_url.trim();
    }

    public String getPic_a() {
        return pic_a;
    }

    public void setPic_a(String pic_a) {
        this.pic_a = pic_a == null ? null : pic_a.trim();
    }

    public String getPic_b() {
        return pic_b;
    }

    public void setPic_b(String pic_b) {
        this.pic_b = pic_b == null ? null : pic_b.trim();
    }

    public Integer getIs_certificate_check() {
        return is_certificate_check;
    }

    public void setIs_certificate_check(Integer is_certificate_check) {
        this.is_certificate_check = is_certificate_check;
    }

    public Integer getVerify_status() {
        return verify_status;
    }

    public void setVerify_status(Integer verify_status) {
        this.verify_status = verify_status;
    }

    public Integer getRegistry_platform() {
        return registry_platform;
    }

    public void setRegistry_platform(Integer registry_platform) {
        this.registry_platform = registry_platform;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBaby_birth_hospital() {
        return baby_birth_hospital;
    }

    public void setBaby_birth_hospital(String baby_birth_hospital) {
        this.baby_birth_hospital = baby_birth_hospital == null ? null : baby_birth_hospital.trim();
    }

    public Integer getCertificate_type() {
        return certificate_type;
    }

    public void setCertificate_type(Integer certificate_type) {
        this.certificate_type = certificate_type;
    }

    public String getCertificate_value() {
        return certificate_value;
    }

    public void setCertificate_value(String certificate_value) {
        this.certificate_value = certificate_value == null ? null : certificate_value.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public Date getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Date delete_time) {
        this.delete_time = delete_time;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
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
}