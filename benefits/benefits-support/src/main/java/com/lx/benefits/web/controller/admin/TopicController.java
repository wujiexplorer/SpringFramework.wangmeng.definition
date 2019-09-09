package com.lx.benefits.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.dto.product.TopicProductBean;
import com.lx.benefits.bean.entity.operation.TopicEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.operation.TopicService;
import com.lx.benefits.service.product.ProductTopicService;
import com.lx.benefits.web.controller.base.BaseAdminController;

/**
 * 【运营】 专题控制层
 * 
 * @author gaosong
 * @date 2019/1/28.
 */
@RestController("topicController")
@RequestMapping("/admin/topic")
public class TopicController extends BaseAdminController {

	@Autowired
	private TopicService topicService;

	@Autowired
	private ProductTopicService productTopicService;

	// 获取专题列表
	@GetMapping("/queryPage")
	public Object queryPage(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, PageBean pageBean,
			@RequestParam(required = false) String name, @RequestParam(required = false) Integer id) {
		PageBean overridePageBean = new PageBean() {
			@Override
			public Integer getPageSize() {
				return pageSize;
			}
		};
		overridePageBean.setPageSize(pageSize);
		overridePageBean.setPage(pageBean.getPage());
		return Response.succ(topicService.getTopicsByPage(overridePageBean, id, name));
	}

	// 添加专题
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@RequestBody TopicEntity topicEntity) {
		int insert = topicService.insert(topicEntity);
		if (insert > 0) {
			return Response.succ("新增成功");
		}
		return Response.fail("新增失败");
	}

	// 修改专题
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public Object modify(@RequestBody TopicEntity topicEntity) {
		int update = topicService.update(topicEntity);
		if (update > 0) {
			return Response.succ("编辑成功");
		}
		return Response.fail("编辑失败");
	}

	// 删除专题
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public Object del(@PathVariable Integer id) {
		int del = topicService.deleteById(id);
		if (del > 0) {
			return Response.succ("删除成功");
		}
		return Response.fail("删除失败");
	}

	// 获取专题下的所有商品
	@GetMapping("/{topicId}/products")
	public Object getTopicProducts(@PathVariable Integer topicId) {
		List<ProductEntity> pageResultBean = productTopicService.getTopicProducts(topicId);
		return Response.succ(Maps.immutableEntry("list", pageResultBean));
	}

	// 删除专题关联商品
	@RequestMapping(value = "/delProduct", method = RequestMethod.POST)
	public Object delProduct(@Validated @RequestBody TopicProductBean topicProductBean) {
		List<Long> spuCodes = topicProductBean.getSpuCodes();
		if (CollectionUtils.isEmpty(spuCodes)) {
			return Response.fail("请选择专题对应商品删除!");
		}
		int del = productTopicService.deleteTopicProduct(topicProductBean.getTopicId(), spuCodes);
		if (del > 0) {
			return Response.succ("删除成功");
		}
		return Response.fail("删除失败");
	}

	// 商品排序
	@RequestMapping(value = "/updateSort", method = RequestMethod.POST)
	public Object updateSort(@Validated @RequestBody TopicProductBean topicProductBean) {
		int update = productTopicService.updateSort(topicProductBean.getTopicId(), topicProductBean.getSpuCodes());
		if (update > 0) {
			return Response.succ("更新排序成功");
		}
		return Response.fail("更新排序失败");
	}
}
