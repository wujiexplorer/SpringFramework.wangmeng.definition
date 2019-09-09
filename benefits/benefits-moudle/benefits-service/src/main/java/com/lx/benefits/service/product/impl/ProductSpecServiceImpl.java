package com.lx.benefits.service.product.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lx.benefits.bean.entity.product.ProductSpec;
import com.lx.benefits.bean.entity.product.ProductSpecCondition;
import com.lx.benefits.bean.entity.product.ProductSpecCondition.Criteria;
import com.lx.benefits.mapper.product.ProductSpecMapper;
import com.lx.benefits.service.product.ProductSpecService;

@Service
public class ProductSpecServiceImpl implements ProductSpecService {

	@Autowired
	private ProductSpecMapper productSpecMapper;

	@Override
	public List<ProductSpec> getBaseProductSpecName(Integer supplierId) {
		ProductSpecCondition productSpecCondition = new ProductSpecCondition();
		if (supplierId == null) {
			productSpecCondition.createCriteria().andSupplierIdEqualTo(0);
		} else {
			productSpecCondition.createCriteria().andSupplierIdIn(Arrays.asList(0, supplierId));
		}
		productSpecCondition.setOrderByClause("spec_id ASC");
		return productSpecMapper.selectByExample(productSpecCondition);
	}

	@Override
	public ProductSpec getProductSpecByName(String specName, Integer supplierId) {
		ProductSpecCondition productSpecCondition = new ProductSpecCondition();
		Criteria criteria = productSpecCondition.createCriteria().andSpecNameEqualTo(specName);
		if (supplierId == null) {
			criteria.andSupplierIdEqualTo(0);
		} else {
			criteria.andSupplierIdIn(Arrays.asList(0, supplierId));
		}
		List<ProductSpec> productSpecs = productSpecMapper.selectByExample(productSpecCondition);
		if (CollectionUtils.isEmpty(productSpecs)) {
			return null;
		}
		for (ProductSpec item : productSpecs) {
			if (item.getSupplierId().equals(0)) {
				return item;
			}
		}
		return productSpecs.get(0);
	}

	@Override
	public ProductSpec addtProductSpecByName(String specName, Integer supplierId) {
		ProductSpec record = new ProductSpec();
		record.setSpecName(specName);
		record.setSupplierId(supplierId);
		try {
			productSpecMapper.insertSelective(record);
		} catch (DuplicateKeyException e) {
			return this.getProductSpecByName(specName, supplierId);
		}
		return record;
	}
}
