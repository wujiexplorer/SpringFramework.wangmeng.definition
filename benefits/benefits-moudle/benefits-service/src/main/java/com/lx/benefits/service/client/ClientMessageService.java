package com.lx.benefits.service.client;

import com.lx.benefits.bean.entity.client.ClientMessage;

public interface ClientMessageService {

	int addMessageRecorder(ClientMessage clientMessage);

	int udateMessageStatus(String messageId, boolean isSuccess);

	ClientMessage getMessageById(String messageById);

}
