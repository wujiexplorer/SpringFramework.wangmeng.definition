package com.lx.benefits.service.user.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.user.UserCollection;
import com.lx.benefits.bean.entity.user.UserCollectionCondition;
import com.lx.benefits.mapper.user.UserCollectionMapper;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.user.UserCollectionService;

@Service("userCollectionService")
public class UserCollectionServiceImpl implements UserCollectionService {

	@Autowired
	private UserCollectionMapper userCollectionMapper;
	@Autowired
	private ProductService productService;

	@Override
	public PageResultBean<ProductEntity> listByUserId(Long userId, PageBean pageBean) {
		Assert.notNull(userId, "用户信息不能为空!");
		PageResultBean<ProductEntity> pageResult = new PageResultBean<>();
		int count = userCollectionMapper.countProducts(userId);
		pageResult.setCount(count);
		pageResult.setPageInfo(pageBean);
		if (count == 0 || pageBean.getOffset() >= count) {
			pageResult.setList(Collections.emptyList());
		} else {
			pageResult.setList(userCollectionMapper.selectProducts(userId, pageBean));
		}
		return pageResult;
	}

	@Override
	public int addUserCollection(Long userId, Long spuCode) {
		Assert.notNull(userId, "用户信息不能为空!");
		if (this.checkExist(userId, spuCode)) {
			return 0;
		}
		ProductEntity basicInfo = productService.getBasicInfo(spuCode);
		if (basicInfo == null) {
			throw new BusinessException("该商品不存在!");
		}
		UserCollection record = new UserCollection();
		record.setUserId(userId);
		record.setSpuCode(spuCode);
		try {
			return userCollectionMapper.insertSelective(record);
		} catch (DuplicateKeyException e) {
		}
		return 0;
	}

	@Override
	public int deleteUserCollection(Long userId, List<Long> spuCodeList) {
		Assert.notNull(userId, "用户信息不能为空!");
		if (CollectionUtils.isEmpty(spuCodeList)) {
			return 0;
		}
		UserCollectionCondition example = new UserCollectionCondition();
		example.createCriteria().andUserIdEqualTo(userId).andSpuCodeIn(spuCodeList);
		return userCollectionMapper.deleteByExample(example);
	}

	@Override
	public boolean checkExist(Long accountId, Long spuCode) {
		UserCollectionCondition example = new UserCollectionCondition();
		example.createCriteria().andUserIdEqualTo(accountId).andSpuCodeEqualTo(spuCode);
		long count = userCollectionMapper.countByExample(example);
		return count > 0;
	}

}
