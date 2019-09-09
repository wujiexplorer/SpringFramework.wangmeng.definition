package com.lx.benefits.service.user.impl;

import com.lx.benefits.bean.entity.user.UserCompany;
import com.lx.benefits.mapper.user.UserCompanyMapper;
import com.lx.benefits.service.user.UserCompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @ClassName: UserCompanyServiceImpl
* @Description:
* @author wind
* @date 2019-2-11
*/
@Service("userCompanyService")
public class UserCompanyServiceImpl implements UserCompanyService {
	
	@Resource(name="userCompanyMapper")
    private UserCompanyMapper userCompanyMapper;


    @Override
    public Long doAddRecord(UserCompany record) {
        return userCompanyMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(UserCompany record) {
        return userCompanyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserCompany getById(Integer id) {
        return userCompanyMapper.selectByPrimaryKey(id);
    }


}

