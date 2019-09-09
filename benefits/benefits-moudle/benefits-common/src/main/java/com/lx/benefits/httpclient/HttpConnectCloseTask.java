/**
 * Copyright (C) 2009-2016 Hangzhou 2Dfire Technology Co., Ltd. All rights reserved
 */
package com.lx.benefits.httpclient;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Http 链接关闭任务
 */
@Component
public class HttpConnectCloseTask {
    public final static Logger logger = LoggerFactory.getLogger(HttpConnectCloseTask.class);
    @Resource
    private PoolingHttpClientConnectionManager poolingClientConnectionManager;

    /**
     * 3秒执行一次清理失效链接
     */
    @Scheduled(fixedDelay = 3000)
    public void close() {
        if (poolingClientConnectionManager != null) {
            poolingClientConnectionManager.closeExpiredConnections();
            poolingClientConnectionManager.closeIdleConnections(3, TimeUnit.SECONDS);
            //logger.debug("当前HttpClient的链接:" + poolingClientConnectionManager.getTotalStats() + ",=" + poolingClientConnectionManager.getRoutes());
        } else {
            logger.error("poolingClientConnectionManager 为空");
        }
    }
}
