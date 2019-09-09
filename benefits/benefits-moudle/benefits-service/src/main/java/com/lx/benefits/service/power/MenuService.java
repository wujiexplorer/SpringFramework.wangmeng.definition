package com.lx.benefits.service.power;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.entity.power.SysMenu;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 17:43
 */
public interface MenuService {

    JSONObject findSysMenu();

    JSONObject delete(Long id);

    JSONObject insert(SysMenu record);

    JSONObject update(SysMenu record);

}
