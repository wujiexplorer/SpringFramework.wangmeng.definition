package com.lx.benefits.mapper.yxOrder;

import com.lx.benefits.bean.entity.yxOrder.YxOrderInfoCancel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface YxOrderInfoCancelMapper {

    int deleteByPrimaryKey(Long id);

    int insert(YxOrderInfoCancel record);

    YxOrderInfoCancel selectByOrderCode(@Param("orderCode") Long orderCode);

    YxOrderInfoCancel selectByPrimaryKey(Long id);

    int update(YxOrderInfoCancel record);

}