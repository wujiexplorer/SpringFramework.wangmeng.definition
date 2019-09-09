package com.lx.benefits.bean.enums.yianapi.model;

import java.io.Serializable;

/**
 * 怡安用户信息
 * Created by lidongri on 2018/11/15.
 */
public class YiAnUserInfoResult implements Serializable {

    private static final long serialVersionUID = 5433202030807260048L;
    /**
     * 员工号
     */
    private String ee_no;

    /**
     * 公司号
     */
    private String org_code;

    /**
     * 母公司编号
     */
    private String root_org_code;

    /**
     * 员工唯一标识id
     */
    private Long user_id;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String national_id;

    /**
     * 用户语言设置，可选项en/zh
     */
    private String language;

    /**
     *身份证类型
     */
    private String national_id_type;

    /**
     * 跳转来源
     */
    private String from_application_name;


    public String getEe_no() {
        return ee_no;
    }

    public void setEe_no(String ee_no) {
        this.ee_no = ee_no;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getRoot_org_code() {
        return root_org_code;
    }

    public void setRoot_org_code(String root_org_code) {
        this.root_org_code = root_org_code;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNational_id_type() {
        return national_id_type;
    }

    public void setNational_id_type(String national_id_type) {
        this.national_id_type = national_id_type;
    }

    public String getFrom_application_name() {
        return from_application_name;
    }

    public void setFrom_application_name(String from_application_name) {
        this.from_application_name = from_application_name;
    }
}
