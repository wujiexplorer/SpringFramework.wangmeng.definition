package com.lx.benefits.bean.dto.yianapi.client;


import com.lx.benefits.bean.base.dto.BaseQuery;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/12/1.
 */
public class ClientCodeReq extends BaseQuery implements Serializable {


   private String clientname;

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }
}
