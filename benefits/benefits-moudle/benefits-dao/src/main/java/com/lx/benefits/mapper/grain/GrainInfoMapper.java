package com.lx.benefits.mapper.grain;

import com.lx.benefits.bean.dto.grain.GrainInfo;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/17
 * Time:16:27
 * Verision:2.x
 * Description:TODO
 **/
public interface GrainInfoMapper {

    int delete(Long id);

    int insert(GrainInfo grainInfo);


    int updateByPrimaryKeySelective(GrainInfo grainInfo);


    List<GrainInfo> findGrainInfoList(GrainInfo grainInfo);


    List<GrainInfo> findGrainInfoListByServiceStatus(Map<String,Object> params);

    List<GrainInfo> findGrainInfoListByStatus(Map<String,Object> params);

   Integer countGrainInfoListByStatus();

    Integer countGrainInfoListByServiceStatus();

    GrainInfo selectByPrimaryKey(Long id);

    List<GrainInfo> findGrainInfoListByverifyStatus(Map<String,Object> params);

    Integer countGrainInfoListByverifyStatus();
}
