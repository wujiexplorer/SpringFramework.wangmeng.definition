package com.lx.benefits.service.user.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.user.UserReceiveAddress;
import com.lx.benefits.mapper.user.UserReceiveAddressMapper;
import com.lx.benefits.service.user.UserReceiveAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @ClassName: UserReceiveAddressServiceImpl
* @Description:
* @author wind
* @date 2019-2-11
*/
@Service("userReceiveAddressService")
public class UserReceiveAddressServiceImpl implements UserReceiveAddressService {
	
	@Resource(name="userReceiveAddressMapper")
    private UserReceiveAddressMapper userReceiveAddressMapper;


    @Override
    public Long doAddRecord(UserReceiveAddress record) {
        return userReceiveAddressMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(UserReceiveAddress record) {
        return userReceiveAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserReceiveAddress getById(Long id) {
        return userReceiveAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserReceiveAddress getUserDefaultAddress(Long userId) throws BusinessException {
        return userReceiveAddressMapper.selectUserDefaultAddress(userId);
    }
}

