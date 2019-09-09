package com.lx.benefits.mapper.enterpruserinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprBindRecorder;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprBindRecorderCondition;
import com.lx.benefits.bean.vo.enterpr.EnterprBindRecorderBean;

public interface EnterprBindRecorderMapper {
	long countByExample(EnterprBindRecorderCondition example);

	int deleteByExample(EnterprBindRecorderCondition example);

	int deleteByPrimaryKey(Integer id);

	int insert(EnterprBindRecorder record);

	int insertSelective(EnterprBindRecorder record);

	List<EnterprBindRecorder> selectByExample(EnterprBindRecorderCondition example);

	EnterprBindRecorder selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") EnterprBindRecorder record, @Param("example") EnterprBindRecorderCondition example);

	int updateByExample(@Param("record") EnterprBindRecorder record, @Param("example") EnterprBindRecorderCondition example);

	int updateByPrimaryKeySelective(EnterprBindRecorder record);

	int updateByPrimaryKey(EnterprBindRecorder record);

	List<EnterprBindRecorderBean> getAgentEnterprisesBindhistory(@Param("agentId") Integer agentId, @Param("pageBean") PageBean pageBean);

	int countDistinctAgentUnbind(@Param("agentId") Integer agentId);
}