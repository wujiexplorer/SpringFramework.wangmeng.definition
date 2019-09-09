package com.lx.benefits.service.order;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.OrderPayRecord;
import org.apache.ibatis.annotations.Param;

/**
* @ClassName: OrderPayRecordService
* @Description:
* @author wind
* @date 2019-3-1
*/
public interface OrderPayRecordService {
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-3-1
     */
	Long doAddRecord(OrderPayRecord record) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-3-1
     */
    int doModRecord(OrderPayRecord record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-3-1
     */
    OrderPayRecord getById(Integer id) throws BusinessException;

	/**
	 * 查询支付信息
	 * @param payMark
	 * @param payChannel
	 * @return
	 */
	OrderPayRecord getByPayMark(String payMark ,Integer payChannel);

	/**
	 * 更新支付单状态
	 * @param payMark
	 * @param payTid
	 * @param payChannel
	 * @param isPay
	 * @return
	 */
	int modifyPayStatusByPayMark(String payMark ,String payTid,Integer payChannel,Integer isPay);
 }

