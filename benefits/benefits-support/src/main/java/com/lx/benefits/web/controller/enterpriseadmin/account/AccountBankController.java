package com.lx.benefits.web.controller.enterpriseadmin.account;

import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.account.AccountBankReq;
import com.lx.benefits.bean.vo.account.AccountBankVO;
import com.lx.benefits.service.account.AccountBankService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * User:wangmeng
 * Date:2019/7/15
 * Time:18:07
 * Version:2.x
 * Description:TODO
 **/

@RestController("AccountBankEnterpriseController")
@RequestMapping("/enterpriseadmin/account")
@Slf4j
public class AccountBankController {

    @Autowired
    private AccountBankService accountBankService;

    /**
     * 获取对账单报表
     * @param accountBankReq
     * @return
     */
    @PostMapping("/getAccountBankDetail")
    public JSONObject getAccountBankDetail(@RequestBody(required = false) AccountBankReq accountBankReq){
        try{
            PageResultBean<AccountBankVO> list = accountBankService.getAccountBankDetail(accountBankReq);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("list",list);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }


    /**
     * 获取对账单Excel
     * @param accountBankReq
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/exportAccountBank")
    public void exportAccountBank(AccountBankReq accountBankReq, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = "对账单列表信息.xlsx";
        OutputStream outputStream = response.getOutputStream();
        try {
            response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE);
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO-8859-1"));
            accountBankService.exportAccountBank(accountBankReq,outputStream);
        } catch (BusinessException e) {
            response.setHeader("Content-Disposition", "");
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            String failMessage = Response.fail(e.getMessage()).toString();
            log.error(failMessage);
            response.getOutputStream().write(failMessage.getBytes("UTF-8"));
            response.setStatus(HttpStatus.SC_OK);
        }
    }
}
