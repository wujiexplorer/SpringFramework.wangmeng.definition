package com.lx.benefits.service.card.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.card.CardAmount;
import com.lx.benefits.bean.entity.card.CardBatchRecorder;
import com.lx.benefits.bean.entity.card.CardSaleOrder;
import com.lx.benefits.bean.entity.card.CardSaleOrderCondition;
import com.lx.benefits.bean.entity.card.CardSaleOrderCondition.Criteria;
import com.lx.benefits.bean.entity.card.CardSaleOrderItem;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.enums.card.CardOrderStatus;
import com.lx.benefits.bean.enums.card.CardPayType;
import com.lx.benefits.bean.enums.card.MemberCardBatchStatus;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.card.CardSaleOrderDetail;
import com.lx.benefits.bean.vo.card.CustomerOrderReport;
import com.lx.benefits.mapper.card.CardSaleOrderItemMapper;
import com.lx.benefits.mapper.card.CardSaleOrderMapper;
import com.lx.benefits.service.card.CardSaleService;
import com.lx.benefits.service.card.MemberCardService;
import com.lx.benefits.service.enterprise.EnterpriseService;

@Service
public class CardSaleServiceImpl implements CardSaleService {

	@Autowired
	private CardSaleOrderMapper cardSaleOrderMapper;
	@Autowired
	private CardSaleOrderItemMapper cardSaleOrderItemMapper;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private MemberCardService memberCardService;

	@Transactional
	@Override
	public void createSaleOrder(CardSaleOrderDetail cardSaleOrderBean) {
		CardSaleOrder cardSaleOrder = new CardSaleOrder();
		cardSaleOrder.setStatus(CardOrderStatus.INIT.status);
		if (enterpriseService.getEnterprise(cardSaleOrderBean.getCustomerId()) == null) {
			throw new BusinessException("客户信息不存在");
		}
		if (!cardSaleOrderBean.getCustomerId().equals(cardSaleOrderBean.getBindEnterprId())) {
			if (enterpriseService.getEnterprise(cardSaleOrderBean.getBindEnterprId()) == null) {
				throw new BusinessException("绑定的企业信息不存在");
			}
		}
		cardSaleOrder.setCustomerId(cardSaleOrderBean.getCustomerId());
		cardSaleOrder.setBindEnterprId(cardSaleOrderBean.getBindEnterprId());
		Integer payType = cardSaleOrderBean.getPayType();
		if (!CardPayType.check(payType)) {
			throw new BusinessException("付款方式不存在");
		}
		cardSaleOrder.setPayType(payType);
		Byte isCustomCard = cardSaleOrderBean.getIsCustomCard();
		if (Byte.valueOf("0").equals(isCustomCard) || Byte.valueOf("1").equals(isCustomCard)) {
		} else {
			throw new BusinessException("会员卡定制类型错误");
		}
		cardSaleOrder.setIsCustomCard(isCustomCard);
		BigDecimal discountOnSale = cardSaleOrderBean.getDiscountOnSale();
		if (BigDecimal.ZERO.compareTo(discountOnSale) >= 0) {
			throw new BusinessException("折扣不能小于0%");
		}
		cardSaleOrder.setDiscountOnSale(discountOnSale);
		cardSaleOrder.setPayType(payType);
		List<CardSaleOrderItem> cards = cardSaleOrderBean.getCards();
		Map<Integer, Integer> cardsMap = new HashMap<>();
		for (CardSaleOrderItem item : cards) {
			if (item.getNum() <= 0 || item.getAmountId() == null) {
				continue;
			}
			Integer oldVale = cardsMap.put(item.getAmountId(), item.getNum());
			if (oldVale != null) {
				throw new BusinessException("参数错误!");
			}
		}
		if (cardsMap.size() == 0) {
			throw new BusinessException("无有效的会员卡数据");
		}
		List<CardAmount> cardAmounts = memberCardService.getCardAmounts();
		List<CardSaleOrderItem> cardSaleOrderItems = new ArrayList<>();
		int totalAmount = 0;
		int totalNum = 0;
		for (CardAmount cardAmount : cardAmounts) {
			Integer num = cardsMap.get(cardAmount.getAmountId());
			if (num != null) {
				CardSaleOrderItem cardSaleOrderItem = new CardSaleOrderItem();
				cardSaleOrderItem.setAmount(cardAmount.getAmount());
				cardSaleOrderItem.setNum(num);
				totalAmount += cardAmount.getAmount() * num;
				totalNum += num;
				cardSaleOrderItems.add(cardSaleOrderItem);
			}
		}
		if (cardSaleOrderItems.size() != cardsMap.size()) {
			throw new BusinessException("参数错误!");
		}
		if (totalNum > 100000) {
			throw new BusinessException("生成会员卡数量过大,一次最多可生成100000张");
		}
		cardSaleOrder.setSumPayable(BigDecimal.valueOf(totalAmount).multiply(discountOnSale).setScale(2, RoundingMode.HALF_UP));
		cardSaleOrder.setCreateUser(SessionContextHolder.getCurrentLoginName());
		cardSaleOrder.setCreateTime(new Date());
		Integer saleOrderId = cardSaleOrderBean.getSaleOrderId();
		if (saleOrderId != null) {
			int deleteCount = this.deleteUnpassedSaleOrder(saleOrderId);
			if (deleteCount != 1) {
				throw new BusinessException("修改失败,请刷新后重试!");
			}
		}
		cardSaleOrderMapper.insertSelective(cardSaleOrder);
		cardSaleOrderItemMapper.batchInsert(cardSaleOrderItems, cardSaleOrder.getSaleOrderId());
	}

	@Override
	public PageResultBean<CardSaleOrderDetail> getSaleOrdersByPage(Date startTime, Date endTime, Long customerId, Integer status, PageBean pageBean) {
		CardSaleOrderCondition example = new CardSaleOrderCondition();
		Criteria criteria = example.createCriteria().andDeletedEqualTo(false);
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
		List<CardSaleOrderDetail> cardSaleOrderDetails = cardSaleOrderMapper.getSaleOrdersByPage(startTime, endTime, customerId, status, pageBean);
		// 设置客户名称
		List<Long> customerIds = cardSaleOrderDetails.stream().map(CardSaleOrderDetail::getCustomerId).collect(Collectors.toList());
		List<EnterprUserInfo> enterprises = enterpriseService.getEnterpriseNames(customerIds);
		Map<Long, String> enterpriseNameMap = enterprises.stream().collect(Collectors.toMap(EnterprUserInfo::getEnterprId, EnterprUserInfo::getEnterprName));
		for (CardSaleOrderDetail item : cardSaleOrderDetails) {
			item.setCustomerName(enterpriseNameMap.get(item.getCustomerId()));
		}
		return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, cardSaleOrderDetails);
	}

	@Override
	public CardSaleOrderDetail getSaleOrderDetail(Integer saleOrderId) {
		CardSaleOrderDetail cardSaleOrderDetail = cardSaleOrderMapper.getSaleOrderDetail(saleOrderId);
		if (cardSaleOrderDetail == null) {
			return null;
		}
		if (cardSaleOrderDetail.getCustomerId().equals(cardSaleOrderDetail.getBindEnterprId())) {
			EnterprUserInfo enterprise = enterpriseService.getEnterprise(cardSaleOrderDetail.getCustomerId());
			if (enterprise != null) {
				cardSaleOrderDetail.setCustomerName(enterprise.getEnterprName());
				cardSaleOrderDetail.setBindEnterprName(enterprise.getEnterprName());
			}
		} else {
			List<EnterprUserInfo> enterprises = enterpriseService
					.getEnterpriseNames(Arrays.asList(cardSaleOrderDetail.getCustomerId(), cardSaleOrderDetail.getBindEnterprId()));
			if (!CollectionUtils.isEmpty(enterprises)) {
				for (EnterprUserInfo enterprUserInfo : enterprises) {
					if (enterprUserInfo.getEnterprId().equals(cardSaleOrderDetail.getCustomerId())) {
						cardSaleOrderDetail.setCustomerName(enterprUserInfo.getEnterprName());
					} else {
						cardSaleOrderDetail.setBindEnterprName(enterprUserInfo.getEnterprName());
					}
				}
			}
		}
		List<CardSaleOrderItem> cards = cardSaleOrderDetail.getCards();
		int totalCount = 0;
		long totalAmount = 0;
		Map<Integer, CardSaleOrderItem> cardMap = new HashMap<>();
		for (CardSaleOrderItem cardSaleOrderItem : cards) {
			cardMap.put(cardSaleOrderItem.getAmount(), cardSaleOrderItem);
			totalAmount += cardSaleOrderItem.getAmount() * cardSaleOrderItem.getNum();
			totalCount += cardSaleOrderItem.getNum();
		}
		List<CardAmount> cardAmounts = memberCardService.getCardAmounts();
		List<CardSaleOrderItem> handledCardList = new ArrayList<>();
		for (CardAmount cardAmount : cardAmounts) {
			CardSaleOrderItem cardSaleOrderItem = cardMap.remove(cardAmount.getAmount());
			if (cardSaleOrderItem == null) {
				cardSaleOrderItem = new CardSaleOrderItem();
				cardSaleOrderItem.setAmount(cardAmount.getAmount());
				cardSaleOrderItem.setNum(0);
			}
			cardSaleOrderItem.setAmountId(cardAmount.getAmountId());
			handledCardList.add(cardSaleOrderItem);
		}
		if (cardMap.size() > 0) {// 处理未找到对应额度的面值
			for (Entry<Integer, CardSaleOrderItem> entry : cardMap.entrySet()) {
				CardSaleOrderItem value = entry.getValue();
				value.setAmountId(-1);
				handledCardList.add(value);
			}
		}
		cardSaleOrderDetail.setCards(handledCardList);
		cardSaleOrderDetail.setTotalAmount(totalAmount);
		cardSaleOrderDetail.setTotalCount(totalCount);
		return cardSaleOrderDetail;
	}

	@Override
	public int deleteUnpassedSaleOrder(Integer saleOrderId) {
		CardSaleOrder record = new CardSaleOrder();
		record.setDeleted(true);
		CardSaleOrderCondition example = new CardSaleOrderCondition();
		example.createCriteria().andSaleOrderIdEqualTo(saleOrderId).andStatusEqualTo(CardOrderStatus.VERIFY_UNPASSED.status).andDeletedEqualTo(false);
		return cardSaleOrderMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int completeSaleOrder(Integer saleOrderId) {
		CardBatchRecorder cardBatchRecorder = memberCardService.getCardBatchBySaleOrderId(saleOrderId);
		if (!MemberCardBatchStatus.DELIVERED.status.equals(cardBatchRecorder.getStatus())) {
			throw new BusinessException("处理失败，批次卡状态必须为已发卡状态!");
		}
		CardSaleOrder record = new CardSaleOrder();
		record.setStatus(CardOrderStatus.COMPLETE.status);
		CardSaleOrderCondition condition = new CardSaleOrderCondition();
		condition.createCriteria().andSaleOrderIdEqualTo(saleOrderId).andStatusEqualTo(CardOrderStatus.VERIFY_PASSED.status);
		return cardSaleOrderMapper.updateByExampleSelective(record, condition);
	}

	@Override
	public CardSaleOrder getSaleOrderById(Integer saleOrderId) {
		return cardSaleOrderMapper.selectByPrimaryKey(saleOrderId);
	}

	@Override
	public PageResultBean<CustomerOrderReport> getCustomerInfos(Long customerId, PageBean pageBean) {
		int count = cardSaleOrderMapper.countOrderCustomers(customerId);
		if (pageBean.getOffset() >= count) {
			return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, Collections.emptyList());
		}
		List<CustomerOrderReport> customerOrderReports = cardSaleOrderMapper.getCustomerOrderReports(customerId, pageBean);
		Map<Long, CustomerOrderReport> reportMap = customerOrderReports.stream().map(item -> {
			item.setUsedAmount(BigDecimal.ZERO);// 初始化用户已使用金额
			return item;
		}).collect(Collectors.toMap(CustomerOrderReport::getCustomerId, Function.identity()));
		List<CardSaleOrderDetail> cardSaleOrderDetails = cardSaleOrderItemMapper.getTotalAmountByStatus(reportMap.keySet(), CardOrderStatus.COMPLETE.status);
		List<EnterprUserInfo> enterpriseNames = enterpriseService.getEnterpriseNames(new ArrayList<>(reportMap.keySet()));
		List<CustomerOrderReport> usedAmounts = memberCardService.getUsedAmount(new ArrayList<>(reportMap.keySet()));
		for (EnterprUserInfo item : enterpriseNames) {
			reportMap.get(item.getEnterprId()).setCustomerName(item.getEnterprName());
		}
		for (CardSaleOrderDetail item : cardSaleOrderDetails) {
			reportMap.get(item.getCustomerId()).setTotalAmount(item.getTotalAmount());
			reportMap.get(item.getCustomerId()).setTotalCount(item.getTotalCount());
		}
		for (CustomerOrderReport item : usedAmounts) {
			reportMap.get(item.getCustomerId()).setUsedAmount(item.getUsedAmount());
		}
		return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, customerOrderReports);
	}

	@Override
	public PageResultBean<CardSaleOrderDetail> getCustomerCompleteOrderInfos(Long customerId, PageBean pageBean) {
		CardSaleOrderCondition example = new CardSaleOrderCondition();
		example.createCriteria().andCustomerIdEqualTo(customerId).andStatusEqualTo(CardOrderStatus.COMPLETE.status);
		int count = (int) cardSaleOrderMapper.countByExample(example);
		if (pageBean.getOffset() >= count) {
			return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, Collections.emptyList());
		}
		List<CardSaleOrderDetail> list = cardSaleOrderMapper.getCustomerCompleteOrderInfos(customerId, pageBean);
		return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, list);
	}
}
