package com.lx.benefits.service.yx;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.yx.utils.DateUtils;
import com.lx.benefits.bean.dto.yx.utils.YXOpenApiUtils;
import com.lx.benefits.bean.util.RetryUtil;
import com.lx.benefits.config.properties.YanXuan;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ldr on 2017/5/26.
 */
@Service
public class YXBaseService extends YXConfigService{


    Logger logger = LoggerFactory.getLogger(this.getClass());

    private int timeout = 60000;

    private String defaultCharset = "UTF-8";

    private int maxTotal = 200;

    private int defaultMaxPerRoute = 50;

    private HttpClient httpClient;
    @Resource
    private YanXuan yanXuan;

    @PostConstruct
    public void init() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    public String handle(String path,Map<String, String> data, String method) {
        // 构建时间戳
        long currentTime = System.currentTimeMillis();
        String timestamp = DateUtils.parseLongToString(currentTime, "yyyy-MM-dd HH:mm:ss");

        TreeMap<String, String> paramsMap = YXOpenApiUtils.packageParams(yanXuan.getApiKey(), yanXuan.getApiSecret(), timestamp, method, data);

        // 组装参数
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        String url = yanXuan.getApiHost() + yanXuan.getApiUrl();
        if (StringUtils.isNotBlank(path)) {
            url = url + path;
        }
        try {
            long t1 = System.currentTimeMillis();
            // 调用严选open api
            String response = doPost(url, params, "utf-8");
            long t2 = System.currentTimeMillis();
            logger.info("{} method={}, params={}, response={}, 耗时={}", method, method, JSONObject.toJSONString(params), response, t2 - t1);
            return response;
        } catch (Exception e) {
            logger.error("{} Exception={}, method={}, params={}", method, e.getMessage(), method, JSONObject.toJSONString(params));
            logger.error(method, e);
            RetryUtil.setDelayRetryTimes(3, 10000).retry(data,method);
            return null;
        }
    }


    public String handle(Map<String, String> data, String method) {
        // 构建时间戳
        long currentTime = System.currentTimeMillis();
        String timestamp = DateUtils.parseLongToString(currentTime, "yyyy-MM-dd HH:mm:ss");

        TreeMap<String, String> paramsMap = YXOpenApiUtils.packageParams(yanXuan.getApiKey(), yanXuan.getApiSecret(), timestamp, method, data);

        // 组装参数
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
       String url = yanXuan.getApiHost() + yanXuan.getApiUrl();
        try {
            long t1 = System.currentTimeMillis();
            // 调用严选open api
            String response = doPost(url, params, "utf-8");
            long t2 = System.currentTimeMillis();
            logger.info("{} method={}, params={}, response={}, 耗时={}", method, method, JSONObject.toJSONString(params), response, t2 - t1);
            return response;
        } catch (Exception e) {
            logger.error("{} Exception={}, method={}, params={}", method, e.getMessage(), method, JSONObject.toJSONString(params));
            logger.error(method, e);
            RetryUtil.setDelayRetryTimes(3, 10000).retry(data,method);
            return null;
        }
    }


    public String doPost(String url, List<NameValuePair> parameters, String charset) throws IOException {
        charset = getCharset(charset);
        HttpPost postMethod = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).setRedirectsEnabled(false).build();
        postMethod.setConfig(requestConfig);
        if (parameters != null && parameters.size() > 0) {
            UrlEncodedFormEntity data = new UrlEncodedFormEntity(parameters, charset);
            postMethod.setEntity(data);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("[HttpClientTemplate#doPost] request to url={}, param={}", url, JSONObject.toJSON(parameters));
        }
        String result = requestAndParse(postMethod, charset);
        if (logger.isDebugEnabled()) {
            logger.debug("[HttpClientTemplate#doPost] response from url={}, param={}, data={}", url, JSONObject.toJSON(parameters), result);
        }
        return result;
    }

    private String getCharset(String charset) {
        if (StringUtils.isEmpty(charset)) {
            return defaultCharset;
        }
        return charset;
    }

    private String requestAndParse(HttpUriRequest httpRequest, String charset) throws IOException {
        HttpResponse httpResponse = httpClient.execute(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        if (null == statusLine) {
            throw new IOException("status not specified");
        }
        int statusCode = statusLine.getStatusCode();
        if (statusCode < 200 || statusCode > 299) {
            String val = EntityUtils.toString(httpResponse.getEntity(), charset);
            logger.info("--------------------" + val);
            EntityUtils.consumeQuietly(httpResponse.getEntity());
            throw new IOException("status code: " + statusCode);
        }
        HttpEntity entity = httpResponse.getEntity();
        if (null == entity) {
            return null;
        }
        return EntityUtils.toString(entity, charset);
    }

    public String getString(List<Long> skuIds) {
        if (CollectionUtils.isEmpty(skuIds)) {
            return StringUtils.EMPTY;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < skuIds.size(); i++) {
            if (skuIds.get(i) == null) {
                continue;
            }
            stringBuilder.append(skuIds.get(i));
            if (i < skuIds.size() - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
