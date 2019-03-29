package com.wangmeng.servlet;

import javax.servlet.*;
import java.util.EnumSet;

public class UserListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("UserListener.....contextInitialized.....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        ServletRegistration.Dynamic servlet = servletContext.addServlet("userServlet",new UserServlet());
        servlet.addMapping("/user");
        //servletContext注册不了Filter
        FilterRegistration.Dynamic filter = servletContext.addFilter("userFilter",new UserFilter());
        filter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST),true,"/*");
        System.out.println("UserListener.....contextDestroyed....");
    }
}
