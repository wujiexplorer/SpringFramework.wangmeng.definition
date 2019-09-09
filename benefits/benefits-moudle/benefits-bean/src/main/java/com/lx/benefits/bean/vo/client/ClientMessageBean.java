package com.lx.benefits.bean.vo.client;

import java.util.Date;

public class ClientMessageBean<T> {
	private String messageId;
	private Integer messageType;
	private T messageInfo;
	private Date createTime;

	public ClientMessageBean(String messageId, Integer messageType, T messageInfo, Date createTime) {
		this.messageId = messageId;
		this.messageType = messageType;
		this.messageInfo = messageInfo;
		this.createTime = createTime;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public T getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(T messageInfo) {
		this.messageInfo = messageInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
