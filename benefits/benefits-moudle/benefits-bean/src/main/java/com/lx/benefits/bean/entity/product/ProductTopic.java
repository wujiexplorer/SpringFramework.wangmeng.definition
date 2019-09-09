package com.lx.benefits.bean.entity.product;

import java.io.Serializable;
import java.util.Date;

public class ProductTopic implements Serializable {
    private Integer id;

    private Long spuCode;

    private Integer topicId;

    private Integer sortNum;

    private Date createdTime;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(Long spuCode) {
        this.spuCode = spuCode;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}