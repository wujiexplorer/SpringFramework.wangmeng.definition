package com.lx.benefits.dao.entercreditinfo;



import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfo;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfoExample;

import java.util.List;
import java.util.Map;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface EnterprCreditInfoDao {

    Long insert(EnterprCreditInfo enterprCreditInfo);


    Integer update(EnterprCreditInfo enterprCreditInfo, EnterprCreditInfoExample example);

    List<EnterprCreditInfo> find(EnterprCreditInfoExample example);


    Integer count(EnterprCreditInfoExample example);

    int updateByPrimaryKeySelective(EnterprCreditInfo record);
    
    int updateEmployeeCreditInfoReduceCredit(EnterprCreditInfo enterprCreditInfo);

    EnterprCreditInfo findByEnterprIdOne(EnterprCreditInfoExample example);

    int updateEmployeeCreditInfoAddCredit(EnterprCreditInfo enterprCreditInfo);

    int updateValidCreditByEnterprId(Map<String,Object> params);
}
