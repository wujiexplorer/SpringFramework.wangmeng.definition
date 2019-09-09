package com.lx.benefits.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.TokenRequestBean;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.client.ClientAccessToken;
import com.lx.benefits.service.client.ClientService;

@RestController
@RequestMapping("/client/api/oauth2")
public class ClientAuthController {

	@Autowired
	private ClientService clientService;

	// 获取Access Token信息
	@PostMapping("/accessToken")
	public Object getAccessToken(@Validated @RequestBody TokenRequestBean request) {
		if (!request.signCheck()) {
			return Response.fail("数据签名错误!");
		}
		ClientAccessToken clientAccessToken = clientService.getAccessToken(request.getClientId(), request.getClientSecret());
		if (clientAccessToken == null) {
			return Response.fail("clientId和clientSecret不匹配!");
		}
		return Response.succ(clientAccessToken);
	}
}
