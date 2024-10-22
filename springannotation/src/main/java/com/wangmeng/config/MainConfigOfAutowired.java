package com.wangmeng.config;

import com.wangmeng.bean.Car;
import com.wangmeng.bean.Color;
import com.wangmeng.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan({"com.wangmeng.dao","com.wangmeng.service","com.wangmeng.controller","com.wangmeng.bean"})
public class MainConfigOfAutowired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }

    //@Bean+省略的@Autowired参数注入，参数从IOC容器中获得
    @Bean
    public Color color(Car car){
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
