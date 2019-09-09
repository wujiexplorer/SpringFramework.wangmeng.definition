package com.lx.benefits.support.refund;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alipay.api.AlipayApiException;
import com.apollo.common.annotation.RedisLock;
import com.apollo.common.exception.ArgumentException;
import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.money.MoneyUtil;
import com.google.common.collect.ImmutableSet;
import com.lx.benefits.bean.bo.order.OrderRefundBO;
import com.lx.benefits.bean.bo.order.SellerOrderReverseOverviewBO;
import com.lx.benefits.bean.bo.pay.OrderCreditBO;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorage;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorageExample;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.order.OrderPay;
import com.lx.benefits.bean.entity.order.RefundApply;
import com.lx.benefits.bean.entity.order.RefundApplyProblem;
import com.lx.benefits.bean.entity.order.RefundPackage;
import com.lx.benefits.bean.entity.supplierInfo.SupplierInfo;
import com.lx.benefits.bean.enums.OptTypeEnum;
import com.lx.benefits.bean.enums.OrderEnum;
import com.lx.benefits.bean.enums.PayChannelEnum;
import com.lx.benefits.bean.enums.RefundEnum;
import com.lx.benefits.bean.param.refund.RefundApplyParam;
import com.lx.benefits.config.properties.ThirdPlaformSeller;
import com.lx.benefits.mapper.cardkey.CardKeyStorageMapper;
import com.lx.benefits.service.agent.AgentCashService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.enterprfestival.EnterprFestivalService;
import com.lx.benefits.service.order.OrderPayService;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.order.RefundApplyProblemService;
import com.lx.benefits.service.order.RefundApplyService;
import com.lx.benefits.service.order.RefundPackageService;
import com.lx.benefits.service.supplierInfo.SupplierInfoService;
import com.lx.benefits.support.common.IdGenerator;
import com.lx.benefits.support.order.OrderAliPaySupport;
import com.lx.benefits.support.order.OrderWxPaySupport;

@Component
public class RefundSupport {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderPayService orderPayService;
    @Resource
    private RefundApplyService refundApplyService;
    @Resource
    private SupplierInfoService supplierInfoService;
    @Resource
    private RefundPackageService refundPackageService;
    @Resource
    private RefundApplyProblemService refundApplyProblemService;
    @Resource
    private OrderAliPaySupport orderAliPaySupport;
    @Resource
    private EmployeeCreditInfoService employeeCreditInfoService;
    @Resource
    private EnterprFestivalService enterprFestivalService;
    @Resource
    private ThirdPlaformSeller thirdPlaformSeller;
    @Resource
    private OrderWxPaySupport orderWxPaySupport;
    @Resource
    private AgentCashService agentCashService;
    @Resource
    private IdGenerator idGenerator;
    @Autowired
	private CardKeyStorageMapper cardKeyStorageMapper;

    private Set<Integer> needRefundMoneyOrderStatusSet  = ImmutableSet.of(OrderEnum.STATUS.PAID.getCode(),OrderEnum.STATUS.SHIPPED.getCode(),OrderEnum.STATUS.RECEIVED.getCode(),OrderEnum.STATUS.COMPLETED.getCode());

    
    @RedisLock(name = "refundApply",keys = {"#accountId"})
    @Transactional(rollbackFor = Exception.class)
    public void apply(RefundApplyParam refundApplyParam,Long accountId,boolean isUserCancel) {
    	CardKeyStorageExample example = new CardKeyStorageExample();
    	example.createCriteria().andOrderNumberEqualTo(refundApplyParam.getItemOrderNumber());
    	List<CardKeyStorage> cardKeyStorageLists = cardKeyStorageMapper.selectByExample(example);
    	if(CollectionUtils.isNotEmpty(cardKeyStorageLists)) {
    		throw new BusinessException("虚拟商品不能退货！");
    	}
    	
        RefundApplyProblem refundApplyProblem = refundApplyProblemService.getById(refundApplyParam.getRefundApplyProblemId());
        if(Objects.isNull(refundApplyProblem)){
            throw new BusinessException("请选择退款原因");
        }
        List<Order> orders = orderService.listByNumberAndLevel(refundApplyParam.getItemOrderNumber(),3);
        if(CollectionUtils.isEmpty(orders)){
            throw new BusinessException("订单不存在");
        }
        Order order = orders.get(0);
        if(isUserCancel){
            if(order.getSellerId().equals(thirdPlaformSeller.getJd().getId())
                    || order.getSellerId().equals(thirdPlaformSeller.getYx().getId())){
                throw new BusinessException("该订单需要联系客服申请售后");
            }
        }

        if(isUserCancel && !order.getBuyerUserId().equals(accountId)) {
            throw new BusinessException("非法操作");
        }
        List<RefundApply> list = refundApplyService.listValidByProductOrderNumber(refundApplyParam.getItemOrderNumber());
        if(CollectionUtils.isNotEmpty(list)){
            throw new BusinessException("该商品已申请过退款");
        }
        if (orderService.modItemOrderReverseStatusFirstStep(refundApplyParam.getItemOrderNumber())>0) {

            OrderPay orderPay = orderPayService.getById(order.getPayId());
            SupplierInfo supplierInfo = supplierInfoService.getSupplierById(order.getSellerId());
            String pics = CollectionUtils.isEmpty(refundApplyParam.getPicList())?"": JSONArray.toJSONString(refundApplyParam.getPicList());

            RefundApply refundApply = new RefundApply();
            refundApply.setNumber(idGenerator.generateRefundNumber());
            refundApply.setBatchNumber(idGenerator.generateRefundNumber());
            refundApply.setRefundApplyProblemId(refundApplyParam.getRefundApplyProblemId());
            refundApply.setAccountId(order.getBuyerUserId());
            refundApply.setSellerOrderNumber(order.getParentNumber());
            refundApply.setProductOrderNumber(order.getNumber());
            refundApply.setOrderType(order.getType());
            refundApply.setSellerId(order.getSellerId().intValue());
            refundApply.setSellerName(supplierInfo.getName());
            refundApply.setApplyMoney(order.getPrice());
            refundApply.setApplyPostageMoney(order.getShipExpense().longValue());
            refundApply.setRealMoney(order.getPrice());
            refundApply.setRealPostageMoney(order.getShipExpense().longValue());
            refundApply.setReturnAccountPointApply(order.getPointAmount().intValue()+order.getShipExpensePointAmount());
            refundApply.setReturnAccountPoint(order.getPointAmount().intValue()+order.getShipExpensePointAmount());
            refundApply.setCount(order.getQuantity());
            refundApply.setAccountRemark(refundApplyParam.getReason());
            refundApply.setIsUserCancel(isUserCancel?1:0);
            refundApply.setPics(pics);
            if (Objects.nonNull(orderPay)) {
                refundApply.setPayAccoutId(orderPay.getBuyerId());
                refundApply.setOrderPayMark(orderPay.getPayMark());
                refundApply.setOrderPayChannel(orderPay.getPayChannel());
            }
            refundApplyService.doAddRecord(refundApply);
            orderService.modItemOrderReverseStatusFirstStep(order.getNumber());

        }else {
            throw new BusinessException("退款申请失败");
        }
    }

    @RedisLock(name = "refundApply",keys = {"#accountId"})
    @Transactional(rollbackFor = Exception.class)
    public void cancel(Long id,Long accountId){
        RefundApply refundApply = refundApplyService.getById(id);
        if(Objects.isNull(refundApply)){
            throw new BusinessException("退款记录不存在");
        }
        if(!refundApply.getAccountId().equals(accountId)) {
            throw new BusinessException("非法操作");
        }
        if(refundApply.getSellerId().equals(thirdPlaformSeller.getJd().getId())
                || refundApply.getSellerId().equals(thirdPlaformSeller.getYx().getId())){
            throw new BusinessException("该售后需要联系客服取消");
        }
        orderService.modItemOrderReverseStatusOver(refundApply.getProductOrderNumber(),OrderEnum.REVERSE_STATUS.END_WHITOUT_SUCC);
        refundApplyService.cancelRefundApply(id);
    }

    @RedisLock(name = "refundApply",keys = {"#accountId"})
    @Transactional(rollbackFor = Exception.class)
    public void enterLogistics(Long id,String express,String expressNumber,Long accountId){
        RefundApply refundApply = refundApplyService.getById(id);
        if(Objects.isNull(refundApply)){
            throw new BusinessException("退款记录不存在");
        }
        if(!refundApply.getAccountId().equals(accountId)) {
            throw new BusinessException("非法操作");
        }
        if(!refundApply.getStatus().equals(RefundEnum.STATUS.PENDING_RETURN.getCode())) {
            throw new BusinessException("退款状态异常");
        }
        if(refundApplyService.modifyReturnStatus2Refund(id)>0){
            RefundPackage refundPackage = new RefundPackage();
            refundPackage.setRefundApplyNumber(refundApply.getNumber());
            refundPackage.setProductOrderNumber(refundApply.getProductOrderNumber());
            refundPackage.setSellerId(refundApply.getSellerId().longValue());
            refundPackage.setLogisticsChannel(express);
            refundPackage.setLogisticsNumber(expressNumber);
            refundPackageService.doAddRecord(refundPackage);

            //TODO 目前没有财务审核 ，填写物流之后直接退款 ；正常场景退款是需要财务审核之后再打款的，目前缺少财务审核步骤
            this.refundPay(id);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @RedisLock(name = "refund",keys = {"#refundApplyId"})
    public void refund(Long refundApplyId,Integer status,Long userId,boolean isAdmin) {

        RefundApply refundApply = refundApplyService.getById(refundApplyId);
        if(Objects.isNull(refundApply)){
            throw new BusinessException("退款记录不存在");
        }
        if( !isAdmin && !Objects.equals(refundApply.getSellerId().longValue(), userId)){
            throw new BusinessException("非法操作");
        }
        if(refundApply.getStatus()!= RefundEnum.STATUS.APPLY.getCode()){
            throw new BusinessException("当前退款状态不允许该操作");
        }

        RefundApplyProblem refundApplyProblem =refundApplyProblemService.getById(refundApply.getRefundApplyProblemId());
        if(refundApplyProblem.getType() ==0 ){
            throw new BusinessException("退款异常");
        }
        if(status==1){
            if(refundApplyProblem.getType()==2){
                status = RefundEnum.STATUS.PENDING_RETURN.getCode();
            }else {
                status = RefundEnum.STATUS.PENDING_REFUND.getCode();
            }
        }else if(status==0){
            status = RefundEnum.STATUS.REFUND_REJECTION.getCode();
        }else {
            throw new ArgumentException("请求非法");
        }

        if (status != RefundEnum.STATUS.PENDING_REFUND.getCode()) {

            RefundApply refundApplyUpdate = new RefundApply();
            refundApplyUpdate.setId(refundApply.getId());
            refundApplyUpdate.setStatus(status);
            refundApplyService.doModRecord(refundApplyUpdate);
            if(status==RefundEnum.STATUS.REFUND_REJECTION.getCode()){
                orderService.modItemOrderReverseStatusOver(refundApply.getProductOrderNumber(),OrderEnum.REVERSE_STATUS.END_WHITOUT_SUCC);
            }
        } else {
            //TODO 目前没有财务审核 ，仅退款订单同意之后直接退款 ；正常场景退款是需要财务审核之后再打款的，目前缺少财务审核步骤
            refundPay(refundApplyId);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void refundPay(Long refundApplyId) {

        RefundApply refundApply = refundApplyService.getById(refundApplyId);
        RefundApplyProblem refundApplyProblem = refundApplyProblemService.getById(refundApply.getRefundApplyProblemId());

        Order itemOrder = orderService.listByNumberAndLevel(refundApply.getProductOrderNumber(),3).get(0);
        Order sellerOrder = orderService.listByNumberAndLevel(refundApply.getSellerOrderNumber(),2).get(0);

        //暂时没有财务审核步骤，同意退款时直接变为退款成功状态
        refundApplyService.modifyReturnStatus2Succ(refundApplyId);

        OrderEnum.REVERSE_STATUS reverseStatusEnum = refundApplyProblem.getType()==1 ? OrderEnum.REVERSE_STATUS.END:OrderEnum.REVERSE_STATUS.END_SUCC;
        //更新商品级订单逆向状态为逆向完结状态
        orderService.modItemOrderReverseStatusOver(itemOrder.getNumber(),reverseStatusEnum);
        SellerOrderReverseOverviewBO reverseOverviewBO = orderService.getReverseOrderCount(itemOrder.getParentNumber());
        //全部退款情况关闭商家级订单
        if(reverseOverviewBO.getOverCount()==reverseOverviewBO.getAllCount()){
            orderService.modifyOrderCloseStatus(itemOrder.getParentNumber());
        }
        //代理商收益处理
        agentCashService.addAbnormalOrderIncomeRecorde(Arrays.asList(itemOrder.getNumber().toString()));
       
        //返还积分
        Integer deductionPointAmount = refundApply.getReturnAccountPoint();
        if(deductionPointAmount>0){
            EnterprFestivalPacket festivalPacket = enterprFestivalService.getById(sellerOrder.getCampaignId());
            OrderCreditBO orderCreditBO = new OrderCreditBO();
            orderCreditBO.setEmployeeId(itemOrder.getBuyerUserId());
            orderCreditBO.setPayOrderNumber(sellerOrder.getParentNumber().toString());
            orderCreditBO.setCampaignId(festivalPacket.getCampaignId());
            orderCreditBO.setCreditType(festivalPacket.getCreditType());
            orderCreditBO.setOptType(OptTypeEnum.USER_REFUND_ADD.getValue());
            orderCreditBO.setRemark(OptTypeEnum.USER_REFUND_ADD.getDesc());
            orderCreditBO.setReduceCredit(MoneyUtil.changeF2Y(deductionPointAmount));
			if (itemOrder.getCardAmount() != null) {
				orderCreditBO.setCardAmount(MoneyUtil.changeF2Y(itemOrder.getCardAmount().intValue()));
			}
            employeeCreditInfoService.modifyEmployeeCredit4Order(orderCreditBO);
        }
        if (needRefundMoneyOrderStatusSet.contains(itemOrder.getStatus())) {
            //TODO 返还钱
            OrderPay orderPay = orderPayService.getByPayOrderNumber(sellerOrder.getParentNumber());
            if(Objects.nonNull(orderPay)){

                Order payOrder = orderService.getOrderByNumber(sellerOrder.getParentNumber());
                Long totalFee = payOrder.getPrice()+payOrder.getShipExpense();
                //退款金额
                Long refundAmout = refundApply.getRealMoney()+refundApply.getRealPostageMoney();
                OrderRefundBO refundBO = new OrderRefundBO();
                refundBO.setOperatorId(refundApply.getAccountId().toString());
                refundBO.setOutRequestNo(refundApply.getNumber()+"-refund-"+ idGenerator.generateRefundNumber());
                refundBO.setPayMark(orderPay.getPayMark());
                refundBO.setRefundApplyNumber(refundApply.getNumber());
                refundBO.setRefundAmount(MoneyUtil.changeF2Y(refundAmout.intValue()));
                refundBO.setTotalFee(MoneyUtil.changeF2Y(totalFee.intValue()));
                refundBO.setPayAccount(orderPay.getBuyerId());
                /**支付宝退款*/
                if (orderPay.getPayChannel().equals(PayChannelEnum.ALIPAY.getCode())) {
                    try {
                        orderAliPaySupport.alipayRefund(refundBO);
                    } catch (AlipayApiException e) {
                        throw new BusinessException(e.getErrCode(),e.getErrMsg());
                    }
                }else if(orderPay.getPayChannel().equals(PayChannelEnum.WEIXINPAY.getCode())){
                    orderWxPaySupport.wxRefund(refundBO);
                }else {
                    throw new BusinessException("支付渠道异常");
                }

                //TODO 微信支付待做
            }
        }
    }


}






