package com.lx.benefits.web.controller.enterpriseadmin;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.LoginReqDto;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.JsonUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionEnterpriseInfo;
import com.lx.benefits.constant.PlatformConstatnts;
import com.lx.benefits.service.enterprise.EnterpriseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author unknow on 2018-12-04 23:06.
 */
@Api(tags = "企业后台-管理员用户模块")
@RestController("enterpriseadminAccountController")
@RequestMapping("/enterpriseadmin/account")
public class AccountAdminController extends BaseEnterpriseAdminController {

	@Autowired
	private EnterpriseService enterpriseService;

	@ApiOperation(value = "企业用户登录", response = SessionEnterpriseInfo.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject login(@ApiParam(value = "Request", name = "req") @RequestBody LoginReqDto req,
			HttpServletResponse httpServletResponse) {
		String loginName = req.getLoginName();
		EnterprUserInfo enterprUserInfo = enterpriseService.login(loginName, req.getPassword());
		SessionEnterpriseInfo sessionEnterpriseInfo = new SessionEnterpriseInfo();
		sessionEnterpriseInfo.setEnterprId(enterprUserInfo.getEnterprId());
		sessionEnterpriseInfo.setLoginName(loginName);
		sessionEnterpriseInfo.setEnterprName(enterprUserInfo.getEnterprName());

		String tokenSecret = EncryptUtil.encryptByMd5(UUID.randomUUID().toString());
		String token = EncryptUtil.aesEncrypt(JsonUtils.getJsonString(sessionEnterpriseInfo), tokenSecret);
		if (null == token || token.isEmpty()) {
			return Response.fail("生成token信息失败,请联系网站管理员!");
		}
		String viewToken = EncryptUtil.encodeToken(token, tokenSecret);

		// 写header头信息
		httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
		httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
		httpServletResponse.setHeader(PlatformConstatnts.ENTERPRISE_TOKEN, viewToken);
		httpServletResponse.setHeader(PlatformConstatnts.ENTERPRISE_NAME, loginName);

		return Response.succ(sessionEnterpriseInfo);
	}

	@ApiOperation(value = "企业用户退出登录", response = Boolean.class)
	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public JSONObject loginOut(HttpServletResponse httpServletResponse) {
		SessionContextHolder.clearSessionInfo();
		httpServletResponse.setHeader("Access-Control-Expose-Headers", "");
		httpServletResponse.setHeader(PlatformConstatnts.ENTERPRISE_TOKEN, "");
		httpServletResponse.setHeader(PlatformConstatnts.ENTERPRISE_NAME, "");
		return Response.succ(true);
	}

	@ApiOperation(value = "管理员修改当前登录密码", response = Boolean.class)
	@RequestMapping(value = "/modifypassword", method = RequestMethod.POST)
	public JSONObject modifyPassword(@ApiParam(value = "Request", name = "req") @RequestBody ModifyPasswordReqDto req) {
		SessionEnterpriseInfo sessionEnterpriseInfo = SessionContextHolder.getSessionEnterpriseInfo();
		enterpriseService.modifyPassword(sessionEnterpriseInfo.getEnterprId(), req);
		return Response.succ("密码修改成功");
	}
}
