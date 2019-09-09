package com.lx.benefits.bean.enums.yianapi.model.kuaidi100query;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/11/18.
 */
public class Kuaidi100Line implements Serializable {

    private String context;

    private String time;

    private String ftime;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }
}
