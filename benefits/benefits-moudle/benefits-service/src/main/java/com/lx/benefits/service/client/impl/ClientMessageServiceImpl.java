package com.lx.benefits.service.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.benefits.bean.entity.client.ClientMessage;
import com.lx.benefits.mapper.client.ClientMessageMapper;
import com.lx.benefits.service.client.ClientMessageService;

@Service
public class ClientMessageServiceImpl implements ClientMessageService {

	@Autowired
	private ClientMessageMapper clientMessageMapper;

	@Override
	public int addMessageRecorder(ClientMessage clientMessage) {
		return clientMessageMapper.insertSelective(clientMessage);
	}

	@Override
	public int udateMessageStatus(String messageId, boolean isSuccess) {
		return clientMessageMapper.udateMessageStatus(messageId, isSuccess);
	}

	@Override
	public ClientMessage getMessageById(String messageId) {
		return clientMessageMapper.selectByPrimaryKey(messageId);
	}

}
