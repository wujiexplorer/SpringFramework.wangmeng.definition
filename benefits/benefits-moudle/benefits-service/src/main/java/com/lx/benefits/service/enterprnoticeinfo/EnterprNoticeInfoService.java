package com.lx.benefits.service.enterprnoticeinfo;



import com.lx.benefits.bean.dto.admin.customized.EnterprNoticeReqDto;
import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfo;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface EnterprNoticeInfoService {

    Long insertSelective(EnterprNoticeInfo record);

    Integer updateSelective(EnterprNoticeInfo record);

    EnterprNoticeInfo findById(Long enterpriseId, boolean isFront);

    List<EnterprNoticeInfo> findByIdList(Long enterpriseId, boolean isFront);

    Boolean setNotice(EnterprNoticeReqDto req);
    
    Boolean setEmail(EnterprNoticeReqDto req);
    
    EnterprNoticeReqDto findEmailDetail(Long enterprId);

}
