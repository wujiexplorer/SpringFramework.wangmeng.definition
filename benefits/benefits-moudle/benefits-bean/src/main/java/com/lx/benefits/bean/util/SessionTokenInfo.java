package com.lx.benefits.bean.util;

import io.swagger.annotations.ApiModel;

/**
 * @author unknow on 2018-12-17 22:30.
 */
@ApiModel("透传给前端的token信息")
public class SessionTokenInfo {
    /**
     * token 信息
     */
    private String token;
    /**
     * secret 信息
     */
    private String secret;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "SessionTokenInfo{" +
                "token='" + token + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
