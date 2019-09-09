package com.lx.benefits.config.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lx.benefits.interceptor.AuthSecurityInterceptor;
import com.lx.benefits.interceptor.ClientAuthSecurityInterceptor;

@Configuration("webmvcConfig")
public class WebmvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthSecurityInterceptor authSecurityInterceptor;
    @Autowired
    private ClientAuthSecurityInterceptor clientAuthSecurityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //全局处理拦截，和业务无关的
        registry.addInterceptor(authSecurityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/webjars/**", "/swagger-resources/**")
                //.excludePathPatterns("/swagger-ui.html/**")
                .excludePathPatterns("/v2/api-docs/**")
                .excludePathPatterns("/templates/watermark/**");

        registry.addInterceptor(clientAuthSecurityInterceptor)
                .addPathPatterns("/client/api/**")
                .excludePathPatterns("/client/api/oauth2/accessToken");
        
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
