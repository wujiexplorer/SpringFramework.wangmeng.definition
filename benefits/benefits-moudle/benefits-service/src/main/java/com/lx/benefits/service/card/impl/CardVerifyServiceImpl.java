package com.lx.benefits.service.card.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.card.CardBatchRecorder;
import com.lx.benefits.bean.entity.card.CardBatchRecorderCondition;
import com.lx.benefits.bean.entity.card.CardPayRecord;
import com.lx.benefits.bean.entity.card.CardPayRecordCondition;
import com.lx.benefits.bean.entity.card.CardSaleOrder;
import com.lx.benefits.bean.entity.card.CardSaleOrderCondition;
import com.lx.benefits.bean.entity.card.CardSaleOrderCondition.Criteria;
import com.lx.benefits.bean.entity.card.CardSaleOrderItem;
import com.lx.benefits.bean.entity.card.CardSaleOrderItemCondition;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.enums.card.CardOrderStatus;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.card.CardAmountStorage;
import com.lx.benefits.bean.vo.card.CardSaleOrderDetail;
import com.lx.benefits.bean.vo.card.CardStorageBean;
import com.lx.benefits.bean.vo.card.CardVerifyBean;
import com.lx.benefits.mapper.card.CardBatchRecorderMapper;
import com.lx.benefits.mapper.card.CardPayRecordMapper;
import com.lx.benefits.mapper.card.CardSaleOrderItemMapper;
import com.lx.benefits.mapper.card.CardSaleOrderMapper;
import com.lx.benefits.service.card.CardVerifyService;
import com.lx.benefits.service.card.MemberCardService;
import com.lx.benefits.service.enterprise.EnterpriseService;

@Service
public class CardVerifyServiceImpl implements CardVerifyService {

	@Autowired
	private CardSaleOrderMapper cardSaleOrderMapper;
	@Autowired
	private CardSaleOrderItemMapper cardSaleOrderItemMapper;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private MemberCardService memberCardService;
	@Autowired
	private CardPayRecordMapper cardPayRecordMapper;
	@Autowired
	private CardBatchRecorderMapper cardBatchRecorderMapper;

	@Override
	public PageResultBean<CardSaleOrderDetail> getVerifyOrdersByPage(Date startTime, Date endTime, Long customerId, Integer status, PageBean pageBean) {
		CardSaleOrderCondition example = new CardSaleOrderCondition();
		Criteria criteria = example.createCriteria().andDeletedEqualTo(false).andStatusNotEqualTo(CardOrderStatus.VERIFY_UNPASSED.status);
		if (startTime != null) {
			criteria.andCreateTimeGreaterThan(startTime);
		}
		if (endTime != null) {
			criteria.andCreateTimeLessThanOrEqualTo(endTime);
		}
		if (customerId != null) {
			criteria.andCustomerIdEqualTo(customerId);
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		int count = (int) cardSaleOrderMapper.countByExample(example);
		if (count == 0 || pageBean.getOffset() >= count) {
			return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, Collections.emptyList());
		}
		List<CardSaleOrderDetail> cardSaleOrderDetails = cardSaleOrderMapper.getVerifyOrdersByPage(startTime, endTime, customerId, status, pageBean);
		List<Long> customerIds = new ArrayList<>();
		for (CardSaleOrderDetail item : cardSaleOrderDetails) {
			customerIds.add(item.getCustomerId());
			if (BigDecimal.ZERO.compareTo(item.getUnpaidAmount()) > 0) {// 如果已回款金额大于应回款金额，则设置未回款金额为0
				item.setUnpaidAmount(BigDecimal.ZERO);
			}
		}
		// 设置客户名称
		List<EnterprUserInfo> enterprises = enterpriseService.getEnterpriseNames(customerIds);
		Map<Long, String> enterpriseNameMap = enterprises.stream().collect(Collectors.toMap(EnterprUserInfo::getEnterprId, EnterprUserInfo::getEnterprName));
		for (CardSaleOrderDetail item : cardSaleOrderDetails) {
			item.setCustomerName(enterpriseNameMap.get(item.getCustomerId()));
		}

		return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, cardSaleOrderDetails);
	}

	@Transactional
	@Override
	public void verifySaleOrder(Integer saleOrderId, CardVerifyBean cardVerifyBean) {
		CardSaleOrder cardSaleOrder = cardSaleOrderMapper.selectByPrimaryKey(saleOrderId);
		if (!CardOrderStatus.INIT.status.equals(cardSaleOrder.getStatus())) {
			throw new BusinessException("请勿重复审核!");
		}
		Boolean isPassed = cardVerifyBean.getIsPassed();
		// 更新审核状态
		CardSaleOrder record = new CardSaleOrder();
		record.setStatus(isPassed ? CardOrderStatus.VERIFY_PASSED.status : CardOrderStatus.VERIFY_UNPASSED.status);
		record.setVerifyInfo(cardVerifyBean.getVerifyInfo());
		record.setVerifyTime(new Date());
		record.setVerifyUser(SessionContextHolder.getCurrentLoginName());
		CardSaleOrderCondition example = new CardSaleOrderCondition();
		example.createCriteria().andSaleOrderIdEqualTo(saleOrderId).andStatusEqualTo(CardOrderStatus.INIT.status);
		int update = cardSaleOrderMapper.updateByExampleSelective(record, example);
		if (update != 1) {
			throw new BusinessException("审核失败，请刷新后重试!");
		}
		// 如果通过，则生成会员卡数据
		if (isPassed) {
			CardSaleOrderItemCondition condition = new CardSaleOrderItemCondition();
			condition.createCriteria().andSaleOrderIdEqualTo(saleOrderId);
			List<CardSaleOrderItem> cardSaleOrderItem = cardSaleOrderItemMapper.selectByExample(condition);
			List<CardAmountStorage> cardAmountBeans = cardSaleOrderItem.stream().map(item -> new CardAmountStorage(item.getAmount(), item.getNum()))
					.collect(Collectors.toList());
			memberCardService.createMemberCards(cardSaleOrder.getCustomerId(), cardSaleOrder.getBindEnterprId(), 10, saleOrderId, cardAmountBeans);
		}
	}

	@Transactional
	@Override
	public void cardOrderPay(Integer saleOrderId, CardPayRecord cardPayRecord) {
		BigDecimal payAmount = cardPayRecord.getPayAmount();
		if (BigDecimal.ZERO.compareTo(payAmount) == 0) {
			throw new BusinessException("回款金额不能为0!");
		}
		CardSaleOrder cardSaleOrder = cardSaleOrderMapper.selectByPrimaryKey(saleOrderId);
		if (cardSaleOrder == null) {
			throw new BusinessException("该订单不存在!");
		}
		// 添加支付记录
		CardPayRecord record = new CardPayRecord();
		record.setCreateUser(SessionContextHolder.getCurrentLoginName());
		record.setCreateTime(new Date());
		record.setPayAmount(payAmount);
		record.setPayVoucher(cardPayRecord.getPayVoucher());
		record.setRemark(cardPayRecord.getRemark());
		record.setSaleOrderId(saleOrderId);
		record.setPayTime(cardPayRecord.getPayTime());
		cardPayRecordMapper.insertSelective(record);
		// 修改已支付金额
		CardSaleOrderCondition cardSaleOrderCondition = new CardSaleOrderCondition();
		cardSaleOrderCondition.createCriteria().andSaleOrderIdEqualTo(saleOrderId).andPaidAmountEqualTo(cardSaleOrder.getPaidAmount());
		CardSaleOrder orderRecord = new CardSaleOrder();
		orderRecord.setPaidAmount(cardSaleOrder.getPaidAmount().add(payAmount));
		cardSaleOrderMapper.updateByExampleSelective(orderRecord, cardSaleOrderCondition);
	}

	@Override
	public PageResultBean<CardPayRecord> getCardOrderPayRecords(Integer saleOrderId, PageBean pageBean) {
		CardPayRecordCondition example = new CardPayRecordCondition();
		example.createCriteria().andSaleOrderIdEqualTo(saleOrderId);
		int count = (int) cardPayRecordMapper.countByExample(example);
		if (count == 0 || pageBean.getOffset() >= count) {
			return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, Collections.emptyList());
		}
		Integer pageSize = pageBean.getPageSize();
		example.setLimit(pageSize);
		example.setOffset(pageBean.getOffset());
		example.setOrderByClause("id DESC");
		List<CardPayRecord> cardPayRecords = cardPayRecordMapper.selectBasicInfoByExample(example);
		return new PageResultBean<>(pageBean.getPage(), pageSize, count, cardPayRecords);
	}

	@Override
	public PageResultBean<CardStorageBean> getSaleOrderCardList(Integer saleOrderId, PageBean pageBean) {
		CardSaleOrder cardSaleOrder = cardSaleOrderMapper.selectByPrimaryKey(saleOrderId);
		if (cardSaleOrder == null) {
			throw new BusinessException("该订单不存在!");
		}
		if (!CardOrderStatus.COMPLETE.status.equals(cardSaleOrder.getStatus())) {
			throw new BusinessException("获取失败，该订单还未发卡");
		}
		CardBatchRecorderCondition example = new CardBatchRecorderCondition();
		example.createCriteria().andSaleOrderIdEqualTo(saleOrderId);
		List<CardBatchRecorder> cardBatchRecorders = cardBatchRecorderMapper.selectByExample(example);
		PageResultBean<CardStorageBean> saleOrderCardlist = memberCardService.getCardlistByBatchId(cardBatchRecorders.get(0).getBatchId(), false, pageBean);
		EnterprUserInfo enterprise = enterpriseService.getEnterprise(cardSaleOrder.getBindEnterprId());
		if (enterprise != null) {
			for (CardStorageBean item : saleOrderCardlist.getList()) {
				item.setEnterprName(enterprise.getEnterprName());
			}
		}
		return saleOrderCardlist;
	}

	@Override
	public void exportSaleOrderCardlist(Integer saleOrderId, OutputStream outputStream) throws IOException {
		CardSaleOrder cardSaleOrder = cardSaleOrderMapper.selectByPrimaryKey(saleOrderId);
		if (cardSaleOrder == null) {
			throw new BusinessException("该订单不存在!");
		}
		Integer cardSaleStatus = cardSaleOrder.getStatus();
		if (CardOrderStatus.INIT.status.equals(cardSaleStatus) || CardOrderStatus.VERIFY_UNPASSED.status.equals(cardSaleStatus)) {
			throw new BusinessException("导出失败，该状态的订单还未生成会员卡信息");
		}
		CardBatchRecorder cardBatch = memberCardService.getCardBatchBySaleOrderId(saleOrderId);
		if (cardBatch == null) {
			throw new BusinessException("未找到对应的会员卡片生成信息，请联系管理员处理");
		}
		memberCardService.exportBatchCard(cardBatch.getBatchId(), outputStream, false);
	}

}
