package com.lx.benefits.bean.enums.yianapi.model;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/11/15.
 */
public class YiAnUserInfoRequest implements Serializable {
    private static final long serialVersionUID = 8645086052827124128L;

    private String code;

    public YiAnUserInfoRequest(String code) {
        this.code = code;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
