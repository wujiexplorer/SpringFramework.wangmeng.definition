package com.lx.benefits.mapper.ent;

import java.util.List;
import java.util.Map;

import com.lx.benefits.bean.entity.ent.ClientInfo;
import com.lx.benefits.bean.entity.ent.ClientInfoCondition;

public interface ClientInfoMapper {

	ClientInfo queryByClientInfo(ClientInfo info);

	int updateById(ClientInfo info);

	List<ClientInfo> queryByParam(Map<String, Object> params);

	Integer queryByCount(ClientInfo info);
	
	int insert(ClientInfo info);
	
	int countByExample(ClientInfoCondition example);
	
    List<ClientInfo> selectByExample(ClientInfoCondition example);
}