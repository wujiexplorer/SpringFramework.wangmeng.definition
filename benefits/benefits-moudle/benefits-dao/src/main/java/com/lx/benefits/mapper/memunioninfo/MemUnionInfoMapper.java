package com.lx.benefits.mapper.memunioninfo;

import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfo;
import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

public interface MemUnionInfoMapper {
    int countByExample(MemUnionInfoExample example);

    int deleteByExample(MemUnionInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemUnionInfo record);

    int batchInsert(@Param("memUnionInfoList") List<MemUnionInfo> memUnionInfoList);

    int insertSelective(MemUnionInfo record);

    List<MemUnionInfo> selectByExample(MemUnionInfoExample example);

    MemUnionInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemUnionInfo record, @Param("example") MemUnionInfoExample example);

    int updateByExample(@Param("record") MemUnionInfo record, @Param("example") MemUnionInfoExample example);

    int updateByPrimaryKeySelective(MemUnionInfo record);

    int updateByPrimaryKey(MemUnionInfo record);


    MemUnionInfo queryUniqueByObject(MemUnionInfo info);

    List<MemUnionInfo> queryByParam(Map<String, Object> params);

    Integer queryCountByUnionInfo(MemUnionInfo info);

    Integer queryCountByParams(Map<String, Object> params);

    List<T> queryPageByParam(Map<String, Object> params);

    Integer updateByMemId(MemUnionInfo info);

}