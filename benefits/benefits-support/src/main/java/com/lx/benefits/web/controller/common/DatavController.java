package com.lx.benefits.web.controller.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/common/datav")
public class DatavController implements InitializingBean {

	@Value("${baidu.tongji.username:xg2016}")
	private String baiduUserName;
	@Value("${baidu.tongji.password:Xg201618}")
	private String baiduPassword;
	@Value("${baidu.tongji.token:baa32bd073b732f54c9bddf0a5367579}")
	private String baiduToken;
	@Value("${baidu.tongji.dataurl:https://api.baidu.com/json/tongji/v1/ReportService/getData}")
	private String baiduDataUrl;
	@Value("${baidu.tongji.sitelisturl:https://api.baidu.com/json/tongji/v1/ReportService/getSiteList}")
	private String baiduSiteListUrl;

	private RestTemplate restTemplate;
	private final JSONObject baiduHeaderJson = new JSONObject();

	@RequestMapping("/getSiteList")
	public Object getDataOverview() {
		JSONObject request = new JSONObject();
		request.put("header", baiduHeaderJson);
		return restTemplate.postForObject(baiduSiteListUrl, request, JSONObject.class);
	}

	@RequestMapping("/overview/{siteId}")
	public Object getDataOverview(@PathVariable String siteId, @RequestParam(defaultValue = "1") Integer granType,
			@RequestParam(defaultValue = "0") Integer pvRadix, @RequestParam(defaultValue = "0") Integer uvRadix,
			@RequestParam(defaultValue = "0") Integer ipRadix) {
		JSONObject request = new JSONObject();
		request.put("header", baiduHeaderJson);
		JSONObject body = new JSONObject();
		request.put("body", body);
		body.put("site_id", siteId);
		body.put("method", "overview/getTimeTrendRpt");
		if (granType.equals(1)) {
			body.put("metrics", "pv_count,visitor_count,ip_count");
			body.put("gran", "day");
			Calendar instance = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			body.put("end_date", dateFormat.format(instance.getTime()));// 今天
			instance.add(Calendar.DAY_OF_MONTH, -1);
			body.put("start_date", dateFormat.format(instance.getTime()));// 昨天
		} else {
			body.put("metrics", "pv_count");
			body.put("gran", "hour");
		}
		JSONObject result = restTemplate.postForObject(baiduDataUrl, request, JSONObject.class);
		log.info("getDataOverview result: {}", result.toString());
		if (result.getJSONObject("header").getIntValue("succ") == 1) {
			JSONArray response = new JSONArray();
			JSONObject data = result.getJSONObject("body").getJSONArray("data").getJSONObject(0);
			JSONArray jsonArray = data.getJSONObject("result").getJSONArray("items");
			if (granType.equals(1)) {
				JSONArray fieldValues = jsonArray.getJSONArray(1);
				String[] yesterday = fieldValues.getString(0).replaceAll("[\\[ \\]]", "").split(",");
				String[] today = fieldValues.getString(1).replaceAll("[\\[ \\]]", "").split(",");
				response.add(new ContentObject(""));
				response.add(new ContentObject("PV"));
				response.add(new ContentObject("UV"));
				response.add(new ContentObject("IP"));
				response.add(new ContentObject("今日"));
				response.add(new ContentObject(today[0], pvRadix));
				response.add(new ContentObject(today[1], uvRadix));
				response.add(new ContentObject(today[2], ipRadix));
				response.add(new ContentObject("昨日"));
				response.add(new ContentObject(yesterday[0], pvRadix));
				response.add(new ContentObject(yesterday[1], uvRadix));
				response.add(new ContentObject(yesterday[2], ipRadix));
			} else {
				JSONArray hours = jsonArray.getJSONArray(0);
				JSONArray hourValues = jsonArray.getJSONArray(1);
				for (int i = 0; i < hours.size(); i++) {
					String x = hours.getJSONArray(i).getString(0);
					String y = hourValues.getJSONArray(i).getString(0);
					response.add(new XYObject(x, y, pvRadix));
				}
			}
			return response;
		} else {
			return Response.fail("获取失败!");
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		baiduHeaderJson.clear();
		baiduHeaderJson.put("username", baiduUserName);
		baiduHeaderJson.put("password", baiduPassword);
		baiduHeaderJson.put("token", baiduToken);
		restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		messageConverters.clear();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
	}

	@SuppressWarnings("unused")
	private static class ContentObject {
		private String content;

		public ContentObject(String content) {
			if (content.contains("--")) {
				this.content = "0";
			} else {
				this.content = content;
			}
		}

		public ContentObject(String content, Integer radix) {
			if (content.contains("--")) {
				content = "0";
			}
			if (radix != null && radix > 0) {
				try {
					this.content = (Integer.valueOf(content.trim()) + radix) + "";
				} catch (Exception e) {
					log.error(e.getMessage());
					this.content = content;
				}
			} else {
				this.content = content;
			}
		}

		public String getContent() {
			return content;
		}
	}

	@SuppressWarnings("unused")
	private static class XYObject {
		private String x;
		private String y;

		public XYObject(String x, String y) {
			this.x = x;
			if (y.contains("--")) {
				this.y = "0";
			} else {
				this.y = y;
			}
		}

		public XYObject(String x, String y, Integer radix) {
			this.x = x;
			if (y.contains("--")) {
				this.y = "0";
			} else {
				if (radix != null && radix > 0) {
					try {
						this.y = (Integer.valueOf(y.trim()) + radix) + "";
					} catch (Exception e) {
						log.error(e.getMessage());
						this.y = y;
					}
				} else {
					this.y = y;
				}
			}

		}

		public String getX() {
			return x;
		}

		public String getY() {
			return y;
		}

	}

}
