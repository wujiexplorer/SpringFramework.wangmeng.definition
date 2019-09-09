package com.lx.benefits.web.controller.common;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.category.CategoryReqDto;
import com.lx.benefits.bean.dto.admin.category.CategoryResDto;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.product.CategoryTree;
import com.lx.benefits.service.product.CategoryService;
import com.lx.benefits.web.util.Query;

@RestController
@RequestMapping({ "/admin/category", "/supplieradmin/category" })
public class CategoryQueryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/queryFirst", method = RequestMethod.POST)
	public JSONObject queryFirst(@RequestBody CategoryReqDto req) {
		JSONObject jsonObject = new JSONObject();
		// 一级分类
		Map<String, Object> params = JSON.parseObject(JSON.toJSONString(req));
		List<CategoryResDto> list = categoryService.queryByParam(params).stream().map(x -> Beans.convert(x, CategoryResDto.class)).collect(toList());
		jsonObject.put("list", list);
		return Response.succ(jsonObject);
	}

	@RequestMapping(value = "/queryPage", method = RequestMethod.POST)
	public JSONObject queryPage(@RequestBody Map<String, Object> params) {
		JSONObject jsonObject = new JSONObject();
		Query query = new Query(params);
		params.put("orderByClause", "created_time");

		int count = categoryService.selectCount(query);
		List<CategoryResDto> list = new ArrayList<>();
		if (count > 0) {
			list = categoryService.selectPageList(query).stream().map(x -> Beans.convert(x, CategoryResDto.class)).collect(toList());
		}
		jsonObject.put("list", list);
		jsonObject.put("count", count);
		return Response.succ(jsonObject);
	}

	@RequestMapping(value = "/querySec", method = RequestMethod.POST)
	public JSONObject querySec(@RequestBody CategoryReqDto req) {
		JSONObject jsonObject = new JSONObject();
		// 二级分类
		Map<String, Object> params = JSON.parseObject(JSON.toJSONString(req));
		List<CategoryResDto> list = categoryService.queryByParam(params).stream().map(x -> Beans.convert(x, CategoryResDto.class)).collect(toList());
		jsonObject.put("list", list);
		return Response.succ(jsonObject);
	}
	
	@GetMapping("/tree")
	public Object getCategoryTree() {
		List<CategoryTree> categoryTrees=categoryService.getCategoryTrees();
		return Response.succ(categoryTrees);
	}
}
