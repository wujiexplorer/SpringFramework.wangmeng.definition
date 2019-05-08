package com.wangmeng.quartz;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * 创建者 科帮网
 * 创建时间	2018年3月28日
 * API接口测试：http://localhost:8080/quartz/swagger-ui.html
 */
@SpringBootApplication
public class Application {
	private static final Logger logger = Logger.getLogger(Application.class);
	
	public static void main(String[] args) throws InterruptedException, SchedulerException {
		SpringApplication.run(Application.class, args);
		logger.info("项目启动 ");
	}
}