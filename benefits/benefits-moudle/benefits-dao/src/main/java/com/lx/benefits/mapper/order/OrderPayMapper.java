package com.lx.benefits.mapper.order;
import com.lx.benefits.bean.entity.order.OrderPay;
import com.lx.benefits.mapper.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @ClassName: OrderPayMapper
* @Description:
* @author wind
* @date 2019-2-10
*/
public interface OrderPayMapper extends IBaseMapper<OrderPay> {

    /**
     * 查询订单支付记录
     * @param payOrderNumber
     * @return
     */
    OrderPay selectByPayOrderNumber(@Param("payOrderNumber") Long payOrderNumber);
}