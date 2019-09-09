package com.lx.benefits.mapper.orderdelivery;

import com.lx.benefits.bean.entity.orderdelivery.OrderDelivery;
import com.lx.benefits.bean.entity.orderdelivery.OrderDeliveryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDeliveryMapper {
    int countByExample(OrderDeliveryExample example);

    int deleteByExample(OrderDeliveryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderDelivery record);

    int insertSelective(OrderDelivery record);

    List<OrderDelivery> selectByExample(OrderDeliveryExample example);

    OrderDelivery selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderDelivery record, @Param("example") OrderDeliveryExample example);

    int updateByExample(@Param("record") OrderDelivery record, @Param("example") OrderDeliveryExample example);

    int updateByPrimaryKeySelective(OrderDelivery record);

    int updateByPrimaryKey(OrderDelivery record);
}