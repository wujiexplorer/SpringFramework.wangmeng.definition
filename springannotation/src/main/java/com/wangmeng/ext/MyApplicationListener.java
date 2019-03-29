package com.wangmeng.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    public void onApplicationEvent(ApplicationEvent event){
        System.out.println("收到的事件："+event);
    }

}
