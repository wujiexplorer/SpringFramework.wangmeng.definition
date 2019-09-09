package com.lx.benefits.mapper.grain;

import com.lx.benefits.bean.dto.grain.GrainSpecialPerson;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/17
 * Time:16:29
 * Verision:2.x
 * Description:TODO
 **/
public interface GrainSpecialPersonMapper {

    int delete(Long id);

    int insert(GrainSpecialPerson grainSpecialPerson);


    int updateByPrimaryKeySelective(GrainSpecialPerson grainSpecialPerson);


    List<GrainSpecialPerson> findrainSpecialPersonList(Map<String,Object> params);


    GrainSpecialPerson selectByPrimaryKey(Long id);

    Integer countGrainSpecialPersonList(Map<String,Object> params);

}
