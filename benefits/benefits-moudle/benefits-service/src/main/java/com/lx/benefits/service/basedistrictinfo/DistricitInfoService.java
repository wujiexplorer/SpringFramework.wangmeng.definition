package com.lx.benefits.service.basedistrictinfo;


import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfoEntity;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfoExample;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.vo.order.JDAddress;

import java.util.List;
import java.util.Map;


public interface DistricitInfoService {

    List<DistrictInfo> find(DistrictInfoExample example);

    DistrictInfo findByName(DistrictInfoExample example);

    List<DistrictInfo> queryByParam(Map<String,Object> params);

    List<DistrictInfo> queryChildren(Long id);

    DistrictInfoEntity getById(Integer id);

    List<DistrictInfo> selectCacheList(DistrictInfoExample example);

	JDAddress getJdAddress(ConsigneeAdress userReceiveAddress);
	
	JDAddress getJdAddress(Long userReceiveAddrId);
}