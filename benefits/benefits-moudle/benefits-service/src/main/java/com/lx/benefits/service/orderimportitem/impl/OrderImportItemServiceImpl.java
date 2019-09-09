package com.lx.benefits.service.orderimportitem.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.order.OrderImportItemQueryDto;
import com.lx.benefits.bean.dto.admin.order.OrderImportItemResDto;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.entity.orderimportitem.OrderImportItem;
import com.lx.benefits.bean.entity.orderimportitem.OrderImportItemExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.dao.orderimportitem.OrderImportItemDao;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.orderimportitem.OrderImportItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author unknow on 2018-12-26 17:08.
 */
@Service
public class OrderImportItemServiceImpl implements OrderImportItemService {

    private final static Logger logger = LoggerFactory.getLogger(OrderImportItemServiceImpl.class);
    
    @Autowired
    private OrderImportItemDao orderImportItemDao;
    
    @Autowired
    private EnterprUserInfoMapper enterprUserInfoMapper;

    @Override
    public Long insertSelective(OrderImportItem record) {
        return orderImportItemDao.insertSelective(record);
    }

    @Override
    public JSONObject selectByExample(OrderImportItemQueryDto req) {
        req = null == req ? new OrderImportItemQueryDto() : req;
        JSONObject jsonObject = new JSONObject();
        List<OrderImportItemResDto> orderImportResDtoList = new ArrayList<>();
        int count = 0;
        OrderImportItemExample example = new OrderImportItemExample();
        example.setPage(req.getPage());
        example.setPageSize(req.getPageSize());
        example.createCriteria().andImportIdEqualTo(req.getImportId());
        List<OrderImportItem> orderImportItemList = orderImportItemDao.selectByExample(example);
        if (null != orderImportItemList && !orderImportItemList.isEmpty()) {
            List<Long> optUserIdList = new ArrayList<>();
            for (OrderImportItem orderImportItem: orderImportItemList) {
                Long optUserId = orderImportItem.getUserid();
                if (!optUserIdList.contains(optUserId)) {
                    optUserIdList.add(optUserId);
                }
            }
            EnterprUserInfoCondition enterprUserExample = new EnterprUserInfoCondition();
            enterprUserExample.createCriteria().andEnterprIdIn(optUserIdList);
            enterprUserExample.setOffset(0);
            enterprUserExample.setLimit(100);
            List<EnterprUserInfo> enterprUserInfoList =enterprUserInfoMapper.selectByExample(enterprUserExample);
            Map<Long, EnterprUserInfo> enterprUserInfoMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(enterprUserInfoList)) {
                for (EnterprUserInfo enterprUserInfo: enterprUserInfoList) {
                    enterprUserInfoMap.put(enterprUserInfo.getEnterprId(), enterprUserInfo);
                }
            }
            try {
                for (OrderImportItem orderImportItem: orderImportItemList) {
                    OrderImportItemResDto orderImportItemResDto = new OrderImportItemResDto();
                    BeanUtils.copyProperties(orderImportItem, orderImportItemResDto);
                    orderImportItemResDto.setCreated(DateUtil.unixTime2Date(orderImportItem.getCreated()));
                    orderImportResDtoList.add(orderImportItemResDto);
                }
            } catch (Exception e) {
                logger.info("OrderImportItemServiceImpl exception {}",e.getMessage());
            }

            count = orderImportItemDao.countByExample(example);
        }
        jsonObject.put("list", orderImportResDtoList);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }

    @Override
    public Integer update(OrderImportItem orderImportItem, OrderImportItemExample example) {
        return orderImportItemDao.update(orderImportItem, example);
    }

}
