package com.lx.benefits.web.controller.admin.voucher;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.voucher.Voucher;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User:wangmeng
 * Date:2019/8/8
 * Time:10:33
 * Version:2.x
 * Description:TODO
 **/

@RestController
@RequestMapping("/admin/voucher")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @PostMapping("/insert")
    public Object insertVoucher(@RequestBody Voucher voucher){
        return Response.succ(voucherService.insertVoucher(voucher));
    }


    @PostMapping("/update")
    public Object updateVoucher(@RequestBody Voucher voucher){
        return Response.succ(voucherService.updateVoucher(voucher));
    }

    @GetMapping("/select")
    public Object findVouchers(String voucherName, Integer voucherStatus, Integer voucherType, PageBean pageBean){
        return Response.succ(voucherService.findVouchers(voucherName,voucherStatus,voucherType,pageBean));
    }

    @GetMapping("/findTemplateVouchers")
    public Object findTemplateVouchers(){
        return Response.succ(voucherService.findTemplateVouchers());
    }

}
