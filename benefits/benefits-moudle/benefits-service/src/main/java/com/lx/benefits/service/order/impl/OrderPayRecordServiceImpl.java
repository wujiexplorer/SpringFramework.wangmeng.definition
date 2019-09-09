package com.lx.benefits.service.order.impl;

import com.lx.benefits.bean.entity.order.OrderPayRecord;
import com.lx.benefits.mapper.order.OrderPayRecordMapper;
import com.lx.benefits.service.order.OrderPayRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @ClassName: OrderPayRecordServiceImpl
* @Description:
* @author wind
* @date 2019-3-1
*/
@Service("orderPayRecordService")
public class OrderPayRecordServiceImpl implements OrderPayRecordService {
	
	@Resource(name="orderPayRecordMapper")
    private OrderPayRecordMapper orderPayRecordMapper;


    @Override
    public Long doAddRecord(OrderPayRecord record) {
        return orderPayRecordMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(OrderPayRecord record) {
        return orderPayRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public OrderPayRecord getById(Integer id) {
        return orderPayRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public OrderPayRecord getByPayMark(String payMark, Integer payChannel) {
        return orderPayRecordMapper.selectByPayMark(payMark,payChannel);
    }

    @Override
    public int modifyPayStatusByPayMark(String payMark, String payTid, Integer payChannel, Integer isPay) {
        return orderPayRecordMapper.updatePayStatusByPayMark(payMark,payTid,payChannel,isPay);
    }
}

