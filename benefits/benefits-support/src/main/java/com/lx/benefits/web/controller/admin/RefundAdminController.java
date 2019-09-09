package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apollo.common.bean.KeyValuePair;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.apollo.starter.web.utils.PageResult;
import com.apollo.starter.web.utils.Result;
import com.lx.benefits.bean.entity.order.RefundApplyProblem;
import com.lx.benefits.bean.enums.RefundEnum;
import com.lx.benefits.bean.param.order.RefundQueryParam;
import com.lx.benefits.bean.param.refund.AgreeRefundParam;
import com.lx.benefits.bean.param.refund.RefundApplyParam;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.order.RefundApplyListVO;
import com.lx.benefits.bean.vo.order.RefundApplyVO;
import com.lx.benefits.service.order.RefundApplyProblemService;
import com.lx.benefits.service.order.RefundApplyService;
import com.lx.benefits.support.refund.RefundQuerySupport;
import com.lx.benefits.support.refund.RefundSupport;
import com.lx.benefits.web.controller.supplieradmin.BaseSupplierAdminController;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController("adminRefundAdminController")
@RequestMapping("/admin/refund")
public class RefundAdminController extends BaseSupplierAdminController {

    private final static Logger logger = LoggerFactory.getLogger(RefundAdminController.class);

    @Resource
    private RefundApplyProblemService refundApplyProblemService;
    @Resource
    private RefundApplyService refundApplyService;
    @Resource
    private RefundSupport refundSupport;
    @Resource
    private RefundQuerySupport refundQuerySupport;


    @PostMapping("/problem/list")
    public Result<List<KeyValuePair<Integer,String>>> list(){
        List<RefundApplyProblem> refundApplyProblems = refundApplyProblemService.listValidProblems(null);
        List<KeyValuePair<Integer,String>> refundApplyProblemList = CollectionExtUtil.copyListWithCheck(refundApplyProblems, refundApplyProblem -> new KeyValuePair<>(refundApplyProblem.getId(),refundApplyProblem.getName()));
        return Result.wrapDefaultSuccessResult(refundApplyProblemList);
    }

    @PostMapping(value = "/list")
    public PageResult<RefundApplyListVO> list(@RequestBody RefundQueryParam queryParam) {

        List<RefundApplyListVO> listVOS = refundApplyService.listRefundApply(queryParam );
        if(CollectionUtils.isEmpty(listVOS)){
            return PageResult.EMPTY;
        }else {
            listVOS.forEach(refundApplyListVO -> {
                refundApplyListVO.setStatusDesc(RefundEnum.STATUS.getDescByCode(refundApplyListVO.getStatus()));
                if(StringUtils.isNotBlank(refundApplyListVO.getPics())){
                    refundApplyListVO.setPicList(JSONArray.parseArray(refundApplyListVO.getPics(),String.class));
                }
            });
        }
        int count = refundApplyService.getRefundCount(queryParam);
        return PageResult.wrapPageResult(listVOS,count,queryParam.getPageSize());
    }

    @GetMapping("/detail/{refundApplyId}")
    public Result<RefundApplyVO> detail(@PathVariable Long refundApplyId){
        return Result.wrapDefaultSuccessResult(refundQuerySupport.getRefundDetail(refundApplyId));
    }

    /**
     * 商品级订单退款申请
     * @param refundApplyParam 商品级订单
     * @return
     */
    @PostMapping("/apply")
    public Result apply(@RequestBody RefundApplyParam refundApplyParam){
        Long userId = SessionContextHolder.getSessionFuliInfo().getAdminId();
        refundSupport.apply(refundApplyParam,userId,false);
        return Result.wrapDefaultSuccessResult();
    }

    @ApiOperation(value = "同意或者拒绝退款", response = JSONObject.class)
    @PostMapping(value = "/agreeOrReject/{id}")
    public Result agreeOrReject(@PathVariable Long id, @RequestBody AgreeRefundParam param) {
        Long userId = SessionContextHolder.getSessionFuliInfo().getAdminId();
        refundSupport.refund(id,param.getStatus(),userId,true);
        return Result.wrapDefaultSuccessResult();
    }



}