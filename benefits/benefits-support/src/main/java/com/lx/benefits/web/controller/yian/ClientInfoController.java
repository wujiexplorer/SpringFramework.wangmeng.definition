package com.lx.benefits.web.controller.yian;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.apollo.starter.web.utils.PageResult;
import com.lx.benefits.bean.base.dto.ClientInfoDto;
import com.lx.benefits.bean.entity.ent.ClientOrderCondition;
import com.lx.benefits.bean.entity.ent.ClientOrderCondition.Criteria;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.yianapi.ClientInfoService;
import com.lx.benefits.service.yianapi.ClientOrderService;
import com.lx.benefits.web.controller.base.BaseAdminController;

@RestController
@RequestMapping("/admin/client")
public class ClientInfoController extends BaseAdminController {

	@Autowired
	private ClientInfoService clientInfoService;
	@Autowired
	private ClientOrderService clientOrderService;

	// 获取第三方列表
	@RequestMapping("/clientlist")
	public PageResult<?> getClientInfos(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "clientName", required = false) String clientName) {
		return clientInfoService.getClientInfosByName(pageNo, pageSize, clientName);
	}

	// 添加第三方平台
	@PostMapping("/add")
	public JSONObject addClientInfo(@Valid @RequestBody ClientInfoDto clientInfo) {
		clientInfoService.addClientInfo(clientInfo);
		return Response.succ();
	}

	// 更新第三方平台
	@PostMapping("/update/{id}")
	public JSONObject updateClientInfo(@PathVariable Long id, @Valid @RequestBody ClientInfoDto clientInfo) {
		clientInfoService.updateClientInfoById(id, clientInfo);
		return Response.succ();
	}

	// 获取第三方订单列表
	@RequestMapping("/orderlist")
	public PageResult<?> getOrderList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, // 页码
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, // 每页大小
			@RequestParam(value = "code", required = false) String code, // 我方订单号
			@RequestParam(value = "oid", required = false) String oid, // 第三方订单号
			@RequestParam(value = "orgCode", required = false) String orgCode, // 公司缩写
			@RequestParam(value = "memberId", required = false) String memberId, // 我方用户ID
			@RequestParam(value = "eeno", required = false) String eeno, // 员工工号
			@RequestParam(value = "status", required = false) Byte status// 订单状态
	) {
		ClientOrderCondition clientOrderCondition = new ClientOrderCondition();
		Criteria criteria = clientOrderCondition.createCriteria();
		if (!StringUtils.isEmpty(code)) {
			if (!code.trim().matches("\\d+")) {
				return PageResult.EMPTY;
			}
			criteria.andCodeEqualTo(Long.valueOf(code.trim()));
		}
		if (!StringUtils.isEmpty(memberId)) {
			if (!memberId.trim().matches("\\d+")) {
				return PageResult.EMPTY;
			}
			criteria.andMemberIdEqualTo(Long.valueOf(memberId.trim()));
		}
		if (oid != null && (oid = oid.trim()).length() > 0) {
			criteria.andOidEqualTo(oid);
		}
		if (orgCode != null && (orgCode = orgCode.trim()).length() > 0) {
			criteria.andOrgCodeEqualTo(orgCode);
		}
		if (eeno != null && (eeno = eeno.trim()).length() > 0) {
			criteria.andEeNoEqualTo(eeno);
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		return clientOrderService.getOrderListByExample(pageNo, pageSize, clientOrderCondition);
	}

}