package com.lx.benefits.mapper.enterprnoticeinfo;


import com.lx.benefits.bean.dto.admin.customized.EnterprNoticeReqDto;
import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfo;
import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterprNoticeInfoMapper {
    int countByExample(EnterprNoticeInfoExample example);

    int deleteByExample(EnterprNoticeInfoExample example);

    int deleteByPrimaryKey(Long customId);

    int insert(EnterprNoticeInfo record);

    int insertSelective(EnterprNoticeInfo record);

    List<EnterprNoticeInfo> selectByExample(EnterprNoticeInfoExample example);

    EnterprNoticeInfo selectByPrimaryKey(Long customId);

    int updateByExampleSelective(@Param("record") EnterprNoticeInfo record, @Param("example") EnterprNoticeInfoExample example);

    int updateByExample(@Param("record") EnterprNoticeInfo record, @Param("example") EnterprNoticeInfoExample example);

    int updateByPrimaryKeySelective(EnterprNoticeInfo record);

    int updateByPrimaryKey(EnterprNoticeInfo record);
    
    Integer insertEmail(EnterprNoticeReqDto req);
    
    Integer updateEmail(EnterprNoticeReqDto req);
    
    EnterprNoticeReqDto findEmailDetail(Long enterprId);
}