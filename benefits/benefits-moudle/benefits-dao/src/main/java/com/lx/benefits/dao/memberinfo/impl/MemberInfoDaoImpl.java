package com.lx.benefits.dao.memberinfo.impl;


import com.lx.benefits.bean.entity.memberinfo.MemberInfo;
import com.lx.benefits.bean.entity.memberinfo.MemberInfoExample;
import com.lx.benefits.dao.memberinfo.MemberInfoDao;
import com.lx.benefits.mapper.memberinfo.MemberInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author unknow on 2019-01-01 19:29.
 */
@Repository
public class MemberInfoDaoImpl implements MemberInfoDao {
    
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    
    @Override
    public int insertSelective(MemberInfo record) {
        return memberInfoMapper.insertSelective(record);
    }

    @Override
    public int batchInsert(List<MemberInfo> memberInfoList) {
        return memberInfoMapper.batchInsert(memberInfoList);
    }

    @Override
    public List<MemberInfo> selectByExample(MemberInfoExample example) {
        return memberInfoMapper.selectByExample(example);
    }

    @Override
    public MemberInfo selectByPrimaryKey(Long id) {
        return memberInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberInfo record) {
        return memberInfoMapper.updateByPrimaryKeySelective(record);
    }
}
