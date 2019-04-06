package com.wangmeng.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void  add(String name){
        String sql = "insert into log(log) values(?);";
        int updateResult = jdbcTemplate.update(sql,name);
        System.out.println("#####logDao######updateResult:"+updateResult);
    }
}
