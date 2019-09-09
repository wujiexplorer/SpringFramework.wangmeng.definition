package com.lx.benefits.web.controller.enterprise.voucher;

import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/8/12
 * Time:17:27
 * Version:2.x
 * Description:TODO
 **/
@RestController
@RequestMapping("/enterprise/voucher")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @GetMapping("/findVouchersByEmployee")
    public Object findVouchersByEmployee(){
        return Response.succ(voucherService.findVouchersByEmployee());
    }


    @GetMapping("/processReceivedVouchers")
    public Object processReceivedVouchers(Long voucherId){
        return Response.succ(voucherService.processReceivedVouchers(voucherId));
    }

    @PostMapping("/findSkusByVoucherId")
    public Object findSkusByVoucherId(@RequestBody Map<String,Object> params){
        return Response.succ(voucherService.findSkusByVoucherId(params));
    }

    @PostMapping("/findSpusByVoucherId")
    public Object findSpusByVoucherId(@RequestBody Map<String,Object> params){
        return Response.succ(voucherService.findSpusByVoucherId(params));
    }

    @GetMapping("/validateVouchersThreshold")
    public Object validateVouchersThreshold(Long userReceiveAddrId){
        return Response.succ(voucherService.validateVouchersThreshold(userReceiveAddrId));
    }

    @GetMapping("/updateEmployeeCreditInfoByOrderVouchers")
    public Object updateEmployeeCreditInfoByOrderVouchers(String voucherSkus){
        return Response.succ(voucherService.updateEmployeeCreditInfoByOrderVouchers(voucherSkus));
    }


}
