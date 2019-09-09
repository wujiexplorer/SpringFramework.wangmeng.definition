package com.lx.benefits.mapper.order;
import com.lx.benefits.bean.entity.order.RefundApplyProblem;
import com.lx.benefits.mapper.base.IBaseMapper;

import java.util.List;

/**
* @ClassName: RefundApplyProblemMapper
* @Description:
* @author wind
* @date 2019-3-1
*/
public interface RefundApplyProblemMapper extends IBaseMapper<RefundApplyProblem> {

    /**
     * 查询所有有效问题类型
     * @param type
     * @return
     */
    List<RefundApplyProblem> selectValidProblems(Integer type);
}