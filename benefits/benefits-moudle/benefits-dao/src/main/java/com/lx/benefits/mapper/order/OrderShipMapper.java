package com.lx.benefits.mapper.order;
import com.lx.benefits.bean.entity.order.OrderShip;
import com.lx.benefits.mapper.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @ClassName: OrderShipMapper
* @Description:
* @author wind
* @date 2019-2-10
*/
public interface OrderShipMapper extends IBaseMapper<OrderShip>{

    int insertBatch(List<OrderShip> list);

    OrderShip selectByOrderNumber(@Param("orderNumber") Long orderNumber);

	OrderShip selectByProductOrderNumber(@Param("orderNumber")Long orderNumber);
}