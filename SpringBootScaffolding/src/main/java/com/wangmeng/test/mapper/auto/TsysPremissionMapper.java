package com.wangmeng.test.mapper.auto;

import com.wangmeng.test.model.auto.TsysPremission;
import com.wangmeng.test.model.auto.TsysPremissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsysPremissionMapper {
    long countByExample(TsysPremissionExample example);

    int deleteByExample(TsysPremissionExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysPremission record);

    int insertSelective(TsysPremission record);

    List<TsysPremission> selectByExample(TsysPremissionExample example);

    TsysPremission selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysPremission record, @Param("example") TsysPremissionExample example);

    int updateByExample(@Param("record") TsysPremission record, @Param("example") TsysPremissionExample example);

    int updateByPrimaryKeySelective(TsysPremission record);

    int updateByPrimaryKey(TsysPremission record);
}