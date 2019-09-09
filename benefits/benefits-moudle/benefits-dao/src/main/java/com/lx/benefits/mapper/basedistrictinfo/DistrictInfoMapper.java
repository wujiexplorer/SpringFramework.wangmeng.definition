package com.lx.benefits.mapper.basedistrictinfo;


import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfoEntity;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface DistrictInfoMapper {

    List<DistrictInfo> selectByExample(DistrictInfoExample example);

    DistrictInfo selectSimpleById(@Param("id") Long id);

    DistrictInfo getInfoByName(@Param("name") String name);

    List<DistrictInfo> queryByParam(Map<String, Object> params);

    DistrictInfoEntity selectById(@Param("id") Integer id);

    List<DistrictInfoEntity> getByIds(@Param("ids") List<Long> ids);


}