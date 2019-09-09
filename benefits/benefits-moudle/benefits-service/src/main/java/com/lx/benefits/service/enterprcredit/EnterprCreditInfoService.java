package com.lx.benefits.service.enterprcredit;




import com.lx.benefits.bean.dto.enterpriseadmin.credit.EnterprCreditInfoDto;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfo;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfoExample;

import java.util.List;
import java.util.Map;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface EnterprCreditInfoService {

    Long insert(EnterprCreditInfo enterprCreditInfo);
    
    Integer update(EnterprCreditInfo enterprCreditInfo, EnterprCreditInfoExample example);

    List<EnterprCreditInfo> find(EnterprCreditInfoExample example);


    Integer count(EnterprCreditInfoExample example);

    List<EnterprCreditInfoDto> findByEnterprId(Long enterprId);

    EnterprCreditInfoDto findByEnterprIdOne(Long enterprId);

    int updateValidCreditByEnterprId(Map<String,Object> params);
}
