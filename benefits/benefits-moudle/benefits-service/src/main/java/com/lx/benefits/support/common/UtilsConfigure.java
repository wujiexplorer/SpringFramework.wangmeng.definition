package com.lx.benefits.support.common;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class UtilsConfigure {
	
    @Bean("formStringJsonRestTemplate")
	@Primary
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		messageConverters.clear();
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());// 添加表单解析
		messageConverters.add(new ResourceHttpMessageConverter());// 上传/下载解析
		messageConverters.add(new FastJsonHttpMessageConverter());// 添加JSON解析
		return restTemplate;
	}

}
