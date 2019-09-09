package com.lx.benefits.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.config.properties.YibaoProperties;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.web.attributes.FuliRequestAttributes;

@RestController
@RequestMapping("/client/api/finance")
public class ClientFinanceController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private YibaoProperties yibaoProperties;

	// 支付级订单财务对账（时间区间3个月之内，默认获取当天账单信息）
	@GetMapping("/orderCheck")
	public Object orderFinanceCheck(PageBean pageBean, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
			@RequestAttribute(name = FuliRequestAttributes.CLIENT_INFO_ORGCODE) String clientOrgCode) {
		if (yibaoProperties.getOrgCode().equals(clientOrgCode)) {
			Object pageResult = orderService.orderFinanceCheck(yibaoProperties.getEnterprId(), pageBean, startTime, endTime);
			return Response.succ(pageResult);
		}
		return Response.fail("非法请求!");
	}

}
