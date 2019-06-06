package com.dstz.agilebpm.sys.autoconfiguration;

import com.dstz.agilebpm.sys.autoconfiguration.enums.MessageQueueType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 消息队列配置
 *
 * @author wacxhs
 */
@ConfigurationProperties(prefix = "ab.simple-mq")
public class AbSimpleMessageQueueProperties {

    /**
     * 消息队列方式
     */
    private MessageQueueType messageQueueType = MessageQueueType.SYNCHRONOUS;

    public MessageQueueType getMessageQueueType() {
        return messageQueueType;
    }

    public void setMessageQueueType(MessageQueueType messageQueueType) {
        this.messageQueueType = messageQueueType;
    }
}
