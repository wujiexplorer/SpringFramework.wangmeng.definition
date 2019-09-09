package com.lx.benefits.web.controller.admin;

import static org.assertj.core.api.Assertions.fail;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apollo.common.enums.base.CharacterEnum;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.bo.product.EsProductBean;
import com.lx.benefits.bean.dto.admin.product.ProductQueryParam;
import com.lx.benefits.bean.dto.admin.product.SkuQueryParam;
import com.lx.benefits.bean.dto.product.ProductBathOperationBean;
import com.lx.benefits.bean.dto.spec.ProductBean;
import com.lx.benefits.bean.dto.spec.ProductRequestBean;
import com.lx.benefits.bean.entity.operation.TopicEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.product.SkuExcelModel;
import com.lx.benefits.essearch.entity.EsProduct;
import com.lx.benefits.essearch.service.EsProductService;
import com.lx.benefits.service.operation.TopicService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.ProductTopicService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.task.EsProductUpdateTask;
import com.lx.benefits.web.controller.base.BaseAdminController;

/**
 * 【商品管理】 spu控制层
 *
 * @author gaosong
 * @date 2019/1/28.
 */
@RestController("productController")
@RequestMapping("/admin/product")
public class ProductController extends BaseAdminController {

	private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductTopicService productTopicService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private EsProductService esProductService;
	@Autowired
	private EsProductUpdateTask esProductUpdateTask;
	
	// 设置商品毛利率
	@PostMapping("/settings/salerate")
	public Object setSaleRate(@RequestBody SkuEntity request) {
		BigDecimal goodsRate = request.getGoodsRate();
		if (goodsRate == null) {
			return Response.fail("毛利率不能为空");
		}
		productService.setGoodsSaleRate(goodsRate);
		return Response.succ();
	}

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
		PageResultBean<ProductEntity> pageResult = productService.selectPage(queryParam, pageBean);
		return Response.succ(pageResult);
	}

	// 添加商品spu
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@Validated @RequestBody ProductRequestBean productRequestBean) {
		if (productRequestBean.getSupplierId() == null) {
			return Response.fail("供应商不能为空");
		}
		productService.addProduct(productRequestBean, null);
		return Response.succ();
	}

	// 获取商品规格列表
	@GetMapping("/info/{spuCode}")
	public Object getProductInfo(@PathVariable Long spuCode) {
		return Response.succ(productService.getProductSpecBean(spuCode));
	}

	// 更新商品规格信息
	@PostMapping("/update/{spuCode}")
	public Object udpateProductInfo(@PathVariable Long spuCode, @RequestBody ProductBean request) {
		productService.udpateProductInfo(spuCode, request);
		return Response.succ();
	}

	// 更新商品spu上下架状态
	@RequestMapping(value = "/modifyShelf", method = RequestMethod.POST)
	public Object batchUpdateState(@Validated @RequestBody ProductBathOperationBean request) {
		Integer type =request.getType();
		String spuCodes = request.getSpuCodes();
		if (spuCodes != null && !StringUtils.isEmpty(spuCodes)) {
			List<Long> spuCodeList = Stream.of(spuCodes.split(",")).map(item -> item.trim()).filter(StringUtils::isNotEmpty).distinct().map(Long::valueOf)
					.collect(Collectors.toList());
			int updateCount = productService.updateProductState(spuCodeList, type);
			if (updateCount > 0) {// 更新EsProduct
				this.updateEsProduct(spuCodeList);
			}
		} else {
			productService.updateProductState(request, type);
			new Thread(() -> {//全量刷新EsProduct
				esProductUpdateTask.esProductUpdateTask();
			}).start();
		}
		return Response.succ();
	}

	// 更新商品spu上下架状态
	@RequestMapping(value = "/updateState", method = RequestMethod.POST)
	public Object updateState(@RequestBody ProductEntity request) {
		Long spuCode = request.getSpuCode();
		if (null == spuCode || spuCode < 1) {
			return Response.fail("商品id不能为空");
		}
		int updateCount = productService.updateProductState(spuCode, request.getGoodsState());
		if (updateCount > 0) {// 更新EsProduct
			this.updateEsProduct(Arrays.asList(spuCode));
		}
		return Response.succ();
	}
	
	
	private void updateEsProduct(List<Long> spuCodes) {
		List<EsProductBean> esProductBeans = productService.getEsProductBeansByIds(spuCodes);
		esProductService.delete(spuCodes);
		for (EsProductBean item : esProductBeans) {
			EsProduct esProduct = new EsProduct();
			BeanUtils.copyProperties(item, esProduct);
			esProductService.create(esProduct);
		}
	}

	// 获取商品spu所属主题
	@RequestMapping(value = "/{spucode}/topicinfo", method = RequestMethod.GET)
	public Object getProductTopicInfo(@PathVariable Long spucode) {
		List<TopicEntity> topicEntity = topicService.selectListBySpu(spucode.toString());
		return Response.succ(topicEntity);
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

	@PostMapping("/sku/{skuId}/updateState")
	public Object updateSkuState(@PathVariable Long skuId, @RequestBody SkuEntity request) {
		skuService.updateSkuState(skuId, request.getStatus());
		return Response.succ();
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

	/**
	 * spu添加主题
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/addTopic", method = RequestMethod.POST)
	public Object addTopic(@Validated @RequestBody ProductBathOperationBean request) {
		String spuCodes = request.getSpuCodes();
		List<Long> spuCodeList = null;
		if (spuCodes != null && !StringUtils.isEmpty(spuCodes)) {
			spuCodeList = Stream.of(spuCodes.split(",")).map(item -> item.trim()).filter(StringUtils::isNotEmpty).distinct().map(Long::valueOf)
					.collect(Collectors.toList());
		} else {
			PageBean pageBean = new PageBean() {
				public Integer getPageSize() {
					return 1000;
				}
			};
			PageResultBean<ProductEntity> pageResult = productService.selectPage(request, pageBean);
			List<ProductEntity> products = pageResult.getList();
			if (CollectionUtils.isEmpty(products)) {
				spuCodeList = Collections.emptyList();
			} else {
				spuCodeList = products.stream().map(ProductEntity::getSpuCode).collect(Collectors.toList());
			}
		}
		Integer topicId = request.getType();
		productTopicService.addProductToTopic(topicId, spuCodeList);
		return Response.succ();
	}

	/**
	 * sku分页列表
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/querySkuPages", method = RequestMethod.GET)
	public Object querySkuPages(SkuQueryParam skuQueryParam, PageBean pageBean) {
		PageResultBean<SkuEntity> pageResult = skuService.selectPage(skuQueryParam, pageBean);
		BigDecimal saleRate = productService.getGoodsSaleRate();
		JSONObject result = (JSONObject) JSON.toJSON(pageResult);
		result.put("goodsRate", saleRate);
		return Response.succ(result);
	}

	/**
	 * spu改价
	 *
	 * @param
	 * @returnR
	 */
	@RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
	public Object updatePrice(@RequestBody Map<String, Object> params) {
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

	/**
	 * 校验商品是否属于专题
	 *
	 * @param params
	 * @returnR
	 */
	@RequestMapping(value = "/checkProductMix", method = RequestMethod.POST)
	public Object checkProductMix(@RequestBody Map<String, Object> params) {
		if (null == params || params.size() < 1) {
			logger.info("数据不能为空");
			return Response.fail("数据异常");
		}
		String topicId = params.get("topicId").toString();
		String spuCodes = params.get("spuCodes").toString();

		List<Long> list = CollectionExtUtil.splitStrToLongList(spuCodes, CharacterEnum.COMMA);
		Boolean check = productService.checkProductMix(null, Long.valueOf(topicId), list);
		if (check) {
			return Response.succ("校验成功");
		}
		return Response.succ("校验失败");
	}
	
	//sku商品导出
  	@RequestMapping("/sku/export")
  	public void skuExport(SkuQueryParam skuQueryParam,HttpServletRequest request, HttpServletResponse response) throws IOException{
  		final int pageSize = 200;
  		PageBean pageBean = new PageBean() {
  			public Integer getPageSize() {
  				return pageSize;
  			}
  		};// 默认第一页码
  		pageBean.setPageSize(pageSize);
  		PageResultBean<SkuEntity> pageResult = skuService.selectPage(skuQueryParam, pageBean);
  		List<SkuEntity> list = pageResult.getList();
		if (CollectionUtils.isEmpty(list)) {
			JSONObject fail = Response.fail("数据为空!");
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.getWriter().write(fail.toJSONString());
			response.setStatus(HttpStatus.SC_OK);
			return ;
		}
		Integer count = pageResult.getCount();
		int page = (count - 1) / pageBean.getPageSize() + 1;
		if (page > 100) {// 至多导出20000条
			JSONObject fail = Response.fail("每次最多导出20000条记录,请根据查询条件筛选后导出！");
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.getWriter().write(fail.toJSONString());
			response.setStatus(HttpStatus.SC_OK);
			return;
		}
		String filename;
		Class<? extends BaseRowModel> modelclass;
		filename = "sku商品导出.xlsx";
		modelclass = SkuExcelModel.class;
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE);
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO-8859-1"));
		OutputStream outputStream;
		String header = request.getHeader("Accept-Encoding");
		if (header != null && header.contains("gzip")) {// 支持gzip压缩
			response.setHeader("content-encoding", "gzip");
			outputStream = new GZIPOutputStream(response.getOutputStream());
		} else {
			outputStream = response.getOutputStream();
		}
		ExcelWriter writer = EasyExcelFactory.getWriter(outputStream, ExcelTypeEnum.XLSX, true);
		Sheet sheet = new Sheet(1, 1, modelclass);
		sheet.setSheetName(filename);
		
		for (int i = 1; i <= page; i++) {
			if (i > 1) {
				pageBean.setPage(i);
				pageResult = skuService.selectPage(skuQueryParam, pageBean);
			}
			list = pageResult.getList();
			writer.write(list.stream().map(item -> {
				BaseRowModel model;
				try {
					model = modelclass.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				BeanUtils.copyProperties(item, model);
				return model;
			}).collect(Collectors.toList()), sheet);
		}
		writer.finish();
		outputStream.flush();
  	}
}
