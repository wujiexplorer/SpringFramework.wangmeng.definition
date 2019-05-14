package com.wangmeng.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User:wangmeng
 * Date:2019/5/14
 * Time:10:18
 * Verision:2.x
 * Description:TODO
 **/


@ConfigurationProperties(prefix = "wms.hello")
public class HelloProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
