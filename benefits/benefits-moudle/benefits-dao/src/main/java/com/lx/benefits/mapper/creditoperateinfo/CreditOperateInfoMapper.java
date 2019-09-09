package com.lx.benefits.mapper.creditoperateinfo;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.dto.enterprise.EnterprCreditDetailDto;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfoExample;
import com.lx.benefits.bean.vo.enterpr.EmployeeCreditDistributionBean;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 积分操作记录
 *
 * @author luojie
 */
public interface CreditOperateInfoMapper {
    int countByExample(CreditOperateInfoExample example);

    int deleteByExample(CreditOperateInfoExample example);

    int deleteByPrimaryKey(Long optId);

    int insert(CreditOperateInfo record);

    int insertSelective(CreditOperateInfo record);

    List<CreditOperateInfo> selectByExample(CreditOperateInfoExample example);

    CreditOperateInfo selectByPrimaryKey(Long optId);

    int updateByExampleSelective(@Param("record") CreditOperateInfo record, @Param("example") CreditOperateInfoExample example);

    int updateByExample(@Param("record") CreditOperateInfo record, @Param("example") CreditOperateInfoExample example);

    int updateByPrimaryKeySelective(CreditOperateInfo record);

    int updateByPrimaryKey(CreditOperateInfo record);

    List<CreditOperateInfo> pageByMap(HashMap<String, Object> paramMap);

    List<CreditOperateInfo> listByParentOptId(Long parentOptId);

    List<CreditOperateInfo> selectPage(Map<String,Object> map);

    List<CreditOperateInfo> selectOperateInfo(CreditOperateInfo creditOperateInfo);

    Double selectSumByCampaignId(Long campaignId);

    Double selectTotalByCampaignId(Long campaignId);


    List<CreditOperateInfo> creditOptInfo(Map<String,Object> params);

    List<CreditOperateInfo> festivalHistory(CreditOperateInfo creditOperateInfo);

    List<CreditOperateInfo> selectDownExcel(Long campaignId);

    Double selectTotalCredit(CreditOperateInfo creditOperateInfo);

    Integer creditOptInfocount(Map<String,Object> params);
    
    BigDecimal paymentCredit(@Param("enterprId") Long enterprId);
    
    BigDecimal refundCredit(@Param("enterprId") Long enterprId);
    
    BigDecimal recycleCredit(@Param("enterprId") Long enterprId);
    
    List<EnterprCreditDetailDto> getCreditInfoDetailPay(@Param("enterprId") Long enterprId,@Param("pageBean") PageBean pageBean);
    
    List<EnterprCreditDetailDto> getCreditInfoDetailRefund(@Param("enterprId") Long enterprId,@Param("pageBean") PageBean pageBean);
    
    int creditCount(@Param("enterprId") Long enterprId); 
    
    List<CreditOperateInfo> recycleCreditRecord(@Param("enterprId") Long enterprId,@Param("pageBean") PageBean pageBean);
    
    int creditRecordCount(@Param("enterprId") Long enterprId);
    
    int countCreditDistribution(@Param("enterprId") Long enterprId);
    
    List<EmployeeCreditDistributionBean> queryCreditDistribution(@Param("enterprId") Long enterprId,@Param("pageBean") PageBean pageBean);
    
    List<EmployeeCreditDistributionBean> queryOptType(@Param("enterprId") Long enterprId,@Param("pageBean") PageBean pageBean);

	boolean isParticipate(@Param("campaignId")Long campaignId, @Param("employeeId")Long employeeId);
	
	boolean isAuthority(@Param("campaignId")Long campaignId, @Param("employeeId")Long employeeId);

	int updateParticipate(@Param("campaignId")Long campaignId, @Param("employeeId")Long employeeId);
}