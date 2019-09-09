package com.lx.benefits.service.yianapi;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by softw on 2019/3/1.
 */
public interface YiAnService {

    JSONObject login(String code);
}
