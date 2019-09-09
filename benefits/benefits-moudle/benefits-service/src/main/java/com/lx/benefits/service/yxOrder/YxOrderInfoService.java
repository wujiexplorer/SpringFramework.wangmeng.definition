package com.lx.benefits.service.yxOrder;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.yxOrder.YxOrderInfoReq;

/**
 * User: fan
 * Date: 2019/03/03
 * Time: 22:41
 */
public interface YxOrderInfoService {

    /**
     * 查询 yx 订单列表
     *
     * @param record
     * @return
     */
    JSONObject getYxOrderList(YxOrderInfoReq record);

    /**
     * 查询 yx 订单取消
     *
     * @param orderId
     * @return
     */
    JSONObject getYxOrderCancel(String orderId);

    /**
     * 查询 yx 订单更新
     *
     * @param orderId
     * @return
     */
    JSONObject getYxOrderUpdate(String orderId);

}
