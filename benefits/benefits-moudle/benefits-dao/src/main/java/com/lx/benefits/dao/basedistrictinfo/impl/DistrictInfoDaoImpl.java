package com.lx.benefits.dao.basedistrictinfo.impl;


import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfoExample;
import com.lx.benefits.dao.basedistrictinfo.DistrictInfoDao;
import com.lx.benefits.mapper.basedistrictinfo.DistrictInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分操作记录
 *
 * @author luojie
 */
@Repository
public class DistrictInfoDaoImpl implements DistrictInfoDao {

    @Autowired
    private DistrictInfoMapper districtInfoMapper;


    @Override
    public List<DistrictInfo> find(DistrictInfoExample example) {
        return districtInfoMapper.selectByExample(example);
    }


    @Override
    public DistrictInfo findByName(DistrictInfoExample example) {
        List<DistrictInfo> list = districtInfoMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
