package com.lx.benefits.service.cardkey.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.CollectionUtils;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.ImportReqDto;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorage;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorageExample;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorageExample.Criteria;
import com.lx.benefits.bean.enums.CardKeyStatusEnum;
import com.lx.benefits.bean.util.EasyExcelUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionFuliInfo;
import com.lx.benefits.bean.vo.cardkey.CardKeyExcel;
import com.lx.benefits.bean.vo.cardkey.CardKeyVO;
import com.lx.benefits.mapper.cardkey.CardKeyStorageMapper;
import com.lx.benefits.mapper.product.SkuMapper;
import com.lx.benefits.service.cardkey.CardKeyService;
@Service
public class CardKeyServiceImpl implements CardKeyService{
	@Autowired
	private CardKeyStorageMapper cardKeyStorageMapper;
	
	@Autowired
	private SkuMapper skuMapper;
	
	@Override
	public PageResultBean<CardKeyStorage> cardKeyList(Integer sku, CardKeyVO cardKeyVO, PageBean pageBean) {
		CardKeyStorageExample example = new CardKeyStorageExample();
		Criteria createCriteria = example.createCriteria();
		if(cardKeyVO.getOrderNumber() != null) {
			createCriteria.andOrderNumberEqualTo(cardKeyVO.getOrderNumber());
			createCriteria.andStatusEqualTo(CardKeyStatusEnum.COMPLETED.status);
		}
		if(sku != null) {
			createCriteria.andSkuEqualTo(sku);
		}
		if (cardKeyVO.getStoreStartTime() != null) {
			createCriteria.andStoreTimeGreaterThanOrEqualTo(cardKeyVO.getStoreStartTime());
		}
		if (cardKeyVO.getStoreEndTime() != null) {
			createCriteria.andStoreTimeLessThanOrEqualTo(cardKeyVO.getStoreEndTime());
		}
		if (cardKeyVO.getDeliverStartTime() != null) {
			createCriteria.andDeliverTimeGreaterThanOrEqualTo(cardKeyVO.getDeliverStartTime());
		}
		if (cardKeyVO.getDeliverEndTime() != null) {
			createCriteria.andDeliverTimeLessThanOrEqualTo(cardKeyVO.getDeliverEndTime());
		}
		if (cardKeyVO.getDeadStartTime() != null) {
			createCriteria.andDeadTimeGreaterThanOrEqualTo(cardKeyVO.getDeadStartTime());
		}
		if (cardKeyVO.getDeadEndTime() != null) {
			createCriteria.andDeadTimeLessThanOrEqualTo(cardKeyVO.getDeadEndTime());
		}
		if (cardKeyVO.getStatus() != null) {
			createCriteria.andStatusEqualTo(cardKeyVO.getStatus());
		}
		int count = (int)cardKeyStorageMapper.countByExample(example );
		int offset = pageBean.getOffset();
		List<CardKeyStorage> list;
		if (count == 0 || offset >= count) {
			list = Collections.emptyList();
		} else {
			example.setOffset(offset);
			example.setLimit(pageBean.getPageSize());
			example.setOrderByClause("id desc");
			list = cardKeyStorageMapper.selectByExample(example);
		}
		return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, list);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Object store(Integer sku, ImportReqDto reqDto) {
		if (null == reqDto || null == reqDto.getFilePath() || reqDto.getFilePath().isEmpty()) {
			return Response.fail("卡密Excel表格链接不能为空");
		}
		String filePath = reqDto.getFilePath();
		
		// 1. 解析文件中的卡密信息
		List<Object> cardKeyLists;
		try {
			UrlResource resource = new UrlResource(filePath);
			String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
			ExcelTypeEnum excelTypeEnum = "xlsx".equalsIgnoreCase(suffix) ? ExcelTypeEnum.XLSX : ExcelTypeEnum.XLS;
			cardKeyLists = EasyExcelUtil.readExcelWithModel(resource.getInputStream(), CardKeyExcel.class, excelTypeEnum);
		} catch (Exception e) {
			return Response.fail("卡密文件解析出错!");
		}
		if (CollectionUtils.isEmpty(cardKeyLists)) {
			return Response.fail("excel表格数据为空");
		}
		
		// 2. excel表格信息组装&数据验证
		List<CardKeyStorage> cardKeyStorageLists = new ArrayList<>();
		int line = 2;
		for (Object cardKeyList : cardKeyLists) {
			CardKeyExcel cardKeyStorage = (CardKeyExcel)cardKeyList;
			String cardNum = cardKeyStorage.getCardNum();
			String password = cardKeyStorage.getPassword();
			Date deadTime = cardKeyStorage.getDeadTime();
			BigDecimal costPrice = cardKeyStorage.getCostPrice();
			if (null == cardNum && null == password && null == deadTime && null == costPrice) {
				continue;
			}
			if(null == cardNum || cardNum.trim().isEmpty()) {
				return Response.fail(String.format("第 %s 行卡号信息不能为空!", line));
			}
			if(cardNum.trim().length() > 500) {
				return Response.fail(String.format("第 %s 行卡号信息不能超过500!", line));
			}
			if(null == password || password.trim().isEmpty()) {
				return Response.fail(String.format("第 %s 行密码信息不能为空!", line));
			}
			if(password.trim().length() > 500) {
				return Response.fail(String.format("第 %s 行密码信息不能超过500!", line));
			}
			if(null == deadTime) {
				return Response.fail(String.format("第 %s 行截止日期不能为空或者日期格式不正确!", line));
			}
			LocalDate localDate = LocalDate.now();
			ZoneId zone = ZoneId.systemDefault();
			Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
			if(deadTime.before(Date.from(instant))) {
				return Response.fail(String.format("第 %s 行截止日期不能小于当前日期!", line));
			}
			if(null == costPrice) {
				return Response.fail(String.format("第 %s 行成本价不能为空!", line));
			}
			if(BigDecimal.ZERO.compareTo(costPrice)>0) {
				return Response.fail(String.format("第 %s 行成本价不能为负!", line));
			}
			CardKeyStorage cardKey = new CardKeyStorage();
			cardKey.setSku(sku);
			cardKey.setCardNumber(cardNum.trim());
			cardKey.setPassword(password.trim());
			cardKey.setDeadTime(deadTime);
			cardKey.setGoodsCostprice(costPrice);
			cardKey.setStatus(CardKeyStatusEnum.STORED.status);
			cardKeyStorageLists.add(cardKey);
			line++;
		}
		int num = cardKeyStorageMapper.insertRecord(cardKeyStorageLists);
		//sku增加库存
		int addStock = skuMapper.addStock(Long.valueOf(sku.toString()), num);
		if(cardKeyStorageLists.size() == num && addStock == 1) {
			return Response.succ("入库成功");
		}else {
			throw new BusinessException("入库失败！");
		}
		//return Response.fail("入库失败！");
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Object invalid(Integer id, CardKeyStorage cardKeyStorage) {
		SessionFuliInfo sessionFuliInfo = SessionContextHolder.getSessionFuliInfo();
		String loginName = sessionFuliInfo.getLoginName();
		if(StringUtils.isBlank(loginName)) {
			return Response.fail("请先登陆！");
		}
		CardKeyStorage cardKey = cardKeyStorageMapper.selectByPrimaryKey(id);
		if(CardKeyStatusEnum.DELIVERED.status.equals(cardKey.getStatus()) || CardKeyStatusEnum.COMPLETED.status.equals(cardKey.getStatus())) {
			return Response.fail("已发货或已完成的卡不能作废！");
		}
		
		CardKeyStorage record = new CardKeyStorage();
		record.setStatus(CardKeyStatusEnum.EXPIRED.status);
		record.setId(id);
		String remark = cardKeyStorage.getRemark();
		if(!StringUtils.isBlank(remark)) {
			record.setRemark(remark.trim());
		}
		int num = cardKeyStorageMapper.updateByPrimaryKeySelective(record);
		//sku减少库存
		int reduceVirtualStock = skuMapper.reduceVirtualStock(Long.valueOf(cardKey.getSku().toString()), num);
		if(num == 1 && reduceVirtualStock == 1) {
			return Response.succ("作废成功");
		}else {
			throw new BusinessException("作废失败！");
		}
		//return Response.fail("作废失败！");
	}
	@Override
	public List<Long> listItemOrderNumbers(Long buyerUserId, Integer startRecord, Integer pageSize) {
		List<Long> listItemOrderNumbers = cardKeyStorageMapper.listItemOrderNumbers(buyerUserId, startRecord, pageSize);
		return listItemOrderNumbers;
	}
	@Override
	public int countOrderNumber(Long buyerUserId) {
		int countOrderNumber = cardKeyStorageMapper.countOrderNumber(buyerUserId);
		return countOrderNumber;
	}
	@Override
	public List<Long> getItemOrderNumbers(Long buyerUserId, List<Long> sellerOrders) {
		
		return cardKeyStorageMapper.getItemOrderNumbers(buyerUserId, sellerOrders);
	}

}
