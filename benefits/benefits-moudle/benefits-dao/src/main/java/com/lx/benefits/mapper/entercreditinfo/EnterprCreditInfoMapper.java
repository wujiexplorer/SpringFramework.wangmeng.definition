package com.lx.benefits.mapper.entercreditinfo;

import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfo;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 企业积分相关
 *
 * @author luojie
 */
public interface EnterprCreditInfoMapper {
    int countByExample(EnterprCreditInfoExample example);

    int deleteByExample(EnterprCreditInfoExample example);

    int deleteByPrimaryKey(Long creditId);

    int insert(EnterprCreditInfo record);

    int insertSelective(EnterprCreditInfo record);

    List<EnterprCreditInfo> selectByExample(EnterprCreditInfoExample example);

    EnterprCreditInfo selectByPrimaryKey(Long creditId);

    int updateByExampleSelective(@Param("record") EnterprCreditInfo record, @Param("example") EnterprCreditInfoExample example);

    int updateByExample(@Param("record") EnterprCreditInfo record, @Param("example") EnterprCreditInfoExample example);

    int updateByPrimaryKeySelective(EnterprCreditInfo record);

    int updateByPrimaryKey(EnterprCreditInfo record);

    int updateValidCreditByEnterprId(Map<String,Object> params);

    int updateEmployeeCreditInfoReduceCredit(EnterprCreditInfo enterprCreditInfo);

    int updateEmployeeCreditInfoAddCredit(EnterprCreditInfo enterprCreditInfo);
}