package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.yx.result.YXResult;
import com.lx.benefits.bean.dto.yxcallback.YxCallBackReq;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: fan
 * Date: 2019/04/01
 * Time: 18:14
 */
@RestController
@RequestMapping
public class YxCallBackController {

    private static final Logger logger = LoggerFactory.getLogger(YxCallBackController.class);

    @ApiOperation(value = "严选回调", response = JSONObject.class)
    @RequestMapping(value = "/yxapicallback", method = RequestMethod.POST)
    public String handleCallback(@ApiParam(value = "Request", name = "dto") @RequestBody YxCallBackReq refundAddress) {
        //item.skuList.channelSkuDetail.status
        logger.error("------------------>{}", refundAddress.toString());
        logger.info("------------------>{}", refundAddress.toString());
        System.out.println(refundAddress.toString());
        return JSON.toJSONString(new YXResult<>(200, "success"));
    }

}
