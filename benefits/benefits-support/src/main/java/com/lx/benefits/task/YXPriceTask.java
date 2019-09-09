package com.lx.benefits.task;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.dto.admin.product.SkuQueryParam;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.config.properties.YanXuan;
import com.lx.benefits.mapper.product.ProductMapper;
import com.lx.benefits.mapper.product.SkuMapper;
import com.lx.benefits.service.yx.YXConfigService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class YXPriceTask {
	@Resource
	private ProductMapper productMapper;
	@Resource(name = "formStringJsonRestTemplate")
	public RestTemplate restTemplate;
	@Resource
	private SkuMapper skuMapper;
	@Resource
	private YanXuan yanXuan;
	@Resource
	@Qualifier("yxConfigService")
	private YXConfigService yxConfigService;

	@Scheduled(cron = "0 03 03 * * ?")
	public void updateYXPrice() {
		Date beginDate = new Date();
		log.info("开始同步严选商品价格,开始时间:{}", beginDate);
		SkuQueryParam skuQueryParam = new SkuQueryParam();
		skuQueryParam.setSupplierId(yxConfigService.YX_ID);
		int count = skuMapper.countByQueryParam(skuQueryParam);
		int pageSize = 100;
		int page = (count - 1) / pageSize + 1;
		PageBean pageBean = new PageBean() {
			@Override
			public Integer getPageSize() {
				return pageSize;
			}
		};
		Map<String, BigDecimal> yxGoodPriceInfoMap = this.getYXGoodPriceInfo();
		if (CollectionUtils.isEmpty(yxGoodPriceInfoMap)) {
			return;
		}
		pageBean.setPageSize(pageSize);
		// CacheBuilder.newBuilder().maximumSize(10000).build();
		for (int i = 1; i <= page; i++) {
			pageBean.setPage(i);
			List<SkuEntity> yxSkuCodes = skuMapper.getThirdSkuCodeWithPrice(yxConfigService.YX_ID.intValue(), pageBean);
			if (CollectionUtils.isEmpty(yxSkuCodes)) {
				continue;
			}
			for (SkuEntity skuEntity : yxSkuCodes) {
				String skuCode = skuEntity.getSkuCode();
				BigDecimal goodsPrice = yxGoodPriceInfoMap.get(skuCode);
				if (goodsPrice == null || goodsPrice.equals(skuEntity.getGoodsPrice())) {
					continue;
				}
				SkuEntity sku = new SkuEntity();
				sku.setId(skuEntity.getId());
				sku.setGoodsPrice(goodsPrice);
				sku.setGoodsMarkprice(goodsPrice);
				sku.setGoodsCostprice(goodsPrice.multiply(BigDecimal.valueOf(0.85)));
				sku.setGoodsRate(BigDecimal.valueOf(0.15));
				sku.setGoodsDiscount(BigDecimal.valueOf(1));
				log.info("更新严选商品价格。。。。" + JSON.toJSONString(sku));
				skuMapper.update(sku);
			}
		}
		log.info("结束同步严选商品价格, 耗时:{} 分钟", (System.currentTimeMillis() - beginDate.getTime()) / (1000 * 60));
	}

	private Map<String, BigDecimal> getYXGoodPriceInfo() {
		log.info("getYXGoodPriceInfo...");
		List<String> spuIds = this.getYXPackageGoodIds();
		if (CollectionUtils.isEmpty(spuIds)) {
			return Collections.emptyMap();
		}
		if (CollectionUtils.isEmpty(spuIds)) {
			return Collections.emptyMap();
		}
		Map<String, BigDecimal> priceMap = new HashMap<>();
		int pageSize = 30;
		int totalCount = spuIds.size();
		int pageTotal = (totalCount - 1) / pageSize + 1;
		for (int i = 0; i < pageTotal; i++) {
			int start = pageSize * i;
			int end = start + pageSize;
			if (end > totalCount) {
				end = totalCount;
			}
			log.info("yanxuan.item.batch.get, start={}, end={}", start, end);
			MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
			multiValueMap.add("itemIds", spuIds.subList(start, end).stream().collect(Collectors.joining(",")));
			multiValueMap.add("method", "yanxuan.item.batch.get");
			this.signParam(multiValueMap);
			JSONObject response = this.sendRequest(multiValueMap, 10);
			if (response != null && Integer.valueOf(200).equals(response.getInteger("code"))) {
				JSONArray spuInfos = response.getJSONArray("result");
				spuInfos.stream().map(item -> (JSONObject) item).forEach(spuInfo -> {
					JSONArray skuList = spuInfo.getJSONArray("skuList");
					for (int j = 0; j < skuList.size(); j++) {
						JSONObject skuInfo = skuList.getJSONObject(j);
						String skuId = skuInfo.getString("id");
						BigDecimal goodsPrice = skuInfo.getBigDecimal("channelPrice");
						priceMap.put(skuId, goodsPrice);
					}
				});
			}
		}
		return priceMap;
	}

	// 获取渠道能售卖的所有商品（SPU）ID
	public List<String> getYXPackageGoodIds() {
		log.info("getYXPackageGoodIds....");
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("method", "yanxuan.item.id.batch.get");
		this.signParam(multiValueMap);
		JSONObject response = sendRequest(multiValueMap, 10);
		if (response != null && Integer.valueOf(200).equals(response.getInteger("code"))) {
			JSONArray jsonArray = response.getJSONArray("result");
			return jsonArray.stream().map(Object::toString).collect(Collectors.toList());
		} else {
			log.error(response.toString());
		}
		return Collections.emptyList();
	}

	private void signParam(MultiValueMap<String, String> multiValueMap) {
		multiValueMap.add("appKey", yanXuan.getApiKey());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		multiValueMap.add("timestamp", dateFormat.format(new Date()));
		String sign = multiValueMap.entrySet().stream().sorted(Comparator.comparing(Entry::getKey)).map(item -> item.getKey() + "=" + item.getValue().get(0))
				.collect(Collectors.joining(""));
		sign = yanXuan.getApiSecret() + sign + yanXuan.getApiSecret();
		String md5Digest = DigestUtils.md5DigestAsHex(sign.getBytes()).toUpperCase();
		multiValueMap.set("sign", md5Digest);
	}

	private synchronized JSONObject sendRequest(MultiValueMap<String, String> request, int retryTime) {
		if (retryTime < 0) {
			return null;
		}
		try {
			JSONObject response = restTemplate.postForObject(yanXuan.getRequestUrl(), request, JSONObject.class);
			return response;
		} catch (HttpClientErrorException e) {
			log.error(e.getMessage());
			if (e.getStatusCode().value() == 429) {
				try {
					TimeUnit.SECONDS.sleep(7);
				} catch (InterruptedException e1) {
				}
			}
		}
		return sendRequest(request, --retryTime);
	}

}
