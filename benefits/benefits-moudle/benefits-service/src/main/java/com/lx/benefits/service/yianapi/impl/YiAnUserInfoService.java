package com.lx.benefits.service.yianapi.impl;

import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.enums.yianapi.enums.YiAnAPI;
import com.lx.benefits.bean.enums.yianapi.model.*;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.service.yianapi.IYiAnUserInfoService;
import com.lx.benefits.sdk.yian.YiAnHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YiAnUserInfoService  implements IYiAnUserInfoService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private YiAnHttpUtil yiAnHttpUtil;

    @Override
    public YiAnResult<YiAnUserInfoResult> getUserInfo(String code) {
        try {
            YiAnResult<YiAnTokenResult> tokenResult = yiAnHttpUtil.token();

            String token = tokenResult.getResult().getAccess_token();

            YiAnUserInfoRequest request = new YiAnUserInfoRequest(code);
            String res = yiAnHttpUtil.handle(YiAnAPI.GET_USER_INFO, request, token);
            logger.info("YIAN-GET-USER-INFO-RES:" + String.valueOf(res));
            YiAnResult<YiAnUserInfoResult> result = JsonUtil.parseObject(res, new TypeReference<YiAnResult<YiAnUserInfoResult>>() {
            });

            if (result == null || !result.isSuccess()) {
                logger.error("YIAN-GET-USER-INFO-ERROR,RESULT:" + JsonUtil.toString(result));
            }

            return result;
        } catch (Exception e) {
            logger.error("YIAN-GET-USER-INFO-ERROR,", e);
            return null;
        }
    }
}
