package com.lx.benefits.mapper.order;
import com.lx.benefits.bean.entity.order.RefundApply;
import com.lx.benefits.bean.param.order.RefundQueryParam;
import com.lx.benefits.bean.vo.order.RefundApplyListVO;
import com.lx.benefits.mapper.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @ClassName: RefundApplyMapper
* @Description:
* @author wind
* @date 2019-2-11
*/
public interface RefundApplyMapper extends IBaseMapper<RefundApply> {
    /**
     * 退款列表
     * @return
     */
    List<RefundApplyListVO> selectRefundList(RefundQueryParam refundQueryParam);

    /**
     * 退款数
     * @return
     */
    int selectRefundCount(RefundQueryParam refundQueryParam);

    /**
     * 查询正常状态退款记录
     * @param productOrderNumber
     * @return
     */
    List<RefundApply> selectValidByProductOrderNumber(Long productOrderNumber);

    /**
     * 用户退款列表
     * @param accountId
     * @param refundApplyProblemId
     * @param startRecord
     * @param pageSize
     * @return
     */
    List<RefundApply> selectUserRefundList(
                                           @Param("accountId") Long accountId,
                                           @Param("refundApplyProblemId")Integer refundApplyProblemId,
                                           @Param("startRecord")Integer startRecord,
                                           @Param("pageSize")Integer pageSize);

    /**
     * 用户退款记录数
     * @param accountId
     * @param refundApplyProblemId
     * @return
     */
    int selectUserRefundCount(
            @Param("accountId") Long accountId,
            @Param("refundApplyProblemId")Integer refundApplyProblemId);

    /**
     * 退款取消
     * @param id
     * @return
     */
    int updateRefundStatus2Cancel(Long id);

    /**
     * 待退货转待退款&录入物流信息
     * @param id
     * @return
     */
    int updateReturnStatus2Refund(@Param("id")Long id);

    /**
     * 待待退款转退款成功
     * @param id
     * @return
     */
    int updateReturnStatus2Succ(@Param("id")Long id);

	
}