package com.lx.benefits.bean.dto.yianapi.client;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/12/1.
 */
public class ClientCodeResp implements Serializable {

    private String code;

    private String url;

    private String fullurl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFullurl() {
        return fullurl;
    }

    public void setFullurl(String fullurl) {
        this.fullurl = fullurl;
    }
}
