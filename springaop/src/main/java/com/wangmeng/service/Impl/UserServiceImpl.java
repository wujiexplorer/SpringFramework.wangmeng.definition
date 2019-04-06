package com.wangmeng.service.Impl;

import com.wangmeng.annotation.ExtTransaciton;
import com.wangmeng.dao.UserDao;
import com.wangmeng.service.LogService;
import com.wangmeng.service.UserService;
import com.wangmeng.transaction.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransactionUtils transactionUtils;

    @Autowired
    private LogService logService;

//    public void add() {
//        //try{
//            //System.out.println("静态代理 开启事务。。。");
////            int i = 1/0;
////            System.out.println("往数据库添加数据。。。。。");
//            //System.out.println("静态代理  提交事务。。。");
//     //   }catch (Exception e){
//    //        e.printStackTrace();
//     //  }加了try catch,就是没有异常通知，运行通知是有的 运行通知是根据proceedJoinPoint.proceed()是否完整执行，
//        //在业务加try catch proceedJoinPoint.proceed()是可以完整执行的，只是有一段要在异常中执行
//        TransactionStatus transactionStatus = null;
//        try{
//            transactionStatus = transactionUtils.begin();
//            userDao.add("test001",20);
//            System.out.println("开始报错了。。。。。");
//            //int i = 1/0;
//            System.out.println("#################################");
//            userDao.add("test002",21);
//            if(transactionStatus != null)
//            transactionUtils.commit(transactionStatus);
//        }catch (Exception e){
//            e.getMessage();
//            if(transactionStatus != null)
//            transactionUtils.rollback(transactionStatus);
//        }
//
//
//    }

//    public void add(){
//        try{
//            userDao.add("test001",20);
//            // System.out.println("开始报错了。。。。。");
//            int i = 1/0;
//            System.out.println("#################################");
//            userDao.add("test002",21);
//        }catch (Exception e){
//            e.getMessage();//Service层加了try catch之后，有部分提交成功
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }
//    }

    @Transactional
    //@ExtTransaciton
    public void add(){
    //    try{
        logService.addLog();//NOT_SUPPORT以非事务运行，但会对当前事务造成影响，比如抛出异常等，会导致当前事务回滚
            userDao.add("test001",20);
            // System.out.println("开始报错了。。。。。");
            int i = 1/0;
            System.out.println("#################################");
            userDao.add("test002",21);
  //      }catch (Exception e){
    //        e.getMessage();
     //   }

    }

    private  UserServiceImpl(){//反射，方法私有公有都可以初始化
        System.out.println("无参数构造函数初始化。。。。反射技术");
    }
}
