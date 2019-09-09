package com.lx.benefits.support.order;

import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.bean.BeanUtil;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.apollo.common.utils.money.MoneyUtil;
import com.apollo.starter.web.utils.PageResult;
import com.google.common.collect.Lists;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.order.OrderShip;
import com.lx.benefits.bean.entity.order.OrderShipLogistics;
import com.lx.benefits.bean.entity.supplierInfo.SupplierInfo;
import com.lx.benefits.bean.enums.OrderEnum;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.order.*;
import com.lx.benefits.service.cardkey.CardKeyService;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.order.OrderShipLogisticsService;
import com.lx.benefits.service.order.OrderShipService;
import com.lx.benefits.service.order.ProductOrderExService;
import com.lx.benefits.service.supplierInfo.SupplierInfoService;
import com.lx.benefits.bean.param.order.OrderQueryParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class OrderQuerySupport {

    @Resource
    private SupplierInfoService supplierInfoService;
    @Resource
    private OrderService orderService;
    @Resource
    private ProductOrderExService productOrderExService;
    @Resource
    private OrderShipService orderShipService;
    @Resource
    private OrderShipLogisticsService orderShipLogisticsService;
    @Autowired
	private CardKeyService cardKeyService;

    public PageResult<OrderListVO> listUserOrders(OrderQueryParam queryParam) {
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        List<Order> sellerOrderList = null;
        Integer total = null;
        List<Long> listItemOrderNumbers = null;
        if(Integer.valueOf(1).equals(queryParam.getGoodsType())) {
        	List<Long> sellerOrders = orderService.listByNumbersAndLevel( accountId, 3,queryParam.getStartRecord(), queryParam.getPageSize());
        	listItemOrderNumbers = cardKeyService.getItemOrderNumbers(accountId, sellerOrders);
        	sellerOrderList = orderService.listVirtualGoodsOrder(sellerOrders); 
        }else {
        	sellerOrderList = orderService.listUserSellerOrderList(accountId,queryParam.getStatus(),queryParam.getReverseStatus(),queryParam.getStartRecord(),queryParam.getPageSize());
        }
        if(CollectionUtils.isEmpty(sellerOrderList)){
            return PageResult.EMPTY;
        }
        List<Long> sellerIdList = Lists.newArrayList();
        List<Long> sellerOrderNumberList =Lists.newArrayList();
        sellerOrderList.forEach(sellerOrder -> {
            sellerIdList.add(sellerOrder.getSellerId());
            sellerOrderNumberList.add(sellerOrder.getNumber());
        });

        List<SupplierInfo> supplierInfoList = supplierInfoService.listByIds(sellerIdList);
        Map<Long,SupplierInfo> supplierInfoMap = CollectionExtUtil.toMap(supplierInfoList,SupplierInfo::getId);

        List<ItemOrderVO> itemOrderVOList = productOrderExService.selectUserItemOrderList(accountId,sellerOrderNumberList,listItemOrderNumbers);
        Map<Long,List<ItemOrderVO>> itemMap = CollectionExtUtil.groupAndMapping(itemOrderVOList,ItemOrderVO::getSellerOrderNumber, item->{
            int totalPrice = item.getTotalGoodsPrice().add(item.getPointAmount()).intValue();
            int totalFreight = item.getTotalGoodsFreight().add(item.getShipExpensePointAmount()).intValue();
            item.setReverseStatusDesc(OrderEnum.REVERSE_STATUS.getFrontDescByCode(item.getReverseStatus()));
            item.setTotalGoodsPrice(MoneyUtil.changeF2Y(totalPrice));
            item.setTotalGoodsFreight(MoneyUtil.changeF2Y(totalFreight));
            item.setGoodsPrice(MoneyUtil.changeF2Y(item.getGoodsPrice().intValue()));
            return item;
        });

        List<OrderListVO> listVOList = CollectionExtUtil.copyListWithCheck(sellerOrderList,order -> {
            Long totalPrice = order.getPrice()+order.getPointAmount();
            int totalFreight = order.getShipExpense() + order.getShipExpensePointAmount();
            Long totalRealPrice = order.getPrice()+order.getShipExpense();
            SupplierInfo supSupplierInfo = supplierInfoMap.get(order.getSellerId());
            OrderListVO orderListVO = new OrderListVO();
            orderListVO.setSellerOrderNumber(order.getNumber());
            orderListVO.setParentOrderNumber(order.getParentNumber());
            orderListVO.setSellerId(supSupplierInfo.getId());
            orderListVO.setSellerName(supSupplierInfo.getName());
            orderListVO.setTotalPrice(MoneyUtil.changeF2Y(totalPrice.intValue()));
            orderListVO.setTotalFreight(MoneyUtil.changeF2Y(totalFreight));
            orderListVO.setTotalRealPrice(MoneyUtil.changeF2Y(totalRealPrice.intValue()));
            orderListVO.setCount(order.getQuantity());
            orderListVO.setStatus(order.getStatus());
            orderListVO.setStatusDesc(OrderEnum.STATUS.getDescByCode(order.getStatus()));
            orderListVO.setCampaignId(order.getCampaignId());
            orderListVO.setItemOrderVOList(itemMap.get(order.getNumber()));
            orderListVO.setCancelTime(order.getCancelTime());
            orderListVO.setCreateTime(order.getCreateTime());
           return orderListVO;
        });
        if(Integer.valueOf(1).equals(queryParam.getGoodsType())) {
        	total = cardKeyService.countOrderNumber(accountId);
        }else {
        	total = orderService.getUserSellerOrderCount(accountId,queryParam.getStatus(),queryParam.getReverseStatus());
        }
        return PageResult.wrapPageResult(listVOList,total,queryParam.getPageSize());
    }


    /**
     * 订单详情
     * @param sellerOrderNumber
     * @return
     */
    public OrderDetailVO getOrderDetailVO(Long sellerOrderNumber){
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();

        List<Order> sellerOrderList = orderService.listByNumberAndLevel(sellerOrderNumber,2);
        if(CollectionUtils.isEmpty(sellerOrderList)){
            throw new BusinessException("订单不存在");
        }
        Order sellerOrder = sellerOrderList.get(0);
        if (!sellerOrder.getBuyerUserId().equals(accountId)) {
            throw new BusinessException("非法请求");
        }
        SupplierInfo supplierInfo =supplierInfoService.getSupplierById(sellerOrder.getSellerId());
        Long totalPrice = sellerOrder.getPrice() + sellerOrder.getPointAmount();
        int totalFreightPrice = sellerOrder.getShipExpense() + sellerOrder.getShipExpensePointAmount();
        Long totalRealPrice = sellerOrder.getPrice()+sellerOrder.getShipExpense();
        Long deductionPointAmount = sellerOrder.getPointAmount()+sellerOrder.getShipExpensePointAmount();
        OrderDetailVO orderDetailVO =new OrderDetailVO();
        orderDetailVO.setSellerOrderNumber(sellerOrder.getNumber());
        orderDetailVO.setParentOrderNumber(sellerOrder.getParentNumber());
        orderDetailVO.setSellerId(sellerOrder.getSellerId());
        orderDetailVO.setSellerName(supplierInfo.getName());
        orderDetailVO.setTotalPrice(MoneyUtil.changeF2Y(totalPrice.intValue()));
        orderDetailVO.setTotalFreight(MoneyUtil.changeF2Y(totalFreightPrice));
        orderDetailVO.setTotalRealPrice(MoneyUtil.changeF2Y(totalRealPrice.intValue()));
        orderDetailVO.setDeductionPointAmount(MoneyUtil.changeF2Y(deductionPointAmount.intValue()));
        orderDetailVO.setStatus(sellerOrder.getStatus());
        orderDetailVO.setStatusDesc(OrderEnum.STATUS.getDescByCode(sellerOrder.getStatus()));
        orderDetailVO.setCount(sellerOrder.getQuantity());
        orderDetailVO.setCampaignId(sellerOrder.getCampaignId());
        orderDetailVO.setCreateTime(sellerOrder.getCreateTime());
        orderDetailVO.setCancelTime(sellerOrder.getCancelTime());
        orderDetailVO.setPayTime(sellerOrder.getPayTime());
        orderDetailVO.setBuyerComment(sellerOrder.getBuyerComment());
        List<ItemOrderVO> itemOrderVOList = productOrderExService.selectUserItemOrderList(accountId, Collections.singletonList(sellerOrderNumber),null);
        itemOrderVOList.forEach(item->{
            int totalGoodsPrice = item.getTotalGoodsPrice().add(item.getPointAmount()).intValue();
            int totalFreight = item.getTotalGoodsFreight().add(item.getShipExpensePointAmount()).intValue();
            item.setReverseStatusDesc(OrderEnum.REVERSE_STATUS.getFrontDescByCode(item.getReverseStatus()));
            item.setTotalGoodsPrice(MoneyUtil.changeF2Y(totalGoodsPrice));
            item.setTotalGoodsFreight(MoneyUtil.changeF2Y(totalFreight));
            item.setGoodsPrice(MoneyUtil.changeF2Y(item.getGoodsPrice().intValue()));
            List<OrderShipLogistics> itemOrderLogistics = orderShipLogisticsService.listItemOrderLogistics(item.getItemOrderNumber());
            if(!CollectionUtils.isEmpty(itemOrderLogistics)) {
            	OrderShipLogistics orderShipLogistics = itemOrderLogistics.get(0);
            	LogisticsVO logisticsVO= new LogisticsVO();
            	logisticsVO.setShipExpress(orderShipLogistics.getShipExpress());
            	logisticsVO.setShipExpressNumber(orderShipLogistics.getShipExpressNumber());
            	logisticsVO.setRemark(orderShipLogistics.getRemark());
            	logisticsVO.setShipTime(orderShipLogistics.getCreateTime());
            	item.setLogistics(logisticsVO);
            }
        });
        orderDetailVO.setItemOrderVOList(itemOrderVOList);
        OrderShip orderShip = orderShipService.getByOrderNumber(sellerOrder.getNumber());
        if (Objects.nonNull(orderShip)) {
            orderDetailVO.setOrderShip(BeanUtil.copySpring(orderShip, OrderShipVO.class));
        }
        if(sellerOrder.getVoucherBenefit() != null){
            orderDetailVO.setVoucherBenefit(MoneyUtil.changeF2Y(sellerOrder.getVoucherBenefit().intValue()));
        }
        return orderDetailVO;
    }


}






