package com.lx.benefits.service.card;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.card.CardAmount;
import com.lx.benefits.bean.entity.card.CardBatchRecorder;
import com.lx.benefits.bean.entity.card.CardStorage;
import com.lx.benefits.bean.enums.card.MemberCardStatus;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.bean.vo.card.CardAmountStorage;
import com.lx.benefits.bean.vo.card.CardBatchBean;
import com.lx.benefits.bean.vo.card.CardSaleOrderDetail;
import com.lx.benefits.bean.vo.card.CardStasticReport;
import com.lx.benefits.bean.vo.card.CardStorageBean;
import com.lx.benefits.bean.vo.card.CardStorageStatistics;
import com.lx.benefits.bean.vo.card.CustomerOrderReport;
import com.lx.benefits.bean.vo.card.MemberCardBindRequest;
import com.lx.benefits.bean.vo.card.MemberCardSearchBean;

public interface MemberCardService {

	/**
	 * 创建会员卡信息
	 * 
	 * <pre>
	 * 
	 * 会员卡编码规则(20位会员卡编码)：
	 * 	(1) 类型  ：10两位数字（从10开始）
	 * 	(2) 所属公司： 00001 五位数字（数据来源企业id）
	 * 	(3) 金额： 10000 五位数字（面值金额）
	 * 	(4) 批次： 91 两位数字（企业生成次数）
	 * 	(5) 序列号：000001 六位数字 （延顺数字）
	 * 
	 * </pre>
	 * 
	 * @param customerId
	 *            客户ID
	 * @param bindEnterprId
	 *            绑定的企业ID
	 * @param cardType
	 *            会员卡类型
	 * @param saleOrderId
	 *            会员卡销售订单ID
	 * @param cardAmountStorages
	 *            会员卡数据
	 * 
	 */
	void createMemberCards(Long customerId, Long bindEnterprId, Integer cardType, Integer saleOrderId, List<CardAmountStorage> cardAmountStorages);

	List<CardAmount> getCardAmounts();

	/**
	 * 根据校验码获取会员卡信息
	 * 
	 * @param verifyNumber
	 *            会员卡校验码
	 * @return
	 */
	CardStorage getCardByVerifyNumber(String verifyNumber);

	BigDecimal memberCardReward(Integer employeeId);

	SessionShopInfo bindEmployee(MemberCardBindRequest bindRequest);

	/**
	 * 获取会员卡列表信息
	 * 
	 * @param batchId
	 *            生产批次ID
	 * @param withVerifyNumber
	 *            是否携带卡密
	 * @param pageBean
	 *            分页信息
	 * @return
	 */
	PageResultBean<CardStorageBean> getCardlistByBatchId(Integer batchId, boolean withVerifyNumber, PageBean pageBean);

	/**
	 * 获取会员卡列表信息
	 * 
	 * @param batchId
	 *            生产批次ID
	 * @param cardNumber
	 *            会员卡号(模糊搜索)
	 * @param withVerifyNumber
	 *            是否携带卡密
	 * @param pageBean
	 *            分页信息
	 * @return
	 */
	PageResultBean<CardStorageBean> getCardlistByBatchId(Integer batchId, String cardNumber, boolean withVerifyNumber, PageBean pageBean);

	/**
	 * 会员卡仓库统计（金额、数量）
	 * 
	 * @param memberCardStatus
	 *            会员卡状态
	 * @return
	 */
	CardStorageStatistics getStorageStatistics(MemberCardStatus memberCardStatus);

	/**
	 * 会员卡库存信息（各面值的数量）
	 * 
	 * @param memberCardStatus
	 *            会员卡状态
	 * @return
	 */
	CardStorageStatistics getCardStoreInfo(MemberCardStatus memberCardStatus);

	/**
	 * 获取会员卡生产批次信息
	 * 
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param pageBean
	 * @return
	 */
	PageResultBean<CardBatchBean> getCardBatches(Date startTime, Date endTime, Integer status, PageBean pageBean);

	/**
	 * 获取生产批次对应的销售开单详情
	 * 
	 * @param batchId
	 * @return
	 */
	CardSaleOrderDetail getCardBatchInfo(Integer batchId);

	/**
	 * 导出会员卡信息，并将状态置为制卡中
	 * 
	 * @param batchId
	 * @param outputStream
	 */
	void makingBatchCards(Integer batchId, OutputStream outputStream) throws IOException;

	/**
	 * 会员卡入库
	 * 
	 * @param batchId
	 *            会员卡生产批次ID
	 */
	void storeCards(Integer batchId);

	/**
	 * 根据会员卡销售订单获取会员卡生产批次信息
	 * 
	 * @param saleOrderId
	 *            会员卡销售订单
	 * @return
	 */
	CardBatchRecorder getCardBatchBySaleOrderId(Integer saleOrderId);

	/**
	 * 获取会员卡存储列表
	 * 
	 * @param startTime
	 *            生成开始时间
	 * @param endTime
	 *            生成结束时间
	 * @param cardNumber
	 *            会员卡号
	 * @param pageBean
	 *            分页信息
	 * @return
	 */
	PageResultBean<CardStorageBean> getStoredCards(Date startTime, Date endTime, String cardNumber, PageBean pageBean);

	void exportBatchCard(Integer batchId, OutputStream outputStream, boolean withVerifyNumber) throws IOException;

	/**
	 * 会员卡发卡操作，同时激活会员卡，然后将会员卡销售订单状态置为完成
	 * 
	 * @param batchId
	 *            会员卡生产批次ID
	 */
	void deliverCardsByBatchId(Integer batchId);

	/**
	 * 会员卡发卡操作，同时激活会员卡，然后将会员卡销售订单状态置为完成
	 * 
	 * @param saleOrderId
	 *            会员卡销售订单ID
	 */
	void deliverCardsBySaleOrderId(Integer saleOrderId);

	/**
	 * 获取会员卡客户下会员卡使用情况
	 * 
	 * @param customerId
	 *            客户ID
	 * @param status
	 *            会员卡状态
	 * @param pageBean
	 *            分页信息
	 */
	PageResultBean<CardStorage> getCustomerCardUseInfo(Long customerId, Integer status, PageBean pageBean);

	/**
	 * 获取会员卡统计报表（制作总额、已激活总额、未激活总额、已回款金额）
	 * 
	 * @return
	 */
	CardStasticReport getCardStasticReport();

	/**
	 * 获取客户会员卡使用详情
	 * 
	 * @param customerIds
	 *            客户ID 列表
	 * @return
	 */
	List<CustomerOrderReport> getUsedAmount(List<Long> customerIds);

	/**
	 * 获取已发卡的会员卡使用情况
	 * 
	 * @param searchBean
	 *            搜索条件
	 * @param pageBean
	 *            分页信息详情
	 * @return
	 */
	PageResultBean<CardStorageBean> getCardUsedInfo(MemberCardSearchBean searchBean, PageBean pageBean);

}
