package com.lx.benefits.service.order.impl;

import com.lx.benefits.bean.entity.order.RefundPackage;
import com.lx.benefits.mapper.order.RefundPackageMapper;
import com.lx.benefits.service.order.RefundPackageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @ClassName: RefundPackageServiceImpl
* @Description:
* @author wind
* @date 2019-2-11
*/
@Service("refundPackageService")
public class RefundPackageServiceImpl implements RefundPackageService {
	
	@Resource(name="refundPackageMapper")
    private RefundPackageMapper refundPackageMapper;


    @Override
    public Long doAddRecord(RefundPackage record) {
        return refundPackageMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(RefundPackage record) {
        return refundPackageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public RefundPackage getById(Integer id) {
        return refundPackageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RefundPackage> listLogisticsByRefundNumbers(List<Long> refundApplyNumberList) {
        return refundPackageMapper.selectLogisticsByRefundNumbers(refundApplyNumberList);
    }
}

