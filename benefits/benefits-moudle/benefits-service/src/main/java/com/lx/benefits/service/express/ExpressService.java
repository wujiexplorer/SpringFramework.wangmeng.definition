package com.lx.benefits.service.express;

import com.lx.benefits.mapper.express.entity.ExpressInfo;
import com.lx.benefits.mapper.express.entity.ExpressInfoExample;

import java.util.List;

/**
 * @author by yingcai on 2018/12/18.
 */
public interface ExpressService {
	List<ExpressInfo> selectByExample(ExpressInfoExample example);

	ExpressInfo getExpressInfoByName(String name);
}
