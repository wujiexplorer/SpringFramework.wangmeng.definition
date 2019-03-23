package com.wangmeng.test;

import com.wangmeng.bean.Boss;
import com.wangmeng.bean.Car;
import com.wangmeng.bean.Color;
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
        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);

        Color color = applicationContext.getBean(Color.class);
        System.out.println(color);
        System.out.println(applicationContext);
        applicationContext.close();
    }
}
