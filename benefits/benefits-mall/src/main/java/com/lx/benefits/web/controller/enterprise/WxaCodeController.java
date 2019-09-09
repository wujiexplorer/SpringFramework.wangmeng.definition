package com.lx.benefits.web.controller.enterprise;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.wechat.Code2SessionDTO;
import com.lx.benefits.bean.dto.yianapi.client.ClientResult;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.JsonUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.constant.PlatformConstatnts;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.web.support.WeChatSupport;


@RequestMapping("/enterprise/wxacode")
@Controller
public class WxaCodeController {
	@Autowired
 	private StringRedisTemplate stringRedisTemplate;
	@Autowired
    private WeChatSupport weChatSupport;
	@Autowired
	private EmployeeUserInfoService employeeUserInfoService;
 	private final String ENTERPRISE_CODE_KEY = "code_wxacode_%s";
	@ResponseBody
	@GetMapping("/get-user-wxacode.htm")
	public Object getWxaCode() {
		SessionShopInfo sessionEmployeeInfo = SessionContextHolder.getSessionEmployeeInfo();
		if(null == sessionEmployeeInfo) {
			return Response.fail("请先登入");
		}
		Long employeeId = sessionEmployeeInfo.getEmployeeId();
		ResponseEntity<org.springframework.core.io.Resource> wxaCode = weChatSupport.getWxaCode("benefits-"+String.valueOf(employeeId),false);
		if (HttpStatus.OK == wxaCode.getStatusCode() || wxaCode.getHeaders().getContentDisposition() == null) {
			stringRedisTemplate.opsForValue().set(String.format(ENTERPRISE_CODE_KEY, employeeId), String.valueOf(employeeId));
			return wxaCode;
		} else {
			return JsonUtil.convertObjToStr(new ClientResult<>("get_wxacode", "system error", false));
		}
	}
	//微信扫码登入
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@RequestBody EmployeeUserInfo employeeUserInfo,HttpServletResponse httpServletResponse) {
		
		Long employeeId = employeeUserInfo.getEmployeeId();
		String code = stringRedisTemplate.opsForValue().get(String.format(ENTERPRISE_CODE_KEY, employeeId));
		if(StringUtils.isBlank(code)) {
			return Response.fail("二维码已失效,请刷新页面后重试!");
		}
		String wxOpenId = null;
		if (!StringUtils.isEmpty(employeeUserInfo.getCode())) {
			Code2SessionDTO code2SessionDTO = weChatSupport.code2Session(employeeUserInfo.getCode());
			if (code2SessionDTO != null) {
				wxOpenId = code2SessionDTO.getOpenId();
			}
		}
		SessionShopInfo sessionShopInfo = employeeUserInfoService.login(Long.valueOf(code),wxOpenId);
		if (sessionShopInfo == null) {
			return Response.fail("请重新登录!");
		}
		stringRedisTemplate.delete(String.format(ENTERPRISE_CODE_KEY, employeeId));
		String loginName = sessionShopInfo.getLoginName();
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
	
	@GetMapping("/scenecode")
	@ResponseBody
	public Object getCodeByScene() {
		SessionShopInfo sessionEmployeeInfo = SessionContextHolder.getSessionEmployeeInfo();
		if(null == sessionEmployeeInfo) {
			return Response.fail("请先登入");
		}
		Long employeeId = sessionEmployeeInfo.getEmployeeId();
		String code = stringRedisTemplate.opsForValue().get(String.format(ENTERPRISE_CODE_KEY, employeeId));
		if (code == null) {
			return Response.fail("已过期!");
		} else {
			JSONObject result = new JSONObject();
			result.put("code", code);
			return Response.succ(result);
		}
	}
}
