package com.wangmeng.starter;

/**
 * User:wangmeng
 * Date:2019/5/14
 * Time:10:19
 * Verision:2.x
 * Description:TODO
 **/
public class HelloService {

    HelloProperties helloProperties;

    public String hello() {
        return "hello" + helloProperties.getName();
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

}
