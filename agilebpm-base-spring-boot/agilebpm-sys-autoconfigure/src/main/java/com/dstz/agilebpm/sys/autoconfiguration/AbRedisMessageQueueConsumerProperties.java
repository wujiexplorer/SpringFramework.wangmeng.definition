package com.dstz.agilebpm.sys.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Redis消息队列消费属性配置
 *
 * @author wacxhs
 */
@ConfigurationProperties(prefix = "ab.simple-mq.redis-consumer")
public class AbRedisMessageQueueConsumerProperties {

    /**
     * redisTemplate 容器中名称
     */
    private String redisTemplateBeanName = "redisTemplate";

    /**
     * 处理消息核心线程数
     */
    private int handleMessageCoreThreadSize = 1;

    /**
     * 处理消息最大线程数
     */
    private int handleMessageMaxThreadSize = 2;

    /**
     * 处理消息活跃时间,单位秒
     */
    private long handleMessageKeepAliveTime = 30;

    /**
     * 监听队列间隔(ms)
     */
    private long listenInterval = 5000;

    public String getRedisTemplateBeanName() {
        return redisTemplateBeanName;
    }

    public void setRedisTemplateBeanName(String redisTemplateBeanName) {
        this.redisTemplateBeanName = redisTemplateBeanName;
    }

    public int getHandleMessageCoreThreadSize() {
        return handleMessageCoreThreadSize;
    }

    public void setHandleMessageCoreThreadSize(int handleMessageCoreThreadSize) {
        this.handleMessageCoreThreadSize = handleMessageCoreThreadSize;
    }

    public int getHandleMessageMaxThreadSize() {
        return handleMessageMaxThreadSize;
    }

    public void setHandleMessageMaxThreadSize(int handleMessageMaxThreadSize) {
        this.handleMessageMaxThreadSize = handleMessageMaxThreadSize;
    }

    public long getHandleMessageKeepAliveTime() {
        return handleMessageKeepAliveTime;
    }

    public void setHandleMessageKeepAliveTime(long handleMessageKeepAliveTime) {
        this.handleMessageKeepAliveTime = handleMessageKeepAliveTime;
    }

    public long getListenInterval() {
        return listenInterval;
    }

    public void setListenInterval(long listenInterval) {
        this.listenInterval = listenInterval;
    }
}
