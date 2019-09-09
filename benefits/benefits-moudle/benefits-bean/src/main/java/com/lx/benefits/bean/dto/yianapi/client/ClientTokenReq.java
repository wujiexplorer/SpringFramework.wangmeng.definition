package com.lx.benefits.bean.dto.yianapi.client;


import com.lx.benefits.bean.base.dto.BaseQuery;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/12/1.
 */
public class ClientTokenReq extends BaseQuery implements Serializable {

    private static final long serialVersionUID = -8062463784379087942L;

    public String client_id;

    public String client_secret;


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
}
