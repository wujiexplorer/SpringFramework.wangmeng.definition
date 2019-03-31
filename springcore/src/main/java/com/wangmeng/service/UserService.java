package com.wangmeng.service;

import com.wangmeng02.dao.UserDao;

public class UserService {

    private UserDao userDao;

    public void add(){
        System.out.println("UserService add........");
        //UserDao userDao = new UserDao();
        userDao.add();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        System.out.println("setUserDao.....");
        this.userDao = userDao;
    }
}
