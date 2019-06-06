package com.dstz.agilebpm.sys.autoconfiguration.enums;

/**
 * 消息队列类型
 *
 * @author wacxhs
 */
public enum MessageQueueType {

    /**
     * redis
     */
    REDIS,

    /**
     * java消息队列
     */
    JMS,

    /**
     * 同步方式
     */
    SYNCHRONOUS
}
