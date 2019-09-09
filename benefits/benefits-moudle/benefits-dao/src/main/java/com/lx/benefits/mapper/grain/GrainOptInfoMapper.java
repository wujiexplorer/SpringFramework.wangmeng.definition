package com.lx.benefits.mapper.grain;

import com.lx.benefits.bean.dto.grain.GrainOptInfo;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/17
 * Time:16:28
 * Verision:2.x
 * Description:TODO
 **/
public interface GrainOptInfoMapper {

    int delete(Long id);

    int insert(GrainOptInfo grainOptInfo);


    int updateByPrimaryKeySelective(GrainOptInfo grainOptInfo);


    List<GrainOptInfo> findGrainOptInfoList(Map<String,Object> params);

    Integer countGrainOptInfoList(Map<String,Object> params);

    Integer countReceiveCreditValue(Map<String,Object> params);


    GrainOptInfo selectByPrimaryKey(Long id);
}
