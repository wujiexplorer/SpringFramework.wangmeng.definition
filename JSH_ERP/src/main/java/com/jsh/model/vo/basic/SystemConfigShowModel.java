package com.jsh.model.vo.basic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SystemConfigShowModel implements Serializable
{
    /**
     * 提示信息
     */
    private String msgTip = "";

    public String getMsgTip() 
    {
        return msgTip;
    }

    public void setMsgTip(String msgTip) 
    {
        this.msgTip = msgTip;
    }
}
