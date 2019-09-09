package com.lx.benefits.web.controller.admin.voucher;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.voucher.VoucherProductTemplate;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.voucher.VoucherProductTemplateService;
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
@RequestMapping("/admin/productTemplate")
public class VoucherProductTemplateController {

    @Autowired
    private VoucherProductTemplateService voucherProductTemplateService;

    @PostMapping("/insert")
    public Object insertVoucherProductTemplate(@RequestBody VoucherProductTemplate voucherProductTemplate){
        return Response.succ(voucherProductTemplateService.insertVoucherProductTemplate(voucherProductTemplate));
    }

    @PostMapping("/update")
    public Object updateVoucherProductTemplate(@RequestBody VoucherProductTemplate voucherProductTemplate){
        return Response.succ(voucherProductTemplateService.updateVoucherProductTemplate(voucherProductTemplate));
    }

    @GetMapping("/select")
    public Object findVoucherProductTemplates(String templateName, PageBean pageBean){
        return Response.succ(voucherProductTemplateService.findVoucherProductTemplates(templateName,pageBean));
    }

    @GetMapping("/getVoucherProductTemplateDetail")
    public Object getVoucherProductTemplateDetail(Long templateId){
        return Response.succ(voucherProductTemplateService.getVoucherProductTemplateDetail(templateId));
    }
}
