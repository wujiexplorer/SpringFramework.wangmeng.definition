package com.lx.benefits.service.order.impl;

import com.lx.benefits.bean.entity.order.RefundApplyProblem;
import com.lx.benefits.mapper.order.RefundApplyProblemMapper;
import com.lx.benefits.service.order.RefundApplyProblemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @ClassName: RefundApplyProblemServiceImpl
* @Description:
* @author wind
* @date 2019-3-1
*/
@Service("refundApplyProblemService")
public class RefundApplyProblemServiceImpl implements RefundApplyProblemService {
	
	@Resource(name="refundApplyProblemMapper")
    private RefundApplyProblemMapper refundApplyProblemMapper;


    @Override
    public Long doAddRecord(RefundApplyProblem record) {
        return refundApplyProblemMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(RefundApplyProblem record) {
        return refundApplyProblemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public RefundApplyProblem getById(Integer id) {
        return refundApplyProblemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RefundApplyProblem> listValidProblems(Integer type) {
        return refundApplyProblemMapper.selectValidProblems(type);
    }
}

