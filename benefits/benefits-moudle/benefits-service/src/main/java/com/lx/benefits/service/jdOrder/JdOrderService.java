package com.lx.benefits.service.jdOrder;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.jdOrder.JdMerchantOrderReq;

/**
 * User: fan
 * Date: 2019/03/03
 * Time: 15:48
 */
public interface JdOrderService {

    /**
     * 查询 jd 订单列表
     *
     * @param record
     * @return
     */
    JSONObject getJdOrderList(JdMerchantOrderReq record);

    /**
     * 查询 jd 订单详情
     *
     * @param orderId
     * @return
     */
    JSONObject getJdOrderDetail(Long orderId);

    /**
     * 查询 jd 订单取消
     *
     * @param orderId
     * @return
     */
    JSONObject getJdOrderCancel(Long orderId);

    /**
     * 查询 jd 订单更新
     *
     * @param orderId
     * @return
     */
    JSONObject getJdOrderUpdate(Long orderId);

    /**
     * 查询 jd 订单跟踪
     *
     * @param orderId
     * @return
     */
    JSONObject getJdOrderTrack(Long orderId);

}
