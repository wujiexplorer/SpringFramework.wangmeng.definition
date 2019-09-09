package com.lx.benefits.service.order;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.RefundApplyProblem;

import java.util.List;

/**
* @ClassName: RefundApplyProblemService
* @Description:
* @author wind
* @date 2019-3-1
*/
public interface RefundApplyProblemService {
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-3-1
     */
	Long doAddRecord(RefundApplyProblem record) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-3-1
     */
    int doModRecord(RefundApplyProblem record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-3-1
     */
    RefundApplyProblem getById(Integer id) throws BusinessException;

	/**
	 * 查询有效问题类型列表
	 * @param type
	 * @return
	 */
	List<RefundApplyProblem> listValidProblems(Integer type);


 }

