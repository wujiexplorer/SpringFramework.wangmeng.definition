package com.lx.benefits.bean.enums.yianapi.enums;

/**
 * Created by lidongri on 2018/11/13.
 */
public enum YiAnAPI {

    TOKEN("/api/oauth2/token/"),

    GET_USER_INFO("/api/oauth2/get-user-info/");

    private String path;

    YiAnAPI(String path) {
        this.path = path;
    }


    public String getPath() {
        return path;
    }
}
