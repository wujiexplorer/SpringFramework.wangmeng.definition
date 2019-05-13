package com.wangmeng.itree.model;

import lombok.Data;

/**
 * @author idea
 * @date 2019/4/26
 * @Version V1.0
 */
@Data
public class RequestModel {

    /**
     * 请求url
     */
    private String url;

    /**
     * 请求的方法
     */
    private String method;

}
