package com.lx.benefits.bean.entity.agent;

import java.io.Serializable;
import java.util.Date;

public class AgentBindRecorder implements Serializable {
    private Integer id;

    private Integer agentId;

    private Integer parentAgentId;

    private String parentAgentName;

    private Integer status;

    private String bindRemark;

    private Date bindTime;

    private String unbindRemark;

    private Date unbindTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getParentAgentId() {
        return parentAgentId;
    }

    public void setParentAgentId(Integer parentAgentId) {
        this.parentAgentId = parentAgentId;
    }

    public String getParentAgentName() {
        return parentAgentName;
    }

    public void setParentAgentName(String parentAgentName) {
        this.parentAgentName = parentAgentName == null ? null : parentAgentName.trim();
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

    public Date getUnbindTime() {
        return unbindTime;
    }

    public void setUnbindTime(Date unbindTime) {
        this.unbindTime = unbindTime;
    }
}