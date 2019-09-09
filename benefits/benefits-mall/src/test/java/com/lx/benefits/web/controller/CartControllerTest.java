package com.lx.benefits.web.controller;

import com.lx.benefits.bean.param.cart.CartSaveParam;
import com.lx.benefits.service.yxOrder.IYXOrderService;
import com.lx.benefits.support.order.OrderSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/** 
* CartController Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 26, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartControllerTest {
    @Autowired
    private CartController cartController;
    @Resource
    private IYXOrderService iyxOrderService;

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: index()
    *
    */
    @Test
    public void testIndex() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: add(CartSaveParam cartAddParam)
    *
    */
    @Test
    public void testAdd() throws Exception {


        CartSaveParam cartSaveParam = new CartSaveParam();
        cartSaveParam.setActivityId(0L);
        cartSaveParam.setChecked(0);
        cartSaveParam.setCount(1);
        cartSaveParam.setSkuId(1226308L);
        cartController.add(cartSaveParam);

    }

    /**
    *
    * Method: modChecked(@PathVariable Integer checked, List<Long> idList)
    *
    */
    @Test
    public void testModChecked() throws Exception {
        iyxOrderService.submit(190312633090112L);

    }

    /**
    *
    * Method: remove(@NotNull List<Long> cartProductIdList)
    *
    */
    @Test
    public void testRemove() throws Exception {
    //TODO: Test goes here...
    }


} 
