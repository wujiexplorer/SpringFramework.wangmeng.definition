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
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprCheckingRecorder;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionAgentInfo;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.agent.EnterpriseUniqueInfoBean;
import com.lx.benefits.bean.vo.enterpr.EnterprUserInfoBean;
import com.lx.benefits.service.enterprise.EnterpriseService;

@RestController
@RequestMapping("/agentadmin/enterprises")
public class AgentEnterpriseController {

	@Autowired
	private EnterpriseService enterpriseService;

	// 查看代理商下的企业列表
	@GetMapping("")
	public Object getEnterprises(@RequestParam(required = false) String enterprName, @RequestParam(required = false) Long bindStartTime,
			@RequestParam(required = false) Long bindEndTime, PageBean pageBean) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		PageResultBean<EnterprUserInfo> enterprUserInfos = enterpriseService.getEnterprises(sessionInfo.getAgentId(), enterprName,
				bindStartTime == null ? null : new Date(bindStartTime), bindEndTime == null ? null : new Date(bindEndTime), pageBean);
		return Response.succ(enterprUserInfos);
	}

	// 添加企业信息
	@PostMapping("/add")
	public Object addEnterprise(@Validated @RequestBody EnterprCheckingRecorder request) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		enterpriseService.addEnterprise(request, RegisterType.AGENT_RECOMMEND, sessionInfo.getLoginName(), sessionInfo.getAgentId());
		return Response.succ();
	}

	// 获取审批记录列表
	@GetMapping("/checking/list")
	public Object getCheckingList(PageBean pageBean) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		return Response.succ(enterpriseService.getCheckingList(sessionInfo.getAgentId(), pageBean));
	}

	// 获取企业审核信息
	@GetMapping("/checking/{enterprId}")
	public Object getCheckingInfo(@PathVariable Long enterprId) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		EnterprCheckingRecorder checkingInfo = enterpriseService.getCheckingInfo(sessionInfo.getAgentId(), enterprId);
		if (checkingInfo == null) {
			return Response.fail("记录不存在!");
		}
		return Response.succ(checkingInfo);
	}

	// 删除审批不通过的记录数据
	@GetMapping("/checking/{enterprId}/delete")
	public Object deleteUnpassedCheckingInfo(@PathVariable Long enterprId) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		enterpriseService.deleteUnpassedCheckingInfo(sessionInfo.getAgentId(), enterprId);
		return Response.succ();
	}

	// 企业信息查重
	@PostMapping("/uniquecheck")
	public Object enterpriseUniqueInfoCheck(@Validated @RequestBody EnterpriseUniqueInfoBean enterpriseUniqueInfoBean) {
		enterpriseService.enterpriseUniqueInfoCheck(enterpriseUniqueInfoBean);
		return Response.succ();
	}

	// 获取企业详情
	@GetMapping("/{enterprId}")
	public Object getEnterpriseInfo(@PathVariable Long enterprId) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		EnterprUserInfoBean enterpriseInfo = enterpriseService.getEnterpriseInfoBean(enterprId);
		if (enterpriseInfo == null || !sessionInfo.getAgentId().equals(enterpriseInfo.getAgentId())) {
			return Response.fail("该企业不存在!");
		}
		enterpriseInfo.setLoginName(null);
		return Response.succ(enterpriseInfo);
	}
}
