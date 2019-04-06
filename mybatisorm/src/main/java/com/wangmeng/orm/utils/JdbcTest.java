package com.wangmeng.orm.utils;

import java.sql.ResultSet;
import java.util.ArrayList;

public class JdbcTest {

    public static void main(String[] args) throws Exception{
       // String insertSql = "insert into tbl_user(user_name,age) values(?,?);";
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("王猛");
        objects.add(30);

        ResultSet res = JDBCUtils.query("select * from tbl_user where user_name = ? and age = ?", objects);
        while (res.next()){
            Integer id = res.getInt("id");
            String userName = res.getString("user_name");
            System.out.println("id:"+id+",userName:"+userName);
        }
    }
}
