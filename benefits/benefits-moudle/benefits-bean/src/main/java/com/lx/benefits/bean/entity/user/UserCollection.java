package com.lx.benefits.bean.entity.user;

import java.io.Serializable;
import java.util.Date;

public class UserCollection implements Serializable {
    private Long id;

    private Long spuCode;

    private Long userId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(Long spuCode) {
        this.spuCode = spuCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}