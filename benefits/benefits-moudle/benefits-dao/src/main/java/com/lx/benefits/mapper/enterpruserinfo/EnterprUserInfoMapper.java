package com.lx.benefits.mapper.enterpruserinfo;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.vo.enterpr.EnterprUserInfoBean;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnterprUserInfoMapper {
	long countByExample(EnterprUserInfoCondition example);

	public Long countCurrentEnterprise();

	int deleteByExample(EnterprUserInfoCondition example);

	int deleteByPrimaryKey(Long enterprId);

	int insert(EnterprUserInfo record);

	int insertSelective(EnterprUserInfo record);

	List<EnterprUserInfo> selectByExample(EnterprUserInfoCondition example);

	EnterprUserInfo selectByPrimaryKey(Long enterprId);

	int updateByExampleSelective(@Param("record") EnterprUserInfo record, @Param("example") EnterprUserInfoCondition example);

	int updateByExample(@Param("record") EnterprUserInfo record, @Param("example") EnterprUserInfoCondition example);

	int updateByPrimaryKeySelective(EnterprUserInfo record);

	int updateByPrimaryKey(EnterprUserInfo record);

	void incrEmployeeNum(@Param("enterprId") Long enterprId, @Param("num") int num);

	void reduceEmployeeNum(@Param("enterprId") Long enterprId, @Param("num") int num);

	int countWithAgentName(@Param("agentName") String agentName, @Param("enterprName") String enterprName);

	List<EnterprUserInfoBean> selectWithAgentName(@Param("agentName") String agentName, @Param("enterprName") String enterprName,
			@Param("pageBean") PageBean pageBean);

	List<EnterprUserInfo> selectEnterpriseNames(@Param("enterprIds")List<Long> enterprIds);

}
