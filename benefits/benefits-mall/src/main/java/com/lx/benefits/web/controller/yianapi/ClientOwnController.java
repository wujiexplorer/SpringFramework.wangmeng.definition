package com.lx.benefits.web.controller.yianapi;


import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.dto.jd.utils.AssertUtil;
import com.lx.benefits.bean.dto.yianapi.cache.TokenCacheTO;
import com.lx.benefits.bean.dto.yianapi.client.ClientCodeReq;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.constant.PlatformConstatnts;
import com.lx.benefits.helper.AuthHelper;
import com.lx.benefits.utils.RequestHelper;
import com.lx.benefits.web.ao.ClientAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 我放用户跳转第三方平台
 * Created by lidongri on 2018/12/6.
 */
@Controller
@RequestMapping("/client/own")
public class ClientOwnController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClientAO clientAO;

    @Autowired
    private AuthHelper authHelper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 我方跳转到第三方平台所需code
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/code", method = RequestMethod.POST)
    public String code(HttpServletRequest request) {
        try {
            String yianToken = request.getHeader(PlatformConstatnts.YIAN_TOKEN);
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            ClientCodeReq query = JsonUtil.parse(jsonStr, ClientCodeReq.class);
            TokenCacheTO usr = authHelper.authToken(yianToken);
            AssertUtil.notNull(usr, "用户信息空");
            query.setUserid(usr.getUid());
            return JsonUtil.convertObjToStr(clientAO.code(query));
        } catch (Exception e) {
            logger.error("M_CLIENT_CODE_ERROR", e);
            if (e.getMessage().equals(MResultInfo.ACCOUNT_TIMEOUT.message)) {
                return JsonUtil.convertObjToStr(new MResultVO<>(MResultInfo.ACCOUNT_TIMEOUT));
            }
            return JsonUtil.convertObjToStr(new MResultVO<>(MResultInfo.SYSTEM_ERROR));
        }
    }

    /**
     * 我方获取当前登录用户是否有怡安联合登录信息，用于前端是否展示 跳转信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getunioninfo", method = RequestMethod.POST)
    public String getUnionInfo(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            ClientCodeReq query = JsonUtil.parse(jsonStr, ClientCodeReq.class);
            TokenCacheTO usr = authHelper.authToken(query.getToken());
            AssertUtil.notNull(usr, "用户信息空");
            query.setUserid(usr.getUid());
            return JsonUtil.convertObjToStr(clientAO.getUnionInfo(query));
        } catch (Exception e) {
            logger.error("M_CLIENT_CODE_ERROR", e);
            return JsonUtil.convertObjToStr(new MResultVO<>(MResultInfo.SYSTEM_ERROR));
        }
    }
}
