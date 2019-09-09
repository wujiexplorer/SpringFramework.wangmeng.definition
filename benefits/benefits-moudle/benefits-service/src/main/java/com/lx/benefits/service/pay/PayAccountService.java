package com.lx.benefits.service.pay;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.pay.PayAccount;

/**
* @ClassName: PayAccountService
* @Description:
* @author wind
* @date 2019-2-10
*/
public interface PayAccountService{
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
	Long doAddRecord(PayAccount record) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    int doModRecord(PayAccount record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    PayAccount getById(Integer id) throws BusinessException;


 }

