package com.lx.benefits.service.yx;

import java.util.List;

/**
 * User: fan
 * Date: 2019/04/01
 * Time: 18:33
 */
public interface IYxCallBackService {

    void registerService();

    List<Object> serviceList();

    List<Object> callbackItemChange();

}
