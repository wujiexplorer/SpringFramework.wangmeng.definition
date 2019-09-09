package com.lx.benefits.web.controller.admin.card;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.card.CardSaleOrderDetail;
import com.lx.benefits.service.card.CardSaleService;

/**
 * 会员卡销售流程
 * 
 */
@RestController
@RequestMapping("/admin/cards/sale")
public class CardSaleController {

	@Autowired
	private CardSaleService cardSaleService;

	// 1.1_会员卡销售开单
	@PostMapping("/orders")
	public Object createSaleOrders(@Validated @RequestBody CardSaleOrderDetail cardSaleOrderBean) {
		cardSaleService.createSaleOrder(cardSaleOrderBean);
		return Response.succ();
	}

	// 1.2_获取会员卡销售开单列表
	@GetMapping("/orders")
	public Object getSaleOrders(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
			Long customerId, Integer status, PageBean pageBean) {
		PageResultBean<CardSaleOrderDetail> pageResult = cardSaleService.getSaleOrdersByPage(startTime, endTime, customerId, status, pageBean);
		return Response.succ(pageResult);
	}

	// 1.3_获取会员卡销售开单详情
	@GetMapping("/orders/{saleOrderId}")
	public Object getSaleOrderDetail(@PathVariable Integer saleOrderId) {
		CardSaleOrderDetail cardSaleOrderBean = cardSaleService.getSaleOrderDetail(saleOrderId);
		if (cardSaleOrderBean == null) {
			return Response.fail("该订单信息不存在!");
		}
		return Response.succ(cardSaleOrderBean);
	}

	// 1.4_会员卡销售订单删除（只限于审核不通过的）
	@GetMapping("orders/{saleOrderId}/delete")
	public Object deleteUnpassedSaleOrder(@PathVariable Integer saleOrderId) {
		int count = cardSaleService.deleteUnpassedSaleOrder(saleOrderId);
		if (count == 0) {
			return Response.fail("删除失败!");
		}
		return Response.succ();
	}

}
