package com.lx.benefits.support.pay;


import com.alibaba.fastjson.JSON;
import com.apollo.common.annotation.RedisLock;
import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorage;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.order.OrderPay;
import com.lx.benefits.bean.entity.order.OrderPayRecord;
import com.lx.benefits.bean.entity.order.ProductOrderEx;
import com.lx.benefits.bean.entity.pay.PayAccount;
import com.lx.benefits.bean.entity.seckill.Seckill;
import com.lx.benefits.bean.enums.CardKeyStatusEnum;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.config.properties.ThirdPlaformSeller;
import com.lx.benefits.mapper.cardkey.CardKeyStorageMapper;
import com.lx.benefits.mapper.order.ProductOrderExMapper;
import com.lx.benefits.service.agent.AgentCashService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.order.OrderPayRecordService;
import com.lx.benefits.service.order.OrderPayService;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.pay.PayAccountService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.service.seckill.SeckillService;
import com.lx.benefits.service.voucher.VoucherService;
import com.lx.benefits.support.order.OrderSupport;
import com.lx.benefits.utils.DateTimeUtils;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Component
public class PaySupport {

    @Resource
    private OrderPayService orderPayService;
    @Resource
    private OrderPayRecordService orderPayRecordService;
    @Resource
    private PayAccountService payAccountService;
    @Resource
    private OrderService orderService;
    @Resource
    private SkuService skuService;
    @Resource
    private OrderSupport orderSupport;
    @Resource
    private ThirdPlaformSeller thirdPlaformSeller;
    @Autowired
    private AgentCashService agentCashService;
    @Autowired
    private ProductOrderExMapper productOrderExMapper;
    @Autowired
	private CardKeyStorageMapper cardKeyStorageMapper;
    @Autowired
    private SeckillService seckillService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private EmployeeCreditInfoService employeeCreditInfoService;


    @RedisLock(name = "pay",keys = {"#payMark"})
    @Transactional(rollbackFor = Throwable.class)
    public Boolean payCallback(String payMark, String payTid, int payChannel) {

        try {
            OrderPayRecord orderPayRecord = orderPayRecordService.getByPayMark(payMark,payChannel);
            if (Objects.isNull(orderPayRecord)) {
                throw new BusinessException("支付记录不存在");
            }
            if (orderPayRecord.getIsPay() == 1) {
                return true;
            }
            //更新订单支付状态
            if(orderPayRecordService.modifyPayStatusByPayMark(payMark,payTid,payChannel,1)>0){

                OrderPay existOrderPay = orderPayService.getByPayOrderNumber(orderPayRecord.getPayOrderNumber());
                if (Objects.nonNull(existOrderPay)) {
                    //TODO 重复支付 记录重复支付记录，自动返还逻辑


                    return true;
                }
                PayAccount payAccount = payAccountService.getById(orderPayRecord.getSellerPayId());
                OrderPay orderPay = new OrderPay();
                orderPay.setBuyerId(payAccount.getPayAccount());
                orderPay.setPayMark(payMark);
                orderPay.setPayChannel(payChannel);
                orderPay.setPayTid(payTid);
                orderPay.setPayOrderNumber(orderPayRecord.getPayOrderNumber());
                orderPayService.doAddRecord(orderPay);

                List<Order> sellerOrders =  orderService.listByParentNumberAndLevel(orderPayRecord.getPayOrderNumber(),2);
                if(CollectionUtils.isEmpty(sellerOrders)) {//未找到商家级订单
                	return true;
                }
                List<Long> sellerOrderNumbers = Lists.newArrayList();
                List<Order> thirdOrderList = Lists.newArrayList();
                sellerOrders.forEach(sellerOrder->{
                    sellerOrderNumbers.add(sellerOrder.getNumber());

                    if(sellerOrder.getSellerId().equals(thirdPlaformSeller.getJd().getId())
                            || sellerOrder.getSellerId().equals(thirdPlaformSeller.getYx().getId())){
                        thirdOrderList.add(sellerOrder);
                    }
                    //第三方支付成功后，优惠卷已使用数量更新
                    if(sellerOrder.getVoucherId() != null){
                        voucherService.updateVoucherUsedByNoPay(sellerOrder.getVoucherId());
                    }
                    //秒杀商品的已购买数量叠加
                    if(sellerOrder.getSeckillId() != null && sellerOrder.getNum() != null){
                        Seckill temp = seckillService.getSeckill(sellerOrder.getSeckillId());
                        Seckill seckill = new Seckill();
                        seckill.setSeckillId(sellerOrder.getSeckillId());
                        seckill.setAlreadyBuy(sellerOrder.getNum()+temp.getAlreadyBuy());
                        seckillService.updateSeckill(seckill);
                    }

                });

                List<Order> itemOrders = orderService.listByParentNumbersAndLevel(sellerOrderNumbers,3);
                //更新下单时未支付占用库存
				itemOrders.forEach(order -> {
					Long sellerId = order.getSellerId();
					if (!thirdPlaformSeller.getJd().getId().equals(sellerId) && !thirdPlaformSeller.getYx().getId().equals(sellerId)) {
						skuService.reducePayStock(order.getSkuId(), order.getQuantity());
					}
				});
                List<Long> itemOrderNumbers = CollectionExtUtil.getPropertyList(itemOrders,Order::getNumber);

                //处理代理商收益
                List<ProductOrderEx> productOrderExs = productOrderExMapper.selectItemOrderList(itemOrderNumbers);
                agentCashService.addOrderIncomeRecorder(productOrderExs, itemOrders, orderPayRecord.getPayOrderNumber());
            	
                List<Long> orderNumbers = sellerOrderNumbers;
                orderNumbers.addAll(itemOrderNumbers);
                orderNumbers.add(orderPayRecord.getPayOrderNumber());
                int res = orderService.modifyBatchOrderPayStatus(orderNumbers,orderPay.getId());
                if(res!=orderNumbers.size()){
                    throw new BusinessException(String.format("订单支付状态更新失败,订单编号:%s", JSON.toJSONString(orderNumbers)));
                }
                //虚拟商品更改订单状态为已发货
                List<Order> orders = orderService.listVirtualOrders(itemOrderNumbers);
                if(CollectionUtils.isNotEmpty(orders)) {
                	List<Long> itemOrderNumberLists = CollectionExtUtil.getPropertyList(orders,Order::getNumber);
                	List<Long> itemParentOrderNumbers = CollectionExtUtil.getPropertyList(orders,Order::getParentNumber);
                	itemOrderNumberLists.addAll(itemParentOrderNumbers);
                	int row = orderService.modifyBatchOrderShipStatus(itemOrderNumberLists,orderPay.getId());
    				CardKeyStorage record = new CardKeyStorage();
    				record.setStatus(CardKeyStatusEnum.DELIVERED.status);
    				record.setDeadTime(DateTimeUtils.getDate());
    	        	cardKeyStorageMapper.updateStatusBySkuAndNum(record,orders);
                	if(row!=itemOrderNumberLists.size()){
                		throw new BusinessException(String.format("订单支付状态更新失败,订单编号:%s", JSON.toJSONString(itemOrderNumberLists)));
                	}
                }
                //支付成功，第三方订单处理
                if(CollectionUtils.isNotEmpty(thirdOrderList)){
                    orderSupport.thirdOrderHandle(thirdOrderList);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
