package com.lx.benefits.service.jdOrder;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.bo.product.SkuBO;
import com.lx.benefits.bean.dto.jdOrder.api.JDOrderSubOrder;
import com.lx.benefits.bean.dto.jdOrder.api.JDOrderTrack;
import com.lx.benefits.bean.dto.order.QueryFreightVO;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;

import java.util.List;

/**
 * JDAPI订单相关接口
 * Created by ldr on 2017/3/2.
 */
public interface IJDOrderService {


    /**
     * 下单
     * @param jdSkus
     * @param userReceiveAddress
     * @return
     */
    JDOrderSubOrder submitOrder(Long sellerOrderNumber, List<SkuBO> jdSkus, ConsigneeAdress userReceiveAddress);

    Boolean confirmOrder(String jdOrderId) throws BusinessException;

    /**
     * 取消未确认订单接口
     * 该接口仅能取消未确认的预占库存父订单单号。不能取消已经确认的订单单号。
     * 如果需要取消已确认的订单，可以调用取消订单接口进行取消操作，参数必须为子订单才能取消
     *
     * @param jdOrderId
     * @return
     * @throws Exception
     */
    Boolean cancel(String jdOrderId);

    JDOrderTrack orderTrack(String jdOrderId);

    /**
     * jd 计算运费
     * @param jdSkus
     * @param addressId
     * @return
     */
    Double freight(List<SkuBO> jdSkus, Long addressId);
    
    Double freight(QueryFreightVO qeueryFreightVO);
}
