package com.lx.benefits.service.enterprnoticeinfo.impl;


import com.lx.benefits.bean.dto.admin.customized.EnterprNoticeReqDto;
import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfo;
import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfoExample;
import com.lx.benefits.bean.util.SessionEnterpriseInfo;
import com.lx.benefits.dao.enterprnoticeinfo.EnterprNoticeInfoDao;
import com.lx.benefits.mapper.enterprnoticeinfo.EnterprNoticeInfoMapper;
import com.lx.benefits.service.enterprnoticeinfo.EnterprNoticeInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/22.
 */
@Service
public class EnterprNoticeInfoServiceImpl implements EnterprNoticeInfoService {

    @Autowired
    private EnterprNoticeInfoDao enterprNoticeInfoDao;
    
    @Autowired
    private EnterprNoticeInfoMapper enterprNoticeInfoMapper;
    
    @Override
    public Long insertSelective(EnterprNoticeInfo record) {
        return enterprNoticeInfoDao.insert(record);
    }

    @Override
    public Integer updateSelective(EnterprNoticeInfo record) {
        return enterprNoticeInfoDao.update(record);
    }

    @Override
    public EnterprNoticeInfo findById(Long enterpriseId, boolean isFront) {
        EnterprNoticeInfoExample example = new EnterprNoticeInfoExample();
        example.createCriteria().andEnterprIdEqualTo(enterpriseId);
        List<EnterprNoticeInfo> list = enterprNoticeInfoDao.find(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<EnterprNoticeInfo> findByIdList(Long enterpriseId, boolean isFront) {
        EnterprNoticeInfoExample example = new EnterprNoticeInfoExample();
        example.createCriteria().andEnterprIdEqualTo(enterpriseId);
        return enterprNoticeInfoDao.find(example);
    }

    @Override
    public Boolean setNotice(EnterprNoticeReqDto req) {
        Long id = req.getId();
        EnterprNoticeInfo enterprNoticeInfo = new EnterprNoticeInfo();
        BeanUtils.copyProperties(req,enterprNoticeInfo);
        if(null == id || id < 1) {
            enterprNoticeInfoDao.insert(enterprNoticeInfo);
        }else {
            enterprNoticeInfoDao.update(enterprNoticeInfo);
        }
        return true;
    }

	@Override
	public Boolean setEmail(EnterprNoticeReqDto req) {
		Integer rowUpdate = enterprNoticeInfoMapper.updateEmail(req);
		if(rowUpdate == 0) {
			Integer rowInsert = enterprNoticeInfoMapper.insertEmail(req);
			if(rowInsert == 1) {
				return true;
			}
		}else if(rowUpdate == 1){
			return true;
		}
		return false;
	}

	@Override
	public EnterprNoticeReqDto findEmailDetail(Long enterprId) {
		EnterprNoticeReqDto  EnterprEmail = enterprNoticeInfoMapper.findEmailDetail(enterprId);
		return EnterprEmail;
	}



}
