package com.lx.benefits.service.product.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.lx.benefits.bean.entity.product.ProductSetting;
import com.lx.benefits.bean.entity.product.ProductSettingCondition;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.mapper.product.ProductSettingMapper;
import com.lx.benefits.service.product.ProductSettingService;

@Service
public class ProductSettingServiceImpl implements ProductSettingService {

	@Autowired
	private ProductSettingMapper productSettingMapper;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public ProductSetting getSetting(String settingKey) {
		Assert.notNull(settingKey, "settingKey cannot be null !");
		ProductSettingCondition example = new ProductSettingCondition();
		example.createCriteria().andSettingKeyEqualTo(settingKey);
		List<ProductSetting> productSettings = productSettingMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(productSettings)) {
			return null;
		}
		return productSettings.get(0);
	}

	@Override
	public String getSettingVallue(String settingKey) {
		ProductSetting setting = this.getSetting(settingKey);
		if (setting == null) {
			return null;
		}
		return setting.getSettingValue();
	}

	@Override
	public int updateSettingValue(String settingKey, String settingValue) {
		Assert.notNull(settingKey, "settingKey cannot be null !");
		ProductSettingCondition example = new ProductSettingCondition();
		example.createCriteria().andSettingKeyEqualTo(settingKey);
		ProductSetting record = new ProductSetting();
		record.setSettingValue(settingValue);
		record.setUpdateUser(SessionContextHolder.getCurrentLoginName());
		return productSettingMapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<String> getOrerSubmitBlackEnterprId() {
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		String enterprIds = opsForValue.get(ORDER_SUBMIT_BLACK_ENTERPR);
		if (enterprIds == null) {
			synchronized (ProductSettingService.class) {
				enterprIds = opsForValue.get(ORDER_SUBMIT_BLACK_ENTERPR);
				if (enterprIds == null) {
					enterprIds = this.getSettingVallue(ProductSettingService.ORDER_SUBMIT_BLACK_ENTERPR);
					if (enterprIds == null) {
						enterprIds = "";
					}
				}
			}
		}
		opsForValue.set(ORDER_SUBMIT_BLACK_ENTERPR, enterprIds);
		if (StringUtil.isEmpty(enterprIds)) {
			return Collections.emptyList();
		}
		return Arrays.asList(enterprIds.split(","));
	}

}
