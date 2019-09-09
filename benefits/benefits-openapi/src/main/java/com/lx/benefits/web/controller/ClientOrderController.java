package com.lx.benefits.web.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.bo.order.ProductOrderDetails;
import com.lx.benefits.bean.dto.deliverinfo.ExpressResult;
import com.lx.benefits.bean.dto.deliverinfo.PackageLocationInfo;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.enums.OrderEnum;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.support.order.OrderDeliveryInfoSupport;

@RestController
@RequestMapping("/client/api/orders")
public class ClientOrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDeliveryInfoSupport orderDeliveryInfoSupport;

	// 获取订单状态
	@GetMapping("/{orderNumber}/status")
	public Object getOrderStatus(@PathVariable Long orderNumber) {
		Order order = orderService.getOrderByNumber(orderNumber);
		Integer orderStatus;
		if (order == null) {
			orderStatus = OrderEnum.STATUS.INIT.getCode();
		} else {
			orderStatus = order.getStatus();
		}
		return Response.succ(Collections.singletonMap("status", orderStatus));
	}

	// 获取支付级订单详情
	@GetMapping("/{orderNumber}/details")
	public Object getOrderDetails(@PathVariable Long orderNumber) {
		ProductOrderDetails productOrderDetails = orderService.getProductOrderDetailsByOrderNumber(orderNumber);
		if (productOrderDetails == null) {
			return Response.fail("订单信息不存在!");
		}
		return Response.succ(productOrderDetails);
	}

	// 获取商品订单物流信息
	@GetMapping("/{orderProductNumber}/deliverInfos")
	public Object getOrderDeliverinfo(@PathVariable Long orderProductNumber) {
		List<ExpressResult<PackageLocationInfo>> orderDeliverInfos = orderDeliveryInfoSupport.getClientOrderDeliverInfo(orderProductNumber);
		return Response.succ(Collections.singletonMap("deliverInfos", orderDeliverInfos));
	}
}
