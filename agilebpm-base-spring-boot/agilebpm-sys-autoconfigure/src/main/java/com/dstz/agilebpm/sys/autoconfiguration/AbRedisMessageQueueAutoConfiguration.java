package com.dstz.agilebpm.sys.autoconfiguration;

import com.dstz.sys.api.jms.producer.JmsProducer;
import com.dstz.sys.simplemq.consumer.RedisMessageQueueConsumer;
import com.dstz.sys.simplemq.producer.RedisMessageQueueProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis 消息队列自动装配
 *
 * @author wacxhsz
 */
@ConditionalOnExpression("'${ab.simple-mq.message-queue-type}'.toLowerCase() == 'redis'")
@ConditionalOnClass(RedisTemplate.class)
@EnableConfigurationProperties(AbRedisMessageQueueConsumerProperties.class)
@Configuration
public class AbRedisMessageQueueAutoConfiguration {

    @Bean
    public JmsProducer jmsProducer() {
        return new RedisMessageQueueProducer();
    }

    @Bean
    public RedisMessageQueueConsumer redisMessageQueueConsumer(AbRedisMessageQueueConsumerProperties properties) {
        RedisMessageQueueConsumer redisMessageQueueConsumer = new RedisMessageQueueConsumer();
        redisMessageQueueConsumer.setRedisTemplateBeanName(properties.getRedisTemplateBeanName());
        redisMessageQueueConsumer.setHandleMessageCoreThreadSize(properties.getHandleMessageCoreThreadSize());
        redisMessageQueueConsumer.setHandleMessageMaxThreadSize(properties.getHandleMessageMaxThreadSize());
        redisMessageQueueConsumer.setHandleMessageKeepAliveTime(properties.getHandleMessageKeepAliveTime());
        redisMessageQueueConsumer.setListenInterval(properties.getListenInterval());
        return redisMessageQueueConsumer;
    }

}
