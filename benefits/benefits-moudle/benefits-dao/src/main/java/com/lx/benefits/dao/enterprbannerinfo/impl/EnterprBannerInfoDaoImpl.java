package com.lx.benefits.dao.enterprbannerinfo.impl;


import com.lx.benefits.bean.entity.enterprbannerinfo.EnterprBannerInfo;
import com.lx.benefits.bean.entity.enterprbannerinfo.EnterprBannerInfoExample;
import com.lx.benefits.dao.enterprbannerinfo.EnterprBannerInfoDao;
import com.lx.benefits.mapper.enterprbannerinfo.EnterprBannerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/18.
 */
@Service
public class EnterprBannerInfoDaoImpl implements EnterprBannerInfoDao {

    @Autowired
    private EnterprBannerInfoMapper enterprBannerInfoMapper;

    @Override
    public Long insert(EnterprBannerInfo enterprBannerInfo) {
        enterprBannerInfo.checkBeforeInsert();
        int rowCount = enterprBannerInfoMapper.insertSelective(enterprBannerInfo);
        if (rowCount > 0) {
            return  enterprBannerInfo.getBannerId();
        }
        return null;
    }

    @Override
    public Integer updateByPrimaryKeySelective(EnterprBannerInfo enterprBannerInfo) {
        return enterprBannerInfoMapper.updateByPrimaryKeySelective(enterprBannerInfo);
    }

    @Override
    public List<EnterprBannerInfo> find(EnterprBannerInfoExample example) {
        return enterprBannerInfoMapper.selectByExample(example);
    }

    @Override
    public Integer count(EnterprBannerInfoExample example) {
        return enterprBannerInfoMapper.countByExample(example);
    }
}
