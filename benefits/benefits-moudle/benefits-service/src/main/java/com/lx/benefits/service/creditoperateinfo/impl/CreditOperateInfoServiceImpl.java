package com.lx.benefits.service.creditoperateinfo.impl;

import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.FLPageResDto;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.enums.AuditStatusEnum;
import com.lx.benefits.bean.enums.CreditTypeEnum;
import com.lx.benefits.bean.enums.OptTypeEnum;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketQueryReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.CreditOperateInfoDto;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfoExample;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.enterpr.EmployeeCreditDistributionBean;
import com.lx.benefits.dao.creditoperateinfo.CreditOperateInfoDao;
import com.lx.benefits.mapper.creditoperateinfo.CreditOperateInfoMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 积分操作记录serviceImpl
 *
 * @author luojie
 */
@Service
public class CreditOperateInfoServiceImpl implements CreditOperateInfoService {

    @Autowired
    private CreditOperateInfoDao creditOperateInfoDao;

    @Autowired
    private EnterprUserInfoMapper enterprUserInfoMapper;

    @Autowired
    private CreditOperateInfoMapper creditOperateInfoMapper;

    /**
     * 分页获取积分操作记录 实现方法
     *
     * @param enterpriseId id
     * @param page         当期页码
     * @param pageSize     每页大小
     * @param optType      操作类型
     * @return
     */
    @Override
    public FLPageResDto<CreditOperateInfoDto> pageByEnterpriseId(Long enterpriseId, Integer page, Integer pageSize, Integer optType) {
        HashMap<String, Object> queryMap = new HashMap<>();
        int start = (page - 1) * pageSize;
        int end = page * pageSize;

        List<Integer> optTypes = new ArrayList<Integer>();
        optTypes.add(optType);
        if (optType.equals(OptTypeEnum.HR_DISTRIBUTION_REDUCE.getValue())) {
            optTypes.add(OptTypeEnum.DISTRIBUTION_TO_EMPLOYEE.getValue());
            optTypes.add(OptTypeEnum.BIRTHDAY_CREDIT.getValue());
        } else {
            optTypes.add(OptTypeEnum.ADMIN_DISTRIBUTION_HR_ADD.getValue());
        }
        queryMap.put("page", start);
        queryMap.put("pageSize", end);
        queryMap.put("ownerUserId", enterpriseId);
        queryMap.put("optType", optTypes);
        queryMap.put("parentOptId", 0);
        FLPageResDto<CreditOperateInfoDto> pageResDto = new FLPageResDto<>();
        pageResDto.setResult(new ArrayList<>());
        pageResDto.setPage(page);
        pageResDto.setPageSize(pageSize);

        List<CreditOperateInfo> list = creditOperateInfoDao.pageByMap(queryMap);
        CreditOperateInfoExample example = new CreditOperateInfoExample();

        example.createCriteria().andOwnerUserIdEqualTo(enterpriseId).andOptTypeIn(optTypes)
                .andParentOptIdEqualTo(0L);
        int i = creditOperateInfoDao.countByExample(example);
        pageResDto.setCount(i);

        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(u -> {
                CreditOperateInfoDto dto = new CreditOperateInfoDto();
                BeanUtils.copyProperties(u, dto);
                pageResDto.getResult().add(dto);
            });
        }


        return pageResDto;
    }

    @Override
    public Long insert(CreditOperateInfo operateInfo) {
        Long now = DateUtil.getNowTimestamp10();
        operateInfo.setCreated(now);
        operateInfo.setUpdated(now);
        operateInfo.setOptTime(now);
        creditOperateInfoDao.insertSelective(operateInfo);
        return operateInfo.getOptId();
    }

    @Override
    public List<CreditOperateInfoDto> listByParentOptId(Long parentOptId) {
        List<CreditOperateInfo> list = creditOperateInfoDao.listByParentOptId(parentOptId);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<CreditOperateInfoDto> dtoList = new ArrayList<>();
        List<Long> enterList = new ArrayList<>();
        list.forEach(e -> enterList.add(e.getOptUserId()));
        EnterprUserInfoCondition condition=new EnterprUserInfoCondition();
        condition.createCriteria().andEnterprIdIn(enterList);
        List<EnterprUserInfo> enterPrUserInfos =enterprUserInfoMapper.selectByExample(condition);
        HashMap<Long, EnterprUserInfo> enterPrUserInfoHashMap = new HashMap<>();
        enterPrUserInfos.forEach(e -> enterPrUserInfoHashMap.put(e.getEnterprId(), e));
        list.forEach(e -> {
            CreditOperateInfoDto dto = new CreditOperateInfoDto();
            BeanUtils.copyProperties(e, dto);
            Optional.ofNullable(enterPrUserInfoHashMap.get(e.getOptUserId()))
                    .ifPresent(u -> dto.setOptUserName(u.getLoginName()));
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public List<CreditOperateInfoDto> listByOptId(Long optId) {
        CreditOperateInfoExample example = new CreditOperateInfoExample();
        example.createCriteria().andOptIdEqualTo(optId);

        List<CreditOperateInfo> list = creditOperateInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<CreditOperateInfoDto> dtoList = new ArrayList<>();
        List<Long> enterList = new ArrayList<>();
        list.forEach(e -> enterList.add(e.getOptUserId()));

        EnterprUserInfoCondition condition=new EnterprUserInfoCondition();
        condition.createCriteria().andEnterprIdIn(enterList);
        List<EnterprUserInfo> enterPrUserInfos = enterprUserInfoMapper.selectByExample(condition);
        HashMap<Long, EnterprUserInfo> enterPrUserInfoHashMap = new HashMap<>();
        enterPrUserInfos.forEach(e -> enterPrUserInfoHashMap.put(e.getEnterprId(), e));
        list.forEach(e -> {
            CreditOperateInfoDto dto = new CreditOperateInfoDto();
            BeanUtils.copyProperties(e, dto);
            Optional.ofNullable(enterPrUserInfoHashMap.get(e.getOptUserId()))
                    .ifPresent(u -> dto.setOptUserName(u.getLoginName()));
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public List<CreditOperateInfo> find(CreditOperateInfoExample example) {
        return creditOperateInfoDao.find(example);
    }

    @Override
    public Integer count(CreditOperateInfoExample example) {
        return creditOperateInfoDao.countByExample(example);
    }

    @Override
    public CreditOperateInfo findByOptId(Long optId) {
        CreditOperateInfoExample example = new CreditOperateInfoExample();
        example.createCriteria().andOptIdEqualTo(optId);
        List<CreditOperateInfo> creditOperateInfoList = creditOperateInfoDao.find(example);
        if(null != creditOperateInfoList && creditOperateInfoList.size() > 0) {
            return creditOperateInfoList.get(0);
        }
        return null;
    }

    @Override
    public Integer update(CreditOperateInfo creditOperateInfo, CreditOperateInfoExample example) {
        return creditOperateInfoDao.update(creditOperateInfo, example);
    }

    @Override
    public Integer actionCredit(Long optId, String auditAction) {
        List<Integer> values = new ArrayList<>();
        values.add(OptTypeEnum.DISTRIBUTION.getValue());
        values.add(OptTypeEnum.RECOVERY.getValue());
        CreditOperateInfoExample example = new CreditOperateInfoExample();
        example.createCriteria().andOptIdEqualTo(optId).andOptTypeIn(values);
        if(auditAction.equals(AuditStatusEnum.COMPLETE.name())) {
            Integer auditStatus = 2;
            CreditOperateInfo creditOperateInfo = new CreditOperateInfo();
            creditOperateInfo.setAuditUserId(SessionContextHolder.getSessionFuliInfo().getAdminId());
            creditOperateInfo.setAuditTime(DateUtil.getNowTimestamp10());
            creditOperateInfo.setAuditStatus(auditStatus);
            Integer result = creditOperateInfoDao.update(creditOperateInfo, example);
            return result;
        }else if(auditAction.equals(AuditStatusEnum.UNPAID.name())) {
            Integer auditStatus = 3;
            CreditOperateInfo creditOperateInfo = new CreditOperateInfo();
            creditOperateInfo.setAuditStatus(auditStatus);
            creditOperateInfo.setAuditUserId(SessionContextHolder.getSessionFuliInfo().getAdminId());
            creditOperateInfo.setAuditTime(DateUtil.getNowTimestamp10());
            Integer result = creditOperateInfoDao.update(creditOperateInfo, example);
            return result;
        }
        return 0;
    }

    @Override
    public JSONObject findList(FestivalPacketQueryReqDto req) {
        return null;
    }

    @Override
    public JSONObject useList(Map<String,Object> map) {
        JSONObject jsonObject = new JSONObject();
        CreditOperateInfoExample example =  new CreditOperateInfoExample();
        //  积分分配
        example.createCriteria().andParentOptIdEqualTo(Long.parseLong(map.get("parentOptId").toString()));
        int count = creditOperateInfoDao.countByExample(example);
        List<CreditOperateInfo> creditOperateList = new ArrayList<>();
        if (count > 0) {
            creditOperateList = creditOperateInfoMapper.selectPage(map);
            for (CreditOperateInfo creditOperateInfo :creditOperateList) {
                // 1: 普通积分; 2: 节日礼金; 3: 认可激励积分
                if (creditOperateInfo.getCreditType().equals(1)) {
                    creditOperateInfo.setCreditTypeDesc("普通积分");
                } else if (creditOperateInfo.getCreditType().equals(2)) {
                    creditOperateInfo.setCreditTypeDesc("节日积分");
                } else if (creditOperateInfo.getCreditType().equals(3)) {
                    creditOperateInfo.setCreditTypeDesc(" 认可激励");
                }
            }
        }
        jsonObject.put("count",count);
        jsonObject.put("list",creditOperateList);
        return jsonObject;
    }


    @Override
    public List<CreditOperateInfo> creditOptInfo(Map<String,Object> params) {
        return creditOperateInfoMapper.creditOptInfo(params);
    }

    @Override
    public Integer creditOptInfocount(Map<String,Object> params) {
        return creditOperateInfoMapper.creditOptInfocount(params);
    }

    @Override
    public JSONObject festivalHistory(CreditOperateInfo creditOperateInfo) {
        JSONObject jsonObject = new JSONObject();
        List<CreditOperateInfo> list = creditOperateInfoMapper.festivalHistory(creditOperateInfo);
        for (CreditOperateInfo creditOp : list) {
            creditOp.setOptTimeFormat(DateUtil.unixTime2Date(creditOp.getCreated()));
        }
        jsonObject.put("list",list);

        return Response.succ(jsonObject);
    }

    @Override
    public List<CreditOperateInfo> selectDownExcel(Long campaignId) {
        return creditOperateInfoMapper.selectDownExcel(campaignId);
    }

    @Override
    public Double selectTotalCredit(CreditOperateInfo creditOperateInfo) {
        return creditOperateInfoMapper.selectTotalCredit(creditOperateInfo);
    }

	@Override
	public PageResultBean<EmployeeCreditDistributionBean> employeeCreditDistribution(PageBean pageBean) {
		Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
		int count = creditOperateInfoMapper.countCreditDistribution(enterprId);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		if (count == 0) {
			return new PageResultBean<>(page, pageSize, count, Collections.emptyList());
		}
		List<EmployeeCreditDistributionBean> employeeCreditDistribution = creditOperateInfoMapper.queryCreditDistribution(enterprId, pageBean);
		List<EmployeeCreditDistributionBean> optTypes = creditOperateInfoMapper.queryOptType(enterprId, pageBean);
		for (int i = 0; i < employeeCreditDistribution.size(); i++) {

			if(employeeCreditDistribution.get(i).getCreditType() == CreditTypeEnum.PUTONG.getValue()) {		
				employeeCreditDistribution.get(i).setCreditTypeDesc(CreditTypeEnum.PUTONG.getDesc());
			}else if(employeeCreditDistribution.get(i).getCreditType() == CreditTypeEnum.JIERILIJIN.getValue()) {
				employeeCreditDistribution.get(i).setCreditTypeDesc(CreditTypeEnum.JIERILIJIN.getDesc());
			}else if(employeeCreditDistribution.get(i).getCreditType() == CreditTypeEnum.RENKEJILI.getValue()) {
				employeeCreditDistribution.get(i).setCreditTypeDesc(CreditTypeEnum.RENKEJILI.getDesc());
			}
			for (int j = 0; j < optTypes.size(); j++) {
				if(employeeCreditDistribution.get(i).getParentOptId().compareTo(optTypes.get(j).getOptId()) == 0) {
					if(optTypes.get(j).getOptType() == OptTypeEnum.HR_DISTRIBUTION_REDUCE.getValue()) {
						employeeCreditDistribution.get(i).setHandleType(OptTypeEnum.HR_DISTRIBUTION_REDUCE.getDesc());
					}else if(optTypes.get(j).getOptType() == OptTypeEnum.DISTRIBUTION_TO_EMPLOYEE.getValue()) {
						employeeCreditDistribution.get(i).setHandleType(OptTypeEnum.DISTRIBUTION_TO_EMPLOYEE.getDesc());
					}else if(optTypes.get(j).getOptType() == OptTypeEnum.BIRTHDAY_CREDIT.getValue()){
						employeeCreditDistribution.get(i).setHandleType(OptTypeEnum.BIRTHDAY_CREDIT.getDesc());
					}
				}
			}
		}
		return new PageResultBean<>(page, pageSize, count, employeeCreditDistribution);
	}

	@Override
	public boolean isParticipate(Long campaignId, Long employeeId) {
		return creditOperateInfoMapper.isParticipate(campaignId,employeeId);
	}
	
	@Override
	public boolean isAuthority(Long campaignId, Long employeeId) {
		return creditOperateInfoMapper.isAuthority(campaignId,employeeId);
	}
	
	@Override
	public int updateParticipate(Long campaignId, Long employeeId) {
		return creditOperateInfoMapper.updateParticipate(campaignId,employeeId);
	}
}
