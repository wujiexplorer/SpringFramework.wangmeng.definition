package com.wangmeng.tomcat;

import com.wangmeng.servlet.IndexServlet;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/10
 * TIME 9:35
 * Description no Description
 **/
public class Test001 {

    private static final Integer PROT = 8080;

    private static final String CONTENT_PATH = "/tomcat";

    private static final String SERVLET_PATH = "indexServlet";
    public static void main(String[] args) throws Exception{
        //Java语言创建tomcat容器
        Tomcat tomcatServer = new Tomcat();
        tomcatServer.setPort(PROT);
        tomcatServer.getHost().setAutoDeploy(false);
        StandardContext standardContext = new StandardContext();
        standardContext.setPath(CONTENT_PATH);
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        tomcatServer.getHost().addChild(standardContext);
        tomcatServer.addServlet(CONTENT_PATH,SERVLET_PATH,new IndexServlet());
        standardContext.addServletMappingDecoded("/index",SERVLET_PATH);
        tomcatServer.start();
        System.out.println("tomcat服务器启动成功");
        tomcatServer.getServer().await();
    }
}
