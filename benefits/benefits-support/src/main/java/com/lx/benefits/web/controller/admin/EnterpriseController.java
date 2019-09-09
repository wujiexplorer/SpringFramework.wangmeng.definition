package com.lx.benefits.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.enterprise.EnterpriseInfoDto;
import com.lx.benefits.bean.dto.admin.enterprise.EnterpriseQueryReqDto;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.enterpr.EnterprUserInfoBean;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.web.controller.base.BaseAdminController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "运营后台-企业客户管理模块")
@RestController
@RequestMapping("/admin/enterprise")
public class EnterpriseController extends BaseAdminController {

	@Autowired
	private EnterpriseService enterpriseService;

	@ApiOperation(value = "企业客户信息列表", response = EnterpriseInfoDto.class)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(@ApiParam(value = "Request", name = "req") @ModelAttribute EnterpriseQueryReqDto req) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(req.getPage());
		pageBean.setPageSize(req.getPageSize());
		PageResultBean<EnterprUserInfoBean> enterprises = enterpriseService.getEnterprises(null, req.getEnterpriseName(), pageBean);
		return Response.succ(enterprises);
	}
}