package com.lx.benefits.mapper.user;

import com.lx.benefits.bean.entity.user.WxUserOpenId;
import com.lx.benefits.bean.entity.user.WxUserOpenIdCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxUserOpenIdMapper {
    long countByExample(WxUserOpenIdCondition example);

    int deleteByExample(WxUserOpenIdCondition example);

    int deleteByPrimaryKey(Long id);

    int insert(WxUserOpenId record);

    int insertSelective(WxUserOpenId record);

    List<WxUserOpenId> selectByExample(WxUserOpenIdCondition example);

    WxUserOpenId selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxUserOpenId record, @Param("example") WxUserOpenIdCondition example);

    int updateByExample(@Param("record") WxUserOpenId record, @Param("example") WxUserOpenIdCondition example);

    int updateByPrimaryKeySelective(WxUserOpenId record);

    int updateByPrimaryKey(WxUserOpenId record);

}