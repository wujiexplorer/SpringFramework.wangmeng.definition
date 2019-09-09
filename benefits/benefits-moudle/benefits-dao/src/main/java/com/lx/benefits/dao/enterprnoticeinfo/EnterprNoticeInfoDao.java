package com.lx.benefits.dao.enterprnoticeinfo;


import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfo;
import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfoExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface EnterprNoticeInfoDao {

    Long insert(EnterprNoticeInfo enterprWelcomeInfo);

    Integer update(EnterprNoticeInfo enterprWelcomeInfo);

    List<EnterprNoticeInfo> find(EnterprNoticeInfoExample example);

    Integer count(EnterprNoticeInfoExample example);

}
