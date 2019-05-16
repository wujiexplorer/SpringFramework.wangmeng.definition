package com.wangmeng.test.mapper.auto;

import com.wangmeng.test.model.auto.TsysFileData;
import com.wangmeng.test.model.auto.TsysFileDataExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsysFileDataMapper {
    int countByExample(TsysFileDataExample example);

    int deleteByExample(TsysFileDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysFileData record);

    int insertSelective(TsysFileData record);

    List<TsysFileData> selectByExample(TsysFileDataExample example);

    TsysFileData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysFileData record, @Param("example") TsysFileDataExample example);

    int updateByExample(@Param("record") TsysFileData record, @Param("example") TsysFileDataExample example);

    int updateByPrimaryKeySelective(TsysFileData record);

    int updateByPrimaryKey(TsysFileData record);
}