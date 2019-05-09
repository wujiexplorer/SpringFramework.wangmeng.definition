package com.wangmeng.doc;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 启动类
 * 创建者  小柒2012
 * 创建时间	2017年9月6日
 */
@SpringBootApplication
@ImportResource(locations={"classpath:kaptcha.xml"})  
public class Application extends WebMvcConfigurerAdapter {
	private static final Logger logger = Logger.getLogger(Application.class);
	
	@Value("${web.upload.path}")
    private String uploadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/uploads/**").addResourceLocations(
				"file:"+uploadPath);
		logger.info("自定义静态资源目录");
	}
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
		logger.info("项目启动 ");
	}
}