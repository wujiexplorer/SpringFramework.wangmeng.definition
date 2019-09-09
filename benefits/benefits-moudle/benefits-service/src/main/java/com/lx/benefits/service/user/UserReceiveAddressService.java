package com.lx.benefits.service.user;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.user.UserReceiveAddress;

/**
* @ClassName: UserReceiveAddressService
* @Description:
* @author wind
* @date 2019-2-11
*/
public interface UserReceiveAddressService{
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-11
     */
	Long doAddRecord(UserReceiveAddress record) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-11
     */
    int doModRecord(UserReceiveAddress record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-11
     */
    UserReceiveAddress getById(Long id) throws BusinessException;

	/**
	 * 查询用户默认收货地址
	 * @param userId
	 * @return
	 * @author wind
	 * @date 2019-2-11
	 */
	UserReceiveAddress getUserDefaultAddress(Long userId) throws BusinessException;
 }

