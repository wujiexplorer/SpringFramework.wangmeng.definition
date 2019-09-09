package com.lx.benefits.dao.memberinfo;


import com.lx.benefits.bean.entity.memberinfo.MemberInfo;
import com.lx.benefits.bean.entity.memberinfo.MemberInfoExample;

import java.util.List;

/**
 * @author unknow on 2019-01-01 19:28.
 */
public interface MemberInfoDao {

    int insertSelective(MemberInfo record);
    
    int batchInsert(List<MemberInfo> memberInfoList);

    List<MemberInfo> selectByExample(MemberInfoExample example);

    MemberInfo selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(MemberInfo record);

}