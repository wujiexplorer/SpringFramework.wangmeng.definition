package com.lx.benefits.service.deliverinfo;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.lx.benefits.config.properties.YanXuan;
import com.lx.benefits.sdk.supplier.BaseSupplierInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
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
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.lx.benefits.bean.dto.deliverinfo.ExpressResult;
import com.lx.benefits.bean.dto.deliverinfo.JingdongTokenResult;
import com.lx.benefits.bean.dto.deliverinfo.PackageDeliverInfo;
import com.lx.benefits.bean.dto.deliverinfo.PackageLocationInfo;
import com.lx.benefits.bean.dto.deliverinfo.YanxuanPackageInfo;
import com.lx.benefits.bean.dto.deliverinfo.YanxuanPackageInfo.YanxuanOrderPackage;
import com.lx.benefits.utils.RedisUtils;

import javax.annotation.Resource;

@Service
public class PackageDeliverInfoService extends BaseSupplierInfo{
	private Logger logger = LoggerFactory.getLogger(PackageDeliverInfoService.class);

	@Autowired
	private RedisUtils cacheUtil;

	// 严选接口配置信息
//	@Value("${yx.api_host}")
//	private String yx_api_host;
//	@Value("${yx.api_url}")
//	private String yx_api_url;
//	@Value("${yx.api_key}")
//	private String yx_appKey;
//	@Value("${yx.api_secret}")
//	private String yx_appSecret;

	// 快递100接口配置信息
	@Value("${kuaidi100.poll.url}")
	private String express100_url;
	@Value("${kuaidi100.customerId}")
	private String express100_customerId;
	@Value("${kuaidi100.key}")
	private String express100_authKey;

	// 京东接口配置
	@Value("${jd.access_token_url}")
	private String jd_url;
	@Value("${jd.client_id}")
	private String jd_clientId;
	@Value("${jd.client_secret}")
	private String jd_clientSecret;
	@Value("${jd.username}")
	private String jd_username;
	@Value("${jd.password}")
	private String jd_password;
	@Value("${jd.order_track_url}")
	private String jd_expressUrl;
	@Value("${jd.order_query_url}")
	private String jd_order_query_url;
	
	@Resource
	private YanXuan yanXuan;

	private static final String EXPRESS100_PARAMFORMAT = "{\"com\":\"%s\",\"num\":\"%s\"}";
	private RestTemplate restTemplate = new RestTemplate();
	{
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		for (HttpMessageConverter<?> httpMessageConverter : messageConverters) {
			if (httpMessageConverter instanceof StringHttpMessageConverter) {// 处理文本转换器编码
				StringHttpMessageConverter stringHttpMessageConverter = (StringHttpMessageConverter) httpMessageConverter;
				stringHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
				List<MediaType> mediaTypes = Arrays.asList(MediaType.parseMediaType("text/*;charset=UTF-8"), MediaType.parseMediaType("*/*;charset=UTF-8"));
				stringHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
				break;
			}
		}
		messageConverters.add(new FastJsonHttpMessageConverter());// 添加JSON解析
	}

	/**
	 * 获取严选订单的物流单号
	 * 
	 * <pre>
	 * 接口文档：https://open.you.163.com/doc/API%E6%96%87%E6%A1%A3/1/230
	 * </pre>
	 * 
	 * @param yxOrderId
	 *            严选订单ID
	 */
	public YanxuanPackageInfo getYanxuanOrderInfo(String yxOrderId) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("appKey", yanXuan.getApiKey());
		multiValueMap.add("method", "yanxuan.order.paid.get");
		multiValueMap.add("orderId", yxOrderId);
		multiValueMap.add("timestamp", dateFormat.format(new Date()));
		String sign = multiValueMap.entrySet().stream().map(item -> item.getKey() + "=" + item.getValue().get(0)).collect(Collectors.joining(""));
		sign = yanXuan.getApiSecret() + sign + yanXuan.getApiSecret();
		String md5Digest = DigestUtils.md5DigestAsHex(sign.getBytes()).toUpperCase();
		multiValueMap.set("sign", md5Digest);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);
		try {
			String response = restTemplate.postForObject(yanXuan.getApiHost() + yanXuan.getApiUrl(), httpEntity, String.class);
			logger.info("getYanxuanOrderInfo, orderId={}, response={}", yxOrderId, response);
			JSONObject parse = JSON.parseObject(response, JSONObject.class);
			if (Integer.valueOf(200).equals(parse.getInteger("code"))) {
				String result = parse.getString("result");
				return JSON.parseObject(result, YanxuanPackageInfo.class);
			}
		} catch (HttpClientErrorException e) {
			String responseBody = e.getResponseBodyAsString();
			System.out.println(responseBody);
			logger.error("请求接口失败，HTTP CODE: {}, 失败原因: {}", e.getStatusCode(), responseBody);
		} catch (Exception e) {
			logger.error("请求接口失败, 失败原因: {}", e.getMessage());
		}
		return null;
	}

	/**
	 * 获取严选订单的物流信息
	 * 
	 * <pre>
	 *  接口文档: https://open.you.163.com/doc/API%E6%96%87%E6%A1%A3/1/231
	 * </pre>
	 * 
	 * @param yxOrderId
	 *            严选订单ID
	 * @return
	 */
	public ExpressResult<PackageLocationInfo> getYXPackageDeliverInfo(String yxOrderId) {
		YanxuanPackageInfo yanxuanPackageInfo = getYanxuanOrderInfo(yxOrderId);
		List<YanxuanOrderPackage> orderPackages;
		if (yanxuanPackageInfo == null || CollectionUtils.isEmpty(orderPackages = yanxuanPackageInfo.getOrderPackages())) {
			return ExpressResult.handleFail("暂时没有物流信息");
		}
		YanxuanOrderPackage orderPackage = orderPackages.get(0);
		String expressNo = orderPackage.getExpressNo();
		if (StringUtils.isEmpty(expressNo)) {
			return ExpressResult.handleFail("还未发货");
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("appKey", yanXuan.getApiKey());
		multiValueMap.add("expressCom", orderPackage.getExpressCompany());
		multiValueMap.add("expressNo", expressNo);
		multiValueMap.add("method", "yanxuan.order.express.get");
		multiValueMap.add("orderId", yxOrderId);
		multiValueMap.add("packageId", orderPackage.getPackageId());
		multiValueMap.add("timestamp", dateFormat.format(new Date()));
		String sign = multiValueMap.entrySet().stream().map(item -> item.getKey() + "=" + item.getValue().get(0)).collect(Collectors.joining(""));
		sign = yanXuan.getApiSecret() + sign + yanXuan.getApiSecret();
		String md5Digest = DigestUtils.md5DigestAsHex(sign.getBytes()).toUpperCase();
		multiValueMap.set("sign", md5Digest);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);
		String response = restTemplate.postForObject(yanXuan.getApiHost() + yanXuan.getApiUrl(), httpEntity, String.class);
		logger.info("getYXPackageDeliverInfo, yxOrderId={}, response={}", yxOrderId, response);
		JSONObject parse = JSON.parseObject(response, JSONObject.class);
		if (Integer.valueOf(200).equals(parse.getInteger("code"))) {
			String result = parse.getString("result");
			JSONObject deliverInfos = JSON.parseObject(result, JSONObject.class);
			JSONArray jsonArray = deliverInfos.getJSONArray("content");
			if (!CollectionUtils.isEmpty(jsonArray)) {
				List<PackageLocationInfo> packageLocationInfos = jsonArray.stream().map(item -> (JSONObject) item)
						.map(item -> new PackageLocationInfo(item.getString("desc"), item.getString("time"))).collect(Collectors.toList());

				ExpressResult<PackageLocationInfo> expressResult = ExpressResult.handleSuccess(packageLocationInfos);
				expressResult.setExpressNo(orderPackage.getExpressNo());
				expressResult.setCompany(orderPackage.getExpressCompany());
				return expressResult;
			}
		}
		ExpressResult<PackageLocationInfo> expressResult = ExpressResult.handleFail("暂时没有物流信息");
		expressResult.setExpressNo(orderPackage.getExpressNo());
		expressResult.setCompany(orderPackage.getExpressCompany());
		return expressResult;
	}

	/**
	 * 通过快递100接口查询包裹物流信息
	 * 
	 * <pre>
	 * 接口文档： https://www.kuaidi100.com/openapi/api_post.shtml
	 * </pre>
	 * 
	 * @param expressCompany
	 *            快递公司的编码， 一律用小写字母
	 * @param expressNo
	 *            快递单号
	 */
	public ExpressResult<PackageLocationInfo> getPackageDeliverInfo(String expressCompany, String expressNo) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("customer", express100_customerId);
		String param = String.format(EXPRESS100_PARAMFORMAT, expressCompany.toLowerCase(), expressNo);
		multiValueMap.add("param", param);
		// 按param + key + customer 的顺序进行MD5加密（注意加密后字符串一定要转大写）
		String sign = DigestUtils.md5DigestAsHex((param + express100_authKey + express100_customerId).getBytes()).toUpperCase();
		multiValueMap.add("sign", sign);
		String response = restTemplate.postForObject(express100_url, multiValueMap, String.class);
		logger.info("getPackageDeliverInfo, expressCompany={}, expressNo={}, response={}", expressCompany, expressNo, response);
		PackageDeliverInfo packageDeliverInfo = JSON.parseObject(response, PackageDeliverInfo.class);
		if (StringUtils.isEmpty(packageDeliverInfo.getNu())) {// 获取失败
			return ExpressResult.handleFail("查询无结果，请隔段时间再查");
		}
		List<PackageLocationInfo> data = packageDeliverInfo.getData();
		if (data == null || data.isEmpty()) {
			return ExpressResult.handleFail("暂时没有物流信息");
		}
		ExpressResult<PackageLocationInfo> expressResult = ExpressResult.handleSuccess(data);
		expressResult.setCompany(expressCompany);
		expressResult.setExpressNo(expressNo);
		return expressResult;
	}

	private static final String JD_ACCESS_TOKEN_CACHE_KEY = "jd_access_token";

	/**
	 * 获取京东的AccessToken信息
	 */
	// 缓存处理
	private String getJdAccessToken() {
		String cache = cacheUtil.get(JD_ACCESS_TOKEN_CACHE_KEY);
		if (cache != null) {
			return cache;
		}
		String md5Password=DigestUtils.md5DigestAsHex(jd_password.getBytes());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		String grantType = "access_token";
		multiValueMap.add("grant_type", grantType);
		multiValueMap.add("client_id", jd_clientId);
		multiValueMap.add("client_secret", jd_clientSecret);
		String timestamp = dateFormat.format(new Date());
		multiValueMap.add("timestamp", timestamp);
		multiValueMap.add("username", jd_username);
		multiValueMap.add("password", md5Password);
		String sign = jd_clientSecret + timestamp + jd_clientId + jd_username + md5Password + grantType + jd_clientSecret;
		multiValueMap.add("sign", DigestUtils.md5DigestAsHex(sign.getBytes()).toUpperCase());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);
		String response = restTemplate.postForObject(jd_url, httpEntity, String.class);
		logger.info("getJdAccessToken, response={}", response);
		JingdongTokenResult result = JSON.parseObject(response, JingdongTokenResult.class);
		if (result.getSuccess()) {
			String access_token = result.getResult().getAccess_token();
			int expires_in = (int) (result.getResult().getExpires_in() - 10);
			cacheUtil.set(JD_ACCESS_TOKEN_CACHE_KEY, access_token, expires_in);
			return access_token;
		} else {
			return null;// 获取失败
		}
	}

	/**
	 * 获取京东物流信息
	 * 
	 * @param jdOrderId
	 *            京东的订单
	 */
	public ExpressResult<PackageLocationInfo> getJDPackageDeliverInfo(String jdSkuCode, String jdOrderId) {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		String jdAccessToken = getJdAccessToken();
		if (StringUtils.isEmpty(jdAccessToken)) {
			return ExpressResult.handleFail("获取物流信息失败", jdOrderId, JD_NAME);
		}
		multiValueMap.set("token", jdAccessToken);
		multiValueMap.set("jdOrderId", jdOrderId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);
		if (!StringUtils.isEmpty(jdSkuCode)) {
			String jdOrderInfoStr = restTemplate.postForObject(jd_order_query_url, httpEntity, String.class);
			JSONObject jdOrderInfo = JSONObject.parseObject(jdOrderInfoStr);
			if (Boolean.FALSE.equals(jdOrderInfo.getBoolean("success"))) {
				return ExpressResult.handleFail("获取物流信息失败", jdOrderId, JD_NAME);
			} else {
				JSONObject result = jdOrderInfo.getJSONObject("result");
				if (Integer.valueOf(1).equals(result.getInteger("type"))) {// 父订单
					JSONArray cOrders = jdOrderInfo.getJSONObject("result").getJSONArray("cOrder");
					if (!CollectionUtils.isEmpty(cOrders)) {
						out: for (int i = 0; i < cOrders.size(); i++) {
							JSONObject cOrder = cOrders.getJSONObject(i);
							JSONArray skus = cOrder.getJSONArray("sku");
							if (!CollectionUtils.isEmpty(skus)) {
								for (int j = 0; j < skus.size(); j++) {
									if (jdSkuCode.equals(skus.getJSONObject(j).getString("skuId"))) {
										jdOrderId = cOrder.getString("jdOrderId");
										multiValueMap.set("jdOrderId", jdOrderId);
										break out;
									}
								}
							}
						}
					}
				}
			}
		}
		String response = restTemplate.postForObject(jd_expressUrl, httpEntity, String.class);
		logger.info("getJDPackageDeliverInfo, jdOrderId={}, response={}", jdOrderId, response);
		JSONObject parse = JSONObject.parseObject(response);
		Boolean isSuccess = parse.getBoolean("success");
		if (Boolean.FALSE.equals(isSuccess)) {
			return ExpressResult.handleFail("获取物流信息失败", jdOrderId, JD_NAME);
		}
		JSONObject result = parse.getJSONObject("result");
		JSONArray jsonArray;
		if (result == null || (jsonArray = result.getJSONArray("orderTrack")) == null || jsonArray.isEmpty()) {
			return ExpressResult.handleFail("暂未获取到物流信息", jdOrderId, JD_NAME);
		}
		List<PackageLocationInfo> packageLocationInfos = jsonArray.stream().map(item -> (JSONObject) item)
				.map(item -> new PackageLocationInfo(item.getString("content"), item.getString("msgTime"))).collect(Collectors.toList());
		ExpressResult<PackageLocationInfo> expressResult = ExpressResult.handleSuccess(packageLocationInfos);
		expressResult.setCompany(JD_NAME);
		expressResult.setExpressNo(jdOrderId);
		return expressResult;
	}
	
}
