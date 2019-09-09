package com.lx.benefits.web.controller.admin.account;

import com.alibaba.fastjson.JSONObject;
import com.apollo.common.enums.base.DatePatternEnum;
import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.base.DateUtil;
import com.apollo.common.utils.bean.BeanUtil;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.apollo.common.utils.export.StringTemplateExcelUtil;
import com.google.common.collect.Lists;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.agent.AgentAccountInfo;
import com.lx.benefits.bean.enums.OrderEnum;
import com.lx.benefits.bean.eo.ItemOrderEO;
import com.lx.benefits.bean.param.order.ItemOrderListQueryParam;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.account.AccountBankInfoExcelModel;
import com.lx.benefits.bean.vo.account.AccountBankReq;
import com.lx.benefits.bean.vo.account.AccountBankVO;
import com.lx.benefits.bean.vo.order.ItemOrderListVO;
import com.lx.benefits.mapper.agent.AgentAccountInfoMapper;
import com.lx.benefits.service.account.AccountBankService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;

/**
 * User:wangmeng
 * Date:2019/7/15
 * Time:18:07
 * Version:2.x
 * Description:TODO
 **/

@RestController("AccountBankController")
@RequestMapping("/admin/account")
@Slf4j
public class AccountBankController {

    @Autowired
    private AccountBankService accountBankService;


    @Autowired
    private AgentAccountInfoMapper agentAccountInfoMapper;

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

    @GetMapping(value = "/export")
    public void export(AccountBankReq req, HttpServletResponse response) {


        Function<AccountBankReq, List<AccountBankInfoExcelModel>> f = (pageQueryBO) -> {
            final Integer pageSize = Integer.MAX_VALUE;
            pageQueryBO.setPage(1);
            pageQueryBO.setPageSize(pageSize);
            List<AccountBankVO> list = accountBankService.listAccountBankDetail(pageQueryBO);
            if ( CollectionUtils.isEmpty(list)) {
                return Lists.newArrayList();
            }
            return CollectionExtUtil.copyListWithCheck(list, itemOrderListVO -> {
                AccountBankInfoExcelModel eo = BeanUtil.copySpring(itemOrderListVO, AccountBankInfoExcelModel.class);
                Integer agentId = itemOrderListVO.getAgentId();
                if (null != agentId) {
                    AgentAccountInfo agentAccountInfo = agentAccountInfoMapper.selectByPrimaryKey(agentId);
                    if (null != agentAccountInfo) {
                        eo.setAgentName(agentAccountInfo.getAgentName());
                    }
                }
                eo.setPrice(itemOrderListVO.getPrice().setScale(2, RoundingMode.HALF_UP));
                eo.setThirdCostprice(itemOrderListVO.getThirdCostprice().setScale(2, RoundingMode.HALF_UP));
                eo.setPointAmount(itemOrderListVO.getPointAmount() == null ? BigDecimal.ZERO : itemOrderListVO.getPointAmount().setScale(2, RoundingMode.HALF_UP));
                eo.setCardAmount(itemOrderListVO.getCardAmount() == null ? BigDecimal.ZERO : itemOrderListVO.getCardAmount().setScale(2, RoundingMode.HALF_UP));
                eo.setReturnAccountPoint(itemOrderListVO.getReturnAccountPoint() == null ? BigDecimal.ZERO : itemOrderListVO.getReturnAccountPoint().setScale(2, RoundingMode.HALF_UP));
                return eo;
            });
        };
        StringTemplateExcelUtil.exportCompressDataListBySegment(req,f, AccountBankInfoExcelModel.class, response);
    }
}
