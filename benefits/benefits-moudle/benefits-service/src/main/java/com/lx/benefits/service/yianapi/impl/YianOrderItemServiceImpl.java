package com.lx.benefits.service.yianapi.impl;

import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.entity.ent.YianOrderItem;
import com.lx.benefits.mapper.ent.YianOrderItemMapper;
import com.lx.benefits.service.yianapi.YianOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YianOrderItemServiceImpl implements YianOrderItemService {

    @Autowired
    private YianOrderItemMapper yianOrderItemMapper;
    @Override
    public ResultInfo<List<YianOrderItem>> queryByObject(YianOrderItem itemq) {
        return  new ResultInfo<>(yianOrderItemMapper.queryByObject(itemq));
    }
}
