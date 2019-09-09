package com.lx.benefits.sdk.yian;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lx.benefits.bean.enums.yianapi.enums.YiAnAPI;
import com.lx.benefits.bean.enums.yianapi.model.YiAnResult;
import com.lx.benefits.bean.enums.yianapi.model.YiAnTokenRequest;
import com.lx.benefits.bean.enums.yianapi.model.YiAnTokenResult;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.config.properties.YiAn;
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
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lidongri on 2018/11/13.
 */
@Component
public class YiAnHttpUtil {

    Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Value("${yian.api_host}")
//    private String host;
//
//    @Value("${yian.client_id}")
//    private String clientId;
//
//    @Value("${yian.client_secret}")
//    private String clientSecret;

    private int timeout = 60000;

    private String defaultCharset = "UTF-8";

    private int maxTotal = 200;

    private int defaultMaxPerRoute = 50;

    private HttpClient httpClient;

    @Resource
    private YiAn yiAn;

    @PostConstruct
    public void init() {}

    public YiAnResult<YiAnTokenResult> token() {
        try {
            YiAnTokenRequest req = new YiAnTokenRequest();
            req.setClient_id(yiAn.getClientId());
            req.setClient_secret(yiAn.getClientSecret());
            String s = handle(YiAnAPI.TOKEN, req, null);
            logger.info("YI-AN-GET-TOKEN-RES:"+String.valueOf(s));
            YiAnResult<YiAnTokenResult> res = JsonUtil.parseObject(s, new TypeReference<YiAnResult<YiAnTokenResult>>() {
            });
            if (res == null || !res.isSuccess()) {
                logger.error("YIAN-TOKEN-ERROR" + JsonUtil.toString(res));
            }
            return res;

        }catch (Exception e){
            logger.error("YIAN-TOKEN-ERROR",e);
            return null;
        }
    }

    public String handle(YiAnAPI yiAnAPI, Object data, String token) {
        // 构建时间戳
        long currentTime = System.currentTimeMillis();
        String jsonData = JsonUtil.toString(data, SerializerFeature.MapSortField);
        StringBuilder builder = new StringBuilder();
        builder.append(jsonData).append(yiAn.getClientSecret());
       // String sign = MD5Util.MD5(builder.toString());
        TreeMap<String, String> paramsMap = new TreeMap<>();
        if (token != null) {
            paramsMap.put("Authorization", "Bearer " + token);
        }
        paramsMap.put("data", jsonData);
        // 组装参数
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        String url = yiAn.getApiHost() + yiAnAPI.getPath();
        try {
            long t1 = System.currentTimeMillis();

            String response = doPost(url, params, defaultCharset);

            long t2 = System.currentTimeMillis();

            logger.info("{} params={}, response={}, 耗时={}", yiAnAPI.name(), (params), response, t2 - t1);

            return response;
        } catch (Exception e) {
            logger.error("{} Exception={}, method={}, params={}", yiAnAPI.name(), e.getMessage(), paramsMap, JSONObject.toJSONString(params));
            logger.error(yiAnAPI.name() + " REQUEST-FAIL:", e);
            return null;
        }
    }


    public String doPost(String url, List<NameValuePair> parameters, String charset) throws IOException {
        charset = getCharset(charset);
        HttpPost postMethod = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).
                setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).setRedirectsEnabled(false).build();
        postMethod.setConfig(requestConfig);
        postMethod.setHeader("Content-Type", "application/json");

        for (NameValuePair pair : parameters) {
            if (pair.getName().equals("Authorization")) {
                postMethod.setHeader(pair.getName(), pair.getValue());
            } else if (pair.getName().equals("Accept")) {
                postMethod.setHeader(pair.getName(), pair.getValue());
            } else if (pair.getName().equals("data")) {
                StringEntity entity = new StringEntity(pair.getValue(), charset);
                postMethod.setEntity(entity);
            } else {
                UrlEncodedFormEntity data = new UrlEncodedFormEntity(parameters, charset);
                postMethod.setEntity(data);

            }
        }
        if (logger.isDebugEnabled()) {
            // logger.debug("[HttpClientTemplate#doPost] request to url={}, param={}", url, JSONObject.toJSON(parameters));
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
        //以下为了解决https 证书失败的问题
        X509TrustManager xtm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        try {
            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[]{xtm}, null);
            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上

            httpClient = new DefaultHttpClient();

            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));

            HttpResponse httpResponse = httpClient.execute(httpRequest);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (null == statusLine) {
                throw new IOException("status not specified");
            }
            int statusCode = statusLine.getStatusCode();
//        if (statusCode < 200 || statusCode > 299) {
//
//            String val = EntityUtils.toString(httpResponse.getEntity(), charset);
//            logger.info("--------------------" + val);
//
//            EntityUtils.consumeQuietly(httpResponse.getEntity());
//
//         //   throw new IOException("status code: " + statusCode);
//        }
            HttpEntity entity = httpResponse.getEntity();
            if (null == entity) {
                return null;
            }
            return EntityUtils.toString(entity, charset);
        } catch (Exception e) {
            logger.error("YIAN-HTTP-CLIENT-INIT-ERROR:", e);
        } finally {
            httpClient.getConnectionManager().shutdown(); //关闭连接,释放资源
        }
        return null;
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
