package com.lx.benefits.dao.membercenterinfo.impl;


import com.lx.benefits.bean.entity.membercenterinfo.MemberCenterInfo;
import com.lx.benefits.bean.entity.membercenterinfo.MemberCenterInfoExample;
import com.lx.benefits.dao.membercenterinfo.MemberCenterInfoDao;
import com.lx.benefits.mapper.membercenterinfo.MemberCenterInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by jiuru on 2019/1/2.
 */
@Repository
public class MemberCenterInfoDaoImpl implements MemberCenterInfoDao {

    @Autowired
    private MemberCenterInfoMapper memberCenterInfoMapper;

    @Override
    public Long insert(MemberCenterInfo memberCenterInfo) {
        int rowCount = memberCenterInfoMapper.insertSelective(memberCenterInfo);
        if (rowCount == 1) {
            return memberCenterInfo.getId();
        }
        return null;
    }

    @Override
    public int batchInsert(List<MemberCenterInfo> memberCenterInfoList) {
        return memberCenterInfoMapper.batchInsert(memberCenterInfoList);
    }

    @Override
    public Integer update(MemberCenterInfo memberCenterInfo, MemberCenterInfoExample example) {
        return memberCenterInfoMapper.updateByExampleSelective(memberCenterInfo, example);
    }

    @Override
    public List<MemberCenterInfo> find(MemberCenterInfoExample example) {
        return memberCenterInfoMapper.selectByExample(example);
    }

    @Override
    public Integer count(MemberCenterInfoExample example) {
        return memberCenterInfoMapper.countByExample(example);
    }
}
