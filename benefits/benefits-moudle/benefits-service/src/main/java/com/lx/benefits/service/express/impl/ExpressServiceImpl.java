package com.lx.benefits.service.express.impl;

import com.lx.benefits.mapper.express.ExpressInfoMapper;
import com.lx.benefits.mapper.express.entity.ExpressInfo;
import com.lx.benefits.mapper.express.entity.ExpressInfoExample;
import com.lx.benefits.service.express.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author by yingcai on 2018/12/18.
 */
@Service
public class ExpressServiceImpl implements ExpressService {

	@Autowired
	private ExpressInfoMapper expressInfoMapper;

	@Override
	public List<ExpressInfo> selectByExample(ExpressInfoExample example) {
		return expressInfoMapper.selectByExample(example);
	}

	@Override
	public ExpressInfo getExpressInfoByName(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		ExpressInfoExample example = new ExpressInfoExample();
		example.createCriteria().andNameEqualTo(name);
		List<ExpressInfo> expressInfos = expressInfoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(expressInfos)) {
			return null;
		}
		return expressInfos.get(0);
	}
}
