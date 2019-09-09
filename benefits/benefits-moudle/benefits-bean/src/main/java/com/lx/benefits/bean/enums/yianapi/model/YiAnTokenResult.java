package com.lx.benefits.bean.enums.yianapi.model;

import java.io.Serializable;

/**
 * 怡安token返回信息
 * Created by lidongri on 2018/11/14.
 */
public class YiAnTokenResult  implements Serializable{

    private static final long serialVersionUID = 2526854127794014921L;

    /**
     * token
     */
    private String access_token;

    private Integer expires_in;

    private String token_type;

    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
