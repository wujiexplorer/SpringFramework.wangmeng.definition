package com.lx.benefits.service.pay.impl;

import com.lx.benefits.bean.entity.pay.PayAccount;
import com.lx.benefits.mapper.pay.PayAccountMapper;
import com.lx.benefits.service.pay.PayAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @ClassName: PayAccountServiceImpl
* @Description:
* @author wind
* @date 2019-2-10
*/
@Service("payAccountService")
public class PayAccountServiceImpl implements PayAccountService {
	
	@Resource(name="payAccountMapper")
    private PayAccountMapper payAccountMapper;


    @Override
    public Long doAddRecord(PayAccount record) {
        return payAccountMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(PayAccount record) {
        return payAccountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PayAccount getById(Integer id) {
        return payAccountMapper.selectByPrimaryKey(id);
    }


}

