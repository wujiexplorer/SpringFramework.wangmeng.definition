package com.lx.benefits.mapper.jdOrder;

import com.lx.benefits.bean.entity.jdOrder.JdMerchantOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JdMerchantOrderItemMapper {

    int delete(Long id);

    int insert(JdMerchantOrderItem record);

    List<JdMerchantOrderItem> getOrderItemById(@Param("orderId") String orderId);

    int update(JdMerchantOrderItem record);

}