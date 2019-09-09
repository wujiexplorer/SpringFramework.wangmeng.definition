package com.lx.benefits.task;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.apollo.common.enums.base.DatePatternEnum;
import com.apollo.common.utils.base.DateUtil;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorage;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorageExample;
import com.lx.benefits.bean.enums.CardKeyStatusEnum;
import com.lx.benefits.bean.vo.cardkey.CardKeyInfo;
import com.lx.benefits.mapper.cardkey.CardKeyStorageMapper;
import com.lx.benefits.mapper.product.SkuMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CardKeyTask {
	@Autowired
	private CardKeyStorageMapper cardKeyStorageMapper;
	
	@Autowired
	private SkuMapper skuMapper;
	
	@Scheduled(cron = "${cardKey.cron}")
	@Transactional(rollbackFor = Exception.class)
	public void modifyCardKeyStatus() {
		log.info("更新过期卡密状态，任务开始时间：" + DateUtil.getDate(new Date(), DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
		LocalDate today = LocalDate.now();
		
		CardKeyStorage record = new CardKeyStorage();
		record.setStatus(CardKeyStatusEnum.EXPIRED.status);
		
		CardKeyStorageExample example = new CardKeyStorageExample();
		example.createCriteria().andDeadTimeLessThan(today.toDate()).andStatusEqualTo(CardKeyStatusEnum.STORED.status);
		
		List<CardKeyInfo> selectByExample = cardKeyStorageMapper.selectSkuNum(today.toString());
		for (int i = 0; i < selectByExample.size(); i += 100) {
			List<CardKeyInfo> skuLists = selectByExample.stream().skip(i).limit(100).collect(Collectors.toList());
			skuMapper.bathReduceVirtualStock(skuLists);
		}
		cardKeyStorageMapper.updateByExampleSelective(record, example);
		
		log.info("更新过期卡密状态，任务结束时间：" + DateUtil.getDate(new Date(), DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
	}

}
