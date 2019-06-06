package com.dstz.agilebpm.sys.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dstz.sys.api.jms.producer.JmsProducer;
import com.dstz.sys.simplemq.producer.SynchronousQueueProducer;
import com.dstz.sys.util.EmailUtil;

import cn.hutool.extra.mail.MailAccount;

/**
 * 缓存相关配置
 *
 * @author jeff
 * @date 2018-8-12 22:36:19
 */
@Configuration
@EnableConfigurationProperties({MQMailConfigProperties.class, AbSimpleMessageQueueProperties.class})
public class SimpleMqAutoConfiguration {

    @Autowired
    private MQMailConfigProperties mQMailConfigProperties;

    /**
     * 默认消息发送提供者
     *
     * @return 消息发送提供端
     */
    @ConditionalOnExpression("'${ab.simple-mq.message-queue-type:synchronous}'.toLowerCase() == 'synchronous'")
    @Bean
    public JmsProducer synchronousQueueProducer() {
        return new SynchronousQueueProducer();
    }

    @Component
    class MailAccountAutoConfiguration implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            if (event.getApplicationContext().getParent() == null) {
                setEmailConfiguration();
            }
        }
    }

    private void setEmailConfiguration() {
        MailAccount account = new MailAccount();

        account.setHost(mQMailConfigProperties.getSendHost());
        account.setPort(mQMailConfigProperties.getSendPort());
        account.setFrom(mQMailConfigProperties.getMailAddress());
        account.setUser(mQMailConfigProperties.getNickName());
        account.setPass(mQMailConfigProperties.getPassword());
        account.setSslEnable(mQMailConfigProperties.isSSL());

        EmailUtil.setAccount(account);
    }

}
