package com.lx.benefits.service.card;

import java.util.Date;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.card.CardSaleOrder;
import com.lx.benefits.bean.vo.card.CardSaleOrderDetail;
import com.lx.benefits.bean.vo.card.CustomerOrderReport;

public interface CardSaleService {

	/**
	 * 创建会员卡订单
	 * 
	 * @param cardSaleOrderBean
	 *            会员卡订单信息
	 */
	void createSaleOrder(CardSaleOrderDetail cardSaleOrderBean);

	/**
	 * 获取会员卡订单信息列表
	 * 
	 * @param startTime
	 *            下单开始时间
	 * @param endTime
	 *            下单结束时间
	 * @param customerId
	 *            客户ID
	 * @param status
	 *            订单状态
	 * @param pageBean
	 *            分页信息
	 * @return
	 */
	PageResultBean<CardSaleOrderDetail> getSaleOrdersByPage(Date startTime, Date endTime, Long customerId, Integer status, PageBean pageBean);

	/**
	 * 获取会员卡订单详情
	 * 
	 * @param saleOrderId
	 *            会员卡订单ID
	 * @return
	 */
	CardSaleOrderDetail getSaleOrderDetail(Integer saleOrderId);

	/**
	 * 删除未审核通过的会员卡信息
	 * 
	 * @param saleOrderId
	 *            会员卡销售订单ID
	 * @return
	 */
	int deleteUnpassedSaleOrder(Integer saleOrderId);

	/**
	 * 将会员卡销售订单置为完成状态
	 * 
	 * @param saleOrderId
	 *            会员卡销售订单ID
	 * @return
	 */
	int completeSaleOrder(Integer saleOrderId);

	/**
	 * 获取订单记录
	 * 
	 * @param saleOrderId
	 *            会员卡销售订单ID
	 * @return
	 */
	CardSaleOrder getSaleOrderById(Integer saleOrderId);

	/**
	 * 获取会员卡客户详情报表
	 * 
	 * @param customerId
	 *            客户ID
	 * @param pageBean
	 *            分页信息详情
	 * @return
	 */
	PageResultBean<CustomerOrderReport> getCustomerInfos(Long customerId, PageBean pageBean);

	/**
	 * 获取客户已完成状态的订单列表
	 * 
	 * @param customerId
	 *            客户ID
	 * @param pageBean
	 *            分页信息
	 * @return
	 */
	PageResultBean<CardSaleOrderDetail> getCustomerCompleteOrderInfos(Long customerId, PageBean pageBean);

}
