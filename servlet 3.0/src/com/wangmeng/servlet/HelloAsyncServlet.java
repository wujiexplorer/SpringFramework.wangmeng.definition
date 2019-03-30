package com.wangmeng.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//没有/，不能启动组件，SpringMVC解决了这个问题
@WebServlet(value = "/async",asyncSupported = true)//org.apache.catalina.LifecycleException: Failed to start component [StandardEngine[Catalina].StandardHost[localhost].StandardContext[/servlet3.0]]
public class HelloAsyncServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        //tomcat主线程池
        System.out.println("主线程开始。。。。"+Thread.currentThread()+"====>"+System.currentTimeMillis());
        AsyncContext asyncContext =request.startAsync();
        //异步处理线程池
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("副线程开始。。。。"+Thread.currentThread()+"====>"+System.currentTimeMillis());
                    sayHello();
                    //AsyncContext asyncContext1 =request.getAsyncContext();
                    ServletResponse response1 = asyncContext.getResponse();
                    response1.getWriter().write("hello async");
                    asyncContext.complete();//complete()一定要在最后，否则会出现空指针异常
                    System.out.println("副线程结束。。。。"+Thread.currentThread()+"====>"+System.currentTimeMillis());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        System.out.println("主线程结束。。。。"+Thread.currentThread()+"====>"+System.currentTimeMillis());
    }

    public void sayHello()throws Exception{
        System.out.println(Thread.currentThread()+" processing.....");
        Thread.sleep(3000);
    }

}
