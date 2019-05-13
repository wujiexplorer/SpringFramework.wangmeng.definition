package com.wangmeng.itree.common;


import com.wangmeng.itree.core.handle.response.BaseResponse;
import com.wangmeng.itree.model.ControllerRequest;

/**
 * @author idea
 * @date 2019/4/26
 * @Version V1.0
 */
public interface BaseController {

    /**
     * get请求
     *
     * @param controllerRequest
     * @return
     */
    BaseResponse doGet(ControllerRequest controllerRequest);

    /**
     * post请求
     *
     * @param controllerRequest
     * @return
     */
    BaseResponse doPost(ControllerRequest controllerRequest);

}
