package com.lx.benefits.bean.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: mickey
 * @Date: 2018/8/21655
 * @Description:
 */
public class HttpProtocolUtil {

    private enum HttpResultType{
        STRING,
        BYTES
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpProtocolUtil.class);

    private int     defaultConnectionTimeout    = 8000; //默认连接超时时间
    private int     defaultSoTimeout            = 30000;//默认响应超时时间
    private int     defaultIdleConnTimeout      = 60000;//默认闲置连接超时时间

    private int     defaultHttpConnectionManagerTimeout = 3000; //等待httpConnectionManager返回连接超时时间

    private int     defaultMaxTotalConn         = 80;   //默认最大连接数
    private int     defaultMaxConnPerHost       = 30;   //单host最大连接数

    private String  defaultCharset              ="UTF-8"; //默认字符集


    private static HttpProtocolUtil httpProtocolUtil = new HttpProtocolUtil();

    public static HttpProtocolUtil getInstance(){ return httpProtocolUtil;}

    private HttpConnectionManager connectionManager;
    /**
     *
     * @param url
     * @param params
     * @param charset
     * @return
     * @throws Exception
     */
    public static String postData(String url, Map<String, String> params, String charset) throws Exception {
        long start = System.currentTimeMillis();
        HttpProtocolUtil httpProtocolUtil = getInstance();
        String res = httpProtocolUtil.executePost(url, params, charset);
        LOGGER.info("HTTP_PROTOCOL_UTIL:EXECUTE_POST:url={},cost={}ms", url, System.currentTimeMillis() - start);
//        System.out.println("HTTP_PROTOCOL_UTIL:EXECUTE_POST:cost="+(System.currentTimeMillis() - start));
        return res;
    }

    /**
     *
     * @param url
     * @param charset
     * @return
     * @throws Exception
     */
    public static String getData(String url, String charset) throws Exception {
        long start = System.currentTimeMillis();
        HttpProtocolUtil httpProtocolUtil = getInstance();
        String res = httpProtocolUtil.executeGet(url, charset);
        LOGGER.info("HTTP_PROTOCOL_UTIL:EXECUTE_GET:url={},cost={}", url, System.currentTimeMillis() - start);
//        System.out.println("HTTP_PROTOCOL_UTIL:EXECUTE_GET:cost="+(System.currentTimeMillis() - start));
        return res;
    }




    private HttpProtocolUtil(){
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
        connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);

        IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
        ict.addConnectionManager(connectionManager);
        ict.setConnectionTimeout(defaultIdleConnTimeout);

        ict.start();
    }

    private HttpClient getHttpClient(){
        final HttpClient httpClient = new HttpClient(connectionManager);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);

        httpClient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);

        return httpClient;
    }

    /**
     * 执行POST请求
     * @param url
     * @param params
     * @param charset
     * @return
     * @throws Exception
     */
    public String executePost(String url, Map<String, String> params, String charset) throws Exception {
        String cs = charset == null?defaultCharset:charset;

        HttpRequest request = new HttpRequest(HttpResultType.STRING);
        request.setMethod(HttpRequest.HTTP_POST);
        request.setCharSet(cs);
        request.setUrl(url);
        request.setParams(assembleRequestParams(params));
        HttpResponse response = execute(request);

        if(response != null){
            return response.getStringResult(cs);
        }
        return response.getStringResult(charset);
    }

    /**
     * 执行GET请求
     * @param url
     * @param charset
     * @return
     * @throws Exception
     */
    public String executeGet(String url, String charset) throws Exception {
        long start = System.currentTimeMillis();

        String cs = charset == null?defaultCharset:charset;

        HttpRequest request = new HttpRequest(HttpResultType.STRING);
        request.setMethod(HttpRequest.HTTP_GET);
        request.setCharSet(cs);
        request.setUrl(url);
        HttpResponse response = execute(request);

        LOGGER.info("HTTP_PROTOCOL_UTIL:EXECUTE_GET:url={},cost={}", url, System.currentTimeMillis() - start);

        if(response != null){
            return response.getStringResult(cs);
        }
        return null;
    }

    private HttpResponse execute(HttpRequest request){
        final HttpClient httpClient = getHttpClient();

        HttpMethod method = null;
        if(request.getMethod() == HttpRequest.HTTP_POST){
            method = new PostMethod(request.getUrl());
            ((PostMethod)method).addParameters(request.getParams());
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, request.getCharSet());
        }else if(request.getMethod() == HttpRequest.HTTP_GET){
            method = new GetMethod(request.getUrl());
            method.getParams().setCredentialCharset(request.getCharSet());
            method.setQueryString(request.getQueryString());
            method.setRequestHeader("Accept-Language","zh-CN,zh;q=0.8");
        }

        HttpResponse response = new HttpResponse();
        try {
            httpClient.executeMethod(method);
            if(request.getResultType() == HttpResultType.BYTES){
                response.setByteResult(method.getResponseBody());
            }else if(request.getResultType() == HttpResultType.STRING){
                response.setStringResult(new String(method.getResponseBody(), request.getCharSet()));
            }
        } catch (final Exception e) {
            LOGGER.error("HTTP_PROTOCOL_UTIL_EXECUTE_EXCEPTION", e);
            return null;
        } finally {
            method.releaseConnection();
        }
        return response;
    }

    private synchronized static NameValuePair[] assembleRequestParams(Map<String, String> data) {
        final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();

        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            nameValueList.add(new NameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }

        return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
    }

    private class HttpResponse{
        private String stringResult;

        private byte[] byteResult;

        public String getStringResult(String charset) throws UnsupportedEncodingException{
            if(stringResult != null){
                return stringResult;
            }
            if(byteResult != null){
                return new String(byteResult, charset);
            }
            return null;
        }

        public void setStringResult(String stringResult) {
            this.stringResult = stringResult;
        }

        public byte[] getByteResult() {
            if(byteResult != null){
                return byteResult;
            }
            if(stringResult != null){
                return stringResult.getBytes();
            }
            return null;
        }

        public void setByteResult(byte[] byteResult) {
            this.byteResult = byteResult;
        }
    }

    private class HttpRequest{
        private static final String HTTP_GET = "GET";
        private static final String HTTP_POST = "POST";

        private String url              = null;
        private NameValuePair[] params  = null;
        private String method           = HTTP_POST;
        private String queryString      = null;
        private String charSet          = "UTF-8";

        private HttpResultType resultType = HttpResultType.BYTES;

        public HttpRequest(HttpResultType resultType){
            super();
            this.resultType = resultType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public NameValuePair[] getParams() {
            return params;
        }

        public void setParams(NameValuePair[] params) {
            this.params = params;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getQueryString() {
            return queryString;
        }

        public void setQueryString(String queryString) {
            this.queryString = queryString;
        }

        public String getCharSet() {
            return charSet;
        }

        public void setCharSet(String charSet) {
            this.charSet = charSet;
        }

        public HttpResultType getResultType() {
            return resultType;
        }

        public void setResultType(HttpResultType resultType) {
            this.resultType = resultType;
        }
    }


//    public static void main(String[] args){

//        CountDownLatch countDownLatch = new CountDownLatch(1);

//        Thread[] getThreads = new Thread[50];
//        for(int i = 0; i < 50; i++){
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    String getURL = "http://www.weather.com.cn/data/sk/101190408.html";
//                    try{
//                        countDownLatch.await();
//                        System.out.println(HttpProtocolUtil.getData(getURL, "UTF-8"));
//                    }catch (Exception ex){
//                        ex.printStackTrace();
//                    }
//                }
//            });
//            t.start();
//            getThreads[i] = t;
//        }
//        countDownLatch.countDown();




//        Thread[] postThreads = new Thread[50];
//        for(int i = 0; i < 50; i++){
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    String postURL = "https://m.lixiangshop.com//stock.htm";
//                    try{
//                        System.out.println(HttpProtocolUtil.postData(postURL, new HashMap<String,String>(), "UTF-8"));
//                    }catch (Exception ex){
//                        ex.printStackTrace();
//                    }
//                }
//            });
//            t.start();
//            postThreads[i] = t;
//        }
//        countDownLatch.countDown();
//    }
}
