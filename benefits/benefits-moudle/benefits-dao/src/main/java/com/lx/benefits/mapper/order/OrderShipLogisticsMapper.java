package com.lx.benefits.mapper.order;
import com.lx.benefits.bean.entity.order.OrderShipLogistics;
import com.lx.benefits.mapper.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @ClassName: OrderShipLogisticsMapper
* @Description:
* @author wind
* @date 2019-3-3
*/
public interface OrderShipLogisticsMapper extends IBaseMapper<OrderShipLogistics> {

    int insertBatch(List<OrderShipLogistics> orderShipLogisticsList);

    List<OrderShipLogistics> selectItemOrderLogistics(@Param("itemOrderNumber") Long itemOrderNumber);

    List<OrderShipLogistics> selectSellerOrderLogistics(@Param("sellerOrderNumber") Long sellerOrderNumber);
}