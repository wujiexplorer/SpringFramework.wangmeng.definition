package com.lx.benefits.mapper.enterprmoduleinfo;


import com.lx.benefits.bean.entity.enterprmoduleinfo.EnterprModuleInfo;
import com.lx.benefits.bean.entity.enterprmoduleinfo.EnterprModuleInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterprModuleInfoMapper {
    int countByExample(EnterprModuleInfoExample example);

    int deleteByExample(EnterprModuleInfoExample example);

    int deleteByPrimaryKey(Long moduleId);

    int insert(EnterprModuleInfo record);

    int insertSelective(EnterprModuleInfo record);

    List<EnterprModuleInfo> selectByExample(EnterprModuleInfoExample example);

    EnterprModuleInfo selectByPrimaryKey(Long moduleId);

    int updateByExampleSelective(@Param("record") EnterprModuleInfo record, @Param("example") EnterprModuleInfoExample example);

    int updateByExample(@Param("record") EnterprModuleInfo record, @Param("example") EnterprModuleInfoExample example);

    int updateByPrimaryKeySelective(EnterprModuleInfo record);

    int updateByPrimaryKey(EnterprModuleInfo record);
}