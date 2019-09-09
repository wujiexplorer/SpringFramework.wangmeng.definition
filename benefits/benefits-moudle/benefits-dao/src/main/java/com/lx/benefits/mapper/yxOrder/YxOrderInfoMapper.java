package com.lx.benefits.mapper.yxOrder;

import com.lx.benefits.bean.dto.yxOrder.YxOrderInfoReq;
import com.lx.benefits.bean.entity.yxOrder.YxOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface YxOrderInfoMapper {

    int insert(YxOrderInfo record);

    YxOrderInfo gerOrderById(Long id);

    YxOrderInfo gerOrderByOrderCode(@Param("orderCode") String subOrderCode);

    List<YxOrderInfo> getOrderList(YxOrderInfoReq record);

    Integer getOrderListCount(YxOrderInfoReq record);

    int update(YxOrderInfo record);
}