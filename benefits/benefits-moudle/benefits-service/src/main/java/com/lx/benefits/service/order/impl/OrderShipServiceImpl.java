package com.lx.benefits.service.order.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.OrderShip;
import com.lx.benefits.mapper.order.OrderShipMapper;
import com.lx.benefits.service.order.OrderShipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @ClassName: OrderShipServiceImpl
* @Description:
* @author wind
* @date 2019-2-10
*/
@Service("orderShipService")
public class OrderShipServiceImpl implements OrderShipService {
	
	@Resource(name="orderShipMapper")
    private OrderShipMapper orderShipMapper;


    @Override
    public Long doAddRecord(OrderShip record) {
        return orderShipMapper.insertSelective(record);
    }

    @Override
    public int doAddBatchRecord(List<OrderShip> list) throws BusinessException {
        return orderShipMapper.insertBatch(list);
    }

    @Override
    public int doModRecord(OrderShip record) {
        return orderShipMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public OrderShip getById(Long id) {
        return orderShipMapper.selectByPrimaryKey(id);
    }

    @Override
    public OrderShip getByOrderNumber(Long orderNumber) {
        return orderShipMapper.selectByOrderNumber(orderNumber);
    }

	@Override
	public OrderShip getByProductOrderNumber(Long orderNumber) {
		  return orderShipMapper.selectByProductOrderNumber(orderNumber);
	}



}

