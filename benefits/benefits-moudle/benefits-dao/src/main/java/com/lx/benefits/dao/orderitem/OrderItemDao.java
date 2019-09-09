package com.lx.benefits.dao.orderitem;

import com.lx.benefits.bean.entity.orderitem.OrderItem;
import com.lx.benefits.bean.entity.orderitem.OrderItemExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface OrderItemDao {

    Long insert(OrderItem orderItem);


    Integer update(OrderItem orderItem, OrderItemExample example);

    List<OrderItem> find(OrderItemExample example);


    Integer count(OrderItemExample example);


}
