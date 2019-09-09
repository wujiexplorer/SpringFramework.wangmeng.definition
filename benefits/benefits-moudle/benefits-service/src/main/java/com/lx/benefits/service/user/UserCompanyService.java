package com.lx.benefits.service.user;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.user.UserCompany;

/**
* @ClassName: UserCompanyService
* @Description:
* @author wind
* @date 2019-2-11
*/
public interface UserCompanyService{
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-11
     */
	Long doAddRecord(UserCompany record) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-11
     */
    int doModRecord(UserCompany record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-11
     */
    UserCompany getById(Integer id) throws BusinessException;


 }

