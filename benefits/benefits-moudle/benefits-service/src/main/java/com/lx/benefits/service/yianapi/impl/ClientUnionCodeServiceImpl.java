package com.lx.benefits.service.yianapi.impl;

import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import com.lx.benefits.bean.dto.mem.ClientUnionCode;
import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfo;
import com.lx.benefits.bean.enums.MemberUnionType;
import com.lx.benefits.mapper.ent.ClientUnionCodeMapper;
import com.lx.benefits.mapper.memunioninfo.MemUnionInfoMapper;
import com.lx.benefits.service.yianapi.ClientUnionCodeService;
import com.lx.benefits.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;


/**
 * Created by softw on 2019/3/1.
 */
@Service
public class ClientUnionCodeServiceImpl implements ClientUnionCodeService {

    private final int effective_sec = 60*60*24;

    @Autowired
    private RedisUtils util;

    @Autowired
    private ClientUnionCodeMapper clientUnionCodeMapper;

    @Autowired
    private MemUnionInfoMapper unionInfoMapper;

    @Override
    public ClientUnionCode queryByClientUnionCode(ClientUnionCode info) {
        return clientUnionCodeMapper.queryByClientUnionCode(info);
    }

    @Override
    public ResultInfo<MemUnionInfo>  getUnoinInfo(Long userId) {
        ResultInfo<MemUnionInfo> resultInfo = new ResultInfo<>();
        MemUnionInfo unionInfoQuery = new MemUnionInfo();
        unionInfoQuery.setMem_id(userId);
        unionInfoQuery.setType(MemberUnionType.YIAN.code);
        MemUnionInfo unionInfo = unionInfoMapper.queryUniqueByObject(unionInfoQuery);
        resultInfo.setData(unionInfo);
        return resultInfo;
    }

    private MemUnionInfo getUnoinInfoAction(Long userId) {
        MemUnionInfo unionInfoQuery = new MemUnionInfo();
        unionInfoQuery.setMem_id(userId);
        unionInfoQuery.setType(MemberUnionType.YIAN.code);
        return unionInfoMapper.queryUniqueByObject(unionInfoQuery);
    }


    @Override
    public ResultInfo<ClientUnionCode> code(Long memberId) {
        ResultInfo<ClientUnionCode> resultInfo = new ResultInfo<>();
        MemUnionInfo unionInfo = getUnoinInfoAction(memberId);

        if (unionInfo == null) return resultInfo;

        ClientUnionCode clientUnionCodeQuery = new ClientUnionCode();
        clientUnionCodeQuery.setMemberId(memberId);
        ClientUnionCode code = clientUnionCodeMapper.queryByClientUnionCode(clientUnionCodeQuery);

        if (code == null) {
            code = new ClientUnionCode();
            code.setMemberId(memberId);
            code.setCreateTime(new Date());
            code.setUpdateTime(new Date());
            code.setExpires(System.currentTimeMillis() + effective_sec * 1000);
            code.setCode("cd" + UUID.randomUUID().toString().replaceAll("-", ""));
            String key = "client-union-code-insert-lock-key" + memberId;
            try {
                if (util.lock(key,5)) {
                    clientUnionCodeMapper.insert(code);
                    resultInfo.setData(code);
                    return resultInfo;
                } else {
                    throw new ServiceException("请稍后重试");
                }
            } finally {
                util.unLock(key);
            }
        }
        //code未到期时不更新code
        if(code.getExpires()<System.currentTimeMillis()) {
            code.setCode("cd" + UUID.randomUUID().toString().replaceAll("-", ""));
            code.setExpires(System.currentTimeMillis() + effective_sec * 1000);
            code.setUpdateTime(new Date());
            clientUnionCodeMapper.updateById(code);
        }
        resultInfo.setData(code);

        return resultInfo;

    }

}
