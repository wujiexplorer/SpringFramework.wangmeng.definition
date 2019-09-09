package com.lx.benefits.service.user;

import java.util.List;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.product.ProductEntity;

public interface UserCollectionService {

	PageResultBean<ProductEntity> listByUserId(Long userId, PageBean pageBean);

	int addUserCollection(Long userId, Long spuCode);

	int deleteUserCollection(Long userId, List<Long> spuCodeList);

	boolean checkExist(Long accountId, Long spuCode);
}
