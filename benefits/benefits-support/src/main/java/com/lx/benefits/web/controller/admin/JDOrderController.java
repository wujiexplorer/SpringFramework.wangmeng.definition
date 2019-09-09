package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.jdOrder.JdMerchantOrderReq;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.jdOrder.JdOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: fan
 * Date: 2019/03/03
 * Time: 16:09
 */
@Api(tags = "京东订单")
@RestController
@RequestMapping("/admin/jd/order")
public class JDOrderController {

    @Autowired
    JdOrderService jdOrderService;

    @ApiOperation(value = "查询 jd 订单列表", response = JSONObject.class)
    @RequestMapping(value = "/getJdOrderList", method = RequestMethod.GET)
    JSONObject getJdOrderList(@ApiParam(value = "Request", name = "dto") @ModelAttribute JdMerchantOrderReq record) {
        return jdOrderService.getJdOrderList(record);
    }


    // 主键id
    @ApiOperation(value = "查询 jd 订单详情", response = JSONObject.class)
    @RequestMapping(value = "/getJdOrderDetail/{id}", method = RequestMethod.GET)
    JSONObject getJdOrderDetail(@ApiParam(value = "Request", name = "dto") @PathVariable Long id) {
        if (id == null) {
            return Response.fail("订单ID不能为空");
        }
        return jdOrderService.getJdOrderDetail(id);
    }

    // 主键id
    @ApiOperation(value = "jd 订单取消", response = JSONObject.class)
    @RequestMapping(value = "/getJdOrderCancel/{id}", method = RequestMethod.GET)
    JSONObject getJdOrderCancel(@ApiParam(value = "Request", name = "dto") @PathVariable Long id){
        if (id == null) {
            return Response.fail("订单ID不能为空");
        }
        return jdOrderService.getJdOrderCancel(id);
    }

    // 京东id
    @ApiOperation(value = "jd 订单更新", response = JSONObject.class)
    @RequestMapping(value = "/getJdOrderUpdate/{id}", method = RequestMethod.GET)
    JSONObject getJdOrderUpdate(@ApiParam(value = "Request", name = "dto") @PathVariable Long id) {
        if (id == null) {
            return Response.fail("订单ID不能为空");
        }
        return jdOrderService.getJdOrderUpdate(id);
    }

    @ApiOperation(value = "jd 订单跟踪", response = JSONObject.class)
    @RequestMapping(value = "/getJdOrderTrack/{id}", method = RequestMethod.GET)
    JSONObject getJdOrderTrack(@ApiParam(value = "Request", name = "dto") @PathVariable Long id) {
        if (id == null) {
            return Response.fail("订单ID不能为空");
        }
        return jdOrderService.getJdOrderTrack(id);
    }

}
