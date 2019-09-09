package com.lx.benefits.service.card.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.card.CardAmount;
import com.lx.benefits.bean.entity.card.CardAmountCondition;
import com.lx.benefits.bean.entity.card.CardBatchRecorder;
import com.lx.benefits.bean.entity.card.CardBatchRecorderCondition;
import com.lx.benefits.bean.entity.card.CardEmployeeRecorder;
import com.lx.benefits.bean.entity.card.CardEmployeeRecorderCondition;
import com.lx.benefits.bean.entity.card.CardSaleOrder;
import com.lx.benefits.bean.entity.card.CardStorage;
import com.lx.benefits.bean.entity.card.CardStorageCondition;
import com.lx.benefits.bean.entity.card.CardStorageCondition.Criteria;
import com.lx.benefits.bean.entity.card.EmployeeCardCredit;
import com.lx.benefits.bean.entity.card.EmployeeCardCreditCondition;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.enums.CreditType;
import com.lx.benefits.bean.enums.OptTypeEnum;
import com.lx.benefits.bean.enums.card.CardOrderStatus;
import com.lx.benefits.bean.enums.card.EmployeeCardStatus;
import com.lx.benefits.bean.enums.card.MemberCardBatchStatus;
import com.lx.benefits.bean.enums.card.MemberCardBindType;
import com.lx.benefits.bean.enums.card.MemberCardStatus;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.bean.vo.card.CardAmountStorage;
import com.lx.benefits.bean.vo.card.CardBatchBean;
import com.lx.benefits.bean.vo.card.CardSaleOrderDetail;
import com.lx.benefits.bean.vo.card.CardStasticReport;
import com.lx.benefits.bean.vo.card.CardStorageBean;
import com.lx.benefits.bean.vo.card.CardStorageStatistics;
import com.lx.benefits.bean.vo.card.CustomerOrderReport;
import com.lx.benefits.bean.vo.card.MemberCardBindRequest;
import com.lx.benefits.bean.vo.card.MemberCardInfoExcelModel;
import com.lx.benefits.bean.vo.card.MemberCardInfoWithVerifyExcelModel;
import com.lx.benefits.bean.vo.card.MemberCardSearchBean;
import com.lx.benefits.mapper.card.CardAmountMapper;
import com.lx.benefits.mapper.card.CardBatchRecorderMapper;
import com.lx.benefits.mapper.card.CardEmployeeRecorderMapper;
import com.lx.benefits.mapper.card.CardPayRecordMapper;
import com.lx.benefits.mapper.card.CardStorageMapper;
import com.lx.benefits.mapper.card.EmployeeCardCreditMapper;
import com.lx.benefits.service.card.CardSaleService;
import com.lx.benefits.service.card.MemberCardService;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.enterprise.EnterpriseService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberCardServiceImpl implements MemberCardService {

	@Autowired
	private CardStorageMapper cardStorageMapper;
	@Autowired
	private CardBatchRecorderMapper cardBatchRecorderMapper;
	@Autowired
	private CardEmployeeRecorderMapper cardEmployeeRecorderMapper;
	@Autowired
	private CardAmountMapper cardAmountMapper;
	@Autowired
	private EmployeeCardCreditMapper employeeCardCreditMapper;
	@Autowired
	private EmployeeUserInfoService employeeUserInfoService;
	@Autowired
	private CreditOperateInfoService creditOperateInfoService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private CardSaleService cardSaleService;
	@Autowired
	private CardPayRecordMapper cardPayRecordMapper;

	@Override
	public List<CardAmount> getCardAmounts() {
		CardAmountCondition example = new CardAmountCondition();
		example.setOrderByClause("sort asc");
		return cardAmountMapper.selectByExample(example);
	}

	@Transactional
	@Override
	public void createMemberCards(Long customerId, Long bindEnterprId, Integer cardType, Integer saleOrderId, List<CardAmountStorage> cardAmountStorages) {
		log.info("createMemberCards, customerId={}, cardType={}, saleOrderId={}, cardAmountBeans={}", customerId, cardType, saleOrderId,
				JSON.toJSONString(cardAmountStorages));
		if (customerId == null || bindEnterprId == null || cardType == null || saleOrderId == null || CollectionUtils.isEmpty(cardAmountStorages)) {
			throw new BusinessException("制卡信息错误!");
		}
		if (cardType < 0 || cardType > 99) {// 类型 ：10两位数字（从10开始）
			throw new BusinessException("会员卡类型【" + cardType + "】不符合,请联系管理员处理！");
		}
		if (customerId <= 0 || customerId > 99999) {// 所属公司：00001五位数字（数据来源企业id）
			throw new BusinessException("客户ID【" + customerId + "】不符合,请联系管理员处理！");
		}
		int count = 0;
		for (CardAmountStorage item : cardAmountStorages) {
			if (item.getAmount() > 99999) {// 金额： 10000 五位数字（面值金额）
				throw new BusinessException("面值金额【" + item.getAmount() + "】不符合,请联系管理员处理！");
			}
			int num = item.getNum();
			if (num <= 0) {
				throw new BusinessException("会员卡数量错误!");
			}
			count += num;
		}
		if (count > 999999) {// 序列号：000001 六位数字
			throw new BusinessException("会员卡总数【" + count + "】不符合,请联系管理员处理！");
		}
		int maxCustomerBatchId = cardBatchRecorderMapper.seletMaxCustomerBatchId(customerId);
		int customerBatchId = maxCustomerBatchId + 1;
		if (customerBatchId < 0 || customerBatchId > 99) {// 批次： 91 两位数字（企业生成次数）
			throw new BusinessException("批次【" + customerBatchId + "】不符合,请联系管理员处理！");
		}
		Date nowTime = new Date();
		CardBatchRecorder cardBatchRecorder = new CardBatchRecorder();
		cardBatchRecorder.setType(cardType);
		cardBatchRecorder.setStatus(MemberCardBatchStatus.INIT.status);
		cardBatchRecorder.setCreateTime(nowTime);
		cardBatchRecorder.setCustomerId(customerId);
		cardBatchRecorder.setCustomerBatchId(customerBatchId);
		cardBatchRecorder.setSaleOrderId(saleOrderId);
		cardBatchRecorderMapper.insertSelective(cardBatchRecorder);
		int serialNumber = 1;
		List<CardStorage> cardStorages = new ArrayList<>();
		for (CardAmountStorage item : cardAmountStorages) {
			for (int i = 0; i < item.getNum(); i++) {
				CardStorage cardStorage = new CardStorage();
				cardStorage.setBatchId(cardBatchRecorder.getBatchId());
				cardStorage.setAmount(item.getAmount());
				cardStorage.setStatus(MemberCardStatus.INIT.status);
				cardStorage.setEnterprId(bindEnterprId);
				cardStorage.setCardNumber(String.format("%02d%05d%05d%02d%06d", cardType, customerId, item.getAmount(), customerBatchId, serialNumber++));
				cardStorage.setVerifyNumber(this.generateVerifyNumber());
				cardStorages.add(cardStorage);
				if (cardStorages.size() > 100) {// per 100 recorders hanlde once
					this.batchInsertCardStorage(cardStorages);
					cardStorages.clear();
				}
			}
			if (cardStorages.size() > 0) {// handle remain recorders
				this.batchInsertCardStorage(cardStorages);
				cardStorages.clear();
			}
		}
	}

	@Override
	public CardStorage getCardByVerifyNumber(String verifyNumber) {
		CardStorageCondition example = new CardStorageCondition();
		example.createCriteria().andVerifyNumberEqualTo(verifyNumber);
		List<CardStorage> cardStorages = cardStorageMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(cardStorages)) {
			return null;
		}
		return cardStorages.get(0);
	}

	@Override
	public BigDecimal memberCardReward(Integer employeeId) {
		CardEmployeeRecorderCondition example = new CardEmployeeRecorderCondition();
		example.createCriteria().andEmployeeIdEqualTo(employeeId).andStatusEqualTo(EmployeeCardStatus.INIT.status);
		List<CardEmployeeRecorder> cardEmployeeRecorders = cardEmployeeRecorderMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(cardEmployeeRecorders)) {
			return BigDecimal.ZERO;
		}
		BigDecimal totalRewardAmount = BigDecimal.ZERO;
		Date nowTime = new Date();
		for (CardEmployeeRecorder item : cardEmployeeRecorders) {
			CardEmployeeRecorder record = new CardEmployeeRecorder();
			record.setStatus(EmployeeCardStatus.ACTIVE.status);
			record.setActiveTime(nowTime);
			CardEmployeeRecorderCondition condition = new CardEmployeeRecorderCondition();
			condition.createCriteria().andIdEqualTo(item.getId()).andStatusEqualTo(EmployeeCardStatus.INIT.status);
			int updateCount = cardEmployeeRecorderMapper.updateByExampleSelective(record, condition);
			if (updateCount == 1) {
				totalRewardAmount = totalRewardAmount.add(item.getAmount());
				// 添加积分操作记录
				CreditOperateInfo creditOperateInfo = new CreditOperateInfo();
				creditOperateInfo.setCampaignId(0L);
				creditOperateInfo.setCredit(item.getAmount());
				creditOperateInfo.setCreditType(CreditType.normal.getCode());
				creditOperateInfo.setOptType(OptTypeEnum.MEMBER_CARD_CREDIT.getValue());
				creditOperateInfo.setParentOptId(0L);
				creditOperateInfo.setFinanceNo(item.getCardNumber());
				creditOperateInfo.setOptUserId(employeeId.longValue());
				creditOperateInfo.setOwnerUserId(employeeId.longValue());
				creditOperateInfo.setRemark(OptTypeEnum.MEMBER_CARD_CREDIT.getDesc());
				creditOperateInfoService.insert(creditOperateInfo);
			}
		}
		EmployeeCardCreditCondition cardCreditCondition = new EmployeeCardCreditCondition();
		cardCreditCondition.createCriteria().andEmployeeIdEqualTo(employeeId);
		List<EmployeeCardCredit> employeeCardCredits = employeeCardCreditMapper.selectByExample(cardCreditCondition);
		if (CollectionUtils.isEmpty(employeeCardCredits)) {
			EmployeeCardCredit record = new EmployeeCardCredit();
			record.setEmployeeId(employeeId);
			record.setCardCredit(totalRewardAmount);
			record.setCreateTime(nowTime);
			employeeCardCreditMapper.insertSelective(record);
		} else {
			int updateCount = employeeCardCreditMapper.updateCardCredit(employeeId, totalRewardAmount);
			if (updateCount != 1) {
				throw new BusinessException("用户积分更新失败!");
			}
		}
		return totalRewardAmount;

	}

	@Transactional
	@Override
	public SessionShopInfo bindEmployee(MemberCardBindRequest bindRequest) {
		String verifyNumber = bindRequest.getVerifyNumber();
		MemberCardBindType bindType = MemberCardBindType.getByType(bindRequest.getBindType());
		if (bindType == null || StringUtils.isEmpty(verifyNumber)) {
			log.error("会员卡绑定类型错误，bindRequest={}", JSON.toJSONString(bindRequest));
			throw new BusinessException("参数错误");
		}
		CardStorage cardInfo = this.getCardByVerifyNumber(verifyNumber);
		if (cardInfo == null) {
			throw new BusinessException("会员卡无效!");
		} else if (MemberCardStatus.USED.status.equals(cardInfo.getStatus())) {
			throw new BusinessException("该会员卡已被使用!");
		} else if (!MemberCardStatus.ACTIVE.status.equals(cardInfo.getStatus())) {
			throw new BusinessException("会员卡无效!");
		}
		Long enterprId = cardInfo.getEnterprId();
		if (enterprId == null) {
			throw new BusinessException("会员卡信息错误,未绑定企业信息!");
		}
		SessionShopInfo sessionShopInfo = null;
		if (MemberCardBindType.LOGIN.equals(bindType)) {// 登录绑定
			sessionShopInfo = employeeUserInfoService.login(bindRequest.getLoginName(), bindRequest.getPassword(), bindRequest.getWxOpenId());
			if (sessionShopInfo == null) {
				throw new BusinessException("账号或密码错误!");
			}
		} else if (MemberCardBindType.REGISTER.equals(bindType)) {// 注册绑定
			String loginName = bindRequest.getLoginName();
			if (StringUtils.isEmpty(loginName) || !loginName.matches("[a-zA-Z]\\w{5,17}")) {
				throw new BusinessException("账号格式不正确!");
			}
			String password = bindRequest.getPassword();
			if (StringUtils.isEmpty(password) || !password.matches(".{6,16}")) {
				throw new BusinessException("密码格式不正确!");
			}
			EmployeeUserInfo employeeUserInfo = employeeUserInfoService.findByLoginName(loginName);
			if (employeeUserInfo != null) {
				throw new BusinessException("该账号已存在!");
			}
			employeeUserInfoService.registerEmployee(loginName, loginName, password, enterprId.longValue());
			sessionShopInfo = employeeUserInfoService.login(loginName, password, bindRequest.getWxOpenId());
			if (sessionShopInfo == null) {
				throw new BusinessException("账号注册失败!");
			}
		}
		Long employeeId = sessionShopInfo.getEmployeeId();
		CardStorage record = new CardStorage();
		record.setEmployeeId(sessionShopInfo.getEmployeeId());
		record.setStatus(MemberCardStatus.USED.status);
		record.setMobile(bindRequest.getMobile());
		record.setUsedTime(new Date());
		CardStorageCondition condition = new CardStorageCondition();
		condition.createCriteria().andCardNumberEqualTo(cardInfo.getCardNumber()).andStatusEqualTo(MemberCardStatus.ACTIVE.status);
		int updateCount = cardStorageMapper.updateByExampleSelective(record, condition);
		if (updateCount == 0) {
			throw new BusinessException("会员卡绑定失败,请重试!");
		}
		CardEmployeeRecorder cardEmployeeRecorder = new CardEmployeeRecorder();
		cardEmployeeRecorder.setCardNumber(cardInfo.getCardNumber());
		BigDecimal amount = BigDecimal.valueOf(cardInfo.getAmount());
		cardEmployeeRecorder.setAmount(amount);
		cardEmployeeRecorder.setEmployeeId(employeeId.intValue());
		cardEmployeeRecorder.setStatus(EmployeeCardStatus.INIT.status);
		cardEmployeeRecorderMapper.insertSelective(cardEmployeeRecorder);
		return sessionShopInfo;
	}

	@Override
	public PageResultBean<CardStorageBean> getCardlistByBatchId(Integer batchId, boolean withVerifyNumber, PageBean pageBean) {
		return this.getCardlistByBatchId(batchId, null, withVerifyNumber, pageBean);
	}

	@Override
	public PageResultBean<CardStorageBean> getCardlistByBatchId(Integer batchId, String cardNumber, boolean withVerifyNumber, PageBean pageBean) {
		Assert.notNull(batchId, "生产批次不能为空");
		CardStorageCondition example = new CardStorageCondition();
		if (cardNumber != null && !StringUtils.isEmpty(cardNumber = cardNumber.trim())) {
			example.createCriteria().andBatchIdEqualTo(batchId).andCardNumberLike("%" + cardNumber + "%");
		} else {
			example.createCriteria().andBatchIdEqualTo(batchId);
		}
		int count = (int) cardStorageMapper.countByExample(example);
		if (pageBean.getOffset() >= count) {
			return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, Collections.emptyList());
		}
		Integer pageSize = pageBean.getPageSize();
		example.setLimit(pageSize);
		example.setOffset(pageBean.getOffset());
		List<CardStorageBean> list = cardStorageMapper.selectBasicInfo(example, withVerifyNumber);
		return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, list);
	}

	// try five times if verifyNumber is conflict
	private void batchInsertCardStorage(List<CardStorage> cardStorages) {
		out: for (int j = 0; j < 5; j++) {
			try {
				cardStorageMapper.batchInsert(cardStorages);
				break;
			} catch (DuplicateKeyException e) {
				String message = e.getMessage();
				if (message.contains("unique_verifyNumber")) {
					Pattern pattern = Pattern.compile("Duplicate\\s+entry\\s+'([^']+)");
					Matcher matcher = pattern.matcher(message);
					if (matcher.find()) {
						String conflicValue = matcher.group(1);
						for (CardStorage item : cardStorages) {
							if (item.getVerifyNumber().equals(conflicValue)) {
								item.setVerifyNumber(this.generateVerifyNumber());
								continue out;
							}
						}
					}
				}
				throw e;
			}
		}
	}

	private String generateVerifyNumber() {
		String result = "";
		for (int i = 0; i < 10 && result.length() < 12; i++) {
			UUID uuid = UUID.randomUUID();
			result = (Long.toString(uuid.getMostSignificantBits(), 36) + Long.toString(uuid.getLeastSignificantBits(), 36)).replaceAll("[\\WioIO]", "");
		}
		if (result.length() < 12) {
			result = DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().getBytes());
		}
		return result.substring(0, 12).toUpperCase();
	}

	@Override
	public CardStorageStatistics getStorageStatistics(MemberCardStatus memberCardStatus) {
		return cardStorageMapper.getStorageStatistics(memberCardStatus.status);
	}

	@Override
	public CardStorageStatistics getCardStoreInfo(MemberCardStatus memberCardStatus) {
		List<CardAmountStorage> cardInfo = cardStorageMapper.getCardStoreInfo(memberCardStatus.status);
		int totalCount = 0;
		long totalAmount = 0l;
		Map<Integer, Integer> cardMap = new HashMap<>();
		for (CardAmountStorage item : cardInfo) {
			int num = item.getNum();
			int amount = item.getAmount();
			totalCount += num;
			totalAmount += amount * num;
			cardMap.put(amount, num);
		}
		List<CardAmount> cardAmounts = this.getCardAmounts();
		List<CardAmountStorage> handledCardInfo = new ArrayList<>();
		for (CardAmount item : cardAmounts) {
			Integer count = cardMap.remove(item.getAmount());
			if (count == null) {
				count = 0;
			}
			handledCardInfo.add(new CardAmountStorage(item.getAmount(), count));
		}
		if (cardMap.size() > 0) {
			for (Entry<Integer, Integer> item : cardMap.entrySet()) {
				handledCardInfo.add(new CardAmountStorage(item.getKey(), item.getValue()));
			}
		}
		CardStorageStatistics cardStorageStatistics = new CardStorageStatistics();
		cardStorageStatistics.setTotalCount(totalCount);
		cardStorageStatistics.setTotalAmount(totalAmount);
		cardStorageStatistics.setCardInfo(handledCardInfo);
		return cardStorageStatistics;
	}

	@Override
	public PageResultBean<CardBatchBean> getCardBatches(Date startTime, Date endTime, Integer status, PageBean pageBean) {
		int count = cardBatchRecorderMapper.countCardBatches(startTime, endTime, status);
		if (pageBean.getOffset() >= count) {
			return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, Collections.emptyList());
		}
		List<CardBatchBean> list = cardBatchRecorderMapper.selectCardBatches(startTime, endTime, status, pageBean);
		// 设置客户名称
		Map<Long, List<CardBatchBean>> cardBatchMap = list.stream().collect(Collectors.groupingBy(CardBatchBean::getCustomerId));
		List<EnterprUserInfo> enterprUserInfos = enterpriseService.getEnterpriseNames(new ArrayList<>(cardBatchMap.keySet()));
		if (!CollectionUtils.isEmpty(enterprUserInfos)) {
			for (EnterprUserInfo enterprUserInfo : enterprUserInfos) {
				List<CardBatchBean> cardBatchBeans = cardBatchMap.get(enterprUserInfo.getEnterprId());
				if (cardBatchBeans != null) {
					for (CardBatchBean item : cardBatchBeans) {
						item.setCutomerName(enterprUserInfo.getEnterprName());
					}
				}
			}
		}
		return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, list);
	}

	@Override
	public CardSaleOrderDetail getCardBatchInfo(Integer batchId) {
		CardBatchRecorder cardBatchRecorder = cardBatchRecorderMapper.selectByPrimaryKey(batchId);
		if (cardBatchRecorder == null) {
			throw new BusinessException("该会员卡生产批次不存在");
		}
		return cardSaleService.getSaleOrderDetail(cardBatchRecorder.getSaleOrderId());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void makingBatchCards(Integer batchId, OutputStream outputStream) throws IOException {
		CardBatchRecorder cardBatchRecorder = cardBatchRecorderMapper.selectByPrimaryKey(batchId);
		if (cardBatchRecorder == null) {
			throw new BusinessException("该会员卡生产批次不存在");
		}
		if (!MemberCardBatchStatus.INIT.status.equals(cardBatchRecorder.getStatus())) {
			throw new BusinessException("制卡资料只能下载一次!");
		}
		// 将会员卡批次状态置为制卡状态
		CardBatchRecorderCondition condition = new CardBatchRecorderCondition();
		condition.createCriteria().andBatchIdEqualTo(batchId).andStatusEqualTo(MemberCardBatchStatus.INIT.status);
		CardBatchRecorder record = new CardBatchRecorder();
		String currentUser = SessionContextHolder.getCurrentLoginName();
		record.setMakeUser(currentUser);
		record.setUpdateUser(currentUser);
		record.setMakeTime(new Date());
		record.setStatus(MemberCardBatchStatus.MAKING.status);
		int count = cardBatchRecorderMapper.updateByExampleSelective(record, condition);
		if (count == 0) {
			throw new BusinessException("制卡资料只能下载一次!");
		}
		this.exportBatchCard(batchId, outputStream, true);// 导出该批次卡片
	}

	@Override
	public void exportBatchCard(Integer batchId, OutputStream outputStream, boolean withVerifyNumber) throws IOException {
		CardStorageCondition example = new CardStorageCondition();
		example.createCriteria().andBatchIdEqualTo(batchId);
		int totalCount = (int) cardStorageMapper.countByExample(example);
		if (totalCount == 0) {
			throw new BusinessException("未找到该批次卡片信息!");
		}
		int pageSize = 1000;
		int page = (totalCount - 1) / pageSize + 1;
		CardStorageCondition cardStorageCondition = new CardStorageCondition();
		cardStorageCondition.createCriteria().andBatchIdEqualTo(batchId);
		cardStorageCondition.setLimit(pageSize);
		Class<? extends BaseRowModel> excelModelClass = withVerifyNumber ? MemberCardInfoWithVerifyExcelModel.class : MemberCardInfoExcelModel.class;
		ExcelWriter writer = EasyExcelFactory.getWriter(outputStream, ExcelTypeEnum.XLSX, true);
		Sheet sheet = new Sheet(1, 1, excelModelClass);
		sheet.setSheetName("会员卡信息");
		for (int i = 0; i < page; i++) {
			cardStorageCondition.setOffset((i * pageSize));
			List<CardStorageBean> cardStorageBeans = cardStorageMapper.selectBasicInfo(cardStorageCondition, withVerifyNumber);
			writer.write(cardStorageBeans.stream().map(item -> {
				try {
					BaseRowModel model = excelModelClass.newInstance();
					BeanUtils.copyProperties(item, model);
					return model;
				} catch (InstantiationException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}).collect(Collectors.toList()), sheet);
		}
		writer.finish();
		outputStream.flush();
	}

	@Transactional
	@Override
	public void storeCards(Integer batchId) {
		CardBatchRecorder cardBatchRecorder = cardBatchRecorderMapper.selectByPrimaryKey(batchId);
		if (cardBatchRecorder == null) {
			throw new BusinessException("该会员卡生产批次不存在");
		}
		Integer currentStatus = cardBatchRecorder.getStatus();
		if (MemberCardBatchStatus.STROED.status.equals(currentStatus)) {
			throw new BusinessException("该批次卡已入库,请勿重复操作!");
		} else if (!MemberCardBatchStatus.MAKING.status.equals(currentStatus)) {
			throw new BusinessException("只有制卡完成的批次,才可操作入库!");
		}
		// 更新会员卡批次状态
		CardBatchRecorderCondition condition = new CardBatchRecorderCondition();
		condition.createCriteria().andBatchIdEqualTo(batchId).andStatusEqualTo(MemberCardBatchStatus.MAKING.status);
		CardBatchRecorder record = new CardBatchRecorder();
		String currentUser = SessionContextHolder.getCurrentLoginName();
		record.setStoreUser(currentUser);
		record.setStoreTime(new Date());
		record.setUpdateUser(currentUser);
		record.setStatus(MemberCardBatchStatus.STROED.status);
		int count = cardBatchRecorderMapper.updateByExampleSelective(record, condition);
		if (count == 0) {
			throw new BusinessException("该批次卡已入库,请勿重复操作!");
		}
		// 将会员卡置为入库状态
		CardStorageCondition cardStorageCondition = new CardStorageCondition();
		cardStorageCondition.createCriteria().andBatchIdEqualTo(batchId);
		CardStorage cardStorageRecord = new CardStorage();
		cardStorageRecord.setStatus(MemberCardStatus.STORED.status);
		cardStorageMapper.updateByExampleSelective(cardStorageRecord, cardStorageCondition);
	}

	@Override
	public CardBatchRecorder getCardBatchBySaleOrderId(Integer saleOrderId) {
		CardBatchRecorderCondition example = new CardBatchRecorderCondition();
		example.createCriteria().andSaleOrderIdEqualTo(saleOrderId);
		List<CardBatchRecorder> sardBatchRecorders = cardBatchRecorderMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(sardBatchRecorders)) {
			return null;
		} else if (sardBatchRecorders.size() == 1) {
			return sardBatchRecorders.get(0);
		} else {
			throw new BusinessException("数据错误,一个会员卡订单对应多个批次的会员卡!");
		}
	}

	@Override
	public PageResultBean<CardStorageBean> getStoredCards(Date startTime, Date endTime, String cardNumber, PageBean pageBean) {
		CardStorageCondition example = new CardStorageCondition();
		Criteria criteria = example.createCriteria().andStatusEqualTo(MemberCardStatus.STORED.status);
		if (startTime != null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
		}
		if (endTime != null) {
			criteria.andCreateTimeLessThanOrEqualTo(endTime);
		}
		if (cardNumber != null && !StringUtils.isEmpty(cardNumber = cardNumber.trim())) {
			criteria.andCardNumberLike("%" + cardNumber + "%");
		}
		int count = (int) cardStorageMapper.countByExample(example);
		if (pageBean.getOffset() >= count) {
			return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, Collections.emptyList());
		}
		List<CardStorageBean> cardStorageBeans = cardStorageMapper.getStoredCards(startTime, endTime, cardNumber, pageBean);
		Map<Long, List<CardStorageBean>> customerMap = cardStorageBeans.stream().collect(Collectors.groupingBy(CardStorageBean::getCustomerId));
		List<EnterprUserInfo> enterprises = enterpriseService.getEnterpriseNames(new ArrayList<>(customerMap.keySet()));
		for (EnterprUserInfo item : enterprises) {
			List<CardStorageBean> beans = customerMap.get(item.getEnterprId());
			for (CardStorageBean cardStorageBean : beans) {
				cardStorageBean.setEnterprName(item.getEnterprName());
			}
		}
		return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, cardStorageBeans);
	}

	@Transactional
	@Override
	public void deliverCardsByBatchId(Integer batchId) {
		CardBatchRecorder cardBatchRecorder = cardBatchRecorderMapper.selectByPrimaryKey(batchId);
		this.deliverCards(cardBatchRecorder);
	}

	@Transactional
	@Override
	public void deliverCardsBySaleOrderId(Integer saleOrderId) {
		CardSaleOrder cardSaleOrder = cardSaleService.getSaleOrderById(saleOrderId);
		if (cardSaleOrder == null) {
			throw new BusinessException("该会员卡销售订单不存在");
		}
		if (!CardOrderStatus.VERIFY_PASSED.status.equals(cardSaleOrder.getStatus())) {
			throw new BusinessException("只有审批通过的订单才可操作发卡");
		}
		CardBatchRecorder cardBatchRecorder = this.getCardBatchBySaleOrderId(saleOrderId);
		this.deliverCards(cardBatchRecorder);
	}

	private void deliverCards(CardBatchRecorder cardBatchRecorder) {
		if (cardBatchRecorder == null) {
			throw new BusinessException("该会员卡生产批次不存在");
		}
		Integer currentStatus = cardBatchRecorder.getStatus();
		if (MemberCardBatchStatus.DELIVERED.status.equals(currentStatus)) {
			throw new BusinessException("该批次卡已发卡,请勿重复操作!");
		} else if (!MemberCardBatchStatus.STROED.status.equals(currentStatus)) {
			throw new BusinessException("只有入库的批次,才可操作发卡!");
		}
		Integer batchId = cardBatchRecorder.getBatchId();
		// 更新会员卡批次状态
		CardBatchRecorderCondition condition = new CardBatchRecorderCondition();
		condition.createCriteria().andBatchIdEqualTo(batchId).andStatusEqualTo(MemberCardBatchStatus.STROED.status);
		CardBatchRecorder record = new CardBatchRecorder();
		String currentUser = SessionContextHolder.getCurrentLoginName();
		record.setDeliverUser(currentUser);
		record.setDeliverTime(new Date());
		record.setUpdateUser(currentUser);
		record.setStatus(MemberCardBatchStatus.DELIVERED.status);
		int count = cardBatchRecorderMapper.updateByExampleSelective(record, condition);
		if (count == 0) {
			throw new BusinessException("该批次卡已发卡,请勿重复操作!");
		}
		// 将会员卡置为激活状态（可流通使用）
		CardStorageCondition cardStorageCondition = new CardStorageCondition();
		cardStorageCondition.createCriteria().andBatchIdEqualTo(batchId);
		CardStorage cardStorageRecord = new CardStorage();
		cardStorageRecord.setStatus(MemberCardStatus.ACTIVE.status);
		cardStorageMapper.updateByExampleSelective(cardStorageRecord, cardStorageCondition);
		int affectCount = cardSaleService.completeSaleOrder(cardBatchRecorder.getSaleOrderId());
		if (affectCount != 1) {
			throw new BusinessException("更新会员卡订单状态失败,请联系管理员进行处理!");
		}
	}

	@Override
	public PageResultBean<CardStorage> getCustomerCardUseInfo(Long customerId, Integer status, PageBean pageBean) {
		List<CardStorage> list;
		int count = cardStorageMapper.countCustomerCardUseInfo(customerId, status);
		if (pageBean.getOffset() >= count) {
			list = Collections.emptyList();
		} else {
			list = cardStorageMapper.selectCustomerCardUseInfo(customerId, status, pageBean);
		}
		if (pageBean.getPage() == 1) {
			CardStorageStatistics cardStorageStatistics = cardStorageMapper.statisticsCustomerStorage(customerId);
			return new PageResultBean<CardStorage>(pageBean.getPage(), pageBean.getPageSize(), count, list) {
				@SuppressWarnings("unused")
				public Long getTotalCount() {
					return cardStorageStatistics.getTotalAmount();
				}

				@SuppressWarnings("unused")
				public Long getUsedAmount() {
					return cardStorageStatistics.getUsedAmount();
				}
			};
		}
		return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, list);
	}

	@Override
	public CardStasticReport getCardStasticReport() {
		CardStasticReport cardStasticReport = new CardStasticReport();
		List<CardStasticReport> cardStasticReports = cardStorageMapper.getCardStasticReportGropuByStatus();
		long totalAmount = 0;// 制作总额
		long usedAmount = 0;// 已激活总额
		long unUsedAmoun = 0;// 未激活总额
		for (CardStasticReport item : cardStasticReports) {
			totalAmount += item.getTotalAmount();
			if (MemberCardStatus.USED.status.equals(item.getStatus())) {
				usedAmount += item.getTotalAmount();
			} else if (MemberCardStatus.ACTIVE.status.equals(item.getStatus())) {
				unUsedAmoun += item.getTotalAmount();
			}
		}
		cardStasticReport.setTotalAmount(totalAmount);
		cardStasticReport.setUsedAmount(usedAmount);
		cardStasticReport.setUnUsedAmount(unUsedAmoun);
		BigDecimal totalPayAmount = cardPayRecordMapper.getTotalPayAmount();
		cardStasticReport.setTotalPayAmount(totalPayAmount);
		return cardStasticReport;
	}

	@Override
	public List<CustomerOrderReport> getUsedAmount(List<Long> customerIds) {
		if (CollectionUtils.isEmpty(customerIds)) {
			return Collections.emptyList();
		}
		return cardStorageMapper.getUsedAmount(customerIds);
	}

	@Override
	public PageResultBean<CardStorageBean> getCardUsedInfo(MemberCardSearchBean searchBean, PageBean pageBean) {
		int count = cardStorageMapper.countBySearchBean(searchBean);
		if (pageBean.getOffset() >= count) {
			return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, Collections.emptyList());
		}
		List<CardStorageBean> list = cardStorageMapper.selectBySearchBean(searchBean, pageBean);
		List<Long> enterprIds = list.stream().flatMap(item -> Arrays.asList(item.getEnterprId(), item.getCustomerId()).stream()).filter(Objects::nonNull)
				.distinct().collect(Collectors.toList());
		List<EnterprUserInfo> enterpriseNames = enterpriseService.getEnterpriseNames(enterprIds);
		HashMap<Long, String> enterpriseNameMap = new HashMap<>();
		for (EnterprUserInfo item : enterpriseNames) {
			enterpriseNameMap.put(item.getEnterprId(), item.getEnterprName());
		}
		for (CardStorageBean item : list) {
			item.setEnterprName(enterpriseNameMap.get(item.getEnterprId()));
			item.setCustomerName(enterpriseNameMap.get(item.getCustomerId()));
		}
		return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, list);
	}
}
