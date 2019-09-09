package com.lx.benefits.service.order;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.OrderShip;

import java.util.List;

/**
* @ClassName: OrderShipService
* @Description:
* @author wind
* @date 2019-2-10
*/
public interface OrderShipService{
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
	Long doAddRecord(OrderShip record) throws BusinessException;

	/**
	 * 保存订单收货地址信息
	 * @param list
	 * @return
	 * @throws BusinessException
	 */
	int doAddBatchRecord(List<OrderShip> list) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    int doModRecord(OrderShip record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    OrderShip getById(Long id) throws BusinessException;

	/**
	 * 根据订单编号获取项目信息
	 * @param orderNumber
	 * @return
	 * @author wind
	 * @date 2019-2-10
	 */
	OrderShip getByOrderNumber(Long orderNumber) throws BusinessException;

	/**
	 * 获取收货地址信息
	 * 
	 * @param orderNumber
	 *            商品级订单
	 * @return
	 */
	OrderShip getByProductOrderNumber(Long orderNumber);
 }

