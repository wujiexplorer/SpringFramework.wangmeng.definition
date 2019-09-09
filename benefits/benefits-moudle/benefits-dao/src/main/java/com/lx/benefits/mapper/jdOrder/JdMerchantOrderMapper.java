package com.lx.benefits.mapper.jdOrder;

import com.lx.benefits.bean.dto.jdOrder.JdMerchantOrderReq;
import com.lx.benefits.bean.entity.jdOrder.JdMerchantOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JdMerchantOrderMapper {

    int delOredr(Long id);

    int insert(JdMerchantOrder record);

    JdMerchantOrder getOrderById(Long id);

    JdMerchantOrder getOrderByMerchantId(Long id);

    int update(JdMerchantOrder record);

    List<JdMerchantOrder> getOrderList(JdMerchantOrderReq record);

    Integer getOrderListCount(JdMerchantOrderReq record);
}