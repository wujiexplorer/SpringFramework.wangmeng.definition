package com.lx.benefits.service.yianapi;

import java.util.List;
import java.util.Map;

import com.apollo.starter.web.utils.PageResult;
import com.lx.benefits.bean.base.dto.ClientInfoDto;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.entity.ent.ClientInfo;

/**
 * Created by softw on 2019/3/1.
 */
public interface ClientInfoService {

     ResultInfo getToken(ClientInfo info);

     ClientInfo queryByCientInfo(ClientInfo info);

     List<ClientInfo>  queryByParam(Map<String, Object> params);

     Integer queryByCount(ClientInfo info);

     ResultInfo<ClientInfo> validToken(String token);

	void updateClientInfoById(Long id, ClientInfoDto clientInfo);

	void addClientInfo(ClientInfoDto clientInfo);

	PageResult<ClientInfo> getClientInfosByName(Integer pageNo, Integer pageSize, String clientName);
}
