package com.lx.benefits.task;

import org.apache.tools.ant.types.resources.selectors.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lx.benefits.service.agent.AgentCashService;

@Component
public class AgentWalletTask {
	Logger logger = LoggerFactory.getLogger(AgentWalletTask.class);

	@Autowired
	private AgentCashService agentCashService;

	// 每天进行一次局部校验
	@Scheduled(cron = "0 0 5 * * ?")
	public void partCheckAgentWallet() {
		logger.info("start partCheck agentWallet, startTime={}", new Date());
		agentCashService.checkAgentWallet(false);
		logger.info("end check agentWallet, endTime={}", new Date());
	}

	// 每月进行一次全局校验
	@Scheduled(cron = "0 0 3 1 * ?")
	public void gloabalCheckAgentWallet() {
		logger.info("start loabalCheck agentWallet, startTime={}", new Date());
		agentCashService.checkAgentWallet(true);
		logger.info("end loabalCheck agentWallet, startTime={}", new Date());
	}
}
