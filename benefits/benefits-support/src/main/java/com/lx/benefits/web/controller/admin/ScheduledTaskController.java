package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfoExample;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacketExample;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.enums.FestivalTypeEnum;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.SendMailUtil;
import com.lx.benefits.bean.util.SmsAli;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.enterprfestival.EnterprFestivalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * Created by softw on 2019/2/26.
 */
@RestController("adminScheduledTaskController")
@RequestMapping("/admin/scheduledTask")
public class ScheduledTaskController {

    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    /** 企业员工service */
    @Autowired
    private EmployeeUserInfoService employeeUserInfoService;

    /** 企业service */
    @Autowired
    private EnterprUserInfoMapper enterprUserInfoMapper;

    /** 员工积分service */
    @Autowired
    private EmployeeCreditInfoService employeeCreditInfoService;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private EnterprFestivalService enterprFestivalService;

    /**
     * 验证定时任务
     */
    @RequestMapping(value = "/excuteLeaveEmployee", method = RequestMethod.GET)
    public void excuteLeaveEmployee() {
        logger.info("离职员工积分处理，任务开始时间：" + sdf.format(new Date()));
        // 查询企业离职员工积分回收
        EnterprUserInfoCondition euExample = new EnterprUserInfoCondition();
        euExample.createCriteria().andLeaveCreditEqualTo(1);
        euExample.setOffset(0);
        euExample.setLimit(10000);
        List<EnterprUserInfo> enterprList = enterprUserInfoMapper.selectByExample(euExample);
        if (!CollectionUtils.isEmpty(enterprList)) {
            for (EnterprUserInfo enterprUserInfo : enterprList) {
               // Long enterId = enterprUserInfo.getEnterprId();
                // 离职企业员工积分处理
                employeeCreditInfoService.handelEnterCredit(27L);
            }
        }
        logger.info("离职员工积分处理，任务完成时间：" + sdf.format(new Date()));
    }

    /**
     * 定时回收离职员工积分
     */
    public void leaveEmployeeCredit() {
        logger.info("离职员工积分处理，任务开始时间：" + sdf.format(new Date()));
        // 查询企业离职员工积分回收
        EnterprUserInfoCondition euExample = new EnterprUserInfoCondition();
        euExample.createCriteria().andLeaveCreditEqualTo(1);
        euExample.setOffset(0);
        euExample.setLimit(500);
        List<EnterprUserInfo> enterprList = enterprUserInfoMapper.selectByExample(euExample);
        if (null != enterprList && enterprList.size() > 0) {
            for (EnterprUserInfo enterprUserInfo : enterprList) {
                Long enterId = enterprUserInfo.getEnterprId();
                // 离职企业员工积分处理
                employeeCreditInfoService.handelEnterCredit(enterId);
            }
        }
        logger.info("离职员工积分处理，任务完成时间：" + sdf.format(new Date()));
    }

    /**
     * 发送邮件任务
     */
    @RequestMapping(value = "/sendMailTask", method = RequestMethod.GET)
    public void sendMailTask() {
        logger.info("定时发送邮件，任务开始时间：" + sdf.format(new Date()));
        EnterprFestivalPacketExample exampleFestival = new EnterprFestivalPacketExample();
        exampleFestival.createCriteria().andIsDeletedEqualTo(0).andIsEmailEqualTo(1);
        List<EnterprFestivalPacket> list = enterprFestivalService.queryByParam(exampleFestival);
        CountDownLatch countDownLatch = new CountDownLatch(15);
        for (EnterprFestivalPacket festivalPacket : list) {
            Integer type = festivalPacket.getType();
            if (FestivalTypeEnum.FESTIVAL.getValue().equals(type) || FestivalTypeEnum.ADVANCE.getValue().equals(type)) {
                if ( DateUtil.isThisTime( festivalPacket.getStartTime() * 1000L,DateUtil.DATE_FORMAT)) {
                    //发邮件通知企业员工
                    List<EmployeeUserInfo> employeeUserInfoList = employeeUserInfoService.selectListByEnterprId(festivalPacket.getEnterprId());
                    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> sendMail(employeeUserInfoList,countDownLatch,festivalPacket),threadPoolTaskExecutor);
                }
            } else if (FestivalTypeEnum.BIRTHDAY.getValue().equals(type) ||
                    FestivalTypeEnum.ANNIVERSARY.getValue().equals(type)) {
                //发邮件通知企业员工
                EmployeeUserInfoExample exampleUser = new EmployeeUserInfoExample();
                exampleUser.createCriteria().andEnterprIdEqualTo(festivalPacket.getEnterprId()).andLeaveStatusEqualTo(false);
                List<EmployeeUserInfo> employeeUserInfoList = employeeUserInfoService.selectList(exampleUser);
                CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> sendMail(employeeUserInfoList,countDownLatch,festivalPacket),threadPoolTaskExecutor);
            }
        }
        logger.info("定时发送邮件，任务完成时间：" + sdf.format(new Date()));
    }

    /**
     * 发送短信任务
     */
    @RequestMapping(value = "/sendMobileTask", method = RequestMethod.GET)
    public void sendMobileTask() {
        logger.info("定时发送短信，任务开始时间：" + sdf.format(new Date()));
        try {
            EnterprFestivalPacketExample exampleFestival = new EnterprFestivalPacketExample();
            exampleFestival.createCriteria().andIsDeletedEqualTo(0).andIsSmsEqualTo(1);
            List<EnterprFestivalPacket> list = enterprFestivalService.queryByParam(exampleFestival);
            CountDownLatch countDownLatch = new CountDownLatch(15);
            int count = 0;
            for (EnterprFestivalPacket festivalPacket : list) {
                Integer type = festivalPacket.getType();
                if (FestivalTypeEnum.FESTIVAL.getValue().equals(type) || FestivalTypeEnum.ADVANCE.getValue().equals(type)) {
                    if ( DateUtil.isThisTime( festivalPacket.getStartTime() * 1000L,DateUtil.DATE_FORMAT)) {
                        List<EmployeeUserInfo> employeeUserInfoList = employeeUserInfoService.selectListByEnterprId(festivalPacket.getEnterprId());
                        if (count != 0) {
                            Thread.sleep(60000);
                        }
                        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> sendSms(employeeUserInfoList,countDownLatch,festivalPacket),threadPoolTaskExecutor);
                    }
                } else if (FestivalTypeEnum.BIRTHDAY.getValue().equals(type) ||
                        FestivalTypeEnum.ANNIVERSARY.getValue().equals(type)) {
                    if (count != 0) {
                        Thread.sleep(60000);
                    }
                    EmployeeUserInfoExample exampleUser = new EmployeeUserInfoExample();
                    exampleUser.createCriteria().andEnterprIdEqualTo(festivalPacket.getEnterprId()).andLeaveStatusEqualTo(false);
                    List<EmployeeUserInfo> employeeUserInfoList = employeeUserInfoService.selectList(exampleUser);
                    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> sendSms(employeeUserInfoList,countDownLatch,festivalPacket),threadPoolTaskExecutor);
                }
                count ++;
            }
        } catch (Exception e) {
            logger.error("发送短信暂停异常[]",e.getMessage());
        }
        logger.info("定时发送短信，任务完成时间：" + sdf.format(new Date()));
    }

    /**
     * 发送节日邮件
     * @param list
     * @param countDownLatch
     * @param festivalPacket
     * @return
     */
    private String sendMail(List<EmployeeUserInfo> list,CountDownLatch countDownLatch,EnterprFestivalPacket festivalPacket) {
        Integer type = festivalPacket.getType();
        List<String> emails = new ArrayList<>();
        for (EmployeeUserInfo userInfo : list) {
            if (FestivalTypeEnum.BIRTHDAY.getValue().equals(type)) {
                if (null != userInfo.getBirthday() && !StringUtil.isEmpty(userInfo.getBirthday())) {
                    // 判断生日是否合法
                    if (DateUtil.check(userInfo.getBirthday())){
                        if (!DateUtil.isThisTime(userInfo.getBirthday(),DateUtil.DATE_FORMAT)) {
                            continue;
                        }
                    } else {
                        logger.error("{}生日格式不规范,生日{}",userInfo.getEmployeeName(),userInfo.getBirthday());
                    }
                } else {
                    continue;
                }
            } else if (FestivalTypeEnum.ANNIVERSARY.getValue().equals(type)) {
                Long inductionTime = userInfo.getInductionTime()*1000L;
                if (!DateUtil.isThisTime(inductionTime,DateUtil.DATE_DAY_MONTH)) {
                    continue;
                }
            }
            if (null != userInfo.getEmail() && !StringUtil.isEmpty(userInfo.getEmail())) {
                emails.add(userInfo.getEmail());
            }
        }
        //分批处理
        if( null != emails && emails.size()>0){
            Integer size = emails.size();
            // 限制条数
            int pointsDataLimit = 30;
            //判断是否有必要分批
            if(pointsDataLimit<size){
                //分批数
                int part = size/pointsDataLimit;
                logger.info("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                for (int i = 0; i < part; i++) {
                    //30
                    List<String> listPage = emails.subList(0, pointsDataLimit);
                    SendMailUtil.batchSendMail(listPage,festivalPacket.getCampaignName(),festivalPacket.getRemindEmail());
                    logger.info("发送邮件类型{},员工集合{}", festivalPacket.getCreditType(), JSON.toJSONString(emails));
                    //剔除
                    emails.subList(0, pointsDataLimit).clear();
                }

                if(!emails.isEmpty()){
                    logger.info("发送邮件类型{},员工集合{}", festivalPacket.getCreditType(),JSON.toJSONString(emails));
                    SendMailUtil.batchSendMail(emails,festivalPacket.getCampaignName(),festivalPacket.getRemindEmail());
                }
            } else {
                logger.info("发送邮件类型{},员工集合{}", festivalPacket.getCreditType(),JSON.toJSONString(emails));
                SendMailUtil.batchSendMail(emails,festivalPacket.getCampaignName(),festivalPacket.getRemindEmail());
            }
        }
        return "Success";
    }


    /**
     * 发送短信
     * @param list
     * @param countDownLatch
     * @param festivalPacket
     * @return
     */
    private String sendSms(List<EmployeeUserInfo> list,CountDownLatch countDownLatch,EnterprFestivalPacket festivalPacket) {
        try {
            Integer type = festivalPacket.getType();
            List<String> smsList = new ArrayList<>();
            for (EmployeeUserInfo userInfo : list) {
                if (FestivalTypeEnum.BIRTHDAY.getValue().equals(type)) {
                    if (null != userInfo.getBirthday() && !StringUtil.isEmpty(userInfo.getBirthday())) {
                        // 判断生日是否合法
                        if (DateUtil.check(userInfo.getBirthday())){
                            if (!DateUtil.isThisTime(userInfo.getBirthday(),DateUtil.DATE_FORMAT)) {
                                continue;
                            }
                        } else {
                            logger.error("{}生日格式不规范,生日{}",userInfo.getEmployeeName(),userInfo.getBirthday());
                        }
                    } else {
                        continue;
                    }
                } else if (FestivalTypeEnum.ANNIVERSARY.getValue().equals(type)) {
                    Long inductionTime = userInfo.getInductionTime()*1000L;
                    if (!DateUtil.isThisTime(inductionTime,DateUtil.DATE_DAY_MONTH)) {
                        continue;
                    }
                }
                if (null != userInfo.getMobile() && !StringUtil.isEmpty(userInfo.getMobile())) {
                    smsList.add(userInfo.getMobile());
                }
            }
            if( null != smsList && smsList.size()>0){
                String remidSms = festivalPacket.getRemindSms();
                logger.info("短信发送名单{}",smsList.toString());
                if (null != remidSms && !StringUtil.isEmpty(remidSms)) {
                    for (String str:smsList) {
                        ResultInfo<SendSmsResponse> resultInfo =  SmsAli.sendSmsd(str,remidSms.toString(),"");
                        logger.info("短信发送{}",resultInfo.getData().toString());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("发送短信暂停异常[]",e.getMessage());
        }
        return "Success";
    }
}
