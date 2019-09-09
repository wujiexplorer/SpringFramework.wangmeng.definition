package com.lx.benefits.service.cart;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.cart.CartProductArchive;

import java.util.List;

/**
* @ClassName: CartProductArchiveService
* @Description:
* @author wind
* @date 2019-2-10
*/
public interface CartProductArchiveService{
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
	Long doAddRecord(CartProductArchive record) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    int doModRecord(CartProductArchive record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    CartProductArchive getById(Integer id) throws BusinessException;

	/**
	 * 批量保存
	 * @param list
	 * @return
	 * @throws BusinessException
	 */
	int doAddBatchRecord(List<CartProductArchive> list) throws BusinessException;


 }

