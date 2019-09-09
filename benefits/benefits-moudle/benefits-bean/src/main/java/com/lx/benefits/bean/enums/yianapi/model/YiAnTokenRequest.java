package com.lx.benefits.bean.enums.yianapi.model;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/11/14.
 */
public class YiAnTokenRequest implements Serializable {

    private static final long serialVersionUID = 5425866999808117238L;

    private String client_id;

    private String client_secret;

    private String grant_type = "client_credentials";

    private String scope = "";

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
