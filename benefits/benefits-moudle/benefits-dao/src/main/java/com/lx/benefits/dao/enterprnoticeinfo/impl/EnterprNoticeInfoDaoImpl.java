package com.lx.benefits.dao.enterprnoticeinfo.impl;

import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfo;
import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfoExample;
import com.lx.benefits.dao.enterprnoticeinfo.EnterprNoticeInfoDao;
import com.lx.benefits.mapper.enterprnoticeinfo.EnterprNoticeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/18.
 */
@Service
public class EnterprNoticeInfoDaoImpl implements EnterprNoticeInfoDao {

    @Autowired
    private EnterprNoticeInfoMapper enterprNoticeInfoMapper;

    @Override
    public Long insert(EnterprNoticeInfo enterprNoticeInfo) {
        int rowCount = enterprNoticeInfoMapper.insertSelective(enterprNoticeInfo);
        if (rowCount > 0) {
            return enterprNoticeInfo.getId();
        }
        return null;
    }

    @Override
    public Integer update(EnterprNoticeInfo enterprNoticeInfo) {
        return enterprNoticeInfoMapper.updateByPrimaryKeySelective(enterprNoticeInfo);
    }

    @Override
    public List<EnterprNoticeInfo> find(EnterprNoticeInfoExample example) {
        return enterprNoticeInfoMapper.selectByExample(example);
    }

    @Override
    public Integer count(EnterprNoticeInfoExample example) {
        return enterprNoticeInfoMapper.countByExample(example);
    }
}
