package com.lx.benefits.service.order;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.OrderShipLogistics;

import java.util.List;

/**
* @ClassName: OrderShipLogisticsService
* @Description:
* @author wind
* @date 2019-3-3
*/
public interface OrderShipLogisticsService {
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-3-3
     */
	Long doAddRecord(OrderShipLogistics record) throws BusinessException;

	int doAddBatchRecord(List<OrderShipLogistics> list) throws BusinessException;

	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-3-3
     */
    int doModRecord(OrderShipLogistics record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-3-3
     */
    OrderShipLogistics getById(Long id) throws BusinessException;

	/**
	 * 商品级订单物流
	 * @param itemOrderNumber
	 * @return
	 */
	List<OrderShipLogistics> listItemOrderLogistics(Long itemOrderNumber);

	/**
	 * 商家级订单物流
	 * @param sellerOrderNumber
	 * @return
	 */
	List<OrderShipLogistics> listSelllerOrderLogistics(Long sellerOrderNumber);

 }

