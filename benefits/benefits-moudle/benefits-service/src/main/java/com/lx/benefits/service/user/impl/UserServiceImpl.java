package com.lx.benefits.service.user.impl;

import com.lx.benefits.bean.entity.user.User;
import com.lx.benefits.mapper.user.UserMapper;
import com.lx.benefits.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @ClassName: UserServiceImpl
* @Description:
* @author wind
* @date 2019-2-11
*/
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userMapper")
    private UserMapper userMapper;


    @Override
    public Long doAddRecord(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }


}

