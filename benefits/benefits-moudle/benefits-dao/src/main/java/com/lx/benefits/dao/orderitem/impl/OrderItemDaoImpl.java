package com.lx.benefits.dao.orderitem.impl;

import com.lx.benefits.bean.entity.orderitem.OrderItem;
import com.lx.benefits.bean.entity.orderitem.OrderItemExample;
import com.lx.benefits.dao.orderitem.OrderItemDao;
import com.lx.benefits.mapper.orderitem.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/22.
 */
@Service
public class OrderItemDaoImpl implements OrderItemDao {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Long insert(OrderItem orderItem) {
        return Long.valueOf(orderItemMapper.insertSelective(orderItem));
    }

    @Override
    public Integer update(OrderItem orderItem, OrderItemExample example) {
        return orderItemMapper.updateByExampleSelective(orderItem, example);
    }

    @Override
    public List<OrderItem> find(OrderItemExample example) {
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public Integer count(OrderItemExample example) {
        return orderItemMapper.countByExample(example);
    }
}
