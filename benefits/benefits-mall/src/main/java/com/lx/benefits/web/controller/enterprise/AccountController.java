package com.lx.benefits.web.controller.enterprise;

import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apollo.common.exception.BusinessException;
import com.apollo.starter.web.utils.Result;
import com.lx.benefits.bean.base.dto.LoginReqDto;
import com.lx.benefits.bean.dto.admin.account.ClientUserLoginReqDto;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.dto.wechat.Code2SessionDTO;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.JsonUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.constant.PlatformConstatnts;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.user.WxUserOpenIdService;
import com.lx.benefits.web.support.WeChatSupport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author unknow on 2018-12-10 23:48.
 */
@Api(tags = "企业商城-用户模块")
@RestController("enterpriseAccountController")
@RequestMapping("/enterprise/account")
public class AccountController extends BaseEnterpriseController {

	@Autowired
	private EmployeeUserInfoService employeeUserInfoService;
	@Autowired
	private WxUserOpenIdService wxUserOpenIdService;
	@Autowired
	private WeChatSupport weChatSupport;

	@ApiOperation(value = "企业员工登录", response = SessionShopInfo.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@ApiParam(value = "Request", name = "req") @RequestBody LoginReqDto req, HttpServletResponse httpServletResponse) {
		if (null == req) {
			return Response.fail("员工登录信息不能为空!");
		}
		String loginName = req.getLoginName();
		String password = req.getPassword();
		if (loginName == null || (loginName = loginName.trim()).isEmpty()) {
			return Response.fail("员工登录用户名不能为空!");
		}
		if (password == null || (password = password.trim()).isEmpty()) {
			return Response.fail("员工登录密码不能为空!");
		}
		String wxOpenId = null;
		if (!StringUtils.isEmpty(req.getCode())) {
			Code2SessionDTO code2SessionDTO = weChatSupport.code2Session(req.getCode());
			if (code2SessionDTO != null) {
				wxOpenId = code2SessionDTO.getOpenId();
			}
		}
		SessionShopInfo sessionShopInfo = employeeUserInfoService.login(loginName, password, wxOpenId);
		if (sessionShopInfo == null) {
			return Response.fail("用户名或者密码错误,请重新登录!");
		}
		String tokenSecret = EncryptUtil.encryptByMd5(UUID.randomUUID().toString());
		String token = EncryptUtil.aesEncrypt(JsonUtils.getJsonString(sessionShopInfo), tokenSecret);
		String viewToken = EncryptUtil.encodeToken(token, tokenSecret);
		// 写header头信息
		httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
		httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
		httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_TOKEN, viewToken);
		httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_NAME, loginName);
		return Response.succ(sessionShopInfo);
	}

	@ApiOperation(value = "企业员工退出登录", response = Boolean.class)
	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public Object loginout(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
		httpServletResponse.setHeader("Access-Control-Expose-Headers", "");
		httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_NAME, "");
		httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_TOKEN, "");
		return Response.succ(true);
	}

	@ApiOperation(value = "企业员工登录", response = SessionShopInfo.class)
	@RequestMapping(value = "/wx/login", method = RequestMethod.POST)
	public Object login(@RequestParam(name = "code") String code, HttpServletResponse httpServletResponse) {
		Code2SessionDTO code2SessionDTO = weChatSupport.code2Session(code);
		if (Objects.isNull(code2SessionDTO)) {
			throw new BusinessException("请登录");
		}
		SessionShopInfo sessionShopInfo = employeeUserInfoService.wxLogin(code2SessionDTO.getOpenId());
		if (sessionShopInfo == null) {
			return Response.fail("该用户未注册!");
		}
		String tokenSecret = EncryptUtil.encryptByMd5(UUID.randomUUID().toString());
		String token = EncryptUtil.aesEncrypt(JsonUtils.getJsonString(sessionShopInfo), tokenSecret);
		String viewToken = EncryptUtil.encodeToken(token, tokenSecret);
		// 写header头信息
		httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
		httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
		httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_TOKEN, viewToken);
		httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_NAME, sessionShopInfo.getLoginName());
		return Result.wrapDefaultSuccessResult(sessionShopInfo);
	}

	@ApiOperation(value = "企业员工解绑", response = Boolean.class)
	@RequestMapping(value = "/wx/untying", method = { RequestMethod.PUT, RequestMethod.POST })
	public Object deleteByUserId(@RequestParam(required = false) String code) {
		if (!StringUtils.isEmpty(code)) {
			Code2SessionDTO code2SessionDTO = weChatSupport.code2Session(code);
			if (Objects.nonNull(code2SessionDTO)) {
				wxUserOpenIdService.unbindUserByOpenId(code2SessionDTO.getOpenId());
				return Result.wrapDefaultSuccessResult(true);
			}
		}
		Long userId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
		wxUserOpenIdService.unbindUserByUserId(userId);
		return Result.wrapDefaultSuccessResult(true);
	}

	@ApiOperation(value = "福粒商城修改当前登录密码", response = Boolean.class)
	@RequestMapping(value = "/modifypassword", method = RequestMethod.POST)
	public Object modifyPassword(@ApiParam(value = "Request", name = "req") @RequestBody ModifyPasswordReqDto req) {
		int modifyPassword = employeeUserInfoService.modifyPassword(req);
		if (modifyPassword > 0) {
			return Response.succ("密码修改成功");
		} else {
			return Response.fail("密码修改失败");
		}
	}

	@RequestMapping(value = "/clientlogin", method = RequestMethod.POST)
	public Object clientUserLogin(@Validated @RequestBody ClientUserLoginReqDto req,HttpServletResponse httpServletResponse) {
		SessionShopInfo sessionShopInfo = employeeUserInfoService.clientUserlogin(req.getToken(), req.getOrgCode());
		if (sessionShopInfo == null) {
			return Response.fail("登录失败!");
		}
		String tokenSecret = EncryptUtil.encryptByMd5(UUID.randomUUID().toString());
		String token = EncryptUtil.aesEncrypt(JsonUtils.getJsonString(sessionShopInfo), tokenSecret);
		String viewToken = EncryptUtil.encodeToken(token, tokenSecret);
		// 写header头信息
		httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
		httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
		httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_TOKEN, viewToken);
		httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_NAME, sessionShopInfo.getLoginName());
		return Result.wrapDefaultSuccessResult(sessionShopInfo);
	}
}
