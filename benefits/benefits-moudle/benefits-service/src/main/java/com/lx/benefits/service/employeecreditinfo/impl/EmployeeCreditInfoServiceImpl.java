package com.lx.benefits.service.employeecreditinfo.impl;

import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.bo.pay.OrderCreditBO;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.*;
import com.lx.benefits.bean.enums.*;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketQueryReqDto;
import com.lx.benefits.bean.dto.enterprise.EmployeeCreditRes;
import com.lx.benefits.bean.entity.card.EmployeeCardCredit;
import com.lx.benefits.bean.entity.card.EmployeeCardCreditCondition;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfoExample;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfoExample;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfo;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.dao.creditoperateinfo.CreditOperateInfoDao;
import com.lx.benefits.dao.employeecreditinfo.EmployeeCreditInfoDao;
import com.lx.benefits.dao.employeeuserinfo.EmployeeUserInfoDao;
import com.lx.benefits.dao.entercreditinfo.EnterprCreditInfoDao;
import com.lx.benefits.mapper.card.EmployeeCardCreditMapper;
import com.lx.benefits.mapper.employeecreditinfo.EmployeeCreditInfoMapper;
import com.lx.benefits.service.client.YibaoApiService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 员工积分serviceImpl
 *
 * @author luojie
 */
@Service
public class EmployeeCreditInfoServiceImpl implements EmployeeCreditInfoService {

    private final static Logger logger = LoggerFactory.getLogger(EmployeeCreditInfoServiceImpl.class);

    @Autowired
    private EmployeeCreditInfoDao employeeCreditInfoDao;
    /**
     * 企业积分
     */
    @Autowired
    private EnterprCreditInfoDao enterprCreditInfoDao;

    /**
     * 积分操作记录
     */
    @Autowired
    private CreditOperateInfoDao creditOperateInfoDao;
    /**
     * 企业员工
     */
    @Autowired
    private EmployeeUserInfoDao employeeUserInfoDao;

    @Autowired
    private EmployeeCreditInfoMapper employeeCreditInfoMapper;
    @Autowired
    private EmployeeCardCreditMapper employeeCardCreditMapper;
    @Autowired
    private YibaoApiService yibaoApiService;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int distributionList(CreditDistributionDto distributionDto) {
        if (distributionDto == null || CollectionUtils.isEmpty(distributionDto.getDetailDtoList())) {
            return 0;
        }
        int i;
        //先插入企业积分变动总操作记录
        Long now = DateUtil.getNowTimestamp10();
        CreditOperateInfo operateInfo = new CreditOperateInfo();
        operateInfo.setOwnerUserId(distributionDto.getEnterprId());
        operateInfo.setCreditType(distributionDto.getCreditType());
        operateInfo.setCampaignId(0L);
        operateInfo.setCredit(BigDecimal.ZERO);
        operateInfo.setOptUserId(distributionDto.getOptUserId());
        operateInfo.setOptTime(now);
        operateInfo.setAuditUserId(distributionDto.getOptUserId());
        operateInfo.setAuditTime(now);
        operateInfo.setOptType(OptTypeEnum.HR_DISTRIBUTION_REDUCE.getValue());
        operateInfo.setAuditStatus(AuditStatusEnum.COMPLETE.getValue());
        operateInfo.setRemark(distributionDto.getRemark());
        i = creditOperateInfoDao.insertSelective(operateInfo);
        if (i == 0) {
            throw new RuntimeException("积分变动失败");
        }

        for (CreditDistributionDetailDto dto : distributionDto.getDetailDtoList()) {
            if (BigDecimal.ZERO.compareTo(dto.getCredit()) >= 0) {
                continue;
            }
            try {
                dto.setOptUserId(distributionDto.getOptUserId());
                dto.setEnterprId(distributionDto.getEnterprId());
                dto.setCreditType(distributionDto.getCreditType());
                dto.setRemark(distributionDto.getRemark());
                int d = distribution(dto, operateInfo);
                if (d > 0) {
                    i = i + d;
                    logger.info("积分分配成功：{}", JSONObject.toJSONString(dto));
                }
            } catch (Exception e) {
                logger.info("积分分配失败：{},{}", JSONObject.toJSONString(dto), e);
                throw new RuntimeException("企业不存在员工号:"+dto.getEmployeeNo());
            }
        }
        return i;
    }

    /**
     * 积分分配员工具体实现方法
     *
     * @param dto               数据
     * @param parentOperateInfo 父类
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int distribution(CreditDistributionDetailDto dto, CreditOperateInfo parentOperateInfo) {

        String employeeNo = dto.getEmployeeNo();
        if (null == employeeNo || employeeNo.trim().isEmpty()) {
            throw new RuntimeException("员工工号不能为空");
        }
        EmployeeUserInfoExample example = new EmployeeUserInfoExample();
        example.createCriteria().andEmployeeNoEqualTo(employeeNo).andEnterprIdEqualTo(dto.getEnterprId());
        EmployeeUserInfo employeeUserInfo = employeeUserInfoDao.fetchOne(example);

        if (null == employeeUserInfo) {
            throw new RuntimeException("获取员工信息失败");
        }

        if (StringUtils.isEmpty(dto.getEmployeeNo()) || dto.getCredit() == null
                || dto.getCreditType() == null || dto.getEnterprId() == null
                || dto.getOptUserId() == null || BigDecimal.ZERO.compareTo(dto.getCredit()) == 0) {
            return 0;
        }

        //先更新员工积分（先更新 更新失败插入积分数据）
        EmployeeCreditInfo info = new EmployeeCreditInfo();
        info.setEmployeeId(employeeUserInfo.getEmployeeId());
        info.setCreditType(dto.getCreditType());
        info.setCredit(dto.getCredit());
        int i = employeeCreditInfoDao.updateEmployeeCreditInfoAddCredit(info);
        if (i == 0) {
            info.setCampaignId(0L);
            info.setStatus(1);
            i = employeeCreditInfoDao.insert(info);
            if (i <= 0) {
                throw new RuntimeException("更新积分失败");
            }
        }

        //插入员工积分变动记录
        Long now = DateUtil.getNowTimestamp10();

        CreditOperateInfo operateInfo = new CreditOperateInfo();
        operateInfo.setOwnerUserId(employeeUserInfo.getEmployeeId());
        operateInfo.setParentOptId(parentOperateInfo.getOptId());
        operateInfo.setCreditType(dto.getCreditType());
        operateInfo.setCampaignId(0L);
        operateInfo.setCredit(dto.getCredit());
        operateInfo.setOptUserId(dto.getOptUserId());
        operateInfo.setOptTime(now);
        operateInfo.setAuditUserId(dto.getOptUserId());
        operateInfo.setAuditTime(now);
        operateInfo.setRemark(dto.getRemark());
        operateInfo.setOptType(OptTypeEnum.HR_DISTRIBUTION_USER_ADD.getValue());
        operateInfo.setAuditStatus(AuditStatusEnum.COMPLETE.getValue());
        i = creditOperateInfoDao.insertSelective(operateInfo);
        if (i <= 0) {
            throw new RuntimeException("积分记录插入失败");
        }

        //再更新企业积分（先更新企业积分）
        EnterprCreditInfo enterprCreditInfo = new EnterprCreditInfo();
        enterprCreditInfo.setValidCredit(dto.getCredit());
        enterprCreditInfo.setEnterprId(dto.getEnterprId());
        //扣普通积分
        enterprCreditInfo.setCreditType((byte) 1);
        i = enterprCreditInfoDao.updateEmployeeCreditInfoReduceCredit(enterprCreditInfo);
        if (i <= 0) {
            throw new RuntimeException("企业积分扣减失败");
        }

        //更新积分变动总表
        parentOperateInfo.setCredit(parentOperateInfo.getCredit().add(dto.getCredit()));
        i = creditOperateInfoDao.updateByPrimaryKeySelective(parentOperateInfo);
        if (i <= 0) {
            throw new RuntimeException("积分记录插入失败");
        }
        return i;
    }

    /**
     * 批量回收积分 实现方法 该方法实现是查询出积分 直接置为0 然后记录操作记录 员工积分变动并发下会导致修改失败
     *
     * @param dto 数据类型
     * @return
     */
    @Override
    public int recoveryList(CreditRecoveryDto dto) {

        int result = 0;
        List<EmployeeUserInfo> employeeUserInfos = null;
        //全部回收
        if (!CreditRecoveryRangeEnum.PART.name().equals(dto.getRecoveryRange())) {
            dto.setDetailDtoList(new ArrayList<>());
            //查询所有员工信息
            employeeUserInfos = employeeUserInfoDao.findByEnterPrId(dto.getEnterprId());
        }
        //部分回收
        if (CreditRecoveryRangeEnum.PART.name().equals(dto.getRecoveryRange())) {
            if (CollectionUtils.isEmpty(dto.getDetailDtoList())) {
                return 0;
            }
            List<String> employeeNos = new ArrayList<>();
            dto.getDetailDtoList().forEach(e -> employeeNos.add(e.getEmployeeNo()));
            employeeUserInfos = employeeUserInfoDao.findByEnterPrIdAndEmployeeNos(dto.getEnterprId(), employeeNos);
        }

        //验证员工数据
        if (CollectionUtils.isEmpty(employeeUserInfos)) {
            return 0;
        }

        //循环取出id
        List<Long> employeeIdList = new ArrayList<>();
        employeeUserInfos.forEach(e -> {
            employeeIdList.add(e.getEmployeeId());
        });

        //查询所有员工积分数额列表
        List<EmployeeCreditInfo> employeeCreditInfoListByIds = new ArrayList<>();
        Long campaignId = dto.getCampaignId();
        if (campaignId == null || campaignId < 1) {
            employeeCreditInfoListByIds = employeeCreditInfoDao
                    .findEmployeeCreditInfoListByIds(employeeIdList, dto.getCreditType());
        } else {
            employeeCreditInfoListByIds = employeeCreditInfoDao
                    .findCreditInfoListByIds(employeeIdList, dto.getCampaignId());
        }

        if (CollectionUtils.isEmpty(employeeCreditInfoListByIds)) {
            return 0;
        }

        //循环组装数据
        employeeCreditInfoListByIds.forEach(e -> {
            if (dto.getCreditType() != 4) {
                CreditRecoveryDetailDto detailDto = new CreditRecoveryDetailDto();
                detailDto.setCreditId(detailDto.getCreditId());
                detailDto.setCredit(e.getCredit());
                detailDto.setCreditType(dto.getCreditType());
                detailDto.setEnterprId(dto.getEnterprId());
                detailDto.setOptUserId(dto.getEnterprId());
                detailDto.setRemark(dto.getRemark());
                detailDto.setEmployeeId(e.getEmployeeId());
                dto.getDetailDtoList().add(detailDto);
            }
        });

        //验证员工积分数据
        if (CollectionUtils.isEmpty(dto.getDetailDtoList())) {
            return 0;
        }

        //插入企业积分变动记录
        Long now = DateUtil.getNowTimestamp10();
        CreditOperateInfo operateInfo = new CreditOperateInfo();
        operateInfo.setOwnerUserId(dto.getEnterprId());
        operateInfo.setCreditType(dto.getCreditType());
        operateInfo.setCampaignId(0L);
        operateInfo.setCredit(BigDecimal.ZERO);
        operateInfo.setOptUserId(dto.getOptUserId());
        operateInfo.setOptTime(now);
        operateInfo.setAuditUserId(dto.getOptUserId());
        operateInfo.setAuditTime(now);
        operateInfo.setOptType(OptTypeEnum.HR_RECOVERY_ADD.getValue());
        operateInfo.setAuditStatus(AuditStatusEnum.COMPLETE.getValue());
        operateInfo.setRemark(dto.getRemark());
        int i = creditOperateInfoDao.insertSelective(operateInfo);
        if (i == 0) {
            return 0;
        }

        for (CreditRecoveryDetailDto detailDto : dto.getDetailDtoList()) {
            if (BigDecimal.ZERO.compareTo(detailDto.getCredit()) >= 0) {
                continue;
            }
            try {
                int recovery = recovery(detailDto, operateInfo);
                if (recovery > 0) {
                    result = result + recovery;
                    logger.info("积分回收成功：{}", JSONObject.toJSONString(detailDto));
                }
            } catch (Exception e) {
                logger.info("积分回收出错：{},{}", JSONObject.toJSONString(detailDto), e);
            }
        }
        return result;
    }

    @Override
    public EmployeeCreditInfo getByEmployeeIdAndGrainId(EmployeeCreditInfo employeeCreditInfo) {
        if(employeeCreditInfo == null){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return employeeCreditInfoDao.getByEmployeeIdAndGrainId(employeeCreditInfo);
        }catch (Exception e){
            throw new RuntimeException("根据员工ID和颗粒ID查询员工积分信息出错！",e);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recoveryEmployee(EmployeeRecoveryReqDto reqDto) {
        int result = 0;
        List<EmployeeCreditInfo> employeeCreditInfoListByIds = new ArrayList<>();
        Integer creditStatus = reqDto.getCreditStatus();
        List<Long> employeeIdList = new ArrayList<>();
        employeeIdList.add(reqDto.getEmployeeId());
        //全部回收
        if (EmployeeRecoveryEnum.All.getValue().equals(creditStatus)) {
            //查询员工积分数额列表
            employeeCreditInfoListByIds = employeeCreditInfoDao
                    .findEmployeeCreditInfoListByIds(employeeIdList,null);
        } else  {
            // 普通积分 or 节日积分
            employeeCreditInfoListByIds = employeeCreditInfoDao
                    .findEmployeeCreditInfoListByIds(employeeIdList,creditStatus);
        }

        if (CollectionUtils.isEmpty(employeeCreditInfoListByIds)) {
            return 0;
        }
        List<CreditRecoveryDetailDto> detailDtoList = new ArrayList<>();
        //循环组装数据
        for(EmployeeCreditInfo e : employeeCreditInfoListByIds) {
            Double credit = e.getCredit().doubleValue();
            if (credit > 0 && e.getCreditType() != 4) {
                CreditRecoveryDetailDto detailDto = new CreditRecoveryDetailDto();
                detailDto.setCreditId(detailDto.getCreditId());
                detailDto.setCredit(e.getCredit());
                detailDto.setCreditType(e.getCreditType());
                detailDto.setEnterprId(reqDto.getEnterprId());
                detailDto.setOptUserId(reqDto.getEnterprId());
                detailDto.setRemark(reqDto.getRemark());
                detailDto.setEmployeeId(e.getEmployeeId());
                detailDtoList.add(detailDto);
            }
        }
        //验证员工积分数据
        if (CollectionUtils.isEmpty(detailDtoList)) {
            return 0;
        }

        //插入企业积分变动记录
        Long now = DateUtil.getNowTimestamp10();
        CreditOperateInfo operateInfo = new CreditOperateInfo();
        operateInfo.setOwnerUserId(reqDto.getEnterprId());
        operateInfo.setCreditType(creditStatus);
        operateInfo.setCampaignId(0L);
        operateInfo.setCredit(BigDecimal.ZERO);
        operateInfo.setOptUserId(reqDto.getEmployeeId());
        operateInfo.setOptTime(now);
        operateInfo.setAuditUserId(reqDto.getEmployeeId());
        operateInfo.setAuditTime(now);
        operateInfo.setOptType(OptTypeEnum.HR_RECOVERY_ADD.getValue());
        operateInfo.setAuditStatus(AuditStatusEnum.COMPLETE.getValue());
        operateInfo.setRemark(reqDto.getRemark());
        int i = creditOperateInfoDao.insertSelective(operateInfo);
        if (i == 0) {
            return 0;
        }

        for (CreditRecoveryDetailDto detailDto : detailDtoList) {
            if (BigDecimal.ZERO.compareTo(detailDto.getCredit()) >= 0) {
                continue;
            }
            try {
                int recovery = recovery(detailDto, operateInfo);
                if (recovery > 0) {
                    logger.info("积分回收成功：{}", JSONObject.toJSONString(detailDto));
                    result = result + recovery;
                } else {
                    throw new RuntimeException("积分回收失败");
                }
            } catch (Exception e) {
                logger.info("积分回收出错：{},{}", JSONObject.toJSONString(detailDto), e);
            }
        }
        return result;
    }

    /**
     * 积分回收操作
     *
     * @param detailDto         回收数据
     * @param parentOperateInfo 操作父类记录
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recovery(CreditRecoveryDetailDto detailDto, CreditOperateInfo parentOperateInfo) {

        //先更新员工积分（先更新 更新失败直接返回）
        EmployeeCreditInfo info = new EmployeeCreditInfo();
        info.setEmployeeId(detailDto.getEmployeeId());
        if (!detailDto.getCreditType().equals(EmployeeRecoveryEnum.All.getValue())) {
            info.setCreditType(detailDto.getCreditType());
        }
        info.setCredit(detailDto.getCredit());
        int i = employeeCreditInfoDao.updateEmployeeCreditInfoReduceCredit(info);
        if (i <= 0) {
            return 0;
        }

        //插入员工积分变动记录
        Long now = DateUtil.getNowTimestamp10();

        CreditOperateInfo operateInfo = new CreditOperateInfo();
        operateInfo.setOwnerUserId(detailDto.getEmployeeId());
        operateInfo.setParentOptId(parentOperateInfo.getOptId());
        operateInfo.setCreditType(detailDto.getCreditType());
        operateInfo.setCampaignId(0L);
        operateInfo.setCredit(detailDto.getCredit());
        operateInfo.setOptUserId(detailDto.getOptUserId());
        operateInfo.setOptTime(now);
        operateInfo.setAuditUserId(detailDto.getOptUserId());
        operateInfo.setAuditTime(now);
        operateInfo.setOptType(OptTypeEnum.HR_RECOVERY_USER_REDUCE.getValue());
        operateInfo.setAuditStatus(AuditStatusEnum.COMPLETE.getValue());
        operateInfo.setRemark(detailDto.getRemark());
        i = creditOperateInfoDao.insertSelective(operateInfo);
        if (i <= 0) {
            throw new RuntimeException("员工积分变动插入失败");
        }


        //再更新企业积分（先更新企业积分）
        EnterprCreditInfo enterprCreditInfo = new EnterprCreditInfo();
        enterprCreditInfo.setValidCredit(detailDto.getCredit());
        enterprCreditInfo.setEnterprId(detailDto.getEnterprId());
        //扣普通积分
        enterprCreditInfo.setCreditType(CreditTypeEnum.PUTONG.getValue().byteValue());
        i = enterprCreditInfoDao.updateEmployeeCreditInfoAddCredit(enterprCreditInfo);
        if (i <= 0) {
            throw new RuntimeException("企业积分回收失败");
        }


        //插入企业积分变动记录
        parentOperateInfo.setCredit(parentOperateInfo.getCredit().add(detailDto.getCredit()));
        i = creditOperateInfoDao.updateByPrimaryKeySelective(parentOperateInfo);
        if (i <= 0) {
            throw new RuntimeException("企业积分变动更新失败");
        }

        return i;
    }


    /**
     * 交易积分变动
     * @param orderCreditBO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyEmployeeCredit4Order(OrderCreditBO orderCreditBO) {
        //先更新员工积分（先更新 更新失败直接返回）交易逆向退款时积分为负值
        EmployeeCreditInfo info = new EmployeeCreditInfo();
        info.setEmployeeId(orderCreditBO.getEmployeeId());
        info.setCreditType(orderCreditBO.getCreditType());
		if (orderCreditBO.getCreditType().equals(CreditTypeEnum.PUTONG.getValue())
				|| orderCreditBO.getCreditType().equals(CreditTypeEnum.ADVANCESALE.getValue())) {
			info.setCampaignId(0L);
			info.setCreditType(CreditTypeEnum.PUTONG.getValue());
		} else {
			info.setCampaignId(orderCreditBO.getCampaignId());
		}
		BigDecimal reduceCredit = orderCreditBO.getReduceCredit();
		if (CreditTypeEnum.PUTONG.getValue().equals(info.getCreditType())) {// 普通积分可以用会员卡积分替代
			if (orderCreditBO.getOptType().equals(OptTypeEnum.USER_REFUND_ADD.getValue())) {// 退积分
				BigDecimal cardAmount = orderCreditBO.getCardAmount();
				if (cardAmount != null && cardAmount.compareTo(BigDecimal.ZERO) > 0) {
					BigDecimal cardReduceAmount = cardAmount.compareTo(reduceCredit) > 0 ? reduceCredit : cardAmount;
					int updateCount = employeeCardCreditMapper.updateCardCredit(orderCreditBO.getEmployeeId().intValue(), cardReduceAmount);
					if (updateCount > 0) {
						reduceCredit = reduceCredit.subtract(cardReduceAmount);
					}
				}
			} else {// 扣减积分
				EmployeeCardCredit employeeCardCreditInfo = this.getEmployeeCardCreditInfo(orderCreditBO.getEmployeeId());
				if (employeeCardCreditInfo != null && employeeCardCreditInfo.getCardCredit().compareTo(BigDecimal.ZERO) > 0) {// 会员卡积分有剩余
					BigDecimal cardCredit = employeeCardCreditInfo.getCardCredit();
					BigDecimal cardReduceAmount = cardCredit.compareTo(reduceCredit) > 0 ? reduceCredit : cardCredit;
					int updateCount = employeeCardCreditMapper.updateCardCredit(orderCreditBO.getEmployeeId().intValue(), BigDecimal.ZERO.subtract(cardReduceAmount));
					if (updateCount > 0) {
						orderCreditBO.setCardAmount(cardReduceAmount);
						reduceCredit = reduceCredit.subtract(cardReduceAmount);
					}
				}
			}
		}
		if (orderCreditBO.getOptType().equals(OptTypeEnum.USER_REFUND_ADD.getValue())) {
			info.setCredit(BigDecimal.ZERO.subtract(reduceCredit));
		} else {
			info.setCredit(reduceCredit);
		}
		if (info.getCredit().compareTo(BigDecimal.ZERO) != 0) {
			SessionShopInfo sessionEmployeeInfo = SessionContextHolder.getSessionEmployeeInfo();
			if (sessionEmployeeInfo != null && yibaoApiService.isBelongTo(sessionEmployeeInfo.getEnterprId())) {// 易豹积分扣除处理
				if (!sessionEmployeeInfo.getEmployeeId().equals(orderCreditBO.getEmployeeId())) {
					throw new BusinessException("用户信息不一致!");
				}
				if (info.getCredit().compareTo(BigDecimal.ZERO) < 0) {
					throw new BusinessException("所属企业不支持积分退还!");
				}
				boolean preReducePoints = yibaoApiService.preReducePoints(sessionEmployeeInfo.getEmployeeNo(), info.getCredit(), orderCreditBO.getPayOrderNumber());
				if (!preReducePoints) {
					throw new BusinessException("积分不足,扣除失败");
				}
			} else if (employeeCreditInfoDao.updateEmployeeCreditInfo4Order(info) <= 0) {
				throw new BusinessException("积分不足,扣除失败");
			}
		}
        //插入员工积分变动记录
        Long now = DateUtil.getNowTimestamp10();
        CreditOperateInfo operateInfo = new CreditOperateInfo();
        operateInfo.setCreditType(orderCreditBO.getCreditType());
        operateInfo.setCampaignId(orderCreditBO.getCampaignId());
        operateInfo.setCredit(orderCreditBO.getReduceCredit());
        operateInfo.setOptUserId(orderCreditBO.getEmployeeId());
        operateInfo.setOwnerUserId(orderCreditBO.getEmployeeId());
        operateInfo.setOptTime(now);
        operateInfo.setAuditUserId(orderCreditBO.getEmployeeId());
        operateInfo.setAuditTime(now);
        operateInfo.setOptType(orderCreditBO.getOptType());
        operateInfo.setAuditStatus(AuditStatusEnum.COMPLETE.getValue());
        operateInfo.setFinanceNo(orderCreditBO.getPayOrderNumber());
        operateInfo.setRemark(orderCreditBO.getRemark());
        if (creditOperateInfoDao.insertSelective(operateInfo) <= 0) {
            throw new BusinessException("员工积分变动插入失败");
        }
    }





    @Override
    public int alloCredit(Long employeeId, Long campaignId, Integer creditType, BigDecimal credit) {
        EmployeeCreditInfoExample example = new EmployeeCreditInfoExample();
        example.createCriteria().andEmployeeIdEqualTo(employeeId)
        .andCampaignIdEqualTo(campaignId)
        .andCreditTypeEqualTo(creditType);
        List<EmployeeCreditInfo> employeeCreditInfoList = employeeCreditInfoDao.select(example);
        if(null != employeeCreditInfoList && employeeCreditInfoList.size() > 0) {
            EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoList.get(0);
            EmployeeCreditInfo uEmployee = new EmployeeCreditInfo();
            uEmployee.setCredit(employeeCreditInfo.getCredit().add(credit));
            return employeeCreditInfoDao.update(uEmployee, example);
        }else {
            EmployeeCreditInfo employeeCreditInfo = new EmployeeCreditInfo();
            employeeCreditInfo.setCampaignId(campaignId);
            employeeCreditInfo.setCreditType(creditType);
            employeeCreditInfo.setCredit(credit);
            employeeCreditInfo.setEmployeeId(employeeId);
            int id = employeeCreditInfoDao.insert(employeeCreditInfo);
            return id;
        }
    }

    @Override
    public int insert(EmployeeCreditInfo employeeCreditInfo) {
        if(null == employeeCreditInfo){
            throw new BusinessException("employeeCreditInfo参数不能为空！");
        }
        try{
            return employeeCreditInfoDao.insert(employeeCreditInfo);
        }catch (Exception e){
            throw new RuntimeException("新增员工积分信息出错！",e);
        }
    }

    @Override
	public List<EmployeeCreditInfo> getCreditInfo(Long employeeId) {
		SessionShopInfo sessionEmployeeInfo = SessionContextHolder.getSessionEmployeeInfo();
		if (sessionEmployeeInfo != null) {
			if (!sessionEmployeeInfo.getEmployeeId().equals(employeeId)) {
				throw new BusinessException("用户信息不一致!");
			}
			if (yibaoApiService.isBelongTo(sessionEmployeeInfo.getEnterprId())) {// 处理易豹企业用户
				BigDecimal availablePoints = yibaoApiService.getAvailablePoints(sessionEmployeeInfo.getEmployeeNo());
				EmployeeCreditInfo employeeCreditInfo = new EmployeeCreditInfo();
				employeeCreditInfo.setCreditType(CreditTypeEnum.PUTONG.getValue());
				employeeCreditInfo.setCampaignId(0L);
				employeeCreditInfo.setCredit(availablePoints);
				return Collections.singletonList(employeeCreditInfo);
			}
		}
		EmployeeCreditInfoExample example = new EmployeeCreditInfoExample();
		example.createCriteria().andEmployeeIdEqualTo(employeeId);
		return employeeCreditInfoDao.selectByExample(example);
	}

    @Override
    public JSONObject findList(FestivalPacketQueryReqDto req) {
        JSONObject jsonObject = new JSONObject();
        EmployeeCreditInfoExample example = new EmployeeCreditInfoExample();
        example.createCriteria().andCampaignIdEqualTo(req.getCampaignId());
        example.setPage(req.getPage());
        example.setOrderByClause(" created desc");
        Integer page = req.getPage();
        Integer pageSize = req.getPageSize();
        example.setPage((page - 1) * pageSize);
        example.setPageSize(pageSize);

        int count = employeeCreditInfoDao.countByExample(example);
        List<EmployeeCreditRes> employeeCreditInfoList = new ArrayList<>();
        if (count > 0) {
            List<EmployeeCreditInfo> list = employeeCreditInfoDao.selectByExample(example);
            if (null != list && !list.isEmpty()) {
                for (EmployeeCreditInfo employeeCreditInfo: list) {
                    EmployeeCreditRes employeeCreditRes = new EmployeeCreditRes();
                    BeanUtils.copyProperties(employeeCreditInfo,employeeCreditRes);
                    employeeCreditRes.setCreated(DateUtil.unixTime2Date(employeeCreditInfo.getCreated()));
                    employeeCreditInfoList.add(employeeCreditRes);
                }
            }
        }
        jsonObject.put("list", employeeCreditInfoList);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }

	@Override
	public EmployeeCreditInfo getEmployeeCreditInfo(Long employeeId, Long campaignId) {
		EmployeeCreditInfo employeeCreditInfo = null;
		SessionShopInfo sessionEmployeeInfo = SessionContextHolder.getSessionEmployeeInfo();
		EmployeeCreditInfo employeeCreditInfoTemp = employeeCreditInfoDao.selectEmployeeCreditInfo(employeeId,
                campaignId);
		if (sessionEmployeeInfo != null) {
			if (!sessionEmployeeInfo.getEmployeeId().equals(employeeId)) {
				throw new BusinessException("用户信息不一致!");
			}
			if (yibaoApiService.isBelongTo(sessionEmployeeInfo.getEnterprId())) {// 处理易豹企业用户
				BigDecimal availablePoints = yibaoApiService.getAvailablePoints(sessionEmployeeInfo.getEmployeeNo());
				employeeCreditInfo = new EmployeeCreditInfo();
				employeeCreditInfo.setCreditType(CreditTypeEnum.PUTONG.getValue());
				employeeCreditInfo.setCampaignId(campaignId);
				employeeCreditInfo.setCredit(availablePoints);
				//易豹用户参加促销活动处理
				employeeCreditInfo.setReceivedSeckillIds(employeeCreditInfoTemp.getReceivedSeckillIds());
				employeeCreditInfo.setReceivedVouchers(employeeCreditInfoTemp.getReceivedVouchers());
			}
		}
		if (employeeCreditInfo == null) {
			employeeCreditInfo = employeeCreditInfoDao.selectEmployeeCreditInfo(employeeId, campaignId);
		}
		if (Objects.equals(campaignId, 0L)) {
			EmployeeCardCredit employeeCardCreditInfo = this.getEmployeeCardCreditInfo(employeeId);
			if (employeeCardCreditInfo != null && employeeCardCreditInfo.getCardCredit().compareTo(BigDecimal.ZERO) > 0) {
				if (employeeCreditInfo == null) {
					employeeCreditInfo = new EmployeeCreditInfo();
					employeeCreditInfo.setCredit(employeeCardCreditInfo.getCardCredit());
					employeeCreditInfo.setCreditType(CreditTypeEnum.PUTONG.getValue());
				} else {
					employeeCreditInfo.setCredit(employeeCreditInfo.getCredit().add(employeeCardCreditInfo.getCardCredit()));
				}
			}
		}
		return employeeCreditInfo;
	}

    @Override
    public ResultInfo<EmployeeCreditInfo> info(EmployeeCreditInfo dto) {
        EmployeeCreditInfoExample example = new EmployeeCreditInfoExample();
        example.createCriteria().andEmployeeIdEqualTo(dto.getEmployeeId());
        List<EmployeeCreditInfo> allCredits = employeeCreditInfoDao.selectByExample(example);
        if (org.springframework.util.CollectionUtils.isEmpty(allCredits)) return new ResultInfo<>();
        List<EmployeeCreditInfo> normalCredit = allCredits.stream().filter(e -> (e.getCreditType() == CreditType.normal.getCode() && e.getCampaignId() == CampaignType.all.ordinal())).collect(Collectors.toList());
        if (org.springframework.util.CollectionUtils.isEmpty(normalCredit)) return new ResultInfo<>();

        EmployeeCreditInfo normal = normalCredit.get(0);
        EmployeeCreditInfo info = new EmployeeCreditInfo();
        info.setCredit(normal.getCredit());
        return new ResultInfo<>(info);
    }

    @Override
    public List<EmployeeCreditResDto> festivalInfo(Long employeeId) {
        return employeeCreditInfoMapper.festivalInfo(employeeId);
    }

    /**
     * 离职企业员工积分处理
     * @param enterprId
     */
    @Override
    public void handelEnterCredit(Long enterprId) {
        // 查询离职员工
        EmployeeUserInfoExample example = new EmployeeUserInfoExample();
        example.createCriteria().andLeaveStatusEqualTo(true).andIsDeletedEqualTo(false)
                .andEnterprIdEqualTo(enterprId);
        List<EmployeeUserInfo> employeeUserInfoList = employeeUserInfoDao.selectByExampleList(example);
        for (EmployeeUserInfo employeeUserInfo : employeeUserInfoList) {
            Integer leaveTime = employeeUserInfo.getLeaveTime();
            if (null != leaveTime && leaveTime > 0) {
                boolean recovery = DateUtil.isRecoveryTime(leaveTime);
                if (recovery) {
                    logger.info("定时任务回收离职员工{}积分,离职日期 {}",employeeUserInfo.getEmployeeName(),DateUtil.unixTime2Date(leaveTime));
                    EmployeeRecoveryReqDto reqDto = new EmployeeRecoveryReqDto();
                    reqDto.setEnterprId(enterprId);
                    reqDto.setEmployeeId(employeeUserInfo.getEmployeeId());
                    reqDto.setCreditStatus(EmployeeRecoveryEnum.All.getValue());
                    reqDto.setRemark("离职员工到期，回收积分");
                    recoveryEmployee(reqDto);
                }
            }
        }
    }

    @Override
    public int updateByEmployeeIdSelective(EmployeeCreditInfo record) {
        if(null == record){
            throw new BusinessException("record参数不能为空！");
        }
        try{
            return employeeCreditInfoDao.updateByEmployeeIdSelective(record);
        }catch(Exception e){
            throw new BusinessException("根据员工ID更新员工积分信息出错！",e);
        }
    }

    @Override
    public int updateGrainIdByEmployeeIdSelective(EmployeeCreditInfo employeeCreditInfo) {
        if(null == employeeCreditInfo){
            throw new BusinessException("员工积分信息参数不能为空！");
        }
        try{
            return employeeCreditInfoDao.updateGrainIdByEmployeeIdSelective(employeeCreditInfo);
        }catch (Exception e){
            throw new RuntimeException("根据员工ID更新颗粒号出错！",e);
        }
    }

    @Override
	public EmployeeCardCredit getEmployeeCardCreditInfo(Long employeeId) {
		Assert.notNull(employeeId, "用户ID不能为空!");
		EmployeeCardCreditCondition example = new EmployeeCardCreditCondition();
		example.createCriteria().andEmployeeIdEqualTo(employeeId.intValue());
		List<EmployeeCardCredit> employeeCardCredits = employeeCardCreditMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(employeeCardCredits)) {
			return null;
		}
		return employeeCardCredits.get(0);
	}

}
