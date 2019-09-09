package com.lx.benefits.web.controller.agentadmin;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.LoginReqDto;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.JsonUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionAgentInfo;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.constant.PlatformConstatnts;
import com.lx.benefits.service.agent.AgentUserService;

@RestController("agentAcountController")
@RequestMapping("/agentadmin/account")
public class AgentAcountController {
	Logger logger = LoggerFactory.getLogger(AgentAcountController.class);

	@Autowired
	private AgentUserService agentUserService;

	@PostMapping("/login")
	public Object login(@RequestBody LoginReqDto req, HttpServletResponse response) {
		SessionAgentInfo sessionAgentInfo = agentUserService.login(req.getLoginName(), req.getPassword());
		logger.info("SessionFuliInfo :{}", sessionAgentInfo);
		String secret = EncryptUtil.encryptByMd5(UUID.randomUUID().toString());
		String token = EncryptUtil.aesEncrypt(JsonUtils.getJsonString(sessionAgentInfo), secret);
		if (null == token || token.isEmpty()) {
			return Response.fail("生成token信息失败,请联系网站管理员!");
		}
		String viewToken = EncryptUtil.encodeToken(token, secret);
		// 写header头信息
		response.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
		response.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
		response.setHeader(PlatformConstatnts.AGENT_TOKEN, viewToken);
		response.setHeader(PlatformConstatnts.AGENT_NAME, sessionAgentInfo.getLoginName());
		return Response.succ(sessionAgentInfo);
	}

	@GetMapping("/loginout")
	public Object loginout(HttpServletResponse response) {
		try {
			response.setHeader("Access-Control-Expose-Headers", "");
			response.setHeader(PlatformConstatnts.AGENT_TOKEN, "");
			response.setHeader(PlatformConstatnts.AGENT_NAME, "");
			return Response.succ(true);
		} catch (Exception e) {
			logger.error("系统异常退出登录失败", e);
			return Response.fail("系统异常退出登录失败!");
		}
	}

	@PostMapping("/modifypassword")
	public Object modifyPassword(@RequestBody ModifyPasswordReqDto req) {
		SessionAgentInfo sessionInfo = SessionContextHolder.getSessionAgentInfo();
		agentUserService.modifyPassword(sessionInfo.getLoginName(), req.getOldPassword(), req.getPassword());
		return Response.succ();
	}
}
