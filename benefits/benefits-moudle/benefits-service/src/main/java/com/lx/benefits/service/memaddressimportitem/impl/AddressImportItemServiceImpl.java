package com.lx.benefits.service.memaddressimportitem.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.address.AddressImportItemQueryDto;
import com.lx.benefits.bean.dto.admin.address.AddressImportItemResDto;
import com.lx.benefits.bean.entity.memaddressimportitem.AddressImportItem;
import com.lx.benefits.bean.entity.memaddressimportitem.AddressImportItemExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.dao.memaddressimportitem.AddressImportItemDao;
import com.lx.benefits.service.memaddressimportitem.AddressImportItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author unknow on 2018-12-26 17:08.
 */
@Service
public class AddressImportItemServiceImpl implements AddressImportItemService {

    private final static Logger logger = LoggerFactory.getLogger(AddressImportItemServiceImpl.class);
    
    @Autowired
    private AddressImportItemDao addressImportItemDao;


    @Override
    public Long insertSelective(AddressImportItem record) {
        return addressImportItemDao.insertSelective(record);
    }

    @Override
    public JSONObject selectByExample(AddressImportItemQueryDto req) {
        req = null == req ? new AddressImportItemQueryDto() : req;
        JSONObject jsonObject = new JSONObject();
        List<AddressImportItemResDto> addressImportResDtoList = new ArrayList<>();
        int count = 0;
        AddressImportItemExample example = new AddressImportItemExample();
        example.setPage(req.getPage());
        example.setPageSize(req.getPageSize());
        example.createCriteria().andImportIdEqualTo(req.getImportId());
        if (req.getStatus() != null) {
            example.createCriteria().andStatusEqualTo(req.getStatus());
        }
        List<AddressImportItem> addressImportItemList = addressImportItemDao.selectByExample(example);
        if (null != addressImportItemList && !addressImportItemList.isEmpty()) {
            try {
                for (AddressImportItem orderImportItem: addressImportItemList) {
                    AddressImportItemResDto addressImportItemResDto = new AddressImportItemResDto();
                    BeanUtils.copyProperties(orderImportItem, addressImportItemResDto);
                    addressImportItemResDto.setCreated(DateUtil.unixTime2Date(orderImportItem.getCreated()));
                    addressImportResDtoList.add(addressImportItemResDto);
                }
            } catch (Exception e) {
                logger.info("AddressImportItemServiceImpl exception {}",e.getMessage());
            }

            count = addressImportItemDao.countByExample(example);
        }
        jsonObject.put("list", addressImportResDtoList);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }

    @Override
    public Integer update(AddressImportItem orderImportItem, AddressImportItemExample example) {
        return addressImportItemDao.update(orderImportItem, example);
    }

}
