package com.lx.benefits.mapper.express;


import com.lx.benefits.mapper.express.entity.ExpressInfo;
import com.lx.benefits.mapper.express.entity.ExpressInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpressInfoMapper {
    int countByExample(ExpressInfoExample example);

    int deleteByExample(ExpressInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExpressInfo record);

    int insertSelective(ExpressInfo record);

    List<ExpressInfo> selectByExample(ExpressInfoExample example);

    ExpressInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExpressInfo record, @Param("example") ExpressInfoExample example);

    int updateByExample(@Param("record") ExpressInfo record, @Param("example") ExpressInfoExample example);

    int updateByPrimaryKeySelective(ExpressInfo record);

    int updateByPrimaryKey(ExpressInfo record);

    List<ExpressInfo> queryByParam(Map<String,Object> query);
}