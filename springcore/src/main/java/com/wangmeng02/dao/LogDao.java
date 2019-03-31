package com.wangmeng02.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addLog() {
        jdbcTemplate.update("insert into log values (null,'"+System.currentTimeMillis()+"');");
        System.out.println("addlog添加完毕!");
    }

}
