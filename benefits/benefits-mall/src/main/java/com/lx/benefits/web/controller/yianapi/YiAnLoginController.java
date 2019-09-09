package com.lx.benefits.web.controller.yianapi;

import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.dto.wechat.Code2SessionDTO;
import com.lx.benefits.bean.dto.yianapi.AccountVO;
import com.lx.benefits.bean.dto.yianapi.YiAnLogin;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.user.WxUserOpenId;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.user.WxUserOpenIdService;
import com.lx.benefits.service.yianapi.YiAnService;
import com.lx.benefits.utils.RequestHelper;
import com.lx.benefits.web.support.WeChatSupport;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by lidongri on 2018/11/12.
 */
@Controller
@RequestMapping("/yianapi")
public class YiAnLoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private YiAnService yiAnService;
    @Autowired
    private WeChatSupport weChatSupport;
    @Autowired
    private WxUserOpenIdService wxUserOpenIdService;
    @Autowired
    private EmployeeUserInfoService employeeUserInfoService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            YiAnLogin yiAnLogin = JsonUtil.parseObject(jsonStr, YiAnLogin.class);
            if (yiAnLogin == null || StringUtils.isBlank(yiAnLogin.getCode())) {
                return JsonUtil.convertObjToStr(new MResultVO(MResultInfo.PARAM_ERROR));
            }

            JSONObject jsonObject = yiAnService.login(yiAnLogin.getCode());

            /**怡安微信小程序跳转登录*/
            if(StringUtils.isNotEmpty(yiAnLogin.getWxcode())){
                JSONObject data = jsonObject.getJSONObject("result").getJSONObject("data");
                AccountVO accountVO = data.toJavaObject(AccountVO.class);
                Code2SessionDTO code2SessionDTO = weChatSupport.code2Session(yiAnLogin.getWxcode());
                if(Objects.isNull(code2SessionDTO)){
                    throw new BusinessException("非法请求");
                }
                WxUserOpenId wxUserOpenId = wxUserOpenIdService.getByOpenId(code2SessionDTO.getOpenId());
                if(wxUserOpenId==null){
                    EmployeeUserInfo employeeUserInfo = employeeUserInfoService.findByLoginName(accountVO.getLoginName());
                    wxUserOpenIdService.bindUser(employeeUserInfo.getEmployeeId(), code2SessionDTO.getOpenId());
                }
            }
            return JsonUtil.convertObjToStr(jsonObject);
        } catch (Exception e) {
            logger.error("M_YIAN_LOGIN_ERROR", e);
            return JsonUtil.convertObjToStr(new MResultVO(MResultInfo.SYSTEM_ERROR));
        }
    }
}
