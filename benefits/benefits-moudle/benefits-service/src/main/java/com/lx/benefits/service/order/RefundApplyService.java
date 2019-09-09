package com.lx.benefits.service.order;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.RefundApply;
import com.lx.benefits.bean.param.order.RefundQueryParam;
import com.lx.benefits.bean.vo.order.RefundApplyListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @ClassName: RefundApplyService
* @Description:
* @author wind
* @date 2019-2-11
*/
public interface RefundApplyService{


	/**
	 * 添加记录
	 * @param record
	 * @return
	 * @author wind
	 * @date 2019-2-11
	 */
	Long doAddRecord(RefundApply record) throws BusinessException;

	/**
	 * 根据主键编辑项目信息
	 * @param record
	 * @return
	 * @author wind
	 * @date 2019-2-11
	 */
	int doModRecord(RefundApply record) throws BusinessException;

	/**
	 * 根据主键获取项目信息
	 * @param id
	 * @return
	 * @author wind
	 * @date 2019-2-11
	 */
	RefundApply getById(Long id) throws BusinessException;

	List<RefundApply> listValidByProductOrderNumber(Long productOrderNumber);
	/**
	 * 取消退款
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	int cancelRefundApply(Long id) throws BusinessException;

	/**
	 * 待退货转为待退款&输入物流信息
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	int modifyReturnStatus2Refund(Long id) throws BusinessException;

	/**
	 * 待退款转为退款成功
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	int modifyReturnStatus2Succ(Long id) throws BusinessException;

	/**
	 * 用户退款列表
	 * @param accountId
	 * @param refundApplyProblemId
	 * @param startRecord
	 * @param pageSize
	 * @return
	 */
	List<RefundApply> listUserRefund(Long accountId,Integer refundApplyProblemId,Integer startRecord,Integer pageSize);

	/**
	 * 用户退款记录数
	 * @param accountId
	 * @param refundApplyProblemId
	 * @return
	 */
	int getUserRefundCount(Long accountId,Integer refundApplyProblemId);

	/**
	 * 退款申请列表
	 * @param refundQueryParam
	 * @return
	 */
	List<RefundApplyListVO> listRefundApply(RefundQueryParam refundQueryParam);

	/**
	 * 退款申请记录数
	 * @param refundQueryParam
	 * @return
	 */
	int getRefundCount(RefundQueryParam refundQueryParam);
}

