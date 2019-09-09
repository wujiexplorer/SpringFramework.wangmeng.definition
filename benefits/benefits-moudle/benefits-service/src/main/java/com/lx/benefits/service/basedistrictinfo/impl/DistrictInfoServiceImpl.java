package com.lx.benefits.service.basedistrictinfo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.constants.RedisCacheKeyConstant;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfoEntity;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfoExample;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.vo.order.JDAddress;
import com.lx.benefits.dao.basedistrictinfo.DistrictInfoDao;
import com.lx.benefits.mapper.basedistrictinfo.DistrictInfoMapper;
import com.lx.benefits.mapper.memconsigneeaddress.ConsigneeAdressMapper;
import com.lx.benefits.service.basedistrictinfo.DistricitInfoService;
import com.lx.benefits.utils.RedisUtils;

@Service
public class DistrictInfoServiceImpl implements DistricitInfoService {

	private final static Logger logger = LoggerFactory.getLogger(DistrictInfoServiceImpl.class);

	@Autowired
	private DistrictInfoDao districtInfoDao;

	@Autowired
	private DistrictInfoMapper districtInfoMapper;
	@Autowired
	private ConsigneeAdressMapper consigneeAdressMapper;

	@Autowired
	private RedisUtils redisUtils;

	@Override
	public List<DistrictInfo> find(DistrictInfoExample example) {
		return districtInfoDao.find(example);
	}

	@Override
	public DistrictInfo findByName(DistrictInfoExample example) {
		return districtInfoDao.findByName(example);
	}

	@Override
	public List<DistrictInfo> queryByParam(Map<String, Object> params) {
		return districtInfoMapper.queryByParam(params);
	}

	@Override
	public List<DistrictInfo> queryChildren(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isDelete", 0);
		params.put("parentId", id);
		return districtInfoMapper.queryByParam(params);
	}

	@Override
	public DistrictInfoEntity getById(Integer id) {
		return districtInfoMapper.selectById(id);
	}

	@Override
	public List<DistrictInfo> selectCacheList(DistrictInfoExample example) {
		try {
			List<DistrictInfo> list = JSON.parseObject(redisUtils.get(RedisCacheKeyConstant.PlACE_CACHE_KEY), new TypeReference<List<DistrictInfo>>() {
			});
			if (list != null) {
				return list;
			}
		} catch (Exception e) {
			logger.error("获取产地缓存异常{}", e.getMessage());
		}
		List<DistrictInfo> disList = districtInfoMapper.selectByExample(example);
		redisUtils.set(RedisCacheKeyConstant.PlACE_CACHE_KEY, disList);
		return disList;
	}

	@Override
	public JDAddress getJdAddress(ConsigneeAdress userReceiveAddress) {
		Long provinceId = userReceiveAddress.getProvinceid();
		Long districtId = userReceiveAddress.getDistrictid();
		Long cityId = userReceiveAddress.getCityid();
		Long townId = userReceiveAddress.getStreetid();
		ArrayList<Long> ids = new ArrayList<>();
		ids.add(provinceId);
		ids.add(districtId);
		ids.add(cityId);
		if (townId != null && townId > 0) {
			ids.add(townId);
		}
		List<DistrictInfoEntity> districtInfos = districtInfoMapper.getByIds(ids);
		Map<Long, Long> jdAddressMap = new HashMap<>();
		for (DistrictInfoEntity districtInfo : districtInfos) {
			jdAddressMap.put(districtInfo.getId(), districtInfo.getJdAreaId());
		}
		JDAddress jdAddress = new JDAddress();
		jdAddress.setProvinceId(jdAddressMap.get(provinceId));
		jdAddress.setCityId(jdAddressMap.get(cityId));
		jdAddress.setCountyId(jdAddressMap.get(districtId));
		if (townId != null && townId > 0) {
			Long jdTownId = jdAddressMap.get(townId);
			jdAddress.setTownId(jdTownId == null ? 0 : jdTownId);
		}
		return jdAddress;
	}

	@Override
	public JDAddress getJdAddress(Long userReceiveAddrId) {
		ConsigneeAdress consigneeAdress = consigneeAdressMapper.selectAddressById(userReceiveAddrId);
		if (consigneeAdress == null) {
			return null;
		}
		return this.getJdAddress(consigneeAdress);
	}
}
