package com.wangmeng.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@Scope("prototype")
public class TransactionUtils {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    private TransactionStatus transactionStatus;

    public TransactionStatus begin(){
        System.out.println("开启事务");
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
        return transactionStatus;
    }

    public void commit(TransactionStatus transactionStatus){
        System.out.println("提交事务");
        dataSourceTransactionManager.commit(transactionStatus);
    }

    public void rollback(){
        System.out.println("回滚事务。。。。");
        dataSourceTransactionManager.rollback(transactionStatus );
    }
}
