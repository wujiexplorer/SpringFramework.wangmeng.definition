package com.lx.benefits.http;

import com.lx.benefits.httpclient.HttpKeepAliveStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
public class HttpClientConfig {


    @Bean(name = "poolingClientConnectionManager")
    public PoolingHttpClientConnectionManager poolingClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingClientConnectionManager.setMaxTotal(300);
        poolingClientConnectionManager.setDefaultMaxPerRoute(100);
        return poolingClientConnectionManager;
    }

    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder httpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingClientConnectionManager());
        httpClientBuilder.setDefaultRequestConfig(requestConfig());
        httpClientBuilder.setKeepAliveStrategy(keepAliveStrategy());
        return httpClientBuilder;
    }

    @Bean(name = "requestConfigBuilder")
    public RequestConfig requestConfigBuilder() {
        return RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(3000).build();
    }

    @Bean(name = "keepAliveStrategy")
    public HttpKeepAliveStrategy keepAliveStrategy() {
        HttpKeepAliveStrategy keepAliveStrategy = new HttpKeepAliveStrategy();
        keepAliveStrategy.setKeepAlive(15000);
        return keepAliveStrategy;
    }

    @Bean(name = "requestConfig")
    public RequestConfig requestConfig() {
        RequestConfig requestConfig = RequestConfig.copy(requestConfigBuilder()).build();
        return requestConfig;
    }


    @Bean(name = "httpClient")
    public CloseableHttpClient httpClient() {
        return httpClientBuilder().build();
    }

}
