package com.lx.benefits.dao.membercenterinfo;



import com.lx.benefits.bean.entity.membercenterinfo.MemberCenterInfo;
import com.lx.benefits.bean.entity.membercenterinfo.MemberCenterInfoExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface MemberCenterInfoDao {

    Long insert(MemberCenterInfo memberCenterInfo);
    
    int batchInsert(List<MemberCenterInfo> memberCenterInfoList);


    Integer update(MemberCenterInfo memberCenterInfo, MemberCenterInfoExample example);

    List<MemberCenterInfo> find(MemberCenterInfoExample example);


    Integer count(MemberCenterInfoExample example);


}
