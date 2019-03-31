package com.wangmeng02.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository("userDao2")
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;//xml配置了bean，就不要@Autowired，否则冲突（错误）要加的 ，没有冲突，可以协调

    public void add(){
        System.out.println("UserDao02 add........");
    }


    public void add(String name,Integer age){
        String sql = "insert into tbl_user(user_name,age) values(?,?);";
        int update = jdbcTemplate.update(sql,name,age);
        System.out.println("数据库添加user成功。。。。。。+insert:"+update);
    }



}
