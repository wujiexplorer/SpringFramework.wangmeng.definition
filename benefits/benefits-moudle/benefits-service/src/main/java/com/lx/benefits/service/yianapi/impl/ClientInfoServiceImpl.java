package com.lx.benefits.service.yianapi.impl;

import com.apollo.starter.web.utils.PageResult;
import com.lx.benefits.bean.base.dto.ClientInfoDto;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import com.lx.benefits.bean.entity.ent.ClientInfo;
import com.lx.benefits.bean.entity.ent.ClientInfoCondition;
import com.lx.benefits.mapper.ent.ClientInfoMapper;
import com.lx.benefits.service.yianapi.ClientInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by softw on 2019/3/1.
 */
@Service
public class ClientInfoServiceImpl  implements ClientInfoService {

    private final int effective_sec = 60 * 60 * 10;

    @Autowired
    private ClientInfoMapper clientInfoMapper;

    @Override
    public ResultInfo getToken(ClientInfo info) {
        ResultInfo<ClientInfo> resultInfo = new ResultInfo<>();
        ClientInfo clientInfo = clientInfoMapper.queryByClientInfo(info);
        if (clientInfo == null) {
            throw new ServiceException("平台信息错误");
        }
        long cur = System.currentTimeMillis();
        if (StringUtils.isBlank(clientInfo.getToken()) || clientInfo.getExpiresIn() == null || clientInfo.getExpires() == null
                || clientInfo.getExpires() < cur) {
            updateToken(clientInfo, cur, resultInfo);
            return resultInfo;
        }
        long expires = (clientInfo.getExpires() - cur) / 1000;
        clientInfo.setExpiresIn((int) expires);
        clientInfo.setUpdateTime(new Date());
        clientInfoMapper.updateById(clientInfo);
        resultInfo.setData(clientInfo);
        return resultInfo;
    }

    @Override
    public ClientInfo queryByCientInfo(ClientInfo info) {
        return clientInfoMapper.queryByClientInfo(info);
    }

    @Override
    public List<ClientInfo> queryByParam(Map<String, Object> params) {
        return clientInfoMapper.queryByParam(params);
    }

    @Override
    public Integer queryByCount(ClientInfo info) {
        return clientInfoMapper.queryByCount(info);
    }


    private void updateToken(ClientInfo clientInfo, long cur, ResultInfo<ClientInfo> resultInfo) {
        clientInfo.setToken("tk" + UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
        clientInfo.setExpires(cur + effective_sec * 1000);
        clientInfo.setExpiresIn(effective_sec);
        clientInfo.setUpdateTime(new Date());
        clientInfoMapper.updateById(clientInfo);
        resultInfo.setData(clientInfo);
    }

    @Override
    public ResultInfo<ClientInfo> validToken(String token) {
        ResultInfo<ClientInfo> resultInfo = new ResultInfo<>();
        ClientInfo clientInfoQ = new ClientInfo();
        clientInfoQ.setToken(token);
        ClientInfo clientInfo = queryByCientInfo(clientInfoQ);

        if (clientInfo == null) {
            throw new ServiceException("token error");
        }
        if (clientInfo.getExpires() < System.currentTimeMillis()) {
            throw new ServiceException("token expired");
        }
        resultInfo.setData(clientInfo);
        return resultInfo;
    }
    
    @Override
	public void addClientInfo(ClientInfoDto clientInfo) {
		ClientInfo info = new ClientInfo();
		info.setClientName(clientInfo.getClientName());
		info.setOrgCode(clientInfo.getOrgCode());
		info.setUrl(clientInfo.getUrl());
		info.setClientId("ci" + UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
		info.setClientSecret("cs" + UUID.randomUUID().toString().replaceAll("-", "").toLowerCase()
				+ UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
		Date now = new Date();
		info.setCreateTime(now);
		info.setUpdateTime(now);
		clientInfoMapper.insert(info);
	}

	@Override
	public void updateClientInfoById(Long id, ClientInfoDto clientInfo) {
		ClientInfo info = new ClientInfo();
		info.setId(id);
		info.setClientName(clientInfo.getClientName());
		info.setOrgCode(clientInfo.getOrgCode());
		info.setUrl(clientInfo.getUrl());
		info.setUpdateTime(new Date());
		clientInfoMapper.updateById(info);
	}

	@Override
	public PageResult<ClientInfo> getClientInfosByName(Integer pageNo, Integer pageSize, String clientName) {
		ClientInfoCondition example = new ClientInfoCondition();
		if (!StringUtils.isEmpty(clientName)) {
			example.createCriteria().andClientNameLike("%" + clientName.trim() + "%");
		}
		int count = (int) clientInfoMapper.countByExample(example);
		if (count == 0) {
			return PageResult.EMPTY;
		}
		if (pageSize < 1) {
			pageSize = 10;
		} else if (pageSize > 50) {
			pageSize = 50;
		}
		int totalPageSize = (count - 1) / pageSize + 1;
		if (pageNo < 1) {
			pageNo = 1;
		} else if (pageNo > totalPageSize) {
			pageNo = totalPageSize;
		}
		example.setLimit(pageSize);
		example.setOffset((pageNo - 1) * pageSize);
		return PageResult.wrapPageResult(clientInfoMapper.selectByExample(example), count, pageSize);
	}
	
}
