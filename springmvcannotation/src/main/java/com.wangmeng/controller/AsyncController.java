package com.wangmeng.controller;

import com.wangmeng.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

@Controller
public class AsyncController {


    @RequestMapping("/createOrder")
    @ResponseBody
    public DeferredResult<Object>  createOrder(){
        DeferredResult<Object> deferredResult = new DeferredResult<Object>((long)3000,"create fail");
        DeferredResultQueue.save(deferredResult);
        return deferredResult;
    }


    @RequestMapping("/create")
    @ResponseBody
    public String create(){
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        //setResult(order)触发DeferredResult结果的返回
        deferredResult.setResult(order);
        return "success========>"+order;
    }

    @RequestMapping("/async01")
    @ResponseBody
    public Callable<String> async01(){
        System.out.println("主线程开始。。。。。"+Thread.currentThread()+"=======>"+System.currentTimeMillis());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                //Spring MVC维护了一个线程池
                System.out.println("副线程开始。。。。。"+Thread.currentThread()+"=======>"+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("副线程结束。。。。。"+Thread.currentThread()+"=======>"+System.currentTimeMillis());
                return "Callable<String> async01()";
            }
        };
        System.out.println("主线程结束。。。。。"+Thread.currentThread()+"=======>"+System.currentTimeMillis());
        return callable;
    }

}
