package com.lx.benefits.mapper.enterprbannerinfo;

import com.lx.benefits.bean.entity.enterprbannerinfo.EnterprBannerInfo;
import com.lx.benefits.bean.entity.enterprbannerinfo.EnterprBannerInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterprBannerInfoMapper {
    int countByExample(EnterprBannerInfoExample example);

    int deleteByExample(EnterprBannerInfoExample example);

    int deleteByPrimaryKey(Long bannerId);

    int insert(EnterprBannerInfo record);

    int insertSelective(EnterprBannerInfo record);

    List<EnterprBannerInfo> selectByExample(EnterprBannerInfoExample example);

    EnterprBannerInfo selectByPrimaryKey(Long bannerId);

    int updateByExampleSelective(@Param("record") EnterprBannerInfo record, @Param("example") EnterprBannerInfoExample example);

    int updateByExample(@Param("record") EnterprBannerInfo record, @Param("example") EnterprBannerInfoExample example);

    int updateByPrimaryKeySelective(EnterprBannerInfo record);

    int updateByPrimaryKey(EnterprBannerInfo record);
}