package com.wangmeng02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class TransactionUtils {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    public TransactionStatus begin(){
       TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
       System.out.println("开启事务。。。。");
       return transactionStatus;
    }

    public void commit(TransactionStatus transactionStatus){
        dataSourceTransactionManager.commit(transactionStatus);
        System.out.println("提交事务。。。。");
    }

    public void rollback(TransactionStatus transactionStatus){
        dataSourceTransactionManager.rollback(transactionStatus);
        System.out.println("回滚事务。。。。。");

    }

}
