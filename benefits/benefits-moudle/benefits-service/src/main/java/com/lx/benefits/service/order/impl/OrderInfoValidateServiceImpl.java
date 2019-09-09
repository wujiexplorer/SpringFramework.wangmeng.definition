package com.lx.benefits.service.order.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.order.QueryFreightVO;
import com.lx.benefits.bean.param.order.OrderBuyParam;
import com.lx.benefits.bean.param.order.SkuParam;
import com.lx.benefits.bean.vo.order.JDAddress;
import com.lx.benefits.bean.vo.order.ProductMsg;
import com.lx.benefits.bean.vo.order.ProductValidateResult;
import com.lx.benefits.config.properties.YanXuan;
import com.lx.benefits.service.basedistrictinfo.DistricitInfoService;
import com.lx.benefits.service.jd.IJDAccessTokenService;
import com.lx.benefits.service.jd.JDConfigService;
import com.lx.benefits.service.order.OrderInfoValidateService;
import com.lx.benefits.service.product.SkuService;

import lombok.extern.slf4j.Slf4j;

/**
 * User:wangmeng Date:2019/4/23 Time:16:12 Verision:2.x Description:TODO
 **/
@Service
@Slf4j
public class OrderInfoValidateServiceImpl implements OrderInfoValidateService {

	@Resource
	private IJDAccessTokenService jDAccessTokenServiceImpl;
	@Resource
	private SkuService skuService;
	@Resource
	@Qualifier("jdConfigService")
	private JDConfigService jdConfigService;
	@Resource
	private YanXuan yanXuan;
	@Resource
	private DistricitInfoService districtInfoService;
	@Resource(name = "formStringJsonRestTemplate")
	public RestTemplate restTemplate;

	public List<ProductMsg> validateJdOrderInfo(List<SkuParam> skuParamLists, Long userReceiveAddrId,JDAddress jdAddress) {
		if (CollectionUtils.isEmpty(skuParamLists)) {
			return Collections.emptyList();
		}
		List<String> skuIds = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (SkuParam skuParamList : skuParamLists) {
			String skuId = skuParamList.getSkuId();
			sb.append(skuId).append(",");
			skuIds.add(skuId);
		}
		sb.deleteCharAt(sb.length() - 1);// 删掉最后一个分隔符
		try {
			if(userReceiveAddrId != null) {
				jdAddress = districtInfoService.getJdAddress(userReceiveAddrId);
			}
			List<String> skuIdList = getAreaRestrictSkus(new ArrayList<>(skuIds), jdAddress);
			List<ProductMsg> unableSkus = new ArrayList<ProductMsg>();
			if (!CollectionUtils.isEmpty(skuIdList)) {
				for (String skuId : skuIdList) {
					ProductMsg pm = new ProductMsg();
					pm.setSkuId(skuId);
					pm.setStockStateDesc("配送区域受限");
					unableSkus.add(pm);
				}
			}
			skuIds.removeAll(skuIdList);// 筛出出地域不受限的skuId
			log.info("不受区域限制的skuId：" + skuIds);
			if (CollectionUtils.isEmpty(skuIds)) {
				return unableSkus;
			}
			StringJoiner skuNums = new StringJoiner(",", "[", "]");
			for (SkuParam skuParam : skuParamLists) {
				String sku = skuParam.getSkuId();
				if (skuIds.contains(sku)) {
					skuNums.add("{skuId:" + skuParam.getSkuId()).add("num:" + skuParam.getQuantity() + "}");
				}
			}
			MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
			multiValueMap.add("token", jDAccessTokenServiceImpl.getToken());
			multiValueMap.add("skuNums", skuNums.toString());
			multiValueMap.add("area", jdAddress.getProvinceId() + "_" + jdAddress.getCityId() + "_" + jdAddress.getCountyId());
			JSONObject response = restTemplate.postForObject(jdConfigService.getJdStockNewStockUrl(), multiValueMap, JSONObject.class);
			log.info("查询商品有无货：" + response);
			Boolean isSuccess = response.getBoolean("success");
			if (Boolean.FALSE.equals(isSuccess)) {
				String resultMessage = response.getString("resultMessage");
				if (resultMessage != null && resultMessage.contains("不在您的商品池中")) {
					Pattern pattern = Pattern.compile("(\\d+)不在您的商品池中");
					Matcher matcher = pattern.matcher(resultMessage);
					while (matcher.find()) {
						String skuId = matcher.group(1);
						if (skuIds.contains(skuId)) {
							ProductMsg pm = new ProductMsg();
							pm.setSkuId(skuId);
							pm.setStockStateDesc("已下架");
							unableSkus.add(pm);
						}
					}
				}
				return unableSkus;
			}
			JSONArray result = response.getJSONArray("result");
			List<String> buyAbleSkus = new ArrayList<>();
			if (!CollectionUtils.isEmpty(result)) {
				for (int i = 0; i < result.size(); i++) {
					JSONObject item = result.getJSONObject(i);
					int stockStateId = item.getIntValue("stockStateId");
					if (stockStateId == 33 || stockStateId == 39 || stockStateId == 40) {// 有货
						buyAbleSkus.add(item.getString("skuId"));
					}
				}
			}
			skuIds.removeAll(buyAbleSkus);
			for (String sku : skuIds) {
				ProductMsg pm = new ProductMsg();
				pm.setSkuId(sku);
				pm.setStockStateDesc("无货");
				unableSkus.add(pm);
			}
			return unableSkus;
		} catch (Exception e) {
			log.error("查询有无货失败, message={}", e.getMessage());
			return Collections.emptyList();
		}

	}

	public List<ProductMsg> validateYxOrderInfo(List<SkuParam> skuParamLists) {
		if (CollectionUtils.isEmpty(skuParamLists)) {
			return Collections.emptyList();
		}
		Map<String, Integer> skuMap = skuParamLists.stream().collect(Collectors.toMap(SkuParam::getSkuId, SkuParam::getQuantity));
		Map<String, Integer> yxInventory = this.getYxInventory(new ArrayList<>(skuMap.keySet()));
		if (yxInventory == null) {
			return Collections.emptyList();
		}
		return skuMap.entrySet().stream().map(item -> {
			String sku = item.getKey();
			Integer num = item.getValue();
			Integer inventory = yxInventory.get(sku);
			ProductMsg productMsg = null;
			if (inventory == null) {
				productMsg = new ProductMsg();
				productMsg.setSkuId(sku);
				productMsg.setStockStateDesc("已下架");
			} else if (inventory.compareTo(num) < 0) {
				productMsg = new ProductMsg();
				productMsg.setSkuId(sku);
				productMsg.setStockStateDesc("库存不足");
			}
			return productMsg;
		}).filter(Objects::nonNull).collect(Collectors.toList());
	}

	public Map<String, Integer> getYxInventory(List<String> skuIds) {
		if(CollectionUtils.isEmpty(skuIds)) {
			return Collections.emptyMap();
		}
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("appKey", yanXuan.getApiKey());
		multiValueMap.add("method", "yanxuan.inventory.count.get");
		multiValueMap.add("skuIds", JSONArray.toJSONString(skuIds));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		multiValueMap.add("timestamp", dateFormat.format(new Date()));
		String sign = multiValueMap.entrySet().stream().map(item -> item.getKey() + "=" + item.getValue().get(0)).collect(Collectors.joining(""));
		sign = yanXuan.getApiSecret() + sign + yanXuan.getApiSecret();
		String md5Digest = DigestUtils.md5DigestAsHex(sign.getBytes()).toUpperCase();
		multiValueMap.set("sign", md5Digest);
		Map<String, Integer> inventoryMap = null;
		for (int i = 0; i < 3; i++) {// 尝试三次
			try {
				JSONObject response = restTemplate.postForObject(yanXuan.getApiHost() + yanXuan.getApiUrl(), multiValueMap, JSONObject.class);
				if (Integer.valueOf(200).equals(response.getInteger("code"))) {
					inventoryMap = response.getJSONArray("result").stream().map(item -> (JSONObject) item)
							.collect(Collectors.toMap(item -> item.getString("skuId"), item -> item.getInteger("inventory"), (item1, item2) -> item1));
					log.info("getStorageInfo.....result={}", JSON.toJSONString(inventoryMap));
				} else {
					log.error("getYXCategoryInfo....erro, message={}", response.toString());
					String msg = response.getString("msg");
					if (msg != null) {
						Pattern pattern = Pattern.compile("sku.*?(\\d+)");
						Matcher matcher = pattern.matcher(msg);
						if(matcher.find()) {
							String skuId = matcher.group(1);
							if (!StringUtils.isEmpty(skuId)&&skuIds.contains(skuId)) {
								skuIds.remove(skuId);
								return getYxInventory(skuIds);
							}
						}
					}
				}
			} catch (HttpClientErrorException e) {
				log.error("getStorageInfo....erro, message={}", e.getResponseBodyAsString());
			}
		}
		return inventoryMap;
	}

	@Override
	public ProductValidateResult validateOrderInfo(OrderBuyParam orderBuyParam, Long userReceiveAddrId) throws Exception {
		ProductValidateResult productValidateResult = new ProductValidateResult();
		List<SkuParam> jdSkuList = orderBuyParam.getJdSkuList();
		if (CollectionUtils.isEmpty(jdSkuList)) {
			productValidateResult.setJdRestrictSkus(Collections.emptyList());
		} else {
			productValidateResult.setJdRestrictSkus(validateJdOrderInfo(jdSkuList, userReceiveAddrId, null));
		}
		List<SkuParam> yxSkuList = orderBuyParam.getYxSkuList();
		if (CollectionUtils.isEmpty(yxSkuList)) {
			productValidateResult.setYxRestrictSkus(Collections.emptyList());
		} else {
			productValidateResult.setYxRestrictSkus(validateYxOrderInfo(yxSkuList));
		}
		return productValidateResult;
	}

	@Override
	public List<String> getAreaRestrictSkus(List<String> skuIds, JDAddress jdAddress) {
		if (CollectionUtils.isEmpty(skuIds)) {
			return Collections.emptyList();
		}
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("token", jDAccessTokenServiceImpl.getToken());
		multiValueMap.add("skuIds", skuIds.stream().map(Object::toString).collect(Collectors.joining(",")));
		multiValueMap.add("province", jdAddress.getProvinceId());
		multiValueMap.add("city", jdAddress.getCityId());
		multiValueMap.add("county", jdAddress.getCountyId());
		multiValueMap.add("town", jdAddress.getTownId());
		try {
			JSONObject response = restTemplate.postForObject(jdConfigService.getItem_check_area_limit_url(), multiValueMap, JSONObject.class);
			log.info("getAreaRestrictSkus, skuIds={}, jdAddress={}, response={}", skuIds, JSON.toJSONString(jdAddress), response);
			Boolean isSuccess = response.getBoolean("success");
			if (Boolean.FALSE.equals(isSuccess)) {
				return Collections.emptyList();
			}
			JSONArray result = response.getJSONArray("result");
			List<String> restrictSkus = new ArrayList<>();
			for (Object item : result) {
				JSONObject jo = (JSONObject) item;
				if (Boolean.TRUE.equals(jo.getBoolean("isAreaRestrict"))) {
					restrictSkus.add(jo.getString("skuId"));
				}
			}
			return restrictSkus;
		} catch (Exception e) {
			log.error("getAreaRestrictSkus failed, message={}", e.getMessage());
			return Collections.emptyList();
		}
	}

}
