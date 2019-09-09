package com.lx.benefits.web.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lx.benefits.service.client.YibaoApiService;

@Component
public class ClientMessageTask {

	@Autowired
	private YibaoApiService yibaoApiService;

	@Scheduled(initialDelay = 10 * 1000, fixedDelay = 60 * 1000)
	public void clientMessageHandle() {
		yibaoApiService.handleFailedMessage();
	}

}
