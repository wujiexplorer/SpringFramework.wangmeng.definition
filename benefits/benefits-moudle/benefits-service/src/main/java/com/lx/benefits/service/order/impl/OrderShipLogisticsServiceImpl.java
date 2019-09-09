package com.lx.benefits.service.order.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.OrderShipLogistics;
import com.lx.benefits.bean.entity.order.ProductOrderEx;
import com.lx.benefits.mapper.order.OrderShipLogisticsMapper;
import com.lx.benefits.service.order.OrderShipLogisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @ClassName: OrderShipLogisticsServiceImpl
* @Description:
* @author wind
* @date 2019-3-3
*/
@Service("orderShipLogisticsService")
public class OrderShipLogisticsServiceImpl implements OrderShipLogisticsService {
	
	@Resource(name="orderShipLogisticsMapper")
    private OrderShipLogisticsMapper orderShipLogisticsMapper;


    @Override
    public Long doAddRecord(OrderShipLogistics record) {
        return orderShipLogisticsMapper.insertSelective(record);
    }

    @Override
    public int doAddBatchRecord(List<OrderShipLogistics> list) throws BusinessException {
        return orderShipLogisticsMapper.insertBatch(list);
    }

    @Override
    public int doModRecord(OrderShipLogistics record) {
        return orderShipLogisticsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public OrderShipLogistics getById(Long id) {
        return orderShipLogisticsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderShipLogistics> listItemOrderLogistics(Long itemOrderNumber) {
        return orderShipLogisticsMapper.selectItemOrderLogistics(itemOrderNumber);
    }

    @Override
    public List<OrderShipLogistics> listSelllerOrderLogistics(Long sellerOrderNumber) {
        return orderShipLogisticsMapper.selectSellerOrderLogistics(sellerOrderNumber);
    }
}

