package com.lx.benefits.task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.dto.admin.product.SkuQueryParam;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.enums.ItemStatusEnum;
import com.lx.benefits.mapper.product.ProductMapper;
import com.lx.benefits.mapper.product.SkuMapper;
import com.lx.benefits.service.jd.IJDAccessTokenService;
import com.lx.benefits.service.jd.JDConfigService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JDPriceTask {
	@Resource
	private ProductMapper productMapper;
	@Resource(name = "formStringJsonRestTemplate")
	public RestTemplate restTemplate;
	@Resource
	@Qualifier("jdConfigService")
	private JDConfigService jdConfigService;
	@Resource
	private IJDAccessTokenService jDAccessTokenServiceImpl;
	@Resource
	private SkuMapper skuMapper;

	@Scheduled(cron = "0 03 04 * * ?")
	public void updateJDPriceAndStatus() {
		Date beginDate = new Date();
		log.info("开始同步京东商品价格和上下架状态,开始时间:{}", beginDate);
		SkuQueryParam skuQueryParam = new SkuQueryParam();
		skuQueryParam.setSupplierId(jdConfigService.JD_ID);
		int count = skuMapper.countByQueryParam(skuQueryParam);
		int pageSize = 100;
		int page = (count - 1) / pageSize + 1;
		PageBean pageBean = new PageBean() {
			@Override
			public Integer getPageSize() {
				return pageSize;
			}
		};
		pageBean.setPageSize(pageSize);
		String token = jDAccessTokenServiceImpl.getToken();
		String priceUrl = jdConfigService.getPrice_sale_price_url();
		String skuStateUrl = jdConfigService.getItem_state_url();
		for (int i = 1; i <= page; i++) {
			pageBean.setPage(i);
			List<SkuEntity> skuCodes = skuMapper.getThirdSkuCodeWithPrice(jdConfigService.JD_ID.intValue(), pageBean);
			if (CollectionUtils.isEmpty(skuCodes)) {
				continue;
			}
			Map<String, SkuEntity> skuCodeMap = skuCodes.stream()
					.collect(Collectors.toMap(SkuEntity::getSkuCode, UnaryOperator.identity(), (item1, item2) -> item1));
			String skuIdJoin = skuCodeMap.keySet().stream().collect(Collectors.joining(","));
			MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
			multiValueMap.add("token", token);
			multiValueMap.add("sku", skuIdJoin);
			multiValueMap.add("queryExts", "marketPrice");
			JSONObject response = restTemplate.postForObject(priceUrl, multiValueMap, JSONObject.class);
			if (response.getBooleanValue("success")) {
				Date now = new Date();
				JSONArray result = response.getJSONArray("result");
				List<SkuEntity> updateRecorders = result.stream().map(item -> (JSONObject) item).map(item -> {
					BigDecimal goodsPrice = item.getBigDecimal("jdPrice");
					BigDecimal costPrice = item.getBigDecimal("price");
					BigDecimal marketPrice = item.getBigDecimal("marketPrice");
					SkuEntity skuEntity = skuCodeMap.get(item.getString("skuId"));
					if (skuEntity == null) {
						return null;
					}
					if (!Objects.equals(goodsPrice, skuEntity.getGoodsPrice()) || Objects.equals(costPrice, skuEntity.getGoodsCostprice())
							|| Objects.equals(marketPrice, skuEntity.getGoodsMarkprice())) {
						SkuEntity recorder = new SkuEntity();
						recorder.setId(skuEntity.getId());
						recorder.setGoodsPrice(goodsPrice);
						recorder.setGoodsCostprice(costPrice);
						recorder.setGoodsMarkprice(marketPrice);
						try {
							BigDecimal goodsRate = goodsPrice.subtract(costPrice).divide(goodsPrice, 4, RoundingMode.HALF_UP);
							if (goodsRate.compareTo(BigDecimal.ZERO) < 0) {// 销售价格小于成本价
								recorder.setStatus(0);// 直接下架
							}
							recorder.setGoodsRate(goodsRate);
							recorder.setGoodsDiscount(goodsPrice.divide(marketPrice, 2, RoundingMode.HALF_UP));
							recorder.setUpdatedTime(now);
							recorder.setUpdatedUser("SYSTEM");
						} catch (Exception e) {
							log.error(e.getMessage());
							return null;
						}
						return recorder;
					}
					return null;
				}).filter(Objects::nonNull).collect(Collectors.toList());
				if (!CollectionUtils.isEmpty(updateRecorders)) {
					log.info("更新京东商品价格。。。。" + JSON.toJSONString(updateRecorders));
					skuMapper.updateBatch(updateRecorders);
				}
			}
			multiValueMap = new LinkedMultiValueMap<>();
			multiValueMap.add("token", token);
			multiValueMap.add("sku", skuIdJoin);
			JSONObject skuStateResponse = restTemplate.postForObject(skuStateUrl, multiValueMap, JSONObject.class);
			if (skuStateResponse.getBooleanValue("success")) {
				JSONArray result = skuStateResponse.getJSONArray("result");
				List<Long> downSkuIds = result.stream().map(item -> (JSONObject) item).filter(item -> Integer.valueOf(0).equals(item.getInteger("state")))
						.map(item -> item.getString("sku")).map(item -> skuCodeMap.get(item)).filter(item -> {
							if (item != null && ItemStatusEnum.ONLINE.getValue().equals(item.getStatus())) {
								return true;
							}
							return false;
						}).map(SkuEntity::getId).collect(Collectors.toList());
				if (!CollectionUtils.isEmpty(downSkuIds)) {
					log.info("更新京东商品sku状态为暂停销售状态。。。。" + JSON.toJSONString(downSkuIds));
					skuMapper.updateSkuStatus(downSkuIds, "SYSTEM", ItemStatusEnum.OFFLINE.getValue());
				}
			}
		}
		log.info("结束同步京东商品价格和上下架状态, 耗时:{} 分钟", (System.currentTimeMillis() - beginDate.getTime()) / (1000 * 60));
	}

}
