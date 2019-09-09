package com.lx.benefits.service.account.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.agent.AgentAccountInfo;
import com.lx.benefits.bean.vo.account.AccountBankInfoExcelModel;
import com.lx.benefits.bean.vo.account.AccountBankReq;
import com.lx.benefits.bean.vo.account.AccountBankVO;
import com.lx.benefits.mapper.account.AccountBankMapper;
import com.lx.benefits.mapper.agent.AgentAccountInfoMapper;
import com.lx.benefits.service.account.AccountBankService;
import com.lx.benefits.utils.DateTimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User:wangmeng
 * Date:2019/7/15
 * Time:17:51
 * Version:2.x
 * Description:TODO
 **/
@Service
public class AccountBankServiceImpl implements AccountBankService {

    @Autowired
    private AccountBankMapper accountBankMapper;

    @Autowired
    private AgentAccountInfoMapper agentAccountInfoMapper;


    @Override
    public PageResultBean<AccountBankVO> getAccountBankDetail(AccountBankReq accountBankReq) {
        try{
            if(accountBankReq != null) {
                Date startDate = DateTimeUtils.stringToTime(accountBankReq.getStartTime(), DateTimeUtils.DEFAULT_TIME_FORMAT);
                Date endDate = DateTimeUtils.stringToTime(accountBankReq.getEndTime(), DateTimeUtils.DEFAULT_TIME_FORMAT);
                accountBankReq.setStartDate(startDate);
                accountBankReq.setEndDate(endDate);
                Integer count = accountBankMapper.countAccountBank(accountBankReq);
                accountBankReq.setOffset(accountBankReq.getOffset());
                accountBankReq.setLimit(accountBankReq.getPageSize());
                List<AccountBankVO> list = accountBankMapper.getAccountBankDetail(accountBankReq);
                for(AccountBankVO accountBankVO : list){
                    Integer agentId = accountBankVO.getAgentId();
                    if(null != agentId){
                        AgentAccountInfo  agentAccountInfo = agentAccountInfoMapper.selectByPrimaryKey(agentId);
                        if(null != agentAccountInfo){
                            accountBankVO.setAgentName(agentAccountInfo.getAgentName());
                        }
                    }
                    accountBankVO.setPrice(accountBankVO.getPrice().setScale(2, RoundingMode.HALF_UP));
                    accountBankVO.setThirdCostprice(accountBankVO.getThirdCostprice().setScale(2,RoundingMode.HALF_UP));
                    accountBankVO.setPointAmount(accountBankVO.getPointAmount()== null ? BigDecimal.ZERO : accountBankVO.getPointAmount().setScale(2,RoundingMode.HALF_UP));
                    accountBankVO.setCardAmount(accountBankVO.getCardAmount() == null ? BigDecimal.ZERO : accountBankVO.getCardAmount().setScale(2,RoundingMode.HALF_UP));
                    accountBankVO.setReturnAccountPoint(accountBankVO.getReturnAccountPoint()== null ? BigDecimal.ZERO : accountBankVO.getReturnAccountPoint().setScale(2,RoundingMode.HALF_UP));
                }
                return new PageResultBean<>(accountBankReq.getPage(),accountBankReq.getPageSize(),count,list);
            }else{
                AccountBankReq accountBankReqRes = new AccountBankReq();
                Integer count = accountBankMapper.countAccountBank(accountBankReqRes);
                accountBankReqRes.setOffset(accountBankReqRes.getOffset());
                accountBankReqRes.setLimit(accountBankReqRes.getPageSize());
                List<AccountBankVO> list = accountBankMapper.getAccountBankDetail(accountBankReqRes);
                for(AccountBankVO accountBankVO : list){
                    Integer agentId = accountBankVO.getAgentId();
                    if(null != agentId){
                        AgentAccountInfo  agentAccountInfo = agentAccountInfoMapper.selectByPrimaryKey(agentId);
                        if(null != agentAccountInfo){
                            accountBankVO.setAgentName(agentAccountInfo.getAgentName());
                        }
                    }
                    accountBankVO.setPrice(accountBankVO.getPrice().setScale(2, RoundingMode.HALF_UP));
                    accountBankVO.setThirdCostprice(accountBankVO.getThirdCostprice().setScale(2,RoundingMode.HALF_UP));
                    accountBankVO.setPointAmount(accountBankVO.getPointAmount() == null ? BigDecimal.ZERO : accountBankVO.getPointAmount().setScale(2,RoundingMode.HALF_UP));
                    accountBankVO.setCardAmount(accountBankVO.getCardAmount() == null ? BigDecimal.ZERO : accountBankVO.getCardAmount().setScale(2,RoundingMode.HALF_UP));
                    accountBankVO.setReturnAccountPoint(accountBankVO.getReturnAccountPoint()== null ? BigDecimal.ZERO : accountBankVO.getReturnAccountPoint().setScale(2,RoundingMode.HALF_UP));
                }
                return new PageResultBean<>(accountBankReqRes.getPage(),accountBankReqRes.getPageSize(),count,list);
            }
        }catch (Exception e){
            throw new RuntimeException("获取对账账单明细出错！",e);
        }
    }

    @Override
    public void exportAccountBank(AccountBankReq accountBankReq, OutputStream outputStream) {
        try {
            ExcelWriter writer = EasyExcelFactory.getWriter(outputStream, ExcelTypeEnum.XLSX, true);
            Sheet sheet = new Sheet(1, 1, AccountBankInfoExcelModel.class);
            sheet.setSheetName("对账单信息");
            PageResultBean<AccountBankVO> pageResultBean = null;
            if(null == accountBankReq){
                AccountBankReq accountBankReqRes = new AccountBankReq();
                accountBankReqRes.setPage(1);
                accountBankReqRes.setPageSize(Integer.MAX_VALUE);
                pageResultBean= getAccountBankDetail(accountBankReqRes);
            }else{
                final Integer pageSize = Integer.MAX_VALUE;
                accountBankReq.setPage(1);
                accountBankReq.setPageSize(pageSize);
                pageResultBean = getAccountBankDetail(accountBankReq);
            }
            writer.write(pageResultBean.getList().stream().map(item -> {
                AccountBankInfoExcelModel model = new AccountBankInfoExcelModel();
                BeanUtils.copyProperties(item, model);
                return model;
            }).collect(Collectors.toList()), sheet);
            writer.finish();
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException("导出对账单列表出错！", e);
        }
    }

    @Override
    public List<AccountBankVO> listAccountBankDetail(AccountBankReq accountBankReq) {
        try {
            Date startDate = DateTimeUtils.stringToTime(accountBankReq.getStartTime(), DateTimeUtils.DEFAULT_TIME_FORMAT);
            Date endDate = DateTimeUtils.stringToTime(accountBankReq.getEndTime(), DateTimeUtils.DEFAULT_TIME_FORMAT);
            accountBankReq.setStartDate(startDate);
            accountBankReq.setEndDate(endDate);
            List<AccountBankVO> list = accountBankMapper.listAccountBankDetail(accountBankReq);
            return list;
        } catch (Exception e) {
            throw new RuntimeException("获取对账单列表出错！", e);
        }
    }
}
