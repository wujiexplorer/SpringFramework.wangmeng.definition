package com.lx.benefits.mapper.enterprwelcomeinfo;


import com.lx.benefits.bean.entity.enterprwelcomeinfo.EnterprWelcomeInfo;
import com.lx.benefits.bean.entity.enterprwelcomeinfo.EnterprWelcomeInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterprWelcomeInfoMapper {
    int countByExample(EnterprWelcomeInfoExample example);

    int deleteByExample(EnterprWelcomeInfoExample example);

    int deleteByPrimaryKey(Long customId);

    int insert(EnterprWelcomeInfo record);

    int insertSelective(EnterprWelcomeInfo record);

    List<EnterprWelcomeInfo> selectByExample(EnterprWelcomeInfoExample example);

    EnterprWelcomeInfo selectByPrimaryKey(Long customId);

    int updateByExampleSelective(@Param("record") EnterprWelcomeInfo record, @Param("example") EnterprWelcomeInfoExample example);

    int updateByExample(@Param("record") EnterprWelcomeInfo record, @Param("example") EnterprWelcomeInfoExample example);

    int updateByPrimaryKeySelective(EnterprWelcomeInfo record);

    int updateByPrimaryKey(EnterprWelcomeInfo record);
}