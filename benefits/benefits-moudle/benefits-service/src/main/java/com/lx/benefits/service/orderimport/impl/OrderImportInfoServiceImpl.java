package com.lx.benefits.service.orderimport.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.enterprise.EnterpriseQueryReqDto;
import com.lx.benefits.bean.dto.admin.order.OrderImportResDto;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.orderimportinfo.OrderImportInfo;
import com.lx.benefits.bean.entity.orderimportinfo.OrderImportInfoExample;
import com.lx.benefits.bean.entity.userinfo.UserInfo;
import com.lx.benefits.bean.entity.userinfo.UserInfoExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.dao.orderimportinfo.OrderImportInfoDao;
import com.lx.benefits.dao.userinfo.UserInfoDao;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.mapper.orderimportitem.OrderImportItemMapper;
import com.lx.benefits.service.orderimport.OrderImportInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author unknow on 2018-12-26 17:08.
 */
@Service
public class OrderImportInfoServiceImpl implements OrderImportInfoService {
    
    @Autowired
    private OrderImportInfoDao orderImportInfoDao;
    
    @Autowired
    private EnterprUserInfoMapper enterprUserInfoMapper;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private OrderImportItemMapper orderImportItemMapper;

    @Override
    public Long insertSelective(OrderImportInfo record) {
        return orderImportInfoDao.insertSelective(record);
    }

    @Override
    public JSONObject selectByExample(EnterpriseQueryReqDto req) {
        req = null == req ? new EnterpriseQueryReqDto() : req;
        JSONObject jsonObject = new JSONObject();
        List<OrderImportResDto> orderImportResDtoList = new ArrayList<>();
        int count = 0;
        OrderImportInfoExample example = new OrderImportInfoExample();
        example.setPage(req.getPage());
        example.setPageSize(req.getPageSize());
        if ( null != req.getEnterpriseId() && req.getEnterpriseId() > 0) {
            example.createCriteria().andEnterprIdEqualTo(req.getEnterpriseId());
        }
        example.setOrderByClause(" created desc ");
        List<OrderImportInfo> orderImportInfoList = orderImportInfoDao.selectByExample(example);
        if (null != orderImportInfoList && !orderImportInfoList.isEmpty()) {
            List<Long> optUserIdList = new ArrayList<>();
            for (OrderImportInfo orderImportInfo: orderImportInfoList) {
                Long optUserId = orderImportInfo.getOptUserId();
                if (!optUserIdList.contains(optUserId)) {
                    optUserIdList.add(optUserId);
                }
            }

            UserInfoExample userInfoExample = new UserInfoExample();
            userInfoExample.createCriteria().andEnterprIdIn(optUserIdList);
            userInfoExample.setPage(0);
            userInfoExample.setPageSize(100);
            List<UserInfo> userInfoList = userInfoDao.find(userInfoExample);
            Map<Long, UserInfo> userInfoMap = new HashMap<>();
            if (null != userInfoList && !userInfoList.isEmpty()) {
                for (UserInfo userInfo: userInfoList) {
                    userInfoMap.put(userInfo.getId(), userInfo);
                }
            }
            
            for (OrderImportInfo orderImportInfo: orderImportInfoList) {
                OrderImportResDto orderImportResDto = new OrderImportResDto();
                BeanUtils.copyProperties(orderImportInfo, orderImportResDto);
                UserInfo userInfo = userInfoMap.get(orderImportInfo.getOptUserId());
                if (userInfo != null) {
                    orderImportResDto.setOptUserName(userInfo.getLogin_name());
                }
                orderImportResDto.setImportTime(DateUtil.unixTime2Date(orderImportInfo.getCreated()));
                Double total = orderImportItemMapper.selectTotalPrice(orderImportInfo.getImportId());
                if (total != null) {
                    orderImportResDto.setTotal(total);
                } else {
                    orderImportResDto.setTotal(0.0);
                }
                EnterprUserInfo enterprUserInfo = enterprUserInfoMapper.selectByPrimaryKey(orderImportInfo.getEnterprId());
                if (null != enterprUserInfo) {
                    orderImportResDto.setEnterprName(enterprUserInfo.getEnterprName());
                }
                orderImportResDtoList.add(orderImportResDto);
            }
            count = orderImportInfoDao.countByExample(example);
        }
        jsonObject.put("list", orderImportResDtoList);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }

    @Override
    public Integer update(OrderImportInfo orderImportInfo) {
        return orderImportInfoDao.update(orderImportInfo);
    }

}
