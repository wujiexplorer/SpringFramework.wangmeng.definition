package com.lx.benefits.service.order;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.OrderPay;
import org.apache.ibatis.annotations.Param;

/**
* @ClassName: OrderPayService
* @Description:
* @author wind
* @date 2019-2-10
*/
public interface OrderPayService{
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
	Long doAddRecord(OrderPay record) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    int doModRecord(OrderPay record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    OrderPay getById(Long id) throws BusinessException;

	/**
	 * 查询订单支付记录
	 * @param payOrderNumber
	 * @return
	 */
	OrderPay getByPayOrderNumber(Long payOrderNumber);


 }

