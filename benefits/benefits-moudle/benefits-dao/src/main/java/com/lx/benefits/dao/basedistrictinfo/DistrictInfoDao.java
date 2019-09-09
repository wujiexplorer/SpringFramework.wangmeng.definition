package com.lx.benefits.dao.basedistrictinfo;


import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfoExample;

import java.util.List;

public interface DistrictInfoDao {

    List<DistrictInfo> find(DistrictInfoExample example);

    DistrictInfo findByName(DistrictInfoExample example);

}
