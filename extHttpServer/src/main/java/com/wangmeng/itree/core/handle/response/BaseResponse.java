package com.wangmeng.itree.core.handle.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author idea
 * @date 2019/4/26
 * @Version V1.0
 */
@Data
@AllArgsConstructor
public class BaseResponse {

    /**
     * 返回的响应类型
     */
    private int code;

    private Object data;


}
