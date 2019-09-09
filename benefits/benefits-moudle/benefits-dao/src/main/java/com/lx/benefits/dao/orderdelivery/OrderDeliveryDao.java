package com.lx.benefits.dao.orderdelivery;

import com.lx.benefits.bean.entity.orderdelivery.OrderDelivery;
import com.lx.benefits.bean.entity.orderdelivery.OrderDeliveryExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface OrderDeliveryDao {

    Long insert(OrderDelivery orderDelivery);

    Integer update(OrderDelivery orderDelivery, OrderDeliveryExample example);

    List<OrderDelivery> find(OrderDeliveryExample example);

    Integer count(OrderDeliveryExample example);

}
