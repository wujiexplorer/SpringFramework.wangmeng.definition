package com.lx.benefits.bean.entity.enterpruserinfo;

import java.io.Serializable;
import java.util.Date;

public class EnterprBindRecorder implements Serializable {
    private Integer id;

    private Long enterprId;

    private Integer agentId;

    private String agentName;

    private Integer status;

    private String bindRemark;

    private String bindUser;

    private Date bindTime;

    private String unbindRemark;

    private String unbindUser;

    private Date unbindTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getEnterprId() {
        return enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBindRemark() {
        return bindRemark;
    }

    public void setBindRemark(String bindRemark) {
        this.bindRemark = bindRemark == null ? null : bindRemark.trim();
    }

    public String getBindUser() {
        return bindUser;
    }

    public void setBindUser(String bindUser) {
        this.bindUser = bindUser == null ? null : bindUser.trim();
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getUnbindRemark() {
        return unbindRemark;
    }

    public void setUnbindRemark(String unbindRemark) {
        this.unbindRemark = unbindRemark == null ? null : unbindRemark.trim();
    }

    public String getUnbindUser() {
        return unbindUser;
    }

    public void setUnbindUser(String unbindUser) {
        this.unbindUser = unbindUser == null ? null : unbindUser.trim();
    }

    public Date getUnbindTime() {
        return unbindTime;
    }

    public void setUnbindTime(Date unbindTime) {
        this.unbindTime = unbindTime;
    }
}