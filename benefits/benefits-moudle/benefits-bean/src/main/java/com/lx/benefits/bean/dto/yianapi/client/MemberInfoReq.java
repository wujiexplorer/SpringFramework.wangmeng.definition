package com.lx.benefits.bean.dto.yianapi.client;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/12/1.
 */
public class MemberInfoReq implements Serializable {

    private static final long serialVersionUID = 437271396169755998L;


    private String code;

    private String token;

    private Integer page;

    private Integer size;

    private Integer userId;

    private String eeNo;

    private String orgCode;

    private String nationalId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getEeNo() {
        return eeNo;
    }

    public void setEeNo(String eeNo) {
        this.eeNo = eeNo;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
