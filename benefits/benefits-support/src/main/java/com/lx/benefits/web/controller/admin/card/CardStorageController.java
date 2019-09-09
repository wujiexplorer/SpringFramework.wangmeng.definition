package com.lx.benefits.web.controller.admin.card;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.card.CardAmount;
import com.lx.benefits.bean.enums.card.MemberCardStatus;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.card.CardStorageBean;
import com.lx.benefits.bean.vo.card.CardStorageStatistics;
import com.lx.benefits.service.card.MemberCardService;

import lombok.extern.slf4j.Slf4j;

/**
 * 会员卡仓库中心（会员卡制卡流程）
 * 
 */
@RestController
@RequestMapping("/admin/cards")
@Slf4j
public class CardStorageController {
	@Autowired
	private MemberCardService memberCardService;

	// 3.1_获取会员卡面值列表
	@GetMapping("/amounts")
	public Object getCardAmounts() {
		List<CardAmount> cardAmounts = memberCardService.getCardAmounts();
		return Response.succ(cardAmounts);
	}

	// 3.2获取会员卡仓库统计
	@GetMapping("/storage/statistics")
	public Object getCardStatistics() {
		CardStorageStatistics result = memberCardService.getStorageStatistics(MemberCardStatus.STORED);
		return Response.succ(result);
	}

	// 3.3获取会员卡库存明细
	@GetMapping("/storage/storeInfo")
	public Object getCardStoreInfo() {
		CardStorageStatistics result = memberCardService.getCardStoreInfo(MemberCardStatus.STORED);
		return Response.succ(result);
	}

	// 3.3.2_获取会员卡库存中心列表
	@GetMapping("/storage/storedCards")
	public Object getStoredCards(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
			String cardNumber, PageBean pageBean) {
		PageResultBean<CardStorageBean> result = memberCardService.getStoredCards(startTime, endTime, cardNumber, pageBean);
		return Response.succ(result);
	}

	// 3.4_获取会员卡生产批次列表
	@GetMapping("/batches")
	public Object getCardBatches(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
			Integer status, PageBean pageBean) {
		return Response.succ(memberCardService.getCardBatches(startTime, endTime, status, pageBean));
	}

	// 3.5_获取批次制卡信息详情
	@GetMapping("/batches/{batchId}")
	public Object getCardBatchInfo(@PathVariable Integer batchId) {
		return Response.succ(memberCardService.getCardBatchInfo(batchId));
	}

	// 3.6_获取批次中卡片列表信息
	@GetMapping("/batches/{batchId}/cardlist")
	public Object getBatchCardList(@PathVariable Integer batchId, String cardNumber, PageBean pageBean) {
		return Response.succ(memberCardService.getCardlistByBatchId(batchId, cardNumber, false, pageBean));
	}

	// 3.7_开始制卡，并返回制卡列表信息
	@GetMapping("/batches/{batchId}/making")
	public void makingBatchCards(@PathVariable Integer batchId, HttpServletResponse response) throws IOException {
		String filename = "会员卡列表信息.xlsx";
		OutputStream outputStream = response.getOutputStream();
		try {
			response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE);
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO-8859-1"));
			memberCardService.makingBatchCards(batchId, outputStream);
		} catch (BusinessException e) {
			response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
			response.setHeader("Content-Disposition", "");
			String failMessage = Response.fail(e.getMessage()).toString();
			log.error(failMessage);
			response.getOutputStream().write(failMessage.getBytes("UTF-8"));
			response.setStatus(HttpStatus.OK.value());
		}
	}

	// 3.8_会员卡批次入库操作
	@GetMapping("/batches/{batchId}/store")
	public Object storeCards(@PathVariable Integer batchId) {
		memberCardService.storeCards(batchId);
		return Response.succ();
	}

	// 3.9_会员卡批次发卡操作
	@GetMapping("/batches/{batchId}/deliver")
	public Object deliverCards(@PathVariable Integer batchId) {
		memberCardService.deliverCardsByBatchId(batchId);
		return Response.succ();
	}

}
