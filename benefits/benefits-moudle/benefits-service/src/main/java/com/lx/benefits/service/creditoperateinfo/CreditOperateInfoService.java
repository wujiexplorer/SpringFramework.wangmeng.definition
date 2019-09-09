package com.lx.benefits.service.creditoperateinfo;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.FLPageResDto;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketQueryReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.CreditOperateInfoDto;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfoExample;
import com.lx.benefits.bean.vo.enterpr.EmployeeCreditDistributionBean;

import java.util.List;
import java.util.Map;

/**
 * 积分操作记录service
 *
 * @author luojie
 */
public interface CreditOperateInfoService {


    /**
     * 分页获取积分操作记录
     *
     * @param enterpriseId 企业id
     * @param page         当期页码
     * @param pageSize     每页大小
     * @param optType      操作类型
     * @return
     */
    FLPageResDto<CreditOperateInfoDto> pageByEnterpriseId(Long enterpriseId, Integer page, Integer pageSize, Integer optType);

    /**
     * 插入积分操作记录表
     *
     * @param operateInfo 操作记录数据
     * @return
     */
    Long insert(CreditOperateInfo operateInfo);

    /**
     * 分页获取积分操作记录
     *
     * @param parentOptId 父类id
     * @return
     */
    List<CreditOperateInfoDto> listByParentOptId(Long parentOptId);

    List<CreditOperateInfoDto> listByOptId(Long parentOptId);

    List<CreditOperateInfo> find(CreditOperateInfoExample example);

    Integer count(CreditOperateInfoExample example);

    CreditOperateInfo findByOptId(Long optId);

    Integer update(CreditOperateInfo creditOperateInfo, CreditOperateInfoExample example);


    //审核积分操作
    Integer actionCredit(Long optId, String auditAction);

    JSONObject findList(FestivalPacketQueryReqDto req);

    JSONObject useList(Map<String,Object> map);

    List<CreditOperateInfo> creditOptInfo(Map<String,Object> dto);

    Integer creditOptInfocount(Map<String,Object> dto);

    JSONObject festivalHistory(CreditOperateInfo creditOperateInfo);

    List<CreditOperateInfo> selectDownExcel(Long campaignId);

    Double selectTotalCredit(CreditOperateInfo creditOperateInfo);
    
    PageResultBean<EmployeeCreditDistributionBean> employeeCreditDistribution(PageBean pageBean);

	boolean isParticipate(Long campaignId, Long employeeId);

	boolean isAuthority(Long campaignId, Long employeeId);

	int updateParticipate(Long campaignId, Long employeeId);
}