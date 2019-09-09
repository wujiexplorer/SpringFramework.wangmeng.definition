package com.lx.benefits.bean.dto.yxcallback;

import lombok.Data;

/**
 * User: fan
 * Date: 2019/04/01
 * Time: 18:20
 */
@Data
public class YxCallBackReq {

    private String method;

    private String appKey;

    private String sign;

    private String timestamp;

    private String itemChange;

}
