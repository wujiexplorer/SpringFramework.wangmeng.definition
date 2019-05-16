package com.wangmeng.test.mapper.auto;

import com.wangmeng.test.model.auto.TsysDatas;
import com.wangmeng.test.model.auto.TsysDatasExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsysDatasMapper {
    int countByExample(TsysDatasExample example);

    int deleteByExample(TsysDatasExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysDatas record);

    int insertSelective(TsysDatas record);

    List<TsysDatas> selectByExample(TsysDatasExample example);

    TsysDatas selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysDatas record, @Param("example") TsysDatasExample example);

    int updateByExample(@Param("record") TsysDatas record, @Param("example") TsysDatasExample example);

    int updateByPrimaryKeySelective(TsysDatas record);

    int updateByPrimaryKey(TsysDatas record);
}