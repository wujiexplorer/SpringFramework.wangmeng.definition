package com.lx.benefits.dao.grain.impl;

import com.lx.benefits.bean.dto.grain.GrainSpecialPerson;
import com.lx.benefits.dao.grain.GrainSpecialPersonDao;
import com.lx.benefits.mapper.grain.GrainSpecialPersonMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:11:23
 * Verision:2.x
 * Description:TODO
 **/
@Repository
public class GrainSpecialPersonDaoImpl implements GrainSpecialPersonDao {

    @Autowired
    private GrainSpecialPersonMapper grainSpecialPersonMapper;

    @Override
    public int delete(Long id) {
        return grainSpecialPersonMapper.delete(id);
    }

    @Override
    public GrainSpecialPerson insert(GrainSpecialPerson grainSpecialPerson) {
        Integer count = grainSpecialPersonMapper.insert(grainSpecialPerson);
        if(count > 0 ){
            return grainSpecialPerson;
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(GrainSpecialPerson grainSpecialPerson) {
        return grainSpecialPersonMapper.updateByPrimaryKeySelective(grainSpecialPerson);
    }

    @Override
    public List<GrainSpecialPerson> findrainSpecialPersonList(Map<String,Object> params) {
        return grainSpecialPersonMapper.findrainSpecialPersonList(params);
    }

    @Override
    public GrainSpecialPerson selectByPrimaryKey(Long id) {
        return grainSpecialPersonMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer countGrainSpecialPersonList(Map<String,Object> params) {
        return grainSpecialPersonMapper.countGrainSpecialPersonList(params);
    }
}
