package com.lx.benefits.web.controller.enterprise;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.customized.GoodsModuleInfoDto;
import com.lx.benefits.bean.dto.admin.product.BrandResDto;
import com.lx.benefits.bean.dto.enterprise.GoodsInfoResDto;
import com.lx.benefits.bean.dto.order.QueryFreightVO;
import com.lx.benefits.bean.dto.product.ProductSearchBean;
import com.lx.benefits.bean.dto.spec.ProductBean;
import com.lx.benefits.bean.dto.spec.SkuBean;
import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.product.SkuPriceBean;
import com.lx.benefits.essearch.entity.EsProduct;
import com.lx.benefits.essearch.service.EsProductService;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.enterprcustomprice.EnterprCustomPriceService;
import com.lx.benefits.service.product.BrandService;
import com.lx.benefits.service.product.CategoryService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * @author unknow on 2018-12-09 23:15.
 */
@Api(tags = "企业商城-商品模块")
@RestController
@RequestMapping("/enterprise/goods")
@Slf4j
public class GoodsController extends BaseEnterpriseController {

	@Autowired
	private SkuService skuService;

	@Autowired
	private EnterprCustomGoodsService enterprCustomGoodsService;

	@Autowired
	private BrandService brandService;

	@Resource
	private ProductService productService;
	@Resource
	private EnterprCustomPriceService enterprCustomPriceService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private EsProductService esProductService;

	// 获取商品列表
	@GetMapping("list")
	public Object getProducts(ProductSearchBean productSearchBean, PageBean pageBean) {
		Long enterpriseId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
		String key = productSearchBean.getKey();
		if (key != null && !StringUtils.isEmpty(key = key.trim())) {
			GoodsModuleInfoDto goodsModuleInfoDto = enterprCustomGoodsService.findByIdWithAgent(enterpriseId);
			NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
			builder.withQuery(QueryBuilders.multiMatchQuery(key, "goodsName", "keywords", "goodsSpecname", "categoryName", "categoryName2", "categoryName3")
					.operator(Operator.AND));
			if (goodsModuleInfoDto != null) {
				BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
				if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getBrandIdsList())) {
					boolQueryBuilder.mustNot(QueryBuilders.termsQuery("brandId", goodsModuleInfoDto.getBrandIdsList()));
				}
				if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getSupplierIdsList())) {
					boolQueryBuilder.mustNot(QueryBuilders.termsQuery("supplierId", goodsModuleInfoDto.getSupplierIdsList()));
				}
				if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getCategoryIdsList())) {
					boolQueryBuilder.mustNot(QueryBuilders.termsQuery("categoryId", goodsModuleInfoDto.getCategoryIdsList()));
					boolQueryBuilder.mustNot(QueryBuilders.termsQuery("categoryId2", goodsModuleInfoDto.getCategoryIdsList()));
					boolQueryBuilder.mustNot(QueryBuilders.termsQuery("categoryId3", goodsModuleInfoDto.getCategoryIdsList()));
				}
				if (goodsModuleInfoDto.getLowestPrice() != null) {
					boolQueryBuilder.must(QueryBuilders.rangeQuery("lowPrice").gte(goodsModuleInfoDto.getLowestPrice().doubleValue()));
				}
				builder.withFilter(boolQueryBuilder);
				// 排序
				Integer sort = productSearchBean.getSort();
				if (sort == null) {
				} else if (sort == 1) { // 按价格从高到低
					builder.withSort(SortBuilders.fieldSort("lowPrice").order(SortOrder.DESC));
				} else if (sort == 2) { // 按价格从低到高
					builder.withSort(SortBuilders.fieldSort("lowPrice").order(SortOrder.ASC));
				} else if (sort == 3) {// 按上架时间
					builder.withSort(SortBuilders.fieldSort("statedTime").order(SortOrder.DESC));
				}
				builder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));// 按相关度
			}
			try {
				List<EsProduct> esProducts = esProductService.search(builder, pageBean.getPage() - 1, 100).getContent();
				if (CollectionUtils.isEmpty(esProducts)) {
					PageResultBean<ProductEntity> pageResultBean = new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), 0, Collections.emptyList());
					return Response.succ(pageResultBean);
				} else {
					List<Long> spuCodes = esProducts.parallelStream().map(EsProduct::getSpuCode).collect(Collectors.toList());
					productSearchBean.setSpuCodes(spuCodes);
					productSearchBean.setKey(null);
					PageBean newPageBean = new PageBean() {
						@Override
						public Integer getPageSize() {
							return spuCodes.size();
						}
					};
					newPageBean.setPage(1);
					PageResultBean<ProductEntity> pageResultBean = productService.getProductsByPage(enterpriseId, productSearchBean, newPageBean);
					pageResultBean.setPageSize(pageBean.getPageSize());
					pageResultBean.setPage(pageBean.getPage());
					return Response.succ(pageResultBean);
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		PageResultBean<ProductEntity> pageResultBean = productService.getProductsByPage(enterpriseId, productSearchBean, pageBean);
		return Response.succ(pageResultBean);
	}

	// 获取多个分类商品列表,最多10个
	@GetMapping(value = "list", params = { "categoryIds", "categoryIds!=" })
	public Object getProducts(PageBean pageBean, @RequestParam String categoryIds) {
		pageBean.setPage(1);
		Long enterpriseId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
		List<Object> result = Stream.of(categoryIds.split(",")).parallel().map(String::trim).filter(item -> !StringUtils.isEmpty(item)).distinct().limit(10)
				.map(item -> {
					Integer categoryId;
					try {
						categoryId = Integer.valueOf(item);
					} catch (Exception e) {
						return null;
					}
					ProductSearchBean productSearchBean = new ProductSearchBean();
					productSearchBean.setCategoryId(Integer.valueOf(item));
					PageResultBean<ProductEntity> products = productService.getProductsByPage(enterpriseId, productSearchBean, pageBean);
					if (CollectionUtils.isEmpty(products.getList())) {
						return null;
					}
					JSONObject jsonObject = (JSONObject) JSON.toJSON(products);
					jsonObject.put("categoryId", categoryId);
					CategoryEntity categoryEntity = categoryService.selectById(categoryId.longValue());
					if (categoryEntity != null) {
						jsonObject.put("categoryName", categoryEntity.getName());
						jsonObject.put("categoryNameEn", categoryEntity.getNameEn());
					} else {
						jsonObject.put("categoryName", "");
						jsonObject.put("categoryNameEn", "");
					}
					return jsonObject;
				}).filter(Objects::nonNull).collect(Collectors.toList());
		return Response.succ(result);
	}

	@ApiOperation(value = "企业商品详细信息", response = GoodsInfoResDto.class)
	@GetMapping("/detail/{spuCode}")
	public Object detail(@ApiParam(value = "商品标识id", name = "spuCode") @PathVariable Long spuCode, Long campaignId) {
		ProductBean productSpecBean = productService.getPCProductSpecBean(spuCode, campaignId);
		if (productSpecBean == null || CollectionUtils.isEmpty(productSpecBean.getSkuList())) {
			return Response.fail("商品已下架");
		}
		Long enterpriseId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
		List<SkuBean> skuList = productSpecBean.getSkuList();
		List<Long> skuIds = skuList.stream().map(SkuBean::getSkuId).collect(Collectors.toList());
		Map<Long, SkuPriceBean> mapPrice = skuService.singleCustomPrice(skuIds, enterpriseId);
		boolean productIsDown=productSpecBean.getGoodsState()==1;
		if (!CollectionUtils.isEmpty(mapPrice)) {
			for (SkuBean item : skuList) {
				if (!productIsDown) {
					item.setFlag(false);
				}
				SkuPriceBean skuPriceBean = mapPrice.get(item.getSkuId());
				BigDecimal customPrice;
				if (skuPriceBean != null && (customPrice = skuPriceBean.getGoodsPrice()).compareTo(BigDecimal.ZERO) > 0) {
					item.setGoodsPrice(customPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
					if (skuPriceBean.isCustomer()) {
						item.setFlag(true);
						productSpecBean.setGoodsState(1);
					}
				}
			}
		}
		return Response.succ(productSpecBean);
	}

	@GetMapping("/similar/{spuCode}")
	public Object similar(@ApiParam(value = "商品标识id", name = "spuCode") @PathVariable Long spuCode, Integer count) {
		Map<String, Object> params = new HashMap<>();
		params.put("spuCode", spuCode);
		List<ProductEntity> list = productService.queryByParam(params);
		Integer categoryId3 = list.get(0).getCategoryId3().intValue();
		params.clear();
		params.put("categoryId3", categoryId3);
		params.put("goodsState", 1);
		List<ProductEntity> productEntities = productService.queryByParam(params);
		Long spuCodeRes = null;
		List<Long> spuCodeResList = new ArrayList<>();
		Integer j = 0;
		Integer size = productEntities.size();
		int i = 0;
		if (size - count <= 0) {
			i = 0;
		} else {
			i = (int) (Math.random() * (size - count));
		}
		for (; i < productEntities.size(); i++) {
			// they can not go together,or they do error
			if (productEntities.get(i).getGoodsState() == 0) {
				continue;
			}
			if (productEntities.get(i).getSpuCode().equals(spuCode)) {
				continue;
			}
			Boolean flag = false;
			for (int z = 0; z < spuCodeResList.size(); z++) {
				if (productEntities.get(i).getSpuCode().equals(spuCodeResList.get(z))) {
					flag = true;
					break;
				}
			}
			if (flag == true) {
				continue;
			}
			j++;
			if (j > count) {
				break;
			}
			// list add before
			spuCodeRes = productEntities.get(i).getSpuCode();
			spuCodeResList.add(spuCodeRes);
			if (size - count <= 0) {
				continue;
			} else {
				i = (int) (Math.random() * (size - count + j));
			}
		}
		List<ProductBean> productBeans = new ArrayList<>();
		for (Long temp : spuCodeResList) {
			ProductBean productSpecBean = productService.getPCProductSpecBean(temp);
			if (productSpecBean == null || CollectionUtils.isEmpty(productSpecBean.getSkuList())) {
				return Response.fail("商品已下架");
			}
			Long enterpriseId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
			List<SkuBean> skuList = productSpecBean.getSkuList();
			List<Long> skuIds = skuList.stream().map(SkuBean::getSkuId).collect(Collectors.toList());
			Map<Long, SkuPriceBean> mapPrice = skuService.singleCustomPrice(skuIds, enterpriseId);
			BigDecimal goodsPrice = skuList.get(0).getGoodsPrice();
			if (!CollectionUtils.isEmpty(mapPrice)) {
				for (SkuBean item : skuList) {
					BigDecimal customPrice = mapPrice.get(item.getSkuId()).getGoodsPrice();
					if (customPrice != null && customPrice.compareTo(BigDecimal.ZERO) > 0) {
						item.setGoodsPrice(customPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					if (goodsPrice.compareTo(item.getGoodsPrice()) > 0) {
						goodsPrice = item.getGoodsPrice();
					}
				}
			}
			productSpecBean.setGoodsPrice(goodsPrice);
			productSpecBean.setSkuSpecList(null);
			productSpecBean.setSkuList(null);
			productSpecBean.setSkuSpectValueList(null);
			productSpecBean.setImgUrlList(null);
			productSpecBean.setIntroduction(null);
			productBeans.add(productSpecBean);
		}

		return Response.succ(productBeans);
	}

	private static final String introductionFomat = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "  <meta charset=\"UTF-8\">\n" + "  <title>Title</title>\n"
			+ "</head>\n" + "<body style=\"margin: 0;\">\n" + "%s\n" + "</body>\n" + "</html>";

	@GetMapping(value = "/introduction/{spuCode}", produces = MediaType.TEXT_HTML_VALUE)
	public Object intruduction(@PathVariable Long spuCode) {
		String intruduction = productService.getProductIntruduction(spuCode);
		if (intruduction == null) {
			intruduction = "";
		}
		return String.format(introductionFomat, intruduction);
	}

	@RequestMapping(value = "/queryBrands", method = RequestMethod.GET)
	public JSONObject queryBrands(@RequestParam Map<String, Object> params) {
		JSONObject jsonObject = new JSONObject();
		Long enterpriseId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
		GoodsModuleInfoDto goodsModuleInfoDto = enterprCustomGoodsService.findByIdWithAgent(enterpriseId);
		if (goodsModuleInfoDto.getBrandIdsList() != null && goodsModuleInfoDto.getBrandIdsList().size() > 0) {
			params.put("brandIdNotIn", goodsModuleInfoDto.getBrandIdsList());
		}
		List<BrandResDto> list = brandService.selectPageList(params).stream().map(x -> Beans.convert(x, BrandResDto.class)).collect(toList());
		jsonObject.put("list", list);
		return Response.succ(jsonObject);
	}

	/**
	 * 精选特惠商品
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/featureList", method = RequestMethod.GET)
	public Object featureList(PageBean pageBean) {
		Long enterpriseId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
		PageResultBean<SkuEntity> resutlt = enterprCustomPriceService.getPCFeatureListByPage(enterpriseId, pageBean);
		return Response.succ(resutlt);
	}

	/**
	 * 
	 * @Title: queryFreight @Description: 查询运费 @param: @return @return:
	 *         JSONObject @throws
	 */
	@RequestMapping(value = "/queryStockAndFreight", method = RequestMethod.POST)
	public JSONObject queryStockAndFreight(@RequestBody QueryFreightVO qeueryFreightVO) {
		return Response.succ(enterprCustomGoodsService.queryStockAndFreight(qeueryFreightVO));
	}

	@RequestMapping("/category/{categoryId}")
	public Object queryCategoryId(@PathVariable Long categoryId) {
		return Response.succ(categoryService.queryCategoryId(categoryId));
	}

}
