package com.lx.benefits.service.order.impl;

import com.lx.benefits.bean.entity.order.OrderPoint;
import com.lx.benefits.mapper.order.OrderPointMapper;
import com.lx.benefits.service.order.OrderPointService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @ClassName: OrderPointServiceImpl
* @Description:
* @author wind
* @date 2019-2-23
*/
@Service("orderPointService")
public class OrderPointServiceImpl implements OrderPointService {
	
	@Resource(name="orderPointMapper")
    private OrderPointMapper orderPointMapper;


    @Override
    public Long doAddRecord(OrderPoint record) {
        return orderPointMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(OrderPoint record) {
        return orderPointMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public OrderPoint getById(Integer id) {
        return orderPointMapper.selectByPrimaryKey(id);
    }


}

