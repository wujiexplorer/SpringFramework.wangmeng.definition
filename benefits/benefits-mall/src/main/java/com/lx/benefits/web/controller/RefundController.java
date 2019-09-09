package com.lx.benefits.web.controller;

import com.apollo.common.bean.KeyValuePair;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.apollo.starter.web.utils.PageResult;
import com.apollo.starter.web.utils.Result;
import com.lx.benefits.bean.entity.order.RefundApplyProblem;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.order.RefundApplyProblemService;
import com.lx.benefits.bean.param.order.RefundQueryParam;
import com.lx.benefits.bean.param.refund.RefundApplyParam;
import com.lx.benefits.support.refund.RefundQuerySupport;
import com.lx.benefits.support.refund.RefundSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 退款
 **/
@RestController
@RequestMapping("/benefits/refund")
@Slf4j
public class RefundController {

    @Resource
    private RefundSupport refundSupport;
    @Resource
    private RefundQuerySupport refundQuerySupport;
    @Resource
    private RefundApplyProblemService refundApplyProblemService;

    @PostMapping("/problem/list")
    public Result<List<KeyValuePair<Integer,String>>> list(){
        List<RefundApplyProblem> refundApplyProblems = refundApplyProblemService.listValidProblems(null);
        List<KeyValuePair<Integer,String>> refundApplyProblemList = CollectionExtUtil.copyListWithCheck(refundApplyProblems,refundApplyProblem -> new KeyValuePair<>(refundApplyProblem.getId(),refundApplyProblem.getName()));
        return Result.wrapDefaultSuccessResult(refundApplyProblemList);
    }


    @PostMapping("")
    public PageResult list(@RequestBody RefundQueryParam refundQueryParam){
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        return refundQuerySupport.getUserRefundPage(refundQueryParam,accountId);
    }
    /**
     * 商品级订单退款申请
     * @param refundApplyParam 商品级订单
     * @return
     */
    @PostMapping("/apply")
    public Result apply(@RequestBody RefundApplyParam refundApplyParam){
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        refundSupport.apply(refundApplyParam,accountId,true);
        return Result.wrapDefaultSuccessResult();
    }

    /**
     * 商品级订单退款取消
     * @param id 退款申请ID
     * @return
     */
    @PostMapping("/cancel/{id}")
    public Result cancel(@PathVariable Long id){
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        refundSupport.cancel(id,accountId);
        return Result.wrapDefaultSuccessResult();
    }

    /**
     * 商品级订单退款- 商家同意退货 用户填写物流单信息
     * @param id 退款申请ID
     * @param logisticsChannel 物流公司
     * @param logisticsNumber 物流公司编号
     * @return
     */
    @PostMapping("/enter/logistics/{id}")
    public Result enterLogistics(@PathVariable Long id,
                                 @NotEmpty(message = "请填写物流公司名称") @RequestParam("logisticsChannel") String logisticsChannel,
                                 @NotEmpty(message = "请填写物流单号")@RequestParam("logisticsNumber") String logisticsNumber){
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        refundSupport.enterLogistics(id,logisticsChannel,logisticsNumber,accountId);
        return Result.wrapDefaultSuccessResult();
    }



}