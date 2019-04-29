package com.wangmeng.controller;

import com.wangmeng.model.HelloModel;
import com.wangmeng.model.ReqBody;
import com.wangmeng.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class HelloWorld {
    private final HelloService HelloService;

    @Autowired
    public HelloWorld(HelloService HelloService) {
        this.HelloService = HelloService;

    }

    @RequestMapping("/")
    public String Index() {
        return "Hello World";
    }

    @RequestMapping("/list")
    public List<HelloModel> List() {
        return HelloService.selectAll();
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String Post(
            @RequestBody ReqBody map
    ) throws  IOException {
        return "输入的姓名是" + map.getName() + ",电子邮件是:" + map.getEmail();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Boolean update(@RequestBody HelloModel helloModel){
        return HelloService.updateValue(helloModel);

    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Boolean insert(@RequestBody HelloModel helloModel){
        return HelloService.insert(helloModel);
    }
}
