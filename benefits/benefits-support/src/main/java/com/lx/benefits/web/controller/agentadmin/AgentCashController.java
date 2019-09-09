package com.lx.benefits.web.controller.agentadmin;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.IncomeRecorderPageResultBean;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.agent.AgentWallet;
import com.lx.benefits.bean.entity.agent.AgentWithdrawRecorder;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionAgentInfo;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.agent.WithdrawRequestBean;
import com.lx.benefits.service.agent.AgentCashService;

@RestController("agentCashController")
@RequestMapping("/agentadmin/agents/cash")
public class AgentCashController {
	Logger logger = LoggerFactory.getLogger(AgentCashController.class);

	@Autowired
	private AgentCashService agentCashService;

	// 余额及收支明细
	@GetMapping("/incomerecorders")
	public Object getIncomerecorders(@RequestParam(required = false) Integer type, @RequestParam(required = false) Long createStartTime,
			@RequestParam(required = false) Long createEndTime, PageBean pageBean) {
		SessionAgentInfo sessionAgentInfo = SessionContextHolder.getSessionAgentInfo();
		IncomeRecorderPageResultBean incomerecorders = agentCashService.getIncomerecordersWithCashTotal(sessionAgentInfo.getAgentId(), type,
				createStartTime == null ? null : new Date(createStartTime), createEndTime == null ? null : new Date(createEndTime), pageBean);
		return Response.succ(incomerecorders);
	}

	// 获取收益详情
	@GetMapping("/incomerecorders/{id}")
	public Object incomeDetail(@PathVariable Integer id) {
		SessionAgentInfo sessionAgentInfo = SessionContextHolder.getSessionAgentInfo();
		return Response.succ(agentCashService.getIncomeDetail(sessionAgentInfo.getAgentId(), id));
	}

	// 提现记录
	@GetMapping("/withdrawrecorders")
	public Object getWithdrawRecorders(PageBean pageBean) {
		SessionAgentInfo sessionAgentInfo = SessionContextHolder.getSessionAgentInfo();
		PageResultBean<AgentWithdrawRecorder> result = agentCashService.getWithdrawRecorders(sessionAgentInfo.getAgentId(), pageBean);
		return Response.succ(result);
	}

	// 获取可提现金额
	@GetMapping("/availablewithdraw")
	public Object getAvailablewithdraw() {
		SessionAgentInfo sessionAgentInfo = SessionContextHolder.getSessionAgentInfo();
		AgentWallet agentWallet = agentCashService.getAvailableWithdraw(sessionAgentInfo.getAgentId());
		return Response.succ(agentWallet);
	}

	// 申请提现
	@PostMapping("/withdraw")
	public Object withdraw(@Validated @RequestBody WithdrawRequestBean withdrawRequestBean) {
		SessionAgentInfo sessionAgentInfo = SessionContextHolder.getSessionAgentInfo();
		agentCashService.withdraw(sessionAgentInfo.getAgentId(), withdrawRequestBean);
		return Response.succ();
	}
}
