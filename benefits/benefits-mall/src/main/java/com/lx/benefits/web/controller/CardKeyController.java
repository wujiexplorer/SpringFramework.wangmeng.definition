package com.lx.benefits.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorage;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.cardkey.CardKeyVO;
import com.lx.benefits.service.cardkey.CardKeyService;

/**
 * 我的卡密
 **/
@RestController
@RequestMapping("/benefits/cardkey")
public class CardKeyController {
	@Autowired
	private CardKeyService cardKeyService;
	
	//查看卡密
	@GetMapping("/list/{orderNumber}")
	public Object cardKeyList(@PathVariable Long orderNumber, CardKeyVO cardKeyVO, PageBean pageBean) {
		cardKeyVO.setOrderNumber(orderNumber);
		PageResultBean<CardKeyStorage> result = cardKeyService.cardKeyList(null,cardKeyVO,pageBean);
		return Response.succ(result);
	}
}
