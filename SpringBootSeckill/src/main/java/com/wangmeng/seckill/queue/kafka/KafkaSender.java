package com.wangmeng.seckill.queue.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 生产者
 * @author 科帮网 By https://blog.52itstyle.com
 */
@Component
public class KafkaSender {
    /**
     * 用kafkaTemplate是不行的，抛异常，用JmsTemplate
     */
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 发送消息到kafka
     */
    public void sendChannelMess(String channel, String message){
        kafkaTemplate.send(channel,message);
    }
}
