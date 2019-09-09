package com.lx.benefits.dao.memberdetail.impl;

import com.lx.benefits.bean.entity.memberdetail.MemberDetail;
import com.lx.benefits.dao.memberdetail.MemberDetailDao;
import com.lx.benefits.mapper.memberdetail.MemberDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lufei on 2019-01-06 01:39.
 */
@Repository
public class MemberDetailDaoImpl implements MemberDetailDao {
    
    @Autowired
    private MemberDetailMapper memberDetailMapper;
    
    @Override
    public int batchInsert(List<MemberDetail> memberDetailList) {
        return memberDetailMapper.batchInsert(memberDetailList);
    }
}
