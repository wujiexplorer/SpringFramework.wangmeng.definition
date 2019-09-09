package com.lx.benefits.web.controller.customeradmin;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.JsonUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.card.CustomerInfo;
import com.lx.benefits.constant.PlatformConstatnts;
import com.lx.benefits.service.enterprise.EnterpriseService;

@RestController("customerAcountController")
@RequestMapping("/customeradmin/account")
public class CustomerAcountController {
	@Autowired
	private EnterpriseService enterpriseService;

	// 6.1_客户登录
	@PostMapping("/login")
	public Object checkUser(@Validated @RequestBody CustomerInfo request, HttpServletResponse response) {
		EnterprUserInfo enterprUserInfo = enterpriseService.getEnterpriseByName(request.getCustomerName());
		if (enterprUserInfo == null || !request.getMobile().equals(enterprUserInfo.getMobile())) {
			return Response.fail("客户名称或手机号码不正确！");
		}
		CustomerInfo customerInfo = new CustomerInfo(enterprUserInfo.getEnterprId(), enterprUserInfo.getEnterprName());
		String secret = EncryptUtil.encryptByMd5(UUID.randomUUID().toString());
		String token = EncryptUtil.aesEncrypt(JsonUtils.getJsonString(customerInfo), secret);
		if (null == token || token.isEmpty()) {
			return Response.fail("生成token信息失败,请联系网站管理员!");
		}
		String viewToken = EncryptUtil.encodeToken(token, secret);
		// 写header头信息
		response.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
		response.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
		response.setHeader(PlatformConstatnts.CUSTOMER_TOKEN, viewToken);
		response.setHeader(PlatformConstatnts.CUSTOMER_NAME, customerInfo.getCustomerName());
		return Response.succ(customerInfo);

	}

	// 6.2_客户退出登录
	@GetMapping("/loginout")
	public Object loginout(HttpServletResponse response) {
		response.setHeader("Access-Control-Expose-Headers", "");
		response.setHeader(PlatformConstatnts.CUSTOMER_TOKEN, "");
		response.setHeader(PlatformConstatnts.CUSTOMER_NAME, "");
		return Response.succ(true);
	}

}