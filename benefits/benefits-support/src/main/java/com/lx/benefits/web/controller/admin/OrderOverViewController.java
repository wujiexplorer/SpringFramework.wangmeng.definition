package com.lx.benefits.web.controller.admin;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.order.OrderOverviewVO;
import com.lx.benefits.service.order.OrderOverviewService;
import com.lx.benefits.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/7/12
 * Time:15:34
 * Version:2.x
 * Description:TODO
 **/



@RestController
@RequestMapping("/admin/order")
@Slf4j
public class OrderOverViewController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderOverviewService orderOverviewService;

    @RequestMapping(value = "/orderOverview", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject orderOverview() {
        try {
            Map<String,Object> map = orderService.orderOverview();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("map",map);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/todayHoursOrderOverview", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject todayHoursOrderOverview() {
        try {
            List<OrderOverviewVO> list = orderOverviewService.getOrderHourStatistic();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("list",list);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

}
