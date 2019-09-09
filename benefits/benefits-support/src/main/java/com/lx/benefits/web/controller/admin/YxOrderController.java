package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.jdOrder.JdMerchantOrderReq;
import com.lx.benefits.bean.dto.yxOrder.YxOrderInfoReq;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.yxOrder.YxOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: fan
 * Date: 2019/03/03
 * Time: 22:42
 */
@Api(tags = "严选订单")
@RestController
@RequestMapping("/admin/yx/order")
public class YxOrderController {

    @Autowired
    YxOrderInfoService orderInfoService;


    @ApiOperation(value = "查询 yx 订单列表", response = JSONObject.class)
    @RequestMapping(value = "/getYxOrderList", method = RequestMethod.GET)
    JSONObject getYxOrderList(@ApiParam(value = "Request", name = "dto") @ModelAttribute YxOrderInfoReq record) {
        return orderInfoService.getYxOrderList(record);
    }


    @ApiOperation(value = "yx 订单取消", response = JSONObject.class)
    @RequestMapping(value = "/getYxOrderCancel/{id}", method = RequestMethod.GET)
    JSONObject getYxOrderCancel(@ApiParam(value = "Request", name = "dto") @PathVariable String id){
        if (id == null) {
            return Response.fail("订单ID不能为空");
        }
        return orderInfoService.getYxOrderCancel(id);
    }


    @ApiOperation(value = "yx 订单更新", response = JSONObject.class)
    @RequestMapping(value = "/getYxOrderUpdate/{id}", method = RequestMethod.GET)
    JSONObject getYxOrderUpdate(@ApiParam(value = "Request", name = "dto") @PathVariable String id) {
        if (id == null) {
            return Response.fail("订单ID不能为空");
        }
        return orderInfoService.getYxOrderUpdate(id);
    }

}
