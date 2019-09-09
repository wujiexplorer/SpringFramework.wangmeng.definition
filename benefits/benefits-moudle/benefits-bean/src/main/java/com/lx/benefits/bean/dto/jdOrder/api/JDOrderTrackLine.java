package com.lx.benefits.bean.dto.jdOrder.api;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/16.
 */
public class JDOrderTrackLine implements Serializable {

    private static final long serialVersionUID = 6519568621174112585L;
    private String msgTime;

    private String content;

    private String operator;

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
