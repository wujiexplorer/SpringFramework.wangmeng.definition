package com.lx.benefits.mapper.enterprmenuinfo;

import com.lx.benefits.bean.entity.enterprmenuinfo.EnterprMenuInfo;
import com.lx.benefits.bean.entity.enterprmenuinfo.EnterprMenuInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnterprMenuInfoMapper {
    long countByExample(EnterprMenuInfoExample example);

    int deleteByExample(EnterprMenuInfoExample example);

    int deleteByPrimaryKey(Integer menuId);

    int insert(EnterprMenuInfo record);

    int insertSelective(EnterprMenuInfo record);

    List<EnterprMenuInfo> selectByExample(EnterprMenuInfoExample example);

    EnterprMenuInfo selectByPrimaryKey(Integer menuId);

    int updateByExampleSelective(@Param("record") EnterprMenuInfo record, @Param("example") EnterprMenuInfoExample example);

    int updateByExample(@Param("record") EnterprMenuInfo record, @Param("example") EnterprMenuInfoExample example);

    int updateByPrimaryKeySelective(EnterprMenuInfo record);

    int updateByPrimaryKey(EnterprMenuInfo record);
}