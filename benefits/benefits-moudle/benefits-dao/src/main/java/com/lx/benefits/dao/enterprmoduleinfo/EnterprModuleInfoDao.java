package com.lx.benefits.dao.enterprmoduleinfo;

import com.lx.benefits.bean.entity.enterprmoduleinfo.EnterprModuleInfo;
import com.lx.benefits.bean.entity.enterprmoduleinfo.EnterprModuleInfoExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface EnterprModuleInfoDao {

    Long insert(EnterprModuleInfo enterprModuleInfo);

    Integer updateByPrimaryKeySelective(EnterprModuleInfo record);

    List<EnterprModuleInfo> find(EnterprModuleInfoExample example);

    Integer count(EnterprModuleInfoExample example);

}
