package com.lx.benefits.service.order.impl;

import com.lx.benefits.bean.entity.order.OrderPackage;
import com.lx.benefits.mapper.order.OrderPackageMapper;
import com.lx.benefits.service.order.OrderPackageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @ClassName: OrderPackageServiceImpl
* @Description:
* @author wind
* @date 2019-2-10
*/
@Service("orderPackageService")
public class OrderPackageServiceImpl implements OrderPackageService {
	
	@Resource(name="orderPackageMapper")
    private OrderPackageMapper orderPackageMapper;


    @Override
    public Long doAddRecord(OrderPackage record) {
        return orderPackageMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(OrderPackage record) {
        return orderPackageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public OrderPackage getById(Integer id) {
        return orderPackageMapper.selectByPrimaryKey(id);
    }


}

