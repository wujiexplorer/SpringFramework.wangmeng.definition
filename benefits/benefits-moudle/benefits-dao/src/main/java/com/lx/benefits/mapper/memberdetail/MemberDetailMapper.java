package com.lx.benefits.mapper.memberdetail;

import com.lx.benefits.bean.entity.memberdetail.MemberDetail;
import com.lx.benefits.bean.entity.memberdetail.MemberDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberDetailMapper {
    int countByExample(MemberDetailExample example);

    int deleteByExample(MemberDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberDetail record);
    
    int batchInsert(@Param("memberDetailList") List<MemberDetail> memberDetailList);

    int insertSelective(MemberDetail record);

    List<MemberDetail> selectByExample(MemberDetailExample example);

    MemberDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberDetail record, @Param("example") MemberDetailExample example);

    int updateByExample(@Param("record") MemberDetail record, @Param("example") MemberDetailExample example);

    int updateByPrimaryKeySelective(MemberDetail record);

    int updateByPrimaryKey(MemberDetail record);
}