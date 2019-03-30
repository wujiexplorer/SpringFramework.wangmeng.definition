package com.wangmeng;

import com.wangmeng.config.AppConfig;
import com.wangmeng.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
            RootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
            AppConfig.class
        };
    }

    //拦截所有资源，包括静态资源(xx.png,xx.js),但不包括xx.jsp  /* 也拦截*.jsp
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
