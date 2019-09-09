package com.lx.benefits.service.employeecreditinfo;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.bo.pay.OrderCreditBO;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketQueryReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.*;
import com.lx.benefits.bean.entity.card.EmployeeCardCredit;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import java.math.BigDecimal;
import java.util.List;

/**
 * 员工积分service
 *
 * @author luojie
 */
public interface EmployeeCreditInfoService {


    /**
     * 积分分配操作
     *
     * @param distributionDto 分配详情
     * @return
     */
    int distributionList(CreditDistributionDto distributionDto);

    /**
     * 积分分配操作
     *
     * @param dto               数据
     * @param creditOperateInfo 操作父类记录
     * @return
     */
    int distribution(CreditDistributionDetailDto dto, CreditOperateInfo creditOperateInfo);

    /**
     * 积分回收操作
     *
     * @param dto 数据类型
     * @return
     */
    int recoveryList(CreditRecoveryDto dto);


    EmployeeCreditInfo getByEmployeeIdAndGrainId(EmployeeCreditInfo employeeCreditInfo);

    /**
     * 积分回收操作
     *
     * @param
     * @return
     */
    int recoveryEmployee(EmployeeRecoveryReqDto reqDto);

    /**
     * 积分回收操作
     *
     * @param creditOperateInfo 操作父类
     * @param detailDto         回收列表
     * @return
     */
    int recovery(CreditRecoveryDetailDto detailDto, CreditOperateInfo creditOperateInfo);

    /**
     * 用户积分变动 -交易
     * @param orderCreditBO
     * @return
     */
    void modifyEmployeeCredit4Order(OrderCreditBO orderCreditBO);

    int alloCredit(Long employeeId, Long campaignId, Integer creditType, BigDecimal credit);

    int insert(EmployeeCreditInfo employeeCreditInfo);

    List<EmployeeCreditInfo> getCreditInfo(Long employeeId);

    JSONObject findList(FestivalPacketQueryReqDto req);

	/**
	 * 获取员工积分
	 * 
	 * @param employeeId
	 *            员工ID
	 * @param campaignId
	 *            活动ID，如果活动ID为0，则将会员卡积分与积分相加
	 * @return
	 */
	EmployeeCreditInfo getEmployeeCreditInfo(Long employeeId, Long campaignId);

    ResultInfo<EmployeeCreditInfo> info(EmployeeCreditInfo dto);

    List<EmployeeCreditResDto> festivalInfo(Long employeeId);


    void handelEnterCredit(Long enterId);

    int updateByEmployeeIdSelective(EmployeeCreditInfo record);

    int updateGrainIdByEmployeeIdSelective(EmployeeCreditInfo employeeCreditInfo);

    EmployeeCardCredit getEmployeeCardCreditInfo(Long employeeId);

}