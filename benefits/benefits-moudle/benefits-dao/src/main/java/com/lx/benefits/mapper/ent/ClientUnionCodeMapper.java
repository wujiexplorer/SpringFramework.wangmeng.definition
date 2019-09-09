package com.lx.benefits.mapper.ent;

import com.lx.benefits.bean.dto.mem.ClientUnionCode;

import java.util.List;
import java.util.Map;

public interface ClientUnionCodeMapper {

	ClientUnionCode queryByClientUnionCode(ClientUnionCode info);

	int updateById(ClientUnionCode info);

	List<ClientUnionCode> queryByParam(Map<String, Object> params);

	Integer queryByCount(ClientUnionCode info);

	ClientUnionCode getUnoinInfo(Long userId);

	Integer insert(ClientUnionCode info);
}