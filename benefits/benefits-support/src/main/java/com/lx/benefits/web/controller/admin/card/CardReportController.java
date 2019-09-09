package com.lx.benefits.web.controller.admin.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.card.CardSaleOrderDetail;
import com.lx.benefits.bean.vo.card.CardStorageBean;
import com.lx.benefits.bean.vo.card.CustomerOrderReport;
import com.lx.benefits.bean.vo.card.MemberCardSearchBean;
import com.lx.benefits.service.card.CardSaleService;
import com.lx.benefits.service.card.MemberCardService;

/**
 * 会员卡报表
 * 
 */
@RestController
@RequestMapping("/admin/cards/report")
public class CardReportController {

	@Autowired
	private MemberCardService memberCardService;
	@Autowired
	private CardSaleService cardSaleService;

	// 4.1_获取会员卡报表统计
	@GetMapping("/stastic")
	public Object getCardStasticReport() {
		return Response.succ(memberCardService.getCardStasticReport());
	}

	// 4.2_获取会员卡客户详情报表
	@GetMapping("/customerInfo")
	public Object getCustomerInfos(Long customerId, PageBean pageBean) {
		PageResultBean<CustomerOrderReport> result = cardSaleService.getCustomerInfos(customerId, pageBean);
		return Response.succ(result);
	}

	// 4.3_获取客户已完成状态的订单列表
	@GetMapping("/customerInfo/{customerId}/orders")
	public Object getCustomerCompleteOrderInfos(@PathVariable Long customerId, PageBean pageBean) {
		PageResultBean<CardSaleOrderDetail> result = cardSaleService.getCustomerCompleteOrderInfos(customerId, pageBean);
		return Response.succ(result);
	}

	// 4.4_获取已发卡卡片使用情况
	@GetMapping("/cardUsedInfo")
	public Object getCardUsedInfo(MemberCardSearchBean searchBean, PageBean pageBean) {
		PageResultBean<CardStorageBean> result = memberCardService.getCardUsedInfo(searchBean, pageBean);
		return Response.succ(result);
	}
}
