package com.lx.benefits.web.controller.supplieradmin;

import static org.assertj.core.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.product.ProductQueryParam;
import com.lx.benefits.bean.dto.admin.product.SkuQueryParam;
import com.lx.benefits.bean.dto.spec.ProductBean;
import com.lx.benefits.bean.dto.spec.ProductRequestBean;
import com.lx.benefits.bean.entity.operation.TopicEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.operation.TopicService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.web.controller.base.BaseAdminController;

import io.swagger.annotations.Api;

/**
 * 【商品管理】 spu控制层
 *
 * @author gaosong
 * @date 2019/1/28.
 */
@Api(tags = "供应商后台-商品管理模块")
@RestController("SpuController")
@RequestMapping("/supplieradmin/product")
public class SpuController extends BaseAdminController {

	@Autowired
	private ProductService productService;

	@Autowired
	private TopicService topicService;
	@Autowired
	private SkuService skuService;

	// 获取商品毛利率
	@GetMapping("/settings/salerate")
	public Object getSaleRate() {
		HashMap<String, Object> response = new HashMap<>(1, 1);
		BigDecimal saleRate = productService.getGoodsSaleRate();
		response.put("goodsRate", saleRate);
		return Response.succ(response);
	}

	// 获取商品spu列表
	@RequestMapping(value = "/queryPage", method = RequestMethod.GET)
	public Object queryPage(ProductQueryParam queryParam, PageBean pageBean) {
		queryParam.setSupplierId(SessionContextHolder.getSessionSupplierInfo().getSupplierId());
		PageResultBean<ProductEntity> pageResult = productService.selectPage(queryParam, pageBean);
		return Response.succ(pageResult);
	}

	// 添加商品spu
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JSONObject add(@Validated @RequestBody ProductRequestBean productRequestBean) {
		Long supplierId = SessionContextHolder.getSessionSupplierInfo().getSupplierId();
		productRequestBean.setSupplierId(SessionContextHolder.getSessionSupplierInfo().getSupplierId());
		productService.addProduct(productRequestBean, supplierId.intValue());
		return Response.succ();
	}
	
	// 获取商品规格列表
	@GetMapping("/info/{spuCode}")
	public Object getProductInfo(@PathVariable Long spuCode) {
		ProductBean productSpecBean = productService.getProductSpecBean(spuCode);
		Long supplierId = SessionContextHolder.getSessionSupplierInfo().getSupplierId();
		if (productSpecBean != null && !supplierId.equals(productSpecBean.getSupplierId())) {
			return Response.fail("获取失败");
		}
		return Response.succ(productService.getProductSpecBean(spuCode));
	}

	// 更新商品规格信息
	@PostMapping("/update/{spuCode}")
	public Object udpateProductInfo(@PathVariable Long spuCode, @RequestBody ProductBean request) {
		productService.udpateProductInfo(spuCode, request);
		return Response.succ();
	}
	
	// 获取商品spu所属主题
	@RequestMapping(value = "/{spucode}/topicinfo", method = RequestMethod.GET)
	public Object getProductTopicInfo(@PathVariable Long spucode) {
		List<TopicEntity> topicEntity = topicService.selectListBySpu(spucode.toString());
		return Response.succ(topicEntity);
	}

	@PostMapping("/sku/{skuId}/updatestock")
	public Object updateSkuStock(@PathVariable Long skuId, @RequestBody SkuEntity request) {
		Integer goodsStorge = request.getGoodsStorge();
		if (goodsStorge == null) {
			return Response.fail("库存数量不能为空!");
		}
		if (goodsStorge == 0) {
			return Response.succ();
		}
		int result = skuService.updateSkuStock(skuId, goodsStorge);
		if (result == 0) {
			return fail("库存更新失败!");
		}
		return Response.succ();
	}

	@GetMapping("/sku/{skuId}/stockrecorders")
	public Object getSkuStorageRecorder(@PathVariable Long skuId, PageBean pageBean) {
		return Response.succ(skuService.getSkuStorageRecorders(skuId, pageBean));
	}

	// 获取sku详细信息
	@GetMapping("/sku/{skuId}/info")
	public Object querySkuById(@PathVariable Long skuId, @RequestParam(required = false) Integer withTopicInfo) {
		SkuEntity sku = skuService.getSkuDetailInfo(skuId);
		if (sku == null) {
			return Response.fail("SKU信息不存在!");
		}
		if (Integer.valueOf(1).equals(withTopicInfo)) {
			sku.setTopicInfos(topicService.selectListBySpu(sku.getSpuCode().toString()));
		}
		return Response.succ(sku);
	}

	/**
	 * sku分页列表
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/querySkuPages", method = RequestMethod.GET)
	public JSONObject querySkuPages(SkuQueryParam skuQueryParam, PageBean pageBean) {
		skuQueryParam.setSupplierId(SessionContextHolder.getSessionSupplierInfo().getSupplierId());
		PageResultBean<SkuEntity> pageResult = skuService.selectPage(skuQueryParam, pageBean);
		return Response.succ(pageResult);
	}

	/**
	 * spu改价
	 *
	 * @param
	 * @returnR
	 */
	@RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
	public JSONObject updatePrice(@RequestBody Map<String, Object> params) {
		if (null == params || params.size() < 1) {
			return Response.fail("查询条件为空不操作");
		}
		if (params.get("spuCodes") != null && !params.get("spuCodes").equals("")) {
			String[] spuCodes = params.get("spuCodes").toString().split(",");
			params.put("spuCodeList", spuCodes);
		}
		List<ProductEntity> list = productService.queryByParam(params);
		if (list == null || list.size() == 0) {
			return Response.fail("没有查询到商品信息");
		}
		List<String> spuCodes = new ArrayList<>();
		for (ProductEntity entity : list) {
			spuCodes.add(entity.getSpuCode().toString());
		}
		params.put("spuCodes", spuCodes);
		int update = productService.updateBatch(params);
		if (update > 0) {
			return Response.succ("改价成功");
		}
		return Response.fail("改价失败");
	}

}
