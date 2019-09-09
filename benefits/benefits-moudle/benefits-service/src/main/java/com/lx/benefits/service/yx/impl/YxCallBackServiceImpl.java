package com.lx.benefits.service.yx.impl;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.service.yx.IYxCallBackService;
import com.lx.benefits.service.yx.YXBaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: fan
 * Date: 2019/04/01
 * Time: 18:33
 */
@Service
public class YxCallBackServiceImpl extends YXBaseService implements IYxCallBackService {


    @Override
    public void registerService() {
        Map<String, String> param = new HashMap<>();
        param.put("fields", "item.skuList.channelSkuDetail.status");
        String res = handle(param, getRegisteritemChangeMethod());
        System.out.println("registerService----->"+res);
    }

    @Override
    public List<Object> serviceList() {
        String res = handle(null, getCallBackMethods());
        System.out.println("getCallBackMethods----->"+res);
        return null;
    }

    @Override
    public List<Object> callbackItemChange() {
        Map<String, String> param = new HashMap<>();
        String res = handle(param, getCallbackItemChange());
        System.out.println("callbackItemChange----->"+res);
        return null;
    }




}
