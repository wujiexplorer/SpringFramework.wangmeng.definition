package com.lx.benefits.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.service.user.UserCollectionService;

/**
 * 收藏
 */
@RestController
@RequestMapping("/benefits/user/collection")
public class UserCollectionController {

	@Resource
	private UserCollectionService userCollectionService;
	@Resource
	private ProductService productService;
	@Resource
	private SkuService skuService;

	@GetMapping("")
	public Object list(PageBean pageBean) {
		Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
		PageResultBean<ProductEntity> result = userCollectionService.listByUserId(accountId, pageBean);
		return Response.succ(result);
	}

	@RequestMapping(value = "/add/{spuCode}", method = { RequestMethod.GET, RequestMethod.POST })
	public Object add(@PathVariable Long spuCode) {
		Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
		userCollectionService.addUserCollection(accountId, spuCode);
		return Response.succ();
	}

	@GetMapping("/check/{spuCode}")
	public Object checkExist(@PathVariable Long spuCode) {
		Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
		boolean checkResult = userCollectionService.checkExist(accountId, spuCode);
		Map<String, Object> response = new HashMap<>(1, 1);
		response.put("checkResult", checkResult);
		return Response.succ(response);
	}

	@PostMapping("/del")
	public Object del(@RequestBody List<Long> spuCodeList) {
		Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
		userCollectionService.deleteUserCollection(accountId, spuCodeList);
		return Response.succ();
	}
}
