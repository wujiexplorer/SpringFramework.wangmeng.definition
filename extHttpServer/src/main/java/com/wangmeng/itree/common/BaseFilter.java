package com.wangmeng.itree.common;

import com.wangmeng.itree.model.ControllerRequest;

/**
 * 基础过滤器
 *
 * @author linhao
 * @date 2019/4/30
 * @Version V1.0
 */
public interface BaseFilter {

    /**
     * 过滤器之前执行
     *
     * @param controllerRequest
     */
    void beforeFilter(ControllerRequest controllerRequest);

    /**
     * 过滤器之后执行
     *
     * @param controllerRequest
     */
    void afterFilter(ControllerRequest controllerRequest);
}
