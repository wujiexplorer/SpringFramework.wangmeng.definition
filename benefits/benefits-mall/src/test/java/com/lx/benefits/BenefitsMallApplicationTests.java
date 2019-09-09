package com.lx.benefits;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BenefitsMallApplicationTests {

    @Test
    public void contextLoads() {
        Date date = new Date();
        System.out.println(date);
        //Tue Apr 23 14:15:19 CST 2019
    }

}

