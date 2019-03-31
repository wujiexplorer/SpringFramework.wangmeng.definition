package com.wangmeng02.service;

import com.wangmeng02.TransactionUtils;
import com.wangmeng02.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("userService02")
public class UserService {

   @Autowired//以类型进行查找，要带包名，例如：com.wangmeng02.dao.UserDao
   //@Resource
    private UserDao userDao;

   @Autowired
   private TransactionUtils transactionUtils;

   @Autowired
   private LogService logService;

    public void add(String name,Integer age){
        System.out.println("UserService02 add........");
        //UserDao userDao = new UserDao();
       // int i = 1/0;
        //userDao.add();
        TransactionStatus transactionStatus = null;
        try {
            transactionStatus = transactionUtils.begin();
            userDao.add(name,age);
            //int i = 1/0;
            transactionUtils.commit(transactionStatus);
        }catch (Exception e){
            e.printStackTrace();
            transactionUtils.rollback(transactionStatus);//事务一定要释放，否则一直占用内存，可以commit或rollback释放
        }

    }

    public void add02(){
        System.out.println("UserService02 .....add02.....");
       try{
            userDao.add("yushengjun",20);
            int i = 1/0;
        }catch (Exception e){
           e.printStackTrace();
       }

    }

    public void add021(){
        System.out.println("UserService02 .....add02.....");
        try{
            userDao.add("yushengjun",20);
            int i = 1/0;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //@Transactional(propagation = Propagation.REQUIRED)
    public void add03(){
        logService.addLog();
        System.out.println("UserService02 .....add03.....");
        userDao.add("yushengjun",20);
        int i = 1/0;


    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        System.out.println("setUserDao.....");
        this.userDao = userDao;
    }
}
