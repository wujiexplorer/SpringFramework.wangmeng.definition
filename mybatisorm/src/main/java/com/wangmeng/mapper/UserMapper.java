package com.wangmeng.mapper;

import com.wangmeng.entity.User;
import com.wangmeng.orm.annotation.ExtInsert;
import com.wangmeng.orm.annotation.ExtSelect;
import com.wangmeng.orm.annotation.Extparam;

public interface UserMapper {

    @ExtInsert("insert into tbl_user(user_name,age) values(#{userName},#{age})")
    public int insertUser(@Extparam("userName") String userName,@Extparam("age") Integer  age);

    @ExtSelect("select * from tbl_user where user_name=#{userName} and age=#{age} ")
    User selectUser(@Extparam("userName") String name, @Extparam("age") Integer age);
}
