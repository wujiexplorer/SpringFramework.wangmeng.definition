package com.lx.benefits.service.yianapi;

import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.yianapi.PageInfo;
import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfo;
import com.lx.benefits.bean.enums.MemberUnionType;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * Created by softw on 2019/3/1.
 */
public interface UnionInfoService {

    MemUnionInfo queryByUnionInfo(MemUnionInfo info);

    List<MemUnionInfo> queryByParam(Map<String, Object> params);

    MemUnionInfo queryByParams(Map<String, Object> params);

    Integer queryByCount(MemUnionInfo info);

    ResultInfo<PageInfo<T>> queryageByParam(Map<String, Object> param,PageInfo<T> info);

    MemUnionInfo getUnionInfo(Long code, MemberUnionType type);

    Integer updateById(MemUnionInfo info);
}