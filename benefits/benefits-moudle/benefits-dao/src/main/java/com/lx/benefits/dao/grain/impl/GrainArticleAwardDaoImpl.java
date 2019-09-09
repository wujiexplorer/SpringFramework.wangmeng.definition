package com.lx.benefits.dao.grain.impl;

import com.lx.benefits.bean.dto.grain.GrainArticleAward;
import com.lx.benefits.dao.grain.GrainArticleAwardDao;
import com.lx.benefits.mapper.grain.GrainArticleAwardMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:10:54
 * Verision:2.x
 * Description:TODO
 **/
@Repository
public class GrainArticleAwardDaoImpl implements GrainArticleAwardDao {

    @Autowired
    private GrainArticleAwardMapper grainArticleAwardMapper;

    @Override
    public int delete(Long id) {
        return grainArticleAwardMapper.delete(id);
    }

    @Override
    public GrainArticleAward insert(GrainArticleAward grainArticleAward) {
        Integer count = grainArticleAwardMapper.insert(grainArticleAward);
        if(count > 0){
            return  grainArticleAward;
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(GrainArticleAward grainArticleAward) {
        return grainArticleAwardMapper.updateByPrimaryKeySelective(grainArticleAward);
    }

    @Override
    public List<GrainArticleAward> findGrainArticleAwardList(Map<String,Object> params) {
        return grainArticleAwardMapper.findGrainArticleAwardList(params);
    }

    @Override
    public Integer countGrainArticleAwardList(Map<String, Object> params) {
        return grainArticleAwardMapper.countGrainArticleAwardList(params);
    }

    @Override
    public GrainArticleAward selectByPrimaryKey(Long id) {
        return grainArticleAwardMapper.selectByPrimaryKey(id);
    }

    @Override
    public Double calculateGrainArticleCumulateionAward(Map<String, Object> params) {
        return grainArticleAwardMapper.calculateGrainArticleCumulateionAward(params);
    }
}
