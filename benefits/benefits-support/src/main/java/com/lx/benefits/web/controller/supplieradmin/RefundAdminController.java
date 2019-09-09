package com.lx.benefits.web.controller.supplieradmin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apollo.starter.web.utils.PageResult;
import com.apollo.starter.web.utils.Result;
import com.lx.benefits.bean.enums.RefundEnum;
import com.lx.benefits.bean.param.order.RefundQueryParam;
import com.lx.benefits.bean.param.refund.AgreeRefundParam;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionSupplierInfo;
import com.lx.benefits.bean.vo.order.RefundApplyListVO;
import com.lx.benefits.bean.vo.order.RefundApplyVO;
import com.lx.benefits.service.order.RefundApplyService;
import com.lx.benefits.support.refund.RefundQuerySupport;
import com.lx.benefits.support.refund.RefundSupport;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController("supplieradminRefundAdminController")
@RequestMapping("/supplieradmin/refund")
public class RefundAdminController extends BaseSupplierAdminController {

    @Resource
    private RefundApplyService refundApplyService;
    @Resource
    private RefundSupport refundSupport;
    @Resource
    private RefundQuerySupport refundQuerySupport;

    @PostMapping(value = "/list")
    public PageResult<RefundApplyListVO> list(@RequestBody RefundQueryParam queryParam) {
        SessionSupplierInfo sessionSupplierInfo = SessionContextHolder.getSessionSupplierInfo();
        Long supplierId = sessionSupplierInfo.getSupplierId();
        queryParam.setSellerId(supplierId);

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

    @ApiOperation(value = "同意或者拒绝退款", response = JSONObject.class)
    @PostMapping(value = "/agreeOrReject/{id}")
    public Result agreeOrReject(@PathVariable Long id, @RequestBody AgreeRefundParam param) {
        SessionSupplierInfo sessionSupplierInfo = SessionContextHolder.getSessionSupplierInfo();
        Long supplierId = sessionSupplierInfo.getSupplierId();
        refundSupport.refund(id,param.getStatus(),supplierId,false);
        return Result.wrapDefaultSuccessResult();
    }


}