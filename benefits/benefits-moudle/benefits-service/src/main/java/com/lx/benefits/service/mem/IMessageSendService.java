package com.lx.benefits.service.mem;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.LoginReqDto;

/**
  * @author szy 
  * 接口
  */
public interface IMessageSendService{

	JSONObject smsCode(LoginReqDto req);

}
