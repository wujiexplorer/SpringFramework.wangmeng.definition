package com.lx.benefits.service.order.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.RefundApply;
import com.lx.benefits.bean.param.order.RefundQueryParam;
import com.lx.benefits.bean.vo.order.RefundApplyListVO;
import com.lx.benefits.mapper.order.RefundApplyMapper;
import com.lx.benefits.service.order.RefundApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @ClassName: RefundApplyServiceImpl
* @Description:
* @author wind
* @date 2019-2-11
*/
@Service("refundApplyService")
public class RefundApplyServiceImpl implements RefundApplyService {
	
	@Resource(name="refundApplyMapper")
    private RefundApplyMapper refundApplyMapper;

    @Override
    public Long doAddRecord(RefundApply record) {
        return refundApplyMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(RefundApply record) {
        return refundApplyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public RefundApply getById(Long id) {
        return refundApplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RefundApply> listValidByProductOrderNumber(Long productOrderNumber){
       return refundApplyMapper.selectValidByProductOrderNumber(productOrderNumber);
    }

    @Override
    public int cancelRefundApply(Long id) throws BusinessException {
        return refundApplyMapper.updateRefundStatus2Cancel(id);
    }

    @Override
    public int modifyReturnStatus2Refund(Long id) throws BusinessException {
        return refundApplyMapper.updateReturnStatus2Refund(id);
    }

    @Override
    public int modifyReturnStatus2Succ(Long id) throws BusinessException {
        return refundApplyMapper.updateReturnStatus2Succ(id);
    }

    @Override
    public List<RefundApply> listUserRefund(Long accountId, Integer refundApplyProblemId, Integer startRecord, Integer pageSize) {
        return refundApplyMapper.selectUserRefundList(accountId,refundApplyProblemId,startRecord,pageSize);
    }

    @Override
    public int getUserRefundCount(Long accountId, Integer refundApplyProblemId) {
        return refundApplyMapper.selectUserRefundCount(accountId,refundApplyProblemId);
    }

    @Override
    public List<RefundApplyListVO> listRefundApply(RefundQueryParam refundQueryParam) {
        return refundApplyMapper.selectRefundList(refundQueryParam);
    }

    @Override
    public int getRefundCount(RefundQueryParam refundQueryParam) {
        return refundApplyMapper.selectRefundCount(refundQueryParam);
    }
}

