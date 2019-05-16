package com.wangmeng.test.mapper.auto;

import com.wangmeng.test.model.auto.TsysFile;
import com.wangmeng.test.model.auto.TsysFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsysFileMapper {
    int countByExample(TsysFileExample example);

    int deleteByExample(TsysFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysFile record);

    int insertSelective(TsysFile record);

    List<TsysFile> selectByExample(TsysFileExample example);

    TsysFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysFile record, @Param("example") TsysFileExample example);

    int updateByExample(@Param("record") TsysFile record, @Param("example") TsysFileExample example);

    int updateByPrimaryKeySelective(TsysFile record);

    int updateByPrimaryKey(TsysFile record);
}