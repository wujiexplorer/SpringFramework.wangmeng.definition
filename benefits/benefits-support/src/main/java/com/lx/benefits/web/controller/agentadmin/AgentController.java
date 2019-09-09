package com.lx.benefits.web.controller.agentadmin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.RegisterType;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionAgentInfo;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.agent.AgentAccountInfoBean;
import com.lx.benefits.bean.vo.agent.AgentCheckingBean;
import com.lx.benefits.bean.vo.agent.AgentUniqueInfoBean;
import com.lx.benefits.service.agent.AgentService;

@RestController("agentController")
@RequestMapping("/agentadmin/agents")
public class AgentController {

	@Autowired
	private AgentService agentService;

	// 提交代理商信息
	@PostMapping("/add")
	public Object addAgent(@RequestBody AgentAccountInfoBean request) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		agentService.addAgent(request, RegisterType.AGENT_RECOMMEND, sessionInfo.getLoginName(), sessionInfo.getAgentId());
		return Response.succ();
	}

	// 获取下级代理列表
	@GetMapping("/subagents")
	public Object getSubagents(@RequestParam(required = false) Integer agentType, @RequestParam(required = false) String agentName,
			@RequestParam(required = false) Long bindStartTime, @RequestParam(required = false) Long bindEndTime, PageBean pageBean) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		Integer agentId = sessionInfo.getAgentId();
		PageResultBean<AgentAccountInfoBean> pageResult = agentService.getSubagents(agentId, agentType, agentName,
				bindStartTime == null ? null : new Date(bindStartTime), bindEndTime == null ? null : new Date(bindEndTime), pageBean, true);
		return Response.succ(pageResult);
	}

	// 获取审批记录
	@GetMapping("/checking/list")
	public Object getCheckingList(PageBean pageBean) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		return Response.succ(agentService.getAgentCheckingList(sessionInfo.getAgentId(), pageBean));
	}

	// 获取代理商审批记录
	@GetMapping("/checking/{agentId}")
	public Object getCheckingAgent(@PathVariable Integer agentId) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		AgentCheckingBean checkingInfo = agentService.getAgentCheckingInfo(agentId);
		if (checkingInfo == null || !sessionInfo.getAgentId().equals(checkingInfo.getParentId())) {
			return Response.fail("该记录不存在!");
		}
		checkingInfo.setLoginName(null);// 隐藏账号
		return Response.succ(checkingInfo);
	}

	// 删除审批不通过的记录数据
	@GetMapping("/checking/{agentId}/delete")
	public Object deleteCheckingUnpassData(@PathVariable Integer agentId) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		agentService.deleteCheckingUnpassData(sessionInfo.getAgentId(), agentId);
		return Response.succ();
	}

	// 代理商信息查重
	@PostMapping("/uniquecheck")
	public Object agentUniqueInfoCheck(@Validated @RequestBody AgentUniqueInfoBean agentUniqueInfoBean) {
		agentService.agentUniqueInfoCheck(agentUniqueInfoBean);
		return Response.succ();
	}

	// 获取代理商信息
	@GetMapping("/{agentId}")
	public Object getAgentInfo(@PathVariable Integer agentId) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		AgentAccountInfoBean agentInfoBean = agentService.getAgentInfoBean(agentId);
		if (agentInfoBean == null || !sessionInfo.getAgentId().equals(agentInfoBean.getParentId())) {
			return Response.fail("该代理商不存在!");
		}
		agentInfoBean.setLoginName(null);// 隐藏代理商账号
		return Response.succ(agentInfoBean);
	}
}
