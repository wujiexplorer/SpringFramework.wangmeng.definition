package com.wangmeng.es.common.kafka;

import com.wangmeng.es.common.utils.JsonMapper;
import com.wangmeng.es.log.entity.SysLogs;
import com.wangmeng.es.log.repository.ElasticLogRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 扫描监听
 * 创建者 科帮网
 * 创建时间	2018年2月4日
 */
@Component
public class Listener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
	private ElasticLogRepository elasticLogRepository;
    
    @KafkaListener(topics = {"itstyle"})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value());
        if(record.key().equals("itstyle_log")){
        	try {
        		SysLogs log = JsonMapper.fromJsonString(record.value().toString(), SysLogs.class);
        		logger.info("kafka保存日志: " + log.getUsername());
        		elasticLogRepository.save(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
}
