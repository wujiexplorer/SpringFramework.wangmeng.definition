package com.lx.benefits.service.order;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.OrderPoint;

/**
* @ClassName: OrderPointService
* @Description:
* @author wind
* @date 2019-2-23
*/
public interface OrderPointService {
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-23
     */
	Long doAddRecord(OrderPoint record) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-23
     */
    int doModRecord(OrderPoint record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-23
     */
    OrderPoint getById(Integer id) throws BusinessException;


 }

