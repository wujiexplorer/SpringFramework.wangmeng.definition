package com.lx.benefits.web.controller.admin.card;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.card.CardPayRecord;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.card.CardSaleOrderDetail;
import com.lx.benefits.bean.vo.card.CardStorageBean;
import com.lx.benefits.bean.vo.card.CardVerifyBean;
import com.lx.benefits.service.card.CardVerifyService;
import com.lx.benefits.service.card.MemberCardService;

import lombok.extern.slf4j.Slf4j;

/**
 * 会员卡审批流程
 * 
 */
@RestController
@RequestMapping("/admin/cards/verify")
@Slf4j
public class CardVerifyController {

	@Autowired
	private CardVerifyService cardVerifyService;
	@Autowired
	private MemberCardService memberCardService;

	// 2.1_获取会员卡审批列表
	@GetMapping("/orders")
	public Object getVerifyOrders(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime, Long customerId, Integer status, PageBean pageBean) {
		PageResultBean<CardSaleOrderDetail> pageResult = cardVerifyService.getVerifyOrdersByPage(startTime, endTime, customerId, status, pageBean);
		return Response.succ(pageResult);
	}

	// 2.2_会员卡销售审核
	@PostMapping("/orders/{saleOrderId}")
	public Object verifySaleOrder(@PathVariable Integer saleOrderId, @Validated @RequestBody CardVerifyBean cardVerifyBean) {
		cardVerifyService.verifySaleOrder(saleOrderId, cardVerifyBean);
		return Response.succ();
	}

	// 2.3_会员卡销售订单回款
	@PostMapping("/orders/{saleOrderId}/pay")
	public Object cardOrderPay(@PathVariable Integer saleOrderId, @Validated @RequestBody CardPayRecord cardPayRecord) {
		cardVerifyService.cardOrderPay(saleOrderId, cardPayRecord);
		return Response.succ();
	}

	// 2.4_获取会员卡回款记录
	@GetMapping("/orders/{saleOrderId}/payrecords")
	public Object getCardOrderPayRecords(@PathVariable Integer saleOrderId, PageBean pageBean) {
		PageResultBean<CardPayRecord> pageResult = cardVerifyService.getCardOrderPayRecords(saleOrderId, pageBean);
		return Response.succ(pageResult);
	}

	// 2.5_销售订单会员卡列表
	@GetMapping("/orders/{saleOrderId}/cardlist")
	public Object getSaleOrderCardList(@PathVariable Integer saleOrderId, PageBean pageBean) {
		PageResultBean<CardStorageBean> pageResult = cardVerifyService.getSaleOrderCardList(saleOrderId, pageBean);
		return Response.succ(pageResult);
	}

	// 2.6_销售订单会员卡列表导出
	@GetMapping("/orders/{saleOrderId}/cardexport")
	public void exportSaleOrderCardlist(@PathVariable Integer saleOrderId, HttpServletResponse response) throws IOException {
		String filename = "会员卡列表信息.xlsx";
		OutputStream outputStream = response.getOutputStream();
		try {
			response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE);
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO-8859-1"));
			cardVerifyService.exportSaleOrderCardlist(saleOrderId, outputStream);
		} catch (BusinessException e) {
			response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
			response.setHeader("Content-Disposition", "");
			String failMessage = Response.fail(e.getMessage()).toString();
			log.error(failMessage);
			response.getOutputStream().write(failMessage.getBytes("UTF-8"));
			response.setStatus(HttpStatus.OK.value());
		}
	}

	// 2.7_会员卡销售订单发卡操作
	@GetMapping("/orders/{saleOrderId}/deliver")
	public Object deliverCards(@PathVariable Integer saleOrderId) {
		memberCardService.deliverCardsBySaleOrderId(saleOrderId);
		return Response.succ();
	}
}
