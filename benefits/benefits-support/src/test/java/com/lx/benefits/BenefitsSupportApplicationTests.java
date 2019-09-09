package com.lx.benefits;

import com.lx.benefits.service.yx.IYxCallBackService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BenefitsSupportApplicationTests {

    @Autowired
    IYxCallBackService iYxCallBackService;

    @Test
    public void contextLoads() throws Exception{

        iYxCallBackService.registerService();

        iYxCallBackService.serviceList();



    }

}

