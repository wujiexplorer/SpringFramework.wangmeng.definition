package com.wangmeng.itree.model;

import lombok.Setter;

import java.util.Map;

/**
 * @author idea
 * @date 2019/4/28
 * @Version V1.0
 */
@Setter
public class ControllerRequest {

    private Map<String,Object> params;

    private Map<String,String> header;


    public Object getParameter(String name){
        return params.get(name);
    }

    public Object getHeader(String name){
        return header.get(name);
    }

}
