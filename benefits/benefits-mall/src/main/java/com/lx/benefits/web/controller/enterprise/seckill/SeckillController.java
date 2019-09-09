package com.lx.benefits.web.controller.enterprise.seckill;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.seckill.SeckillOrderReq;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.service.seckill.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/8/20
 * Time:18:26
 * Version:2.x
 * Description:TODO
 **/
@RestController
@RequestMapping("/enterprise/seckill")
@Slf4j
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private SkuService skuService;

    @PostMapping("/findSkusBySeckill")
    public Object findSkusBySeckill(@RequestBody Map<String,Object> params){
        return Response.succ(seckillService.findSkusBySeckillId(params));
    }

    @PostMapping("/selectOrdersBySeckillId")
    public Object selectOrdersBySeckillId(@RequestBody SeckillOrderReq seckillOrderReq){
        return Response.succ(seckillService.selectOrderBySeckillId(seckillOrderReq));
    }
}
