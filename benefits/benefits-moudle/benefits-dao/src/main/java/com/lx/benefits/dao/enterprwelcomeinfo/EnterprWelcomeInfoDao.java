package com.lx.benefits.dao.enterprwelcomeinfo;

import com.lx.benefits.bean.entity.enterprwelcomeinfo.EnterprWelcomeInfo;
import com.lx.benefits.bean.entity.enterprwelcomeinfo.EnterprWelcomeInfoExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface EnterprWelcomeInfoDao {

    Long insert(EnterprWelcomeInfo enterprWelcomeInfo);


    Integer update(EnterprWelcomeInfo enterprWelcomeInfo, EnterprWelcomeInfoExample example);

    List<EnterprWelcomeInfo> find(EnterprWelcomeInfoExample example);


    Integer count(EnterprWelcomeInfoExample example);


}
