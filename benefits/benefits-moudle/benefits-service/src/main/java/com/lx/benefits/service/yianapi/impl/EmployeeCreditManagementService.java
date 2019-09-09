package com.lx.benefits.service.yianapi.impl;

import com.lx.benefits.bean.base.dto.FailInfo;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfoExample;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditOperateDto;
import com.lx.benefits.bean.enums.CampaignType;
import com.lx.benefits.bean.enums.CreditType;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.mapper.creditoperateinfo.CreditOperateInfoMapper;
import com.lx.benefits.mapper.employeecreditinfo.EmployeeCreditInfoMapper;
import com.lx.benefits.service.yianapi.IEmployeeCreditManagementService;
import com.lx.benefits.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lidongri on 2019/1/6.
 */
@Service
public class EmployeeCreditManagementService implements IEmployeeCreditManagementService {

    private static String emp_credit_use_lock_key = "emp-credit-use-lock-key-";

    private static String emp_credit_packback_lock_key = "emp-credit-payback-lock-key-";

    private static int duration = 60;

    @Autowired
    private EmployeeCreditInfoMapper employeeCreditInfoMapper;

    @Autowired
    private CreditOperateInfoMapper creditOperateInfoMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtils cacheUtil;


    /**
     * 使用积分
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo use(EmployeeCreditInfo dto) {
        logger.info("emp-credit-use-info"+JsonUtil.toString(dto));
        if(dto.getCredit().intValue() <= 0) return new ResultInfo(new FailInfo(98100," point should bigger than 0"));
        String lockKey = emp_credit_use_lock_key + dto.getEmployeeId();
        boolean lock = cacheUtil.lock(lockKey, duration);
        if (!lock) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                logger.error("emp-credit-use-point-error:", e);
                new ResultInfo(new FailInfo(98000, "use point failed"));
            }
            lock = cacheUtil.lock(lockKey, duration);
            if (!lock) return new ResultInfo(new FailInfo(98000, "use point failed"));
        }
        try {
            EmployeeCreditInfoExample example = new EmployeeCreditInfoExample();
            example.createCriteria().andEmployeeIdEqualTo(dto.getEmployeeId());
            example.createCriteria().andIsDeletedEqualTo(false);
            List<EmployeeCreditInfo> allCredits = employeeCreditInfoMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(allCredits)) return new ResultInfo(new FailInfo(98001, "point not enough"));
            List<EmployeeCreditInfo> normalCredit = allCredits.stream().filter(e -> (e.getCreditType() == CreditType.normal.getCode() && e.getCampaignId() == CampaignType.all.ordinal())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(normalCredit)) return new ResultInfo(new FailInfo(98001, "point not enough"));

            EmployeeCreditInfo normal = normalCredit.get(0);
            if (dto.getCredit().intValue() > normal.getCredit().intValue()) return new ResultInfo(new FailInfo(98001, "point not enough"));
            EmployeeCreditOperateDto op = new EmployeeCreditOperateDto();
            op.setCredit(new BigDecimal(normal.getCredit().toString()).subtract(new BigDecimal(dto.getCredit().toString()).setScale(2)));
            op.setOriCredit(normal.getCredit());
            op.setEmployeeId(dto.getEmployeeId());
            op.setCreditId(normal.getCreditId());
            logger.info("emp-credit-use-point-info:" + JsonUtil.toString(op));
            //扣减积分
            int res = employeeCreditInfoMapper.updateCredit(op);
            if (res == 0) {
                logger.error("emp-credit-use-point-failed:" + JsonUtil.toString(op));
                return new ResultInfo(new FailInfo(98000, "failed"));
            }
            Long cur = ( System.currentTimeMillis() / 1000);
            //写入积分操作记录

            CreditOperateInfo operateInfo = new CreditOperateInfo();
            operateInfo.setAuditStatus(0);
            operateInfo.setAuditTime(0L);
            operateInfo.setAuditUserId(0L);
            operateInfo.setOptTime(cur);
            //操作类型{1: 运营积分充值; 2: 运营积分退款; 3: HR积分分配扣减企业积分; 4: HR积分回收增加企业积分; 5: HR积分分配员工增加积分; 6: HR积分回收员工扣减积分; 7: 员工下单扣积分; 8: 员工退货退款时退积分; 9: 运营从企业可用积分账户分配积分给员工 - 企业扣减积分; 10: 运营从企业可用积分账户分配积分给员工 - 员工增加积分}
            operateInfo.setOptType(7);
            operateInfo.setOptUserId(dto.getEmployeeId());
            operateInfo.setParentOptId(0L);
            operateInfo.setCampaignId((long) CampaignType.all.ordinal());
            operateInfo.setCreated(cur);
            operateInfo.setUpdated(cur);
            operateInfo.setCredit(dto.getCredit());
            operateInfo.setCreditType(CreditType.normal.getCode());
            operateInfo.setFinanceNo(dto.getFinanceNo());
            operateInfo.setIsDeleted(0);
            operateInfo.setOwnerUserId(dto.getEmployeeId());
            operateInfo.setRemark("client order");
            creditOperateInfoMapper.insert(operateInfo);

            return new ResultInfo();
        } finally {
            cacheUtil.unLock(lockKey);
        }


    }

    @Transactional
    @Override
    public ResultInfo payback(EmployeeCreditInfo dto) {
        logger.info("emp-credit-payback-info:"+JsonUtil.toString(dto));
        if(dto.getCredit().intValue() <= 0) return new ResultInfo(new FailInfo(98100," point should bigger than 0"));
        String lockKey = emp_credit_packback_lock_key + dto.getEmployeeId();
        boolean lock = cacheUtil.lock(lockKey, duration);
        if (!lock) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                logger.error("emp-credit-payback-point-error:", e);
                new ResultInfo(new FailInfo(98000, "payback point failed"));
            }
            lock = cacheUtil.lock(lockKey, duration);
            if (!lock) return new ResultInfo(new FailInfo(98000, "payback point failed"));
        }
        try {
            //查询操作记录
            CreditOperateInfo opQuery = new CreditOperateInfo();
            opQuery.setOwnerUserId(dto.getEmployeeId());
            opQuery.setFinanceNo(dto.getFinanceNo());
            opQuery.setOptType(7);
            opQuery.setCampaignId((long) CampaignType.all.ordinal());
            opQuery.setCreditType(CreditType.normal.getCode());


            List<CreditOperateInfo> hist = creditOperateInfoMapper.selectOperateInfo(opQuery);
            if (CollectionUtils.isEmpty(hist)) {
                logger.error("emp-credit-payback-operate-info-not-exist:" + JsonUtil.toString(dto));
                return new ResultInfo(new FailInfo(98002, "failed"));
            }
            CreditOperateInfo history = hist.get(0);
            //查询退款操作记录
            opQuery.setOptType(8);
            List<CreditOperateInfo> paybackHis = creditOperateInfoMapper.selectOperateInfo(opQuery);
            BigDecimal paybacked = new BigDecimal("0");
            if (!CollectionUtils.isEmpty(paybackHis)) {
                for (CreditOperateInfo info : paybackHis) {
                    paybacked = paybacked.add(new BigDecimal(info.getCredit().toString())).setScale(2);
                }
            }
            BigDecimal totalPayback = new BigDecimal(dto.getCredit().toString()).add(paybacked).setScale(2);
            if (totalPayback.doubleValue() > history.getCredit().doubleValue()) {
                logger.error("emp-credit-payback-return-credit-bigger-than-used:" + JsonUtil.toString(dto));
                return new ResultInfo(new FailInfo(98003, "return bigger than used"));
            }

            List<EmployeeCreditInfo> allCredits = employeeCreditInfoMapper.getUserCreditByMemberId(dto.getEmployeeId());
            if (CollectionUtils.isEmpty(allCredits)) return new ResultInfo(new FailInfo(98004, "point not exist"));
            List<EmployeeCreditInfo> normalCredit = allCredits.stream().filter(e -> (e.getCreditType() == CreditType.normal.getCode() && e.getCampaignId() == CampaignType.all.ordinal())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(normalCredit)) return new ResultInfo(new FailInfo(98004, "point not exist"));
            EmployeeCreditInfo normal = normalCredit.get(0);

            EmployeeCreditOperateDto op = new EmployeeCreditOperateDto();
            op.setCredit(new BigDecimal(normal.getCredit().toString()).add(new BigDecimal(dto.getCredit().toString()).setScale(2)));
            op.setOriCredit(normal.getCredit());
            op.setEmployeeId(dto.getEmployeeId());
            op.setCreditId(normal.getCreditId());
            logger.info("emp-credit-use-point-info:" + JsonUtil.toString(op));
            //返还积分
            int res = employeeCreditInfoMapper.updateCredit(op);
            if (res == 0) {
                logger.error("emp-credit-payback-failed:" + JsonUtil.toString(op));
                return new ResultInfo(new FailInfo(98000, "failed"));
            }

            Long cur = (System.currentTimeMillis() / 1000);
            //写入积分操作记录
            CreditOperateInfo operateInfo = new CreditOperateInfo();
            operateInfo.setAuditStatus(0);
            operateInfo.setAuditTime(0L);
            operateInfo.setAuditUserId(0L);
            operateInfo.setOptTime(cur);
            //操作类型{1: 运营积分充值; 2: 运营积分退款; 3: HR积分分配扣减企业积分; 4: HR积分回收增加企业积分; 5: HR积分分配员工增加积分; 6: HR积分回收员工扣减积分; 7: 员工下单扣积分; 8: 员工退货退款时退积分; 9: 运营从企业可用积分账户分配积分给员工 - 企业扣减积分; 10: 运营从企业可用积分账户分配积分给员工 - 员工增加积分}
            operateInfo.setOptType(8);
            operateInfo.setOptUserId(dto.getEmployeeId());
            operateInfo.setParentOptId(0L);
            operateInfo.setCampaignId((long) CampaignType.all.ordinal());
            operateInfo.setCreated(cur);
            operateInfo.setUpdated(cur);
            operateInfo.setCredit(dto.getCredit());
            operateInfo.setCreditType(CreditType.normal.getCode());
            operateInfo.setFinanceNo(dto.getFinanceNo());
            operateInfo.setIsDeleted(0);
            operateInfo.setOwnerUserId(dto.getEmployeeId());
            operateInfo.setRemark("client order pay back");
            creditOperateInfoMapper.insert(operateInfo);

            return new ResultInfo();
        }finally {
            cacheUtil.unLock(lockKey);
        }

    }

    @Override
    public ResultInfo<EmployeeCreditInfo> info(EmployeeCreditInfo dto) {
        List<EmployeeCreditInfo> allCredits = employeeCreditInfoMapper.getUserCreditByMemberId(dto.getEmployeeId());
        if (CollectionUtils.isEmpty(allCredits)) return new ResultInfo();
        List<EmployeeCreditInfo> normalCredit = allCredits.stream().filter(e -> (e.getCreditType() == CreditType.normal.getCode() && e.getCampaignId() == CampaignType.all.ordinal())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(normalCredit)) return new ResultInfo();

        EmployeeCreditInfo normal = normalCredit.get(0);
        EmployeeCreditInfo info = new EmployeeCreditInfo();
        info.setCredit(normal.getCredit());
        return new ResultInfo<>(info);
    }


    @Override
    public ResultInfo<CreditOperateInfo> getCreditOperateInfo(EmployeeCreditInfo dto) {
        //查询操作记录
        CreditOperateInfo opQuery = new CreditOperateInfo();
        opQuery.setOwnerUserId(dto.getEmployeeId());
        opQuery.setFinanceNo(dto.getFinanceNo());
        opQuery.setOptType(7);
        opQuery.setCampaignId((long) CampaignType.all.ordinal());
        opQuery.setCreditType(CreditType.normal.getCode());

        List<CreditOperateInfo> hist = creditOperateInfoMapper.selectOperateInfo(opQuery);
        if (CollectionUtils.isEmpty(hist)) {
            logger.error("emp-credit-payback-operate-info-not-exist:" + JsonUtil.toString(dto));
            return new ResultInfo();
        }
        CreditOperateInfo history = hist.get(0);

        return new ResultInfo<>(history);
    }
}
