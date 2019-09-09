package com.lx.benefits.web.controller.customeradmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.card.CardStorage;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.card.MemberCardService;

@RestController("customerCardsController")
@RequestMapping("/customeradmin/cards")
public class CustomerCardsController {

	@Autowired
	private MemberCardService memberCardService;

	// 客户会员卡明细
	@GetMapping("/cardDetail")
	public Object cardDetail(@RequestAttribute("customerId") Long customerId, Integer status, PageBean pageBean) {
		PageResultBean<CardStorage> result = memberCardService.getCustomerCardUseInfo(customerId, status, pageBean);
		return Response.succ(result);
	}
}
