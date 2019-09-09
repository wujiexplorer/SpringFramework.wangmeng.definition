/**
 * NewHeight.com Inc.
 * Copyright (c) 2007-2014 All Rights Reserved.
 */
package com.lx.benefits.bean.util;

import com.apollo.common.exception.BusinessException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * HTTP请求工具类
 * </pre>
 * 
 * @author szy
 */

public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	private static String DEFAULT_CHARSET = "UTF-8";
	private static int DEFAULT_CONNECTION_TIMEOUT = 10 * 1000;
	private static int DEFAULT_SO_TIMEOUT = 10 * 1000;

	public static String addUrl(String head, String tail) {
		if (head.endsWith("/")) {
			if (tail.startsWith("/")) {
				return head.substring(0, head.length() - 1) + tail;
			} else {
				return head + tail;
			}
		} else {
			if (tail.startsWith("/")) {
				return head + tail;
			} else {
				return head + "/" + tail;
			}
		}
	}

	/**
	 * 
	 * <pre>
	 * post请求数据
	 * </pre>
	 * 
	 * @param url
	 * @param params
	 * @param codePage
	 * @return
	 * @throws Exception
	 */
	public static String postData(String url, Map<String, String> params, String charset) throws Exception {

		long start = System.currentTimeMillis();
		String res = postDataWithLock(url, params, charset);
		logger.info("HTTP_CLIENT_UTIL:HTTP_POST_DATA: url={}, cost={}ms",
				url, System.currentTimeMillis()-start);
		return res;
	}
	public synchronized static String postDataWithLock(String url, Map<String, String> params, String charset) throws Exception {

		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(DEFAULT_SO_TIMEOUT);

		final PostMethod method = new PostMethod(url);
		charset = StringUtils.isBlank(charset) ? DEFAULT_CHARSET : charset;
		if (params != null) {
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
			method.setRequestBody(assembleRequestParams(params));
		}
		String result = "";
		try {
			httpClient.executeMethod(method);
			result = new String(method.getResponseBody(), charset);
		} catch (final Exception e) {
			logger.error("HTTP以POST请求数据异常", e);
			throw e;
		} finally {
			method.releaseConnection();
		}
		return result;
	}
	
	/**
	 * 
	 * <pre>
	 * post请求数据
	 * </pre>
	 * 
	 * @param url
	 * @param params
	 * @param codePage
	 * @return
	 * @throws Exception
	 */
	public synchronized static String postData(String url, String content, String contentType, Map<String, String> headers) throws Exception {

		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(DEFAULT_SO_TIMEOUT);

		final PostMethod method = new PostMethod(url);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, DEFAULT_CHARSET);
		method.setRequestEntity(new StringRequestEntity(content, contentType, DEFAULT_CHARSET));
		
		if (null != headers && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				method.setRequestHeader(entry.getKey(), entry.getValue());
			}
		}
		String result = "";
		try {
			httpClient.executeMethod(method);
			result = new String(method.getResponseBody(), DEFAULT_CHARSET);
		} catch (final Exception e) {
			logger.error("HTTP以POST请求数据异常", e);
			throw e;
		} finally {
			method.releaseConnection();
		}
		return result;
	}

	/**
	 * 
	 * <pre>
	 * get请求数据
	 * </pre>
	 * 
	 * @param url
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public synchronized static String getData(String url, String charset) throws Exception {
		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(DEFAULT_SO_TIMEOUT);

		final GetMethod method = new GetMethod(url);
		String result = "";
		try {
			
			method.setRequestHeader("Accept-Language","zh-CN,zh;q=0.8");
			httpClient.executeMethod(method);
			charset = StringUtils.isBlank(charset) ? DEFAULT_CHARSET : charset;
			result = new String(method.getResponseBody(), charset);
		} catch (final Exception e) {
			logger.error("HTTP以GET请求数据异常", e);
			throw e;
		} finally {
			method.releaseConnection();
		}
		return result;
	}

	public static String httpGetRequest(String url, Map<String, Object> params,CloseableHttpClient httpClient) throws URISyntaxException {
		URIBuilder ub = new URIBuilder(url);

		ArrayList<org.apache.http.NameValuePair> pairs = covertParams2NVPS(params);
		ub.setParameters(pairs);

		RequestConfig.Builder config = RequestConfig.copy(RequestConfig.DEFAULT);
		config.setConnectionRequestTimeout(DEFAULT_CONNECTION_TIMEOUT);
		config.setSocketTimeout(DEFAULT_SO_TIMEOUT);
		HttpGet httpGet = new HttpGet(ub.build());
		httpGet.setConfig(config.build());

		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			// response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// long len = entity.getContentLength();// -1 表示长度未知
				return EntityUtils.toString(entity);
			}
		} catch (IOException e) {
			throw new BusinessException("发起http请求失败");
		} finally {
			try {
				if (response != null) {
					// 释放Socket流
					response.close();
				}

				// 释放Connection
				// httpClient.close();
			} catch (IOException e) {
				throw new BusinessException("发起http请求失败");
			}
		}

		return "";
	}

	private static ArrayList<org.apache.http.NameValuePair> covertParams2NVPS(Map<String, Object> params) {
		ArrayList<org.apache.http.NameValuePair> pairs = new ArrayList<>();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
		}

		return pairs;
	}

	/**
	 * 组装http请求参数
	 * 
	 * @param params
	 * @param menthod
	 * @return
	 */
	private synchronized static NameValuePair[] assembleRequestParams(Map<String, String> data) {
		final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();

		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			nameValueList.add(new NameValuePair((String) entry.getKey(), (String) entry.getValue()));
		}

		return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
	}



}
