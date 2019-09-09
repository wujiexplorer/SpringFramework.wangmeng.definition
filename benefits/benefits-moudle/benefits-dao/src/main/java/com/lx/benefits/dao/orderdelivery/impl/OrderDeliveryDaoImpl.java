package com.lx.benefits.dao.orderdelivery.impl;

import com.lx.benefits.bean.entity.orderdelivery.OrderDelivery;
import com.lx.benefits.bean.entity.orderdelivery.OrderDeliveryExample;
import com.lx.benefits.dao.orderdelivery.OrderDeliveryDao;
import com.lx.benefits.mapper.orderdelivery.OrderDeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/22.
 */
@Service
public class OrderDeliveryDaoImpl implements OrderDeliveryDao {

    @Autowired
    private OrderDeliveryMapper orderDeliveryMapper;

    @Override
    public Long insert(OrderDelivery orderDelivery) {
        return Long.valueOf(orderDeliveryMapper.insertSelective(orderDelivery));
    }

    @Override
    public Integer update(OrderDelivery orderDelivery, OrderDeliveryExample example) {
        return orderDeliveryMapper.updateByExample(orderDelivery, example);
    }

    @Override
    public List<OrderDelivery> find(OrderDeliveryExample example) {
        return orderDeliveryMapper.selectByExample(example);
    }

    @Override
    public Integer count(OrderDeliveryExample example) {
        return orderDeliveryMapper.countByExample(example);
    }
}
