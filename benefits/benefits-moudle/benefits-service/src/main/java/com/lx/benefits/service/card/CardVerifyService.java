package com.lx.benefits.service.card;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.card.CardPayRecord;
import com.lx.benefits.bean.vo.card.CardSaleOrderDetail;
import com.lx.benefits.bean.vo.card.CardStorageBean;
import com.lx.benefits.bean.vo.card.CardVerifyBean;

public interface CardVerifyService {

	/**
	 * 获取审批列表（审批不通过的除外）
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
	PageResultBean<CardSaleOrderDetail> getVerifyOrdersByPage(Date startTime, Date endTime, Long customerId, Integer status, PageBean pageBean);

	/**
	 * 对会员卡订单进行审批（如果审批通过，则生成对应的会员卡数据）
	 * 
	 * @param saleOrderId
	 *            会员卡销售订单
	 * @param cardVerifyBean
	 *            审核结果
	 */
	void verifySaleOrder(Integer saleOrderId, CardVerifyBean cardVerifyBean);

	/**
	 * 会员卡订单付款
	 * 
	 * @param saleOrderId
	 *            会员卡销售订单
	 * @param cardPayRecord
	 *            支付记录
	 */
	void cardOrderPay(Integer saleOrderId, CardPayRecord cardPayRecord);

	/**
	 * 获取会员卡订单回款记录
	 * 
	 * @param saleOrderId
	 *            会员卡销售订单
	 * @param pageBean
	 *            分页信息
	 * @return
	 */
	PageResultBean<CardPayRecord> getCardOrderPayRecords(Integer saleOrderId, PageBean pageBean);

	/**
	 * 获取会员卡订单生成的会员卡数据
	 * 
	 * @param saleOrderId
	 *            会员卡销售订单
	 * @param pageBean
	 *            分页信息
	 * @return
	 */
	PageResultBean<CardStorageBean> getSaleOrderCardList(Integer saleOrderId, PageBean pageBean);

	/**
	 * 导出销售订单生成的会员卡列表
	 * 
	 * @param saleOrderId
	 *            销售订单
	 * @param outputStream
	 *            输出流
	 * @throws IOException
	 */
	void exportSaleOrderCardlist(Integer saleOrderId, OutputStream outputStream) throws IOException;

}
