package com.lx.benefits.support.refund;

import com.alibaba.fastjson.JSONArray;
import com.apollo.common.utils.bean.BeanUtil;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.apollo.common.utils.money.MoneyUtil;
import com.apollo.starter.web.utils.PageResult;
import com.google.common.collect.Lists;
import com.lx.benefits.bean.entity.order.ProductOrderEx;
import com.lx.benefits.bean.entity.order.RefundApply;
import com.lx.benefits.bean.entity.order.RefundApplyProblem;
import com.lx.benefits.bean.entity.order.RefundPackage;
import com.lx.benefits.bean.enums.RefundEnum;
import com.lx.benefits.bean.vo.order.RefundApplyVO;
import com.lx.benefits.service.order.ProductOrderExService;
import com.lx.benefits.service.order.RefundApplyProblemService;
import com.lx.benefits.service.order.RefundApplyService;
import com.lx.benefits.service.order.RefundPackageService;
import com.lx.benefits.bean.param.order.RefundQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class RefundQuerySupport {

    @Resource
    private ProductOrderExService productOrderExService;
    @Resource
    private RefundApplyService refundApplyService;
    @Resource
    private RefundApplyProblemService refundApplyProblemService;
    @Resource
    private RefundPackageService refundPackageService;

    public PageResult<RefundApplyVO> getUserRefundPage(RefundQueryParam queryParam,Long accountId) {

        List<RefundApply> refundApplyList = refundApplyService.listUserRefund(accountId,null,queryParam.getStartRecord(),queryParam.getPageSize());
        if (CollectionUtils.isEmpty(refundApplyList)) {
            return PageResult.EMPTY;
        }
        List<Long> itemOrderNumbers = Lists.newArrayList();
        List<Long> refundApplyNumbers = Lists.newArrayList();
        refundApplyList.forEach(refundApply -> {
            itemOrderNumbers.add(refundApply.getProductOrderNumber());
            refundApplyNumbers.add(refundApply.getNumber());
        });
        List<RefundPackage> refundPackageList = refundPackageService.listLogisticsByRefundNumbers(refundApplyNumbers);
        Map<Long,RefundPackage> refundPackageMap = CollectionExtUtil.toMap(refundPackageList,RefundPackage::getRefundApplyNumber,r->r);
        List<ProductOrderEx> productOrderExList = productOrderExService.listItemOrder(itemOrderNumbers);
        Map<Long,ProductOrderEx> productOrderExMap = CollectionExtUtil.toMap(productOrderExList,ProductOrderEx::getOrderNumber);

        List<RefundApplyProblem> problemList = refundApplyProblemService.listValidProblems(null);
        Map<Integer,RefundApplyProblem> problemMap = CollectionExtUtil.toMap(problemList,RefundApplyProblem::getId);
        List<RefundApplyVO> refundApplyVOS = CollectionExtUtil.copyListWithCheck(refundApplyList,refundApply -> {
            ProductOrderEx productOrderEx = productOrderExMap.get(refundApply.getProductOrderNumber());
            RefundApplyProblem problem = problemMap.get(refundApply.getRefundApplyProblemId());
            RefundPackage refundPackage = refundPackageMap.get(refundApply.getNumber());

            RefundApplyVO refundApplyVO = BeanUtil.copySpring(refundApply,RefundApplyVO.class);
            refundApplyVO.setRealMoney(MoneyUtil.changeF2Y(refundApply.getRealMoney().intValue()));
            refundApplyVO.setRealPostageMoney(MoneyUtil.changeF2Y(refundApply.getRealPostageMoney().intValue()));
            refundApplyVO.setReturnAccountPoint(MoneyUtil.changeF2Y(refundApply.getReturnAccountPoint()));
            refundApplyVO.setTotalPrice(refundApplyVO.getRealMoney().add(refundApplyVO.getRealPostageMoney()).add(refundApplyVO.getReturnAccountPoint()));
            refundApplyVO.setRefundApplyProblem(problem.getName());
            refundApplyVO.setStatusDesc(RefundEnum.STATUS.getFrontDescByCode(refundApply.getStatus()));

            refundApplyVO.setSkuId(productOrderEx.getSkuId());
            refundApplyVO.setGoodsImg(productOrderEx.getImage());
            refundApplyVO.setGoodsSpec(productOrderEx.getSpec());
            refundApplyVO.setTitle(productOrderEx.getTitle());
            refundApplyVO.setTitleEn(productOrderEx.getTitleEn());

            if(StringUtils.isNotBlank(refundApply.getPics())){
                refundApplyVO.setPicList(JSONArray.parseArray(refundApply.getPics(),String.class));
            }
            if (Objects.nonNull(refundPackage)) {
                refundApplyVO.setLogisticsChannel(refundPackage.getLogisticsChannel());
                refundApplyVO.setLogisticsNumber(refundPackage.getLogisticsNumber());
            }
            return refundApplyVO;
        });

        Integer total = refundApplyService.getUserRefundCount(accountId,null);
        return PageResult.wrapPageResult(refundApplyVOS,total,queryParam.getPageSize());
    }

    /**
     * 退款详情
     * @param refundApplyId
     * @return
     */
    public RefundApplyVO getRefundDetail(Long refundApplyId) {

        RefundApply refundApply = refundApplyService.getById(refundApplyId);
        if (Objects.isNull(refundApply)) {
            return null;
        }
        List<Long> itemOrderNumbers = Collections.singletonList(refundApply.getProductOrderNumber());
        List<Long> refundApplyNumbers = Collections.singletonList(refundApply.getNumber());

        List<RefundPackage> refundPackageList = refundPackageService.listLogisticsByRefundNumbers(refundApplyNumbers);
        Map<Long,RefundPackage> refundPackageMap = CollectionExtUtil.toMap(refundPackageList,RefundPackage::getRefundApplyNumber,r->r);
        List<ProductOrderEx> productOrderExList = productOrderExService.listItemOrder(itemOrderNumbers);
        Map<Long,ProductOrderEx> productOrderExMap = CollectionExtUtil.toMap(productOrderExList,ProductOrderEx::getOrderNumber);

        List<RefundApplyProblem> problemList = refundApplyProblemService.listValidProblems(null);
        Map<Integer,RefundApplyProblem> problemMap = CollectionExtUtil.toMap(problemList,RefundApplyProblem::getId);

        ProductOrderEx productOrderEx = productOrderExMap.get(refundApply.getProductOrderNumber());
        RefundApplyProblem problem = problemMap.get(refundApply.getRefundApplyProblemId());
        RefundPackage refundPackage = refundPackageMap.get(refundApply.getNumber());

        RefundApplyVO refundApplyVO = BeanUtil.copySpring(refundApply,RefundApplyVO.class);
        refundApplyVO.setRealMoney(MoneyUtil.changeF2Y(refundApply.getRealMoney().intValue()));
        refundApplyVO.setRealPostageMoney(MoneyUtil.changeF2Y(refundApply.getRealPostageMoney().intValue()));
        refundApplyVO.setReturnAccountPoint(MoneyUtil.changeF2Y(refundApply.getReturnAccountPoint()));
        refundApplyVO.setTotalPrice(refundApplyVO.getRealMoney().add(refundApplyVO.getRealPostageMoney()).add(refundApplyVO.getReturnAccountPoint()));
        refundApplyVO.setRefundApplyProblem(problem.getName());
        refundApplyVO.setStatusDesc(RefundEnum.STATUS.getFrontDescByCode(refundApply.getStatus()));

        refundApplyVO.setSkuId(productOrderEx.getSkuId());
        refundApplyVO.setGoodsImg(productOrderEx.getImage());
        refundApplyVO.setGoodsSpec(productOrderEx.getSpec());
        refundApplyVO.setTitle(productOrderEx.getTitle());
        refundApplyVO.setTitleEn(productOrderEx.getTitleEn());

        if(StringUtils.isNotBlank(refundApply.getPics())){
            refundApplyVO.setPicList(JSONArray.parseArray(refundApply.getPics(),String.class));
        }
        if (Objects.nonNull(refundPackage)) {
            refundApplyVO.setLogisticsChannel(refundPackage.getLogisticsChannel());
            refundApplyVO.setLogisticsNumber(refundPackage.getLogisticsNumber());
        }
        return refundApplyVO;


    }



}






