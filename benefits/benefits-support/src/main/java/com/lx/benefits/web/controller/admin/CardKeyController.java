package com.lx.benefits.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.ImportReqDto;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorage;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.cardkey.CardKeyVO;
import com.lx.benefits.service.cardkey.CardKeyService;

@RestController
@RequestMapping("/admin/cardkey")
public class CardKeyController {
	@Autowired
	private CardKeyService cardKeyService;
	
	//卡密列表
	@GetMapping("/list/{sku}")
	public Object cardKeyList(@PathVariable Integer sku, CardKeyVO cardKeyVO, PageBean pageBean) {
		PageResultBean<CardKeyStorage> result = cardKeyService.cardKeyList(sku,cardKeyVO,pageBean);
		return Response.succ(result);
	}
	
	//根据sku编码入库
	@PostMapping("/{sku}/import")
	public Object store(@PathVariable Integer sku, @RequestBody ImportReqDto req) {
		try {
			return cardKeyService.store(sku, req);
		} catch (Exception e) {
			return Response.fail("入库失败!");
		}
	}
	
	//作废
	@PostMapping("/invalid/{id}")
	public Object invalid(@PathVariable Integer id, @RequestBody CardKeyStorage cardKeyStorage) {
		return cardKeyService.invalid(id,cardKeyStorage);
	}

}
