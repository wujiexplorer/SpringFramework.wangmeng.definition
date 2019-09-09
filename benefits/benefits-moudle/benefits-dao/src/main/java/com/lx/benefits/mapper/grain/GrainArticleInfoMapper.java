package com.lx.benefits.mapper.grain;

import com.lx.benefits.bean.dto.grain.GrainArticleInfo;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/17
 * Time:16:27
 * Verision:2.x
 * Description:TODO
 **/
public interface GrainArticleInfoMapper {

    int delete(Long id);

    int insert(GrainArticleInfo grainArticleInfo);


    int updateByPrimaryKeySelective(GrainArticleInfo grainArticleInfo);


    List<GrainArticleInfo> findGrainArticleInfoList(Map<String,Object> params);

    Integer countGrainArticleInfoList(Map<String,Object> map);


    GrainArticleInfo selectByPrimaryKey(Long id);

    List<GrainArticleInfo> findGrainArticleInfoListByStatus(Map<String,Object> params);

    Integer countGrainArticleInfoListByStatus(Map<String,Object> params);

    List<GrainArticleInfo> findGrainArticleInfoListByVerifyStatus(Map<String,Object> params);

    Integer countGrainArticleInfoListByVerifyStatus(Map<String,Object> params);

    List<GrainArticleInfo> findGrainArticleInfoListByOpenAwardStatus(Map<String,Object> params);

    List<GrainArticleInfo> findGrainArticleInfoListBySuspendOpenAwardStatus(Map<String,Object> params);

    Integer countGrainArticleInfoListByOpenAwardStatus(Map<String,Object> params);

    Integer countGrainArticleInfoListBySuspendOpenAwardStatus(Map<String,Object> params);

    Integer calculateGrainArticleInfoListByOpenAwardStatus(Map<String,Object> params);
}
