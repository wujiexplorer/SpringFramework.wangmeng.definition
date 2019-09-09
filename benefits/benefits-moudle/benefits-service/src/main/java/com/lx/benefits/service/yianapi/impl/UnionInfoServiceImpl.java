package com.lx.benefits.service.yianapi.impl;

import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.constants.DAOConstant;
import com.lx.benefits.bean.dto.yianapi.PageInfo;
import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfo;
import com.lx.benefits.bean.enums.MemberUnionType;
import com.lx.benefits.mapper.memunioninfo.MemUnionInfoMapper;
import com.lx.benefits.service.yianapi.UnionInfoService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by softw on 2019/3/1.
 */
@Service
public class UnionInfoServiceImpl  implements UnionInfoService {

    @Autowired
    private MemUnionInfoMapper unionInfoMapper;

    @Override
    public MemUnionInfo queryByUnionInfo(MemUnionInfo info) {
        return unionInfoMapper.queryUniqueByObject(info);
    }

    @Override
    public List<MemUnionInfo> queryByParam(Map<String, Object> params) {
        return unionInfoMapper.queryByParam(params);
    }

    @Override
    public MemUnionInfo queryByParams(Map<String, Object> params) {
        List<MemUnionInfo> list = unionInfoMapper.queryByParam(params);
        if (null != list && list.size() > 0) {
            return  list.get(0);
        }
        return null;
    }

    @Override
    public Integer queryByCount(MemUnionInfo info) {
        return unionInfoMapper.queryCountByUnionInfo(info);
    }

    PageInfo<T> queryPageByParam(Map<String,Object> params,PageInfo<T> info) {
        info.setRecords(unionInfoMapper.queryCountByParams(params));
        setLimit(params,info);
        info.setRows(unionInfoMapper.queryPageByParam(params));
        return info;
    }

    @Override
    public ResultInfo<PageInfo<T>> queryageByParam(Map<String, Object> param, PageInfo<T> info) {
        return new ResultInfo<>(queryPageByParam(param,info));
    }

    public void setLimit(final Map<String,Object> params,final PageInfo<T> info){
        Integer begin = (info.getPage()-1)*info.getSize();
        if(begin==null || (begin!=null &&begin<0)){
            begin=0;
        }
        params.put(DAOConstant.MYBATIS_SPECIAL_STRING.LIMIT.name(), begin+","+info.getSize());
    }

    @Override
    public MemUnionInfo getUnionInfo(Long memberId, MemberUnionType type) {
        MemUnionInfo query = new MemUnionInfo();
        query.setMem_id(memberId);
        query.setType(type.code);
        return unionInfoMapper.queryUniqueByObject(query);
    }

    @Override
    public Integer updateById(MemUnionInfo info) {
        return unionInfoMapper.updateByPrimaryKeySelective(info);
    }
}
