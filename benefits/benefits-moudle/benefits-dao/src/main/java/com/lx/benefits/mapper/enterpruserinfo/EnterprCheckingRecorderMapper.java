package com.lx.benefits.mapper.enterpruserinfo;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprCheckingRecorder;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprCheckingRecorderCondition;
import com.lx.benefits.bean.vo.enterpr.EnterpriseCheckingBean;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnterprCheckingRecorderMapper {
	long countByExample(EnterprCheckingRecorderCondition example);

	int deleteByExample(EnterprCheckingRecorderCondition example);

	int deleteByPrimaryKey(Long enterprId);

	int insert(EnterprCheckingRecorder record);

	int insertSelective(EnterprCheckingRecorder record);

	List<EnterprCheckingRecorder> selectByExample(EnterprCheckingRecorderCondition example);

	EnterprCheckingRecorder selectByPrimaryKey(Long enterprId);

	int updateByExampleSelective(@Param("record") EnterprCheckingRecorder record, @Param("example") EnterprCheckingRecorderCondition example);

	int updateByExample(@Param("record") EnterprCheckingRecorder record, @Param("example") EnterprCheckingRecorderCondition example);

	int updateByPrimaryKeySelective(EnterprCheckingRecorder record);

	int updateByPrimaryKey(EnterprCheckingRecorder record);

	List<EnterpriseCheckingBean> selectWithAgentName(@Param("checked") Integer checked, @Param("enterprName") String enterprName,
			@Param("checkStatus") Integer checkStatus, @Param("pageBean") PageBean pageBean);
}