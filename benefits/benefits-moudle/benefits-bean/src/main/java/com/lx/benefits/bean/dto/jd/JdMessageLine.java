package com.lx.benefits.bean.dto.jd;


import com.lx.benefits.bean.dto.jd.annotation.Id;
import com.lx.benefits.bean.dto.jd.base.BaseDO;

import java.io.Serializable;
import java.util.Date;

/**
 * @author szy
 */
public class JdMessageLine extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1489734940072L;

    /**
     * 数据类型bigint(20)
     */
    @Id
    private Long id;

    /**
     * 数据类型bigint(20)
     */
    private Long messageId;

    /**
     * 数据类型int(255)
     * @see com.lx.benefits.bean.enums.jdapi.JDMessageType
     */
    private Integer messageType;

    /**
     * 数据类型datetime
     */
    private Date messageTime;

    /**
     * 数据类型varchar(1024)
     */
    private String messageContent;

    /**
     * 数据类型tinyint(255)
     */
    private Integer status;

    /**
     * 数据类型datetime
     */
    private Date createTime;

    /**
     * 数据类型datetime
     */
    private Date updateTime;

    /**
     * 是否删除 1 删除 0未删除
     */
    private Integer isDel;


    public Long getId() {
        return id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
