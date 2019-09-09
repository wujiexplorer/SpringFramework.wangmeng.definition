package com.lx.benefits.service.order.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.bo.order.OrderOverview;
import com.lx.benefits.bean.bo.order.PaidOrderInfo;
import com.lx.benefits.bean.bo.order.ProductOrderDetails;
import com.lx.benefits.bean.bo.order.ReceiveAddressInfo;
import com.lx.benefits.bean.bo.order.SellerOrderInfo;
import com.lx.benefits.bean.bo.order.SellerOrderReverseOverviewBO;
import com.lx.benefits.bean.bo.order.UserOrderOverviewBO;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.order.OrderShip;
import com.lx.benefits.bean.enums.OrderEnum;
import com.lx.benefits.bean.enums.OrderLevel;
import com.lx.benefits.bean.param.order.ItemOrderListQueryParam;
import com.lx.benefits.bean.util.SendMailUtil;
import com.lx.benefits.bean.vo.order.ItemOrderListVO;
import com.lx.benefits.bean.vo.order.OrderOverviewRes;
import com.lx.benefits.bean.vo.order.OrderOverviewVO;
import com.lx.benefits.mapper.cardkey.CardKeyStorageMapper;
import com.lx.benefits.mapper.order.OrderMapper;
import com.lx.benefits.service.order.OrderOverviewService;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.order.OrderShipService;

/**
* @ClassName: OrderServiceImpl
* @Description:
* @author wind
* @date 2019-2-12
*/
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource(name="orderMapper")
    private OrderMapper orderMapper;
	@Autowired
	private OrderShipService orderShipService;
	@Autowired
    private OrderOverviewService orderOverviewService;
	@Autowired
	private CardKeyStorageMapper cardKeyStorageMapper;

    @Override
    public Long doAddRecord(Order record) {
        return orderMapper.insertSelective(record);
    }

    @Override
    public int doAddBatchRecord(List<Order> list) throws BusinessException {
        return orderMapper.insertBatch(list);
    }

    @Override
    public int doModRecord(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int doModSellerOrderParentNumber(Long sellerOrderNumber,Long payOrderNumber){
        return orderMapper.updateSellerOrderParentNumber(sellerOrderNumber,payOrderNumber);
    }

    @Override
    public int modifyOrderThirdNumber(Long sellerOrderNumber,String thirdOrderNumber) throws BusinessException {

        orderMapper.updateSellerOrderThirdOrderNumber(sellerOrderNumber,thirdOrderNumber);
        return orderMapper.updateItemOrderThirdOrderNumber(sellerOrderNumber,thirdOrderNumber);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelBySellerOrderNumber(Long userId,Long sellerOrderNumber,Long payOrderNumber) {

        //TODO 判断是否要支付级订单取消
        if(orderMapper.updateSellerOrderStatus2Cancel(userId,sellerOrderNumber, OrderEnum.STATUS.CANCEL.getCode(),OrderEnum.STATUS.CANCEL.getCode())==0){
            throw new BusinessException("取消订单失败");
        }
        if(orderMapper.updateItemOrderStatus2Cancel(userId,sellerOrderNumber, OrderEnum.STATUS.CANCEL.getCode(),OrderEnum.STATUS.CANCEL.getCode())==0){
            throw new BusinessException("取消订单失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void confirmReceiptBySellerOrderNumber(Long userId, Long sellerOrderNumber,Long payOrderNumber) {
        //TODO 判断是否要支付级订单确认完结
        orderMapper.updateSellerOrderStatus2Over(userId,sellerOrderNumber,OrderEnum.STATUS.COMPLETED.getCode(),OrderEnum.STATUS.COMPLETED.getCode());
        orderMapper.updateItemOrderStatus2Over(userId,sellerOrderNumber,OrderEnum.STATUS.COMPLETED.getCode(),OrderEnum.STATUS.COMPLETED.getCode());
        //卡密状态改变
        cardKeyStorageMapper.updateBySellerOrderNumber(userId, sellerOrderNumber);
    }

    @Override
    public Order getById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> listByNumberAndLevel(Long number,Integer level) throws BusinessException {
        return orderMapper.selectByNumberAndLevel(number,level);
    }

    @Override
    public List<Order> listByParentNumberAndLevel(Long parentNumber, Integer level) throws BusinessException {
        return orderMapper.selectByParentNumberAndLevel(parentNumber,level);
    }

    @Override
    public List<Order> listByParentNumbersAndLevel(List<Long> parentNumberList, Integer level) throws BusinessException {
        return orderMapper.selectByParentNumbersAndLevel(parentNumberList,level);
    }

    @Override
    public List<ItemOrderListVO> listItemOrderList(ItemOrderListQueryParam itemOrderListQueryParam) {
        return orderMapper.selectItemOrderList(itemOrderListQueryParam);
    }
    
    @Override
    public List<Order> listSellerPaidOrderListByLevel(Long sellerId, Integer level) {
    	return orderMapper.selectSellerPaidOrderListByLevel(sellerId, level);
    }

    @Override
    public int getItemOrderCount(ItemOrderListQueryParam itemOrderListQueryParam) {
        return orderMapper.selectItemOrderCount(itemOrderListQueryParam);
    }

    @Override
    public List<Order> listUserSellerOrderList(Long buyerUserId, Integer status, Integer reverseStatus, Integer startRecord, Integer pageSize) {
        return orderMapper.selectUserSellerOrderList(buyerUserId,status,reverseStatus,startRecord,pageSize);
    }

    @Override
    public Integer getUserSellerOrderCount(Long buyerUserId, Integer status, Integer reverseStatus) {
        return orderMapper.selectUserSellerOrderCount(buyerUserId,status,reverseStatus);
    }

    @Override
    public SellerOrderReverseOverviewBO getReverseOrderCount(Long sellerOrderNumber) {
        return orderMapper.selectReverseOrderCount(sellerOrderNumber);
    }

    @Override
    public int modItemOrderReverseStatusFirstStep(Long itemOrderNumber) {
        return orderMapper.updateItemOrderReverseStatusFirstStep(itemOrderNumber);
    }

    @Override
    public int modItemOrderReverseStatusOver(Long itemOrderNumber,OrderEnum.REVERSE_STATUS reverseStatusEnum) {
        return orderMapper.updateItemOrderReverseStatusOver(itemOrderNumber,reverseStatusEnum.getCode());
    }

    @Override
    public UserOrderOverviewBO getUserOrderView(Long buyerUserId){
        return orderMapper.selectUserOrderOverview(buyerUserId);
    }

    @Override
    public List<Order> listOvertimePaymentOrder(Date createTime,Long minId, Integer startRecord, Integer pageSize) {
        return orderMapper.selectOvertimePaymentOrderList(createTime,minId,startRecord,pageSize);
    }

    @Override
    public List<Order> listOvertimePaymentSeckillOrder(Date createTime, Long minId, Integer startRecord, Integer pageSize) {
        return orderMapper.selectOvertimePaymentSeckillOrderList(createTime,minId,startRecord,pageSize);
    }

    @Override
    public List<Order> listNeedOverOrder(Date createTime,Long minId, Integer startRecord, Integer pageSize) {
        return orderMapper.selectNeedOverOrderList(createTime,minId,startRecord,pageSize);
    }


    @Override
    public int modifyBatchOrderPayStatus(List<Long> orderNumberList,Long orderPayId) {
    	int row = orderMapper.updateBatchOrderPayStatus(orderNumberList,orderPayId);
    	if(row>0) {
        	//邮件通知
    		SendMailUtil.singleSendMail(orderMapper.getEmailByNumber(orderNumberList.get(0)), "福粒科技", "【福粒科技】亲,您的订单已经支付成功!我们会尽快安排发出。订单、物流如有疑问,请咨询在线客服。祝您购物愉快!");
        }
        return row;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyOrderShipStatus(Long orderNumber,Long sellerOrderNumber) {
        Date shipTime = new Date();
        orderMapper.updateOrderShipStatus(sellerOrderNumber,shipTime);
        int row = orderMapper.updateOrderShipStatus(orderNumber,shipTime);
        if(row>0) {
        	//邮件通知
        	SendMailUtil.singleSendMail(orderMapper.getEmailByNumber(orderNumber), "福粒科技", "【福粒科技】亲爱的会员，您所购买的商品已发出！敬请留意签收！如有疑问，请联系在线客服或致电4008264949.祝您购物愉快！");	
        }
        return row;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
	public int modifyBatchOrderShipStatus(List<Long> orderNumberList, Long orderPayId) {
    	int row = orderMapper.updateBatchOrderShipStatus(orderNumberList, orderPayId);
    	if(row>0) {
        	//邮件通知
        	SendMailUtil.singleSendMail(orderMapper.getEmailByNumber(orderNumberList.get(0)), "福粒科技", "【福粒科技】亲爱的会员，您所购买的商品已发出！敬请留意签收！如有疑问，请联系在线客服或致电4008264949.祝您购物愉快！");	
        }
		return row;
	}
    
    @Override
    public int modifyOrderCloseStatus(Long sellerOrderNumber) {
        return orderMapper.updateOrderCloseStatus(sellerOrderNumber);
    }
    
    @Override
	public Order getOrderByNumber(Long number) {
		if (number == null) {
			return null;
		}
		return orderMapper.selectOrderByNumber(number);
	}

	@Override
	public OrderOverview getEnterpriseOrderOverview(Long enterprId) {
		return orderMapper.getEnterpriseOrderOverview(enterprId);
	}

	@Override
	public List<ItemOrderListVO> getItemOrderList(ItemOrderListQueryParam itemOrderListQueryParam) {
		 return orderMapper.queryItemOrderList(itemOrderListQueryParam);
	}

	@Override
	public int ItemOrderCount(ItemOrderListQueryParam itemOrderListQueryParam) {
		return orderMapper.queryItemOrderCount(itemOrderListQueryParam);
	}

    @Override
    public Map<String, Object> orderOverview() {
        try{
            Map<String,Object> map = new HashMap<>();
            Integer count = orderOverviewService.getOrderCountStatistic();
//            Integer refundCount = orderOverviewService.getRefundOrderCountStatistic();
//            count = count -(refundCount == null ? 0 : refundCount);
            OrderOverviewVO todayOrderOverviewVO = orderOverviewService.getTodayOrderStatistic();
            //OrderOverviewVO todayRefundOrderOverviewVO = orderOverviewService.getTodayRefundOrderStatistic();
            OrderOverviewRes todayOrderOverviewVORes = new OrderOverviewRes();
            if(null != todayOrderOverviewVO){
                todayOrderOverviewVORes.setSaleAmount((float)(todayOrderOverviewVO.getSaleAmount())/100);
                todayOrderOverviewVORes.setSalePoint((float)(todayOrderOverviewVO.getSalePoint())/100);
            }
            OrderOverviewVO yesterdayOrderOverviewVO = orderOverviewService.getYesterdayOrderStatistic();
            //OrderOverviewVO yesterdayRefundOrderOverviewVO = orderOverviewService.getYesterdayRefundOrderStatistic();
            OrderOverviewRes yesterdayOrderOverviewVORes = new OrderOverviewRes();
            if(null != yesterdayOrderOverviewVO){
                yesterdayOrderOverviewVORes.setSaleAmount((float)(yesterdayOrderOverviewVO.getSaleAmount())/100);
                yesterdayOrderOverviewVORes.setSalePoint((float)(yesterdayOrderOverviewVO.getSalePoint())/100);
            }
            OrderOverviewVO pre7DayOrderOverviewVO = orderOverviewService.getPreDay7OrderStatistic();
            //OrderOverviewVO pre7DayRefundOrderOverviewVO = orderOverviewService.getPreDay7RefundOrderStatistic();
            OrderOverviewRes pre7DayOrderOverviewVORes = new OrderOverviewRes();
            if(null != pre7DayOrderOverviewVO){
                pre7DayOrderOverviewVORes.setSaleAmount((float)(pre7DayOrderOverviewVO.getSaleAmount())/100);
                pre7DayOrderOverviewVORes.setSalePoint((float)(pre7DayOrderOverviewVO.getSalePoint())/100);
            }
            map.put("count",count);
            map.put("todayOrderOverviewVO",todayOrderOverviewVORes);
            map.put("yesterdayOrderOverviewVO",yesterdayOrderOverviewVORes);
            map.put("pre7DayOrderOverviewVO",pre7DayOrderOverviewVORes);
            return map;
        }catch (Exception e){
            throw new RuntimeException("获取订单概览出错！",e);
        }
    }
    
    @Override
	public ProductOrderDetails getProductOrderDetailsByOrderNumber(Long orderNumber) {
		Order order = this.getOrderByNumber(orderNumber);
		if (order == null) {
			return null;
		}
		Order payOrder = null;
		if (OrderLevel.PAY_LEVEL.code.equals(order.getLevel())) {// 支付级订单
			payOrder = order;
		} else if (OrderLevel.SELLER_LEVEL.code.equals(order.getLevel())) {// 商家级订单
			payOrder = this.getOrderByNumber(order.getParentNumber());
		} else if (OrderLevel.PRODUCT_LEVEL.code.equals(order.getLevel())) {// 商品级订单
			Order sellerOrder = this.getOrderByNumber(order.getParentNumber());
			payOrder = this.getOrderByNumber(sellerOrder.getParentNumber());
		}
		if (payOrder != null) {
			List<SellerOrderInfo> sellerOrderInfos = orderMapper.getProductOrderDetailsByPayNumber(payOrder.getNumber());
			if (!CollectionUtils.isEmpty(sellerOrderInfos)) {
				ProductOrderDetails productOrderDetails = new ProductOrderDetails();
				productOrderDetails.setOrderPayNumber(payOrder.getNumber().toString());
				productOrderDetails.setCreateTime(order.getCreateTime());
				BigDecimal totalPoints = new BigDecimal(payOrder.getPointAmount() + payOrder.getShipExpensePointAmount()).divide(BigDecimal.valueOf(100), 2,
						BigDecimal.ROUND_HALF_UP);
				productOrderDetails.setTotalPoints(totalPoints);
				productOrderDetails.setSellerOrders(sellerOrderInfos);
				OrderShip ordership = orderShipService
						.getByProductOrderNumber(Long.valueOf(sellerOrderInfos.get(0).getProductOrders().get(0).getOrderProductNumber()));
				if (ordership != null) {
					ReceiveAddressInfo receiveAddressInfo = new ReceiveAddressInfo();
					productOrderDetails.setReceiveAddressInfo(receiveAddressInfo);
					receiveAddressInfo.setReceiveUser(ordership.getShipToName());
					receiveAddressInfo.setReceiveMobile(ordership.getShipToMobile());
					StringBuilder stringBuilder = new StringBuilder();
					if (ordership.getShipToProvince() != null) {// 省
						stringBuilder.append(ordership.getShipToProvince());
					}
					if (ordership.getShipToCity() != null) {// 市
						stringBuilder.append(ordership.getShipToCity());
					}
					if (ordership.getShipToDistrict() != null) {// 区
						stringBuilder.append(ordership.getShipToDistrict());
					}
					if (ordership.getShipToTown() != null) {// 街道
						stringBuilder.append(ordership.getShipToTown());
					}
					if (!StringUtils.isEmpty(ordership.getShipToAddress())) {// 详细地址
						stringBuilder.append("(").append(ordership.getShipToAddress()).append(")");
					}
					receiveAddressInfo.setReceiveAddress(stringBuilder.toString());
				}
				return productOrderDetails;
			}
		}
		return null;
	}

	@Override
	public Object orderFinanceCheck(Long enterprId, PageBean pageBean, Date startTime, Date endTime) {
		if (startTime == null && endTime == null) {
			Calendar now = Calendar.getInstance();
			endTime = now.getTime();
			now.set(Calendar.HOUR_OF_DAY, 0);
			now.set(Calendar.MINUTE, 0);
			now.set(Calendar.SECOND, 0);
			now.set(Calendar.MILLISECOND, 0);
			startTime = now.getTime();
		} else if (startTime == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endTime);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			endTime = calendar.getTime();
		} else if (endTime == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startTime);
			calendar.set(Calendar.HOUR_OF_DAY, 24);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.add(Calendar.MILLISECOND, -1);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startTime);
			calendar.add(Calendar.MONTH, 3);
			if (calendar.getTime().compareTo(endTime) < 0) {
				throw new BusinessException("时间区间须在3个月之内");
			}
		}
		int count = orderMapper.countPaidOrderByEnterpr(enterprId, startTime, endTime);
		List<PaidOrderInfo> list;
		if (count == 0 || pageBean.getOffset() > count) {
			list = Collections.emptyList();
		} else {
			list = orderMapper.selectPaidOrderByEnterpr(enterprId, startTime, endTime, pageBean);
		}
		PageResultBean<PaidOrderInfo> pageResultBean;
		if (pageBean.getPage() == 1) {
			BigDecimal totalPoints = orderMapper.sumPaidOrderPointsByEnterpr(enterprId, startTime, endTime);
			pageResultBean = new PageResultBean<PaidOrderInfo>(pageBean.getPage(), pageBean.getPageSize(), count, list) {
				@SuppressWarnings("unused")
				public BigDecimal getTotalPoints() {
					return totalPoints;
				}
			};
		} else {
			pageResultBean = new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, list);
		}
		return pageResultBean;
	}

	@Override
	public List<Order> listVirtualGoodsOrder(List<Long> sellerOrders) {
		List<Order> orders = orderMapper.listVirtualGoodsOrder(sellerOrders);
		return orders;
	}

	@Override
	public List<Long> listByNumbersAndLevel(Long accountId, Integer level,Integer startRecord, Integer pageSize) throws BusinessException {
		List<Long> selectByNumbersAndLevel = orderMapper.selectByUserIdAndLevel(accountId, level,startRecord,pageSize);
		return selectByNumbersAndLevel;
	}

	@Override
	public List<Order> listVirtualOrders(List<Long> sellerOrders) {
		List<Order> orders = orderMapper.listVirtualOrders(sellerOrders);
		return orders;
	}

}

