package com.wangmeng.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 启动类
 * 创建者 科帮网 https://blog.52itstyle.com
 * 创建时间	2017年7月19日
 * API: http://localhost:8080/springboot_mail/swagger-ui.html
 * 
 * 启动 java -jar spring-boot-mail.jar --server.port=8886 
 * linux 下 后台启动  nohup java -jar spring-boot-mail.jar --server.port=8886 & 
 * 
 * 2018-10-10 更新说明：
 * 1）原当当 Dubbox 2.8.4 替换为 Dubbo 2.6.2
 * 2）原spring-context-dubbo.xml 配置 替换为 dubbo-spring-boot-starter 2.0.0
 * 3）原 zkclient 0.6 替换为 curator-recipes 4.0.1
 * 4）原 zookeeper 3.4.6 升级为 zookeeper 3.5.3
 */
@SpringBootApplication
//@EnableDubboConfiguration
@ImportResource({"classpath:spring-context-task.xml"})
public class Application  {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
		logger.info("邮件服务项目启动 ");
	}
}