package com.lx.benefits.service.order.impl;

import com.lx.benefits.bean.entity.order.OrderPay;
import com.lx.benefits.mapper.order.OrderPayMapper;
import com.lx.benefits.service.order.OrderPayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @ClassName: OrderPayServiceImpl
* @Description:
* @author wind
* @date 2019-2-10
*/
@Service("orderPayService")
public class OrderPayServiceImpl implements OrderPayService {
	
	@Resource(name="orderPayMapper")
    private OrderPayMapper orderPayMapper;


    @Override
    public Long doAddRecord(OrderPay record) {
        return orderPayMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(OrderPay record) {
        return orderPayMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public OrderPay getById(Long id) {
        return orderPayMapper.selectByPrimaryKey(id);
    }

    @Override
    public OrderPay getByPayOrderNumber(Long payOrderNumber) {
        return orderPayMapper.selectByPayOrderNumber(payOrderNumber);
    }
}

