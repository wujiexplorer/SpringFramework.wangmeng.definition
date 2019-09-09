package com.lx.benefits.service.user;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.user.User;

/**
* @ClassName: UserService
* @Description:
* @author wind
* @date 2019-2-11
*/
public interface UserService{
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-11
     */
	Long doAddRecord(User record) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-11
     */
    int doModRecord(User record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-11
     */
    User getById(Integer id) throws BusinessException;


 }

