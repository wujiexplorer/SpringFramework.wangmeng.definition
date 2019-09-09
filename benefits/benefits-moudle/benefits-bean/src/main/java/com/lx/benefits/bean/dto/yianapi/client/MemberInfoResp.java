package com.lx.benefits.bean.dto.yianapi.client;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/12/1.
 */
public class MemberInfoResp implements Serializable {

    private String ee_no;

    private String org_code;

    private String root_org_code;

    private String user_id;

    private String name;

    private String  national_id;

    private String language;

    private String national_id_type;

    private String from_application_name;

    private String next;

    private String params;

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
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

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
