package com.wangmeng.test;

import com.wangmeng.config.MainConfigOfAutowired;
import com.wangmeng.config.MainConfigOfLifeCycle;
import com.wangmeng.dao.BookDao;
import com.wangmeng.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Autowired {

    @Test
    public void test01(){
        //创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);
        //BookDao bookDao = applicationContext.getBean(BookDao.class);
       // System.out.println(bookDao);
        applicationContext.close();
    }
}
