package com.lx.benefits.service.order;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.RefundPackage;

import java.util.List;

/**
* @ClassName: RefundPackageService
* @Description:
* @author wind
* @date 2019-2-11
*/
public interface RefundPackageService{
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-11
     */
	Long doAddRecord(RefundPackage record) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-11
     */
    int doModRecord(RefundPackage record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-11
     */
    RefundPackage getById(Integer id) throws BusinessException;

	/**
	 * 查询退款物流信息
	 * @param refundApplyNumberList
	 * @return
	 */
	List<RefundPackage> listLogisticsByRefundNumbers(List<Long> refundApplyNumberList);


 }

