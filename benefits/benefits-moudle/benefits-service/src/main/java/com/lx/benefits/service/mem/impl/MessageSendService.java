package com.lx.benefits.service.mem.impl;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.lx.benefits.bean.base.dto.LoginReqDto;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.constants.SessionKey;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SmsAli;
import com.lx.benefits.bean.util.SmsUtil;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.mapper.employeeuserinfo.EmployeeUserInfoMapper;
import com.lx.benefits.service.mem.IMessageSendService;
import com.lx.benefits.utils.RedisUtils;


@Service
public class MessageSendService implements IMessageSendService {

	private final Logger logger = LoggerFactory.getLogger(MessageSendService.class);

	@Resource
	private EmployeeUserInfoMapper employeeUserInfoMapper;

	@Autowired
	private RedisUtils redisUtils;

	@Override
	public JSONObject smsCode(LoginReqDto req) {
        String mobile = req.getMobile();
		if (null == mobile || StringUtil.isEmpty(mobile)) {
			return Response.fail("手机号不能为空");
		}

		Long employeeId =  SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
		if ( null == employeeId || employeeId < 1) {
			return Response.fail("登录失效，重新登录");
		}
		EmployeeUserInfo employeeUserInfo = employeeUserInfoMapper.selectByPrimaryKey(employeeId);
		if (null == employeeUserInfo) {
			return Response.fail("用户不存在");
		}
		Integer type = req.getSendType();
		if (!type.equals(SessionKey.APP_BINDPHONE.key)) {
			if (StringUtil.isEmpty(employeeUserInfo.getMobile())) {
				return Response.fail("请先绑定手机号");
			}
			if (null != employeeUserInfo.getMobile() && !StringUtil.isEmpty(employeeUserInfo.getMobile())) {
				if (!mobile.equals(employeeUserInfo.getMobile())) {
					return Response.fail("手机不是绑定手机号");
				}
			}
		}

        //生成验证码
		Integer random = SmsUtil.getRandomNumber();
		StringBuffer content = new StringBuffer();
		if(type.equals(SessionKey.APP_REGISTER.key)){
			content.append(SmsUtil.getRegSmsContent(random));
		}else if(type.equals(SessionKey.APP_UPDATE_PASSWORD.key)){
			content.append(SmsUtil.getResetPwdSmsContent(random));
		}else if(type.equals(SessionKey.APP_BINDPHONE.key)){
			content.append(SmsUtil.getBindMobileSmsContent(random));
		}else if(type.equals(SessionKey.RECEIVE_COUPON.key)){
			content.append(SmsUtil.getReceiveCouponSmsContent(random));
		}else if(type.equals(SessionKey.REGISTER_DSS.key)){
			content.append(SmsUtil.getRegDSSSmsContent(random));
		}else if(type.equals(SessionKey.UNION_BINDMOBILE.key)){
			content.append(SmsUtil.getUnionBindmblContent(random));
		}else if(type.equals(SessionKey.MODIFY_MOBILE.key)){
			content.append(SmsUtil.getUnionBindmblContent(random));
		}else if(type.equals(SessionKey.CHANGE_PAY_PASSWORD.key)){
			content.append(SmsUtil.getUnionBindmblContent(random));
		}else if(type.equals(SessionKey.ACTIVE_USER.key)){
			content.append(SmsUtil.getActiveUserContent(random));
		}else if(type.equals(SessionKey.RECHANGE_MOBILE.key)){
			content.append(SmsUtil.getRechageContent(random));
		}else if(type.equals(SessionKey.SCANCODE_COUPON.key)){
			content.append(SmsUtil.getScancodeExchangeContent(random));
		}
        // 发送短信
        try {
			ResultInfo<SendSmsResponse> resultInfo =  SmsAli.sendSmsd(mobile,content.toString(),"");
			logger.info("短信发送{}",resultInfo.getData().toString());
			if (resultInfo.isSuccess() && resultInfo.getData().getCode().equals("OK")) {
				redisUtils.set(mobile,random);
				return Response.succ("短信发送成功");
			}
        } catch (Exception e) {
			logger.error("发送短信失败{}",e.getMessage());
        }
		return Response.succ("短信发送失败");
	}
}

