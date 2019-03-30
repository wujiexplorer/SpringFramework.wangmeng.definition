package com.wangmeng.config;


import com.wangmeng.controller.MyFirstInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@ComponentScan(value = "com.wangmeng",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class})
},useDefaultFilters = false)
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

    public void configureViewResolvers(ViewResolverRegistry registry) {

        //registry.jsp();
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

        configurer.enable();
    }

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");//没有路径不拦截，比如一个/不拦截
    }
}
