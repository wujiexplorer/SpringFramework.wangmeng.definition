package com.lx.benefits.web.controller.admin.seckill;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.seckill.Seckill;
import com.lx.benefits.bean.entity.seckill.SeckillOrderReq;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.seckill.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User:wangmeng
 * Date:2019/8/20
 * Time:11:48
 * Version:2.x
 * Description:TODO
 **/
@RestController
@RequestMapping("/admin/seckill")
@Slf4j
public class SeckillController {

    @Autowired
    private SeckillService seckillService;


    @PostMapping("/insert")
    public Object insertSeckill(@RequestBody Seckill seckill){
        return Response.succ(seckillService.insertSeckill(seckill));
    }

    @GetMapping("/select")
    public Object getSeckill(Long seckillId){
        return Response.succ(seckillService.getSeckill(seckillId));
    }

    @PostMapping("/update")
    public Object updateSeckill(@RequestBody Seckill seckill){
        return Response.succ(seckillService.updateSeckill(seckill));
    }

    @GetMapping("/findSeckills")
    public Object findSeckills(String seckillName, Integer status, PageBean pageBean){
        return Response.succ(seckillService.findSeckills(seckillName,status,pageBean));
    }

    @PostMapping("/selectOrdersBySeckillId")
    public Object selectOrdersBySeckillId(@RequestBody SeckillOrderReq seckillOrderReq){
        return Response.succ(seckillService.selectOrderBySeckillId(seckillOrderReq));
    }
}
