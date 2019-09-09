package com.lx.benefits.web.controller.admin;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.constants.RegisterType;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprCheckingRecorder;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.JsonUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionEnterpriseInfo;
import com.lx.benefits.bean.util.SessionFuliInfo;
import com.lx.benefits.bean.vo.agent.EnterpriseBindInfoBean;
import com.lx.benefits.bean.vo.enterpr.EnterprUserInfoBean;
import com.lx.benefits.bean.vo.enterpr.EnterpriseCheckingBean;
import com.lx.benefits.service.enterprise.EnterpriseService;

@RestController
@RequestMapping("/admin/enterprises")
public class AdminEnterpriseController {

	@Autowired
	private EnterpriseService enterpriseService;

	// 获取客户列表
	@GetMapping("")
	public Object getEnterprises(@RequestParam(required = false) String agentName, @RequestParam(required = false) String enterprName, PageBean pageBean) {
		return Response.succ(enterpriseService.getEnterprises(agentName, enterprName, pageBean));
	}

	// 获取企业详情
	@GetMapping("/{enterprId}")
	public Object getEnterpriseInfo(@PathVariable Long enterprId) {
		EnterprUserInfoBean enterpriseInfoBean = enterpriseService.getEnterpriseInfoBean(enterprId);
		if (enterpriseInfoBean == null) {
			return Response.fail("该企业不存在!");
		}
		return Response.succ(enterpriseInfoBean);
	}

	// 获取企业详情
	@GetMapping("/{enterprId}/bindrecorders")
	public Object getEnterpriseBindrecorders(@PathVariable Long enterprId, PageBean pageBean) {
		return Response.succ(enterpriseService.getEnterpriseBindRecorders(enterprId, pageBean));
	}

	// 免密登录
	@GetMapping("/{enterprId}/authlogin")
	public Object authLogin(@PathVariable Long enterprId, HttpServletResponse response) {
		EnterprUserInfo enterprise = enterpriseService.getEnterprise(enterprId);
		if (enterprise == null) {
			return Response.fail("该企业不存在!");
		}
		SessionEnterpriseInfo sessionEnterpriseInfo = new SessionEnterpriseInfo();
		sessionEnterpriseInfo.setEnterprId(enterprId);
		sessionEnterpriseInfo.setLoginName(enterprise.getLoginName());
		sessionEnterpriseInfo.setEnterprName(enterprise.getEnterprName());
		String tokenSecret = EncryptUtil.encryptByMd5(UUID.randomUUID().toString());
		String token = EncryptUtil.aesEncrypt(JsonUtils.getJsonString(sessionEnterpriseInfo), tokenSecret);
		if (null == token || token.isEmpty()) {
			return Response.fail("生成token信息失败,请联系网站管理员!");
		}
		String viewToken = EncryptUtil.encodeToken(token, tokenSecret);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("enterprId", enterprId);
		jsonObject.put("loginName", enterprise.getLoginName());
		jsonObject.put("enterprName", enterprise.getEnterprName());
		jsonObject.put("enterprise-cache", viewToken);
		return Response.succ(jsonObject);
	}

	// 员工总览
	@GetMapping("/{enterprId}/overview")
	public Object getEnterpriseOverview(@PathVariable Long enterprId) {
		return Response.succ(enterpriseService.getEnterpriseOverview(enterprId));
	}

	// 修改企业积分策略
	@PostMapping("/{enterprId}/creditstrategy")
	public Object updateCreditstrategy(@PathVariable Long enterprId, @RequestBody EnterprUserInfo enterprUserInfo) {
		enterpriseService.updateCreditstrategy(enterprId, enterprUserInfo.getLeaveCredit());
		return Response.succ();
	}

	// 获取企业员工列表
	@GetMapping("/{enterprId}/employees")
	public Object getEnterpriseEmployees(@PathVariable Long enterprId, @RequestParam(required = false) String employeeNo,
			@RequestParam(required = false) String employeeName, @RequestParam(required = false) Integer leaveStatus, PageBean pageBean) {
		return Response.succ(enterpriseService.getEnterpriseEmployees(enterprId, employeeNo, employeeName, leaveStatus, pageBean));
	}

	// 重置企业密码
	@PostMapping("/{enterprId}/password")
	public Object resetPassword(@PathVariable Long enterprId, @RequestBody EnterprUserInfo request) {
		enterpriseService.resetPassword(enterprId, request.getPassword());
		return Response.succ();
	}

	// 修改企业联系人信息
	@PostMapping("/{enterprId}/contactinfo/update")
	public Object updateContactinfo(@PathVariable Long enterprId, @RequestBody EnterprUserInfo request) {
		enterpriseService.updateContactinfo(enterprId, request);
		return Response.succ();
	}

	// 修改企业所属代理商
	@PostMapping("/{enterprId}/bindinfo/update")
	public Object updateEnterpriseBindInfo(@PathVariable Long enterprId, @RequestBody EnterpriseBindInfoBean request) {
		SessionFuliInfo sessionInfo = SessionContextHolder.getSessionFuliInfo();
		enterpriseService.updateEnterpriseBindInfo(enterprId, request, sessionInfo.getLoginName());
		return Response.succ();
	}

	// 添加企业信息(内部开设)
	@PostMapping("/add")
	public Object addEnterprise(@Validated @RequestBody EnterprCheckingRecorder request) {
		SessionFuliInfo sessionInfo = SessionContextHolder.getSessionFuliInfo();
		enterpriseService.addEnterprise(request, RegisterType.INTERNAL_OPENING, sessionInfo.getLoginName(), null);
		return Response.succ();
	}

	// 获取审批/待审批列表
	@GetMapping("/checking/list")
	public Object getCheckingList(@RequestParam(defaultValue = "0") Integer checked, @RequestParam(required = false) String enterprName,
			@RequestParam(required = false) Integer checkStatus, PageBean pageBean) {
		return Response.succ(enterpriseService.getCheckingList(checked, enterprName, checkStatus, pageBean));
	}

	// 查看企业审核信息
	@GetMapping("/checking/{enterprId}")
	public Object getCheckingInfo(@PathVariable Long enterprId) {
		EnterpriseCheckingBean checkingInfo = enterpriseService.getCheckingInfo(enterprId);
		if (checkingInfo == null) {
			return Response.fail("该记录不存在!");
		}
		return Response.succ(checkingInfo);
	}

	// 对企业进行审批
	@PostMapping("/checking/{enterprId}")
	public Object checkingEnterprise(@PathVariable Long enterprId, @RequestBody EnterpriseCheckingBean checkingBean) {
		SessionFuliInfo sessionInfo = SessionContextHolder.getSessionFuliInfo();
		enterpriseService.checkingEnterprise(enterprId, checkingBean, sessionInfo.getLoginName());
		return Response.succ();
	}

	// 冻结/解冻企业帐户
	@PostMapping("/{enterprId}/accountStatus")
	public Object updateEnterpriseAccountStatus(@PathVariable Long enterprId, @RequestBody EnterprUserInfo request) {
		enterpriseService.updateEnterpriseAccountStatus(enterprId, request.getAccountStatus());
		return Response.succ();
	}

}
