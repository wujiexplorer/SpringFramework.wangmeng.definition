package com.lx.benefits.service.jd.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.bo.product.TempProduct;
import com.lx.benefits.bean.dto.admin.category.ThreaLevelCategory;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.entity.product.BrandEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.enums.GoodsStateEnum;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.mapper.basedistrictinfo.DistrictInfoMapper;
import com.lx.benefits.mapper.product.BrandMapper;
import com.lx.benefits.mapper.product.CategoryMapper;
import com.lx.benefits.mapper.product.ProductMapper;
import com.lx.benefits.mapper.product.SkuMapper;
import com.lx.benefits.service.jd.IJDAccessTokenService;
import com.lx.benefits.service.jd.JDConfigService;
import com.lx.benefits.service.jd.JDManagementService;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JDManagementServiceImpl implements JDManagementService {

	@Resource
	@Qualifier("jdConfigService")
	private JDConfigService jdConfigService;

	@Resource
	private IJDAccessTokenService jDAccessTokenServiceImpl;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private SkuMapper skuMapper;
	@Resource
	private CategoryMapper categoryMapper;
	@Resource
	private BrandMapper brandMapper;
	@Resource
	private DistrictInfoMapper districtInfoMapper;

	@Resource(name = "formStringJsonRestTemplate")
	public RestTemplate restTemplate;

	private String imagePath = "http://img10.360buyimg.com/imgzone/";

	@Override
	public List<TempProduct> getSkuDetails(String skuId, boolean withSimilar) {
		log.info("getProductDetail...." + skuId);
		Map<String, String> productSpecValue = this.getProductSpecValue(skuId);// 规格
		List<String> skuIds = new ArrayList<>();
		if (withSimilar) {
			skuIds.addAll(productSpecValue.keySet());
			if (!skuIds.contains(skuId)) {
				skuIds.add(skuId);
			}
		} else {
			skuIds.add(skuId);
		}
		List<TempProduct> skuImages;
		Map<String, TempProduct> price;
		if (skuIds.size() > 100) {
			skuImages = new ArrayList<>();
			price = new HashMap<>();
			int page = (skuIds.size() - 1) / 100 + 1;
			for (int i = 0; i < page; i++) {
				int fromIndex = 100 * i;
				int toIndex = fromIndex + 100;
				if (toIndex > skuIds.size()) {
					toIndex = skuIds.size();
				}
				skuImages.addAll(this.getSkuImages(skuIds.subList(fromIndex, toIndex)));
			}
		} else {
			skuImages = this.getSkuImages(skuIds);
			price = this.getPrice(skuIds);
		}
		List<TempProduct> tempProducts = skuIds.parallelStream().map(skuIdItem -> {
			LinkedMultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
			multiValueMap.add("token", jDAccessTokenServiceImpl.getToken());
			multiValueMap.add("sku", skuIdItem);
			multiValueMap.add("queryExts", "spuId");
			String responseString = restTemplate.postForObject("https://bizapi.jd.com/api/product/getDetail", multiValueMap, String.class);
			JSONObject response = JSON.parseObject(responseString);
			if (response.getBoolean("success")) {
				JSONObject result = response.getJSONObject("result");
				TempProduct tempProduct = new TempProduct();
				tempProduct.setSkuId(skuIdItem);
				tempProduct.setProductArea(result.getString("productArea"));
				tempProduct.setSpuId(result.getString("spuId"));
				tempProduct.setState(result.getInteger("state"));
				tempProduct.setBrandName(result.getString("brandName"));
				tempProduct.setUpc(result.getString("upc"));
				String category = result.getString("category");
				if (!StringUtils.isEmpty(category)) {
					String[] categorys = category.split(";");
					try {
						tempProduct.setCatogoryId(Long.valueOf(categorys[0]));
						tempProduct.setCatogoryId2(Long.valueOf(categorys[1]));
						tempProduct.setCatogoryId3(Long.valueOf(categorys[2]));
					} catch (Exception e) {// 分类信息错误
					}
				}
				tempProduct.setIntroduction(result.getString("introduction"));
				tempProduct.setGoodsName(result.getString("name"));
				tempProduct.setParam(result.getString("param"));
				tempProduct.setImagePath(imagePath + result.getString("imagePath"));
				tempProduct.setWeight(result.getDouble("weight"));
				tempProduct.setSaleUnit(result.getString("saleUnit"));
				tempProduct.setGoodsSpec(productSpecValue.get(skuIdItem));
				TempProduct productPrice = price.get(skuIdItem);
				if (productPrice == null) {
					return null;
				}
				tempProduct.setGoodsPrice(productPrice.getGoodsPrice());
				tempProduct.setMarketPrice(productPrice.getMarketPrice());
				tempProduct.setCostPrice(productPrice.getCostPrice());
				tempProduct.setProfitRate(productPrice.getProfitRate());
				tempProduct.setGoodsDiscount(productPrice.getGoodsDiscount());
				if (!CollectionUtils.isEmpty(skuImages)) {
					tempProduct.setSkuImages(skuImages.get(0).getSkuImages());
				}
				return tempProduct;
			} else {
				return null;
			}
		}).filter(Objects::nonNull).collect(Collectors.toList());
		return tempProducts;
	}

	private String getCategoryName(String categoryId) {
		log.info("getCategoryName...." + categoryId);
		LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("token", jDAccessTokenServiceImpl.getToken());
		multiValueMap.add("cid", categoryId);
		String responseString = restTemplate.postForObject("https://bizapi.jd.com/api/product/getCategory", multiValueMap, String.class);
		JSONObject response = JSON.parseObject(responseString);
		if (response.getBoolean("success")) {
			JSONObject result = response.getJSONObject("result");
			return result.getString("name");
		}
		return null;
	}

	// (最高支持100个商品)
	public List<TempProduct> getSkuImages(List<String> skuIds) {
		log.info("getSkuImages ..............");
		if (CollectionUtils.isEmpty(skuIds)) {
			return Collections.emptyList();
		}
		if (skuIds.size() > 100) {
			throw new BusinessException("获取图片信息上限100个");
		}
		LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("token", jDAccessTokenServiceImpl.getToken());
		if (skuIds.size() == 1) {
			multiValueMap.add("sku", skuIds.get(0));
		} else {
			multiValueMap.add("sku", skuIds.stream().collect(Collectors.joining(",")));
		}
		JSONObject response = restTemplate.postForObject("https://bizapi.jd.com/api/product/skuImage", multiValueMap, JSONObject.class);
		if (response.getBooleanValue("success")) {
			Map<String, Object> skuImagesMap = response.getJSONObject("result").getInnerMap();
			return skuImagesMap.entrySet().stream().map(item -> {
				TempProduct tempProduct = new TempProduct();
				tempProduct.setSkuId(item.getKey());
				JSONArray skuimages = (JSONArray) item.getValue();
				List<String> images = skuimages.stream().map(image -> imagePath + ((JSONObject) image).getString("path")).collect(Collectors.toList());
				tempProduct.setSkuImages(JSONArray.toJSONString(images));
				return tempProduct;
			}).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	// 商品编号，请以，(英文逗号)分割。例如：129408,129409(最高支持100个商品)
	private Map<String, TempProduct> getPrice(List<String> skuIds) {
		log.info("getPrice...." + JSON.toJSONString(skuIds));
		if (CollectionUtils.isEmpty(skuIds)) {
			return Collections.emptyMap();
		}
		if (skuIds.size() > 100) {
			throw new BusinessException("获取价格信息上限100个");
		}
		LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("token", jDAccessTokenServiceImpl.getToken());
		multiValueMap.add("sku", skuIds.stream().collect(Collectors.joining(",")));
		multiValueMap.add("queryExts", "marketPrice");
		String responseString = restTemplate.postForObject("https://bizapi.jd.com/api/price/getSellPrice", multiValueMap, String.class);
		JSONObject response = JSON.parseObject(responseString);
		if (response.getBoolean("success")) {
			JSONArray result = response.getJSONArray("result");
			return result.stream().map(item -> (JSONObject) item).map(item -> {
				TempProduct tempProduct = new TempProduct();
				tempProduct.setSkuId(item.getString("skuId"));
				tempProduct.setCostPrice(item.getBigDecimal("price"));
				tempProduct.setGoodsPrice(item.getBigDecimal("jdPrice"));
				tempProduct.setMarketPrice(item.getBigDecimal("marketPrice"));
				if (tempProduct.getGoodsPrice() == null || tempProduct.getCostPrice() == null || BigDecimal.ZERO.compareTo(tempProduct.getGoodsPrice()) == 0) {
					tempProduct.setProfitRate(BigDecimal.valueOf(0));
				} else {
					BigDecimal rate = tempProduct.getGoodsPrice().subtract(tempProduct.getCostPrice()).divide(tempProduct.getGoodsPrice(), 2,
							RoundingMode.HALF_UP);
					tempProduct.setProfitRate(rate);
				}
				tempProduct.setGoodsDiscount(tempProduct.getGoodsPrice().divide(tempProduct.getMarketPrice(), 2, RoundingMode.HALF_UP));
				return tempProduct;
			}).collect(Collectors.toMap(TempProduct::getSkuId, UnaryOperator.identity(), (item1, item2) -> item1));
		}
		return Collections.emptyMap();
	}

	private Map<String, String> getProductSpecValue(String skuId) {
		if (StringUtils.isEmpty(skuId)) {
			return Collections.emptyMap();
		}
		LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("token", jDAccessTokenServiceImpl.getToken());
		multiValueMap.add("skuId", skuId);
		String responseString = restTemplate.postForObject("https://bizapi.jd.com/api/product/getSimilarSku", multiValueMap, String.class);
		JSONObject response = JSON.parseObject(responseString);
		if (response.getBoolean("success")) {
			JSONArray result = response.getJSONArray("result");
			Map<String, String> saleValueMap = new HashMap<>();
			for (Object obj : result) {
				JSONObject item = (JSONObject) obj;
				JSONArray saleAttrList = item.getJSONArray("saleAttrList");
				for (Object obj2 : saleAttrList) {
					JSONObject saleAttr = (JSONObject) obj2;
					String saleValue = saleAttr.getString("saleValue");
					JSONArray jsonArray = saleAttr.getJSONArray("skuIds");
					for (Object skuitem : jsonArray) {
						saleValueMap.compute(skuitem.toString(), (key, oldValue) -> {
							return StringUtils.isEmpty(oldValue) ? saleValue : oldValue.concat("/").concat(saleValue);
						});
					}
				}
			}
			return saleValueMap;
		}
		return Collections.emptyMap();
	}

	private List<String> getSimilarSkuIds(String skuId) {
		List<String> similarSkuIds = new ArrayList<>();
		LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("token", jDAccessTokenServiceImpl.getToken());
		multiValueMap.add("skuId", skuId);
		JSONObject response = restTemplate.postForObject("https://bizapi.jd.com/api/product/getSimilarSku", multiValueMap, JSONObject.class);
		log.info("getSimilarSku skuId={}, result={}", skuId, response);
		if (Boolean.TRUE.equals(response.getBoolean("success"))) {
			JSONArray result = response.getJSONArray("result");
			if (!CollectionUtils.isEmpty(result)) {
				JSONArray saleAttrList = result.getJSONObject(0).getJSONArray("saleAttrList");
				if (!CollectionUtils.isEmpty(saleAttrList)) {
					for (int i = 0; i < saleAttrList.size(); i++) {
						JSONArray skuIds = saleAttrList.getJSONObject(i).getJSONArray("skuIds");
						for (int j = 0; j < skuIds.size(); j++) {
							similarSkuIds.add(skuIds.getString(j));
						}
					}
				}
			}
		}
		return similarSkuIds;
	}

	@Transactional
	@Override
	public int goodsImport(String skuId, boolean withSimilar) {
		List<TempProduct> skuDetails = this.getSkuDetails(skuId, withSimilar);
		if (CollectionUtils.isEmpty(skuDetails)) {
			return 0;
		}
		List<String> similarSkuIds;
		if (!withSimilar) {
			similarSkuIds = this.getSimilarSkuIds(skuId);
		} else {
			similarSkuIds = skuDetails.parallelStream().map(TempProduct::getSkuId).collect(Collectors.toList());
		}
		List<SkuEntity> skuEntitys = skuMapper.getSupplierSkuCodes(jdConfigService.JD_ID.intValue(), similarSkuIds);
		Long spuCode;
		List<String> existsSkuCodes;
		Date now = new Date();
		String currentUser = SessionContextHolder.getCurrentLoginName();
		// 开始导入spu
		if (CollectionUtils.isEmpty(skuEntitys)) {// 新导入的
			TempProduct tempProduct = skuDetails.get(0);
			ThreaLevelCategory threaLevelCategory = categoryMapper.getThreaLevelCategoryByThirdCatId(tempProduct.getCatogoryId2(),
					jdConfigService.JD_ID.intValue());
			ProductEntity product = new ProductEntity();
			String brandName = tempProduct.getBrandName();
			Long brandId;
			if (!StringUtils.isEmpty(brandName)) {
				BrandEntity brandEntity = brandMapper.selectByName(tempProduct.getBrandName());
				brandId = brandEntity == null ? 0L : brandEntity.getId();
			} else {
				brandId = 0L;
			}
			String productArea = tempProduct.getProductArea();
			Long placeOriginId = null;
			if (!StringUtils.isEmpty(brandName)) {
				DistrictInfo districtInfo = districtInfoMapper.getInfoByName(productArea);
				if (districtInfo != null) {
					placeOriginId = districtInfo.getId();
				}
			}
			product.setBrandName(brandName);
			product.setBrandId(brandId);
			product.setSupplierId(jdConfigService.JD_ID);
			product.setSupplierName(jdConfigService.JD_NAME);
			product.setPlaceOriginId(placeOriginId);
			product.setPlaceOrigin(tempProduct.getProductArea());
			product.setGoodsName(tempProduct.getGoodsName());
			product.setGoodsImage(tempProduct.getImagePath());
			product.setGoodsImages(tempProduct.getSkuImages());
			product.setIntroduction(tempProduct.getIntroduction());
			product.setGoodsState(GoodsStateEnum.SOLDOUT.getValue());
			if (threaLevelCategory == null) {
				product.setCategoryId(0L);
				product.setCategoryId2(0L);
				product.setCategoryId3(0L);
				product.setCategoryName(this.getCategoryName(String.valueOf(tempProduct.getCatogoryId())));
				product.setCategoryName2(this.getCategoryName(String.valueOf(tempProduct.getCatogoryId2())));
				product.setCategoryName3(this.getCategoryName(String.valueOf(tempProduct.getCatogoryId3())));
			} else {
				product.setCategoryId(threaLevelCategory.getCategoryId().longValue());
				product.setCategoryId2(threaLevelCategory.getCategoryId2().longValue());
				product.setCategoryId3(threaLevelCategory.getCategoryId3().longValue());
				product.setCategoryName(threaLevelCategory.getCategoryName());
				product.setCategoryName2(threaLevelCategory.getCategoryName2());
				product.setCategoryName3(threaLevelCategory.getCategoryName3());
			}
			product.setCreatedTime(now);
			product.setCreatedUser(currentUser);
			productMapper.insert(product);
			spuCode = product.getSpuCode();
			existsSkuCodes = Collections.emptyList();
		} else {
			spuCode = skuEntitys.get(0).getSpuCode();
			existsSkuCodes = skuEntitys.stream().map(SkuEntity::getSkuCode).collect(Collectors.toList());
		}
		// 开始导入sku
		List<SkuEntity> skuList = skuDetails.stream().filter(item -> !existsSkuCodes.contains(item.getSkuId())).map(item -> {
			SkuEntity skuEntity = new SkuEntity();
			skuEntity.setSkuCode(item.getSkuId());
			skuEntity.setSpuCode(spuCode);
			skuEntity.setGoodsStorge(99);
			skuEntity.setGoodsCostprice(item.getCostPrice());
			skuEntity.setGoodsMarkprice(item.getMarketPrice());
			skuEntity.setGoodsPrice(item.getGoodsPrice());
			skuEntity.setGoodsRate(item.getProfitRate());
			skuEntity.setGoodsDiscount(item.getGoodsDiscount());
			skuEntity.setGoodsName(item.getGoodsName());
			skuEntity.setGoodsImage(item.getImagePath());
			skuEntity.setGoodsImages(item.getSkuImages());
			skuEntity.setGoodsSpec(item.getGoodsSpec());
			skuEntity.setSaleUnit(item.getSaleUnit());
			skuEntity.setItemNumber(item.getUpc());
			skuEntity.setStatus(0);
			skuEntity.setCreatedTime(now);
			skuEntity.setCreatedUser(currentUser);
			return skuEntity;
		}).collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(skuList)) {
			return skuMapper.insertBatch(skuList);
		}
		return 0;
	}

}
