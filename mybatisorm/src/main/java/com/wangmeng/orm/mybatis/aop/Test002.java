package com.wangmeng.orm.mybatis.aop;

import com.wangmeng.entity.User;
import com.wangmeng.mapper.UserMapper;
import com.wangmeng.sql.SqlSession;

public class Test002 {

    public static void main(String[] args) {
        UserMapper userMapper = SqlSession.getMapper(UserMapper.class);
//        int insertUser = userMapper.insertUser("okwangmeng111111111111猛哥", 1111);
//        System.out.println("insertUser:"+insertUser);
        User user = userMapper.selectUser("王猛", 30);
        System.out.println("结果集："+user.getUserName()+","+user.getAge()+","+user.getId());
    }
}
