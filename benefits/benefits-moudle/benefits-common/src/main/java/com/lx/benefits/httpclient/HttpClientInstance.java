package com.lx.benefits.httpclient;

import com.google.common.primitives.Bytes;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class HttpClientInstance {

    @Autowired
    CloseableHttpClient httpClient;

    public String get(String url) throws Exception {
        return getResponseContent(url, new HttpGet(url));
    }

    public String get(String url, Map<String, String> header) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        if (MapUtils.isNotEmpty(header)) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        return getResponseContent(url, httpGet);
    }

    public String get(String url, String headerAcceptCharset) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        if (StringUtils.isNotEmpty(headerAcceptCharset)) {
            httpGet.addHeader("Accept-Charset", headerAcceptCharset);
        }
        return getResponseContent(url, httpGet);
    }


    public String post(String url, String entity, String headerAcceptCharset) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotEmpty(headerAcceptCharset)) {
            httpPost.setHeader("Accept-Charset", headerAcceptCharset);
        }
        httpPost.setEntity(new StringEntity(entity, "UTF-8"));
        return getResponseContent(url, httpPost);
    }

    public String post(String url, String entity) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept-Charset", "UTF-8");
        httpPost.addHeader("Content-Type", "application/json;");
        httpPost.setHeader("Accept", "text/htm,application/json,*/*");
        httpPost.setEntity(new StringEntity(entity, "UTF-8"));
        return getResponseContent(url, httpPost);
    }


    /**
     * post方法带自定义的header头
     * @param httpPost
     * @param url
     * @return
     * @throws Exception
     */
    public String postWithHeader(HttpPost httpPost, String url) throws Exception {
        return getResponseContent(url,httpPost);
    }

    /**
     * 返回流的post方法
     *
     * @param url
     * @param body
     * @return
     * @throws Exception
     */
    public InputStream postInputStream(String url, String body) throws Exception {

        HttpPost httpPost = new HttpPost(url);  // 接口
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");//必须是json模式的 post
        StringEntity entity;
        entity = new StringEntity(body);
        entity.setContentType("image/png");

        httpPost.setEntity(entity);
        HttpResponse response = null;
        try {

            response = httpClient.execute(httpPost);
            HttpEntity httpEntity=response.getEntity();
            InputStream inputStream=httpEntity.getContent();
            return inputStream;
        } catch (Exception e) {
            throw new Exception("got an error from HTTP for url : " + URLDecoder.decode(url, "UTF-8"), e);
        }

    }


    public String postKuaidi100(String url, String entity) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept-Charset", "UTF-8");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(new StringEntity(entity, "UTF-8"));
        return getResponseContent(url, httpPost);
    }

    public String post(String url, String entity, Map<String, String> header) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept-Charset", "UTF-8");
        httpPost.addHeader("Content-Type", "application/json;");
        httpPost.setHeader("Accept", "application/json");
        if (MapUtils.isNotEmpty(header)) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        httpPost.setEntity(new StringEntity(entity, "UTF-8"));
        return getResponseContent(url, httpPost);
    }


    public String post(String url, StringEntity entity) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept-Charset", "UTF-8");
        httpPost.addHeader("Content-type", "application/json;");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(entity);
        return getResponseContent(url, httpPost);
    }

    public String post(String url, byte[] entity) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept-Charset", "UTF-8");
        httpPost.setEntity(new StringEntity(new String(entity), "UTF-8"));
        return getResponseContent(url, httpPost);
    }

    private String getResponseContent(String url, HttpRequestBase request) throws Exception {
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw new Exception("got an error from HTTP for url : " + URLDecoder.decode(url, "UTF-8"), e);
        } finally {
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
            request.releaseConnection();
        }
    }


    public static String readInputstream(InputStream in, String encode) throws IOException {
        if (StringUtils.isBlank(encode)) {
            encode = "utf-8";
        }
        List<Byte> byteList = new LinkedList<>();
        try (ReadableByteChannel channel = Channels.newChannel(in)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(9600);
            while (channel.read(byteBuffer) != -1) {
                byteBuffer.flip();//为读取做好准备

                while (byteBuffer.hasRemaining()) {
                    byteList.add(byteBuffer.get());
                }
                byteBuffer.clear();//为下一次写入做好准备
            }
        }
        return new String(Bytes.toArray(byteList), encode);
    }
}
