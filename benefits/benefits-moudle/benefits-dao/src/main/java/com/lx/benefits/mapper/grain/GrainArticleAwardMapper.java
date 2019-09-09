package com.lx.benefits.mapper.grain;

import com.lx.benefits.bean.dto.grain.GrainArticleAward;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/17
 * Time:16:26
 * Verision:2.x
 * Description:TODO
 **/
public interface GrainArticleAwardMapper {

    int delete(Long id);

    int insert(GrainArticleAward grainArticleAward);


    int updateByPrimaryKeySelective(GrainArticleAward grainArticleAward);


    List<GrainArticleAward> findGrainArticleAwardList(Map<String,Object> params);

    Integer countGrainArticleAwardList(Map<String,Object> params);


    GrainArticleAward selectByPrimaryKey(Long id);

    Double calculateGrainArticleCumulateionAward(Map<String,Object> params);
}
