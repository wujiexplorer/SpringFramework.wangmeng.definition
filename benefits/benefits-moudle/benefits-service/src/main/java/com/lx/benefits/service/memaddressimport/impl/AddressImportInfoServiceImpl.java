package com.lx.benefits.service.memaddressimport.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.FLPageDto;
import com.lx.benefits.bean.dto.admin.address.AddressImportResDto;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.memadressimport.AddressImportInfo;
import com.lx.benefits.bean.entity.memadressimport.AddressImportInfoExample;
import com.lx.benefits.bean.entity.userinfo.UserInfo;
import com.lx.benefits.bean.entity.userinfo.UserInfoExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.dao.memadressimport.AddressImportInfoDao;
import com.lx.benefits.dao.userinfo.UserInfoDao;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.memaddressimport.AddressImportInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author unknow on 2018-12-26 17:08.
 */
@Service
public class AddressImportInfoServiceImpl implements AddressImportInfoService {
    
    @Autowired
    private AddressImportInfoDao addressImportInfoDao;

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private EnterprUserInfoMapper enterprUserInfoMapper;


    @Override
    public Long insertSelective(AddressImportInfo record) {
        return addressImportInfoDao.insertSelective(record);
    }

    @Override
    public JSONObject selectByExample(FLPageDto req) {
        req = null == req ? new FLPageDto() : req;
        JSONObject jsonObject = new JSONObject();
        List<AddressImportResDto> orderImportResDtoList = new ArrayList<>();
        int count = 0;
        AddressImportInfoExample example = new AddressImportInfoExample();
        example.setPage(req.getPage());
        example.setPageSize(req.getPageSize());
        example.setOrderByClause(" created desc ");
        // 标记一下
        example.getStart();
        List<AddressImportInfo> addressImportInfoList = addressImportInfoDao.selectByExample(example);
        if (null != addressImportInfoList && !addressImportInfoList.isEmpty()) {
            for (AddressImportInfo addressImportInfo: addressImportInfoList) {
                AddressImportResDto orderImportResDto = new AddressImportResDto();
                BeanUtils.copyProperties(addressImportInfo, orderImportResDto);
                orderImportResDto.setImportTime(DateUtil.unixTime2Date(addressImportInfo.getCreated()));
                orderImportResDtoList.add(orderImportResDto);
                UserInfoExample userInfoExample = new UserInfoExample();
                userInfoExample.createCriteria().andIdEqualTo(addressImportInfo.getOptUserId());
                UserInfo userInfo = userInfoDao.fetchOne(userInfoExample);
                if (userInfo != null) {
                    orderImportResDto.setOptUserName(userInfo.getLogin_name());
                }
                EnterprUserInfo enterprUserInfo = enterprUserInfoMapper.selectByPrimaryKey(addressImportInfo.getEnterprId());
                if (enterprUserInfo != null) {
                    orderImportResDto.setEnterprName(enterprUserInfo.getEnterprName());
                }
            }
            count = addressImportInfoDao.countByExample(example);
        }
        jsonObject.put("list", orderImportResDtoList);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }

    @Override
    public Integer update(AddressImportInfo addressImportInfo) {
        return addressImportInfoDao.update(addressImportInfo);
    }

}
