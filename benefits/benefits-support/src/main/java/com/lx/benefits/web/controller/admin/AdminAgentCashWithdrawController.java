package com.lx.benefits.web.controller.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.agent.AgentWithdrawRecorder;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionFuliInfo;
import com.lx.benefits.bean.vo.agent.AgentWithdrawSearchBean;
import com.lx.benefits.bean.vo.agent.WithdrawCheckingBean;
import com.lx.benefits.service.agent.AgentCashService;

@RestController("adminAgentCashWithdrawController")
@RequestMapping("/admin/agents/cash")
public class AdminAgentCashWithdrawController {

	@Autowired
	private AgentCashService agentCashService;

	// 提现记录
	@GetMapping("/withdrawrecorders")
	public Object getWithdrawRecorders(@RequestParam(required = false, defaultValue = "1") Integer checkStatus,
			@RequestParam(required = false) String agentName, @RequestParam(required = false) Long applyStartTime,
			@RequestParam(required = false) Long applyEndTime, PageBean pageBean) {
		AgentWithdrawSearchBean searchBean = new AgentWithdrawSearchBean();
		searchBean.setCheckStatus(checkStatus);
		searchBean.setAgentName(agentName);
		searchBean.setApplyStartTime(applyStartTime == null ? null : new Date(applyStartTime));
		searchBean.setApplyEndTime(applyEndTime == null ? null : new Date(applyEndTime));
		PageResultBean<AgentWithdrawRecorder> result = agentCashService.getWithdrawrecorders(searchBean, pageBean);
		return Response.succ(result);
	}

	// 对代理商提现申请进行审批
	@PostMapping("/withdraw/checking/{cashNumber}")
	public Object checkingWithdraw(@PathVariable String cashNumber, @RequestBody WithdrawCheckingBean checkingBean) {
		SessionFuliInfo sessionFuliInfo = SessionContextHolder.getSessionFuliInfo();
		agentCashService.checkingWithdraw(cashNumber, checkingBean, sessionFuliInfo.getLoginName());
		return Response.succ();
	}
}
