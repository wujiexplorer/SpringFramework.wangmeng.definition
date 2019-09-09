package com.lx.benefits.web.controller.yianapi;


import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.yianapi.client.ClientResult;
import com.lx.benefits.bean.dto.yianapi.client.ClientTokenReq;
import com.lx.benefits.bean.dto.yianapi.client.MemberInfoReq;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.utils.RequestHelper;
import com.lx.benefits.web.ao.ClientAO;
import com.lx.benefits.web.support.WeChatSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 提供给第三方平台获取token和用户信息
 * Created by lidongri on 2018/12/1.
 */

@Controller
@RequestMapping("/client/api")
public class ClientController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ClientAO clientAO;

    @Autowired
 	private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private WeChatSupport weChatSupport;

 	private final String CODE_CACHE_KEY = "code_wxacode_%s";
 	
    /**
     * 第三方平台获取token
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/token.htm", method = RequestMethod.POST)
    public String token(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            logger.info("CLIENT-GET-TOKEN-REQUEST-PARAM:" + String.valueOf(jsonStr));
            ClientTokenReq tokenReq = JsonUtil.parse(jsonStr, ClientTokenReq.class);
            if (tokenReq == null || StringUtils.isBlank(tokenReq.getClient_id()) || StringUtils.isBlank(tokenReq.getClient_secret())) {
                return JsonUtil.convertObjToStr(new ClientResult<>("access_token", "get access token fail param error", false));
            }
            String res = JsonUtil.convertObjToStr(clientAO.token(tokenReq));
            logger.info("CLIENT-GET-TOKEN-REQUEST-RESULT:" + String.valueOf(res));
            return res;
        } catch (Exception e) {
            logger.error("M_CLIENT_TOKEN_ERROR", e);
            return JsonUtil.convertObjToStr(new ClientResult<>("access_token", "get access token fail system error", false));
        }
    }


    /**
     * 根据跳转code第三方平台获取用户信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get-user-info.htm", method = RequestMethod.POST)
    public String memberInfo(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            logger.info("CLIENT-GET-MEMBER-INFO-PARAM:" + String.valueOf(jsonStr));
            MemberInfoReq memberInfoReq = JsonUtil.parse(jsonStr, MemberInfoReq.class);
            if (memberInfoReq == null || StringUtils.isBlank(memberInfoReq.getCode())) {
                return JsonUtil.convertObjToStr(new ClientResult<>("get_user_info", "code is empty", false));
            }
            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
                return JsonUtil.convertObjToStr(new ClientResult<>("get_user_info", "token not valid", false));
            }
            memberInfoReq.setToken(token.substring(7));

            String res = JsonUtil.convertObjToStr(clientAO.memberInfo(memberInfoReq));
            logger.info("CLIENT-GET-MEMBER-INFO-RESULT:" + String.valueOf(res));
            return res;
        } catch (Exception e) {
            logger.error("M_CLIENT_MEMBER_INFO_ERROR", e);
            return JsonUtil.convertObjToStr(new ClientResult<>("get_user_info", "system error", false));
        }
    }

    /**
     * 根据怡安用户ID 第三方平台获取用户信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get-user-info-by-id.htm", method = RequestMethod.POST)
    public String memberInfoByUserId(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            logger.info("CLIENT-GET-MEMBER-INFO-PARAM:" + String.valueOf(jsonStr));
            MemberInfoReq memberInfoReq = JsonUtil.parse(jsonStr, MemberInfoReq.class);
            if (memberInfoReq == null || memberInfoReq.getUserId()== null) {
                return JsonUtil.convertObjToStr(new ClientResult<>("get_user_info_by_id", "user id is empty", false));
            }
            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
                return JsonUtil.convertObjToStr(new ClientResult<>("get_user_info_by_id", "token not valid", false));
            }
            memberInfoReq.setToken(token.substring(7));

            String res = JsonUtil.convertObjToStr(clientAO.memberInfoById(memberInfoReq));
            logger.info("CLIENT-GET-MEMBER-INFO-RESULT:" + String.valueOf(res));
            return res;
        } catch (Exception e) {
            logger.error("M_CLIENT_MEMBER_INFO_ERROR", e);
            return JsonUtil.convertObjToStr(new ClientResult<>("get_user_info_by_id", "system error", false));
        }
    }

    /**
     * 第三方平台获取用户信息 分页
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get-user-info-list.htm", method = RequestMethod.POST)
    public String memberInfoList(HttpServletRequest request) {
        try {

            String jsonStr = RequestHelper.getJsonStrByIO(request);
            logger.info("CLIENT-GET-MEMBER-INFO-LIST-PARAM:" + String.valueOf(jsonStr));
            MemberInfoReq memberInfoReq = JsonUtil.parse(jsonStr, MemberInfoReq.class);

            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
                return JsonUtil.convertObjToStr(new ClientResult<>("user_info_list", "token not valid", false));
            }
            memberInfoReq.setToken(token.substring(7));

            String res = JsonUtil.convertObjToStr(clientAO.memberInfoList(memberInfoReq));
            //logger.info("CLIENT-GET-MEMBER-INFO-LIST-RESULT: 部分数据" + String.valueOf(res));
            return res;
        } catch (Exception e) {
            logger.error("M_CLIENT_MEMBER_INFO_ERROR", e);
            return JsonUtil.convertObjToStr(new ClientResult<>("user_info_list", "system error", false));
        }
    }


    /**
     * 根据eeNo+orgCode 活着eeNo+nationalId第三方平台获取用户信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get-user-info-mul.htm", method = RequestMethod.POST)
    public String memberInfoMul(HttpServletRequest request) {
        try {

            String jsonStr = RequestHelper.getJsonStrByIO(request);
            logger.info("CLIENT-GET-MEMBER-INFO-MUL-PARAM:" + String.valueOf(jsonStr));
            MemberInfoReq memberInfoReq = JsonUtil.parse(jsonStr, MemberInfoReq.class);

            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
                return JsonUtil.convertObjToStr(new ClientResult<>("user_info_mul", "token not valid", false));
            }
            memberInfoReq.setToken(token.substring(7));

            if (memberInfoReq == null  ) {
                return JsonUtil.convertObjToStr(new ClientResult<>("get_user_info_mul", "param error", false));
            }

            if(StringUtils.isBlank(memberInfoReq.getEeNo())){
                return JsonUtil.convertObjToStr(new ClientResult<>("get_user_info_mul", "ee no is empty", false));
            }
            if(StringUtils.isBlank(memberInfoReq.getOrgCode()) && StringUtils.isBlank(memberInfoReq.getNationalId())){
                return JsonUtil.convertObjToStr(new ClientResult<>("get_user_info_mul", "org code and national id both empty", false));
            }

            String res = JsonUtil.convertObjToStr(clientAO.memberInfoByEeNoOrgCodeOrNationalId(memberInfoReq));
            logger.info("CLIENT-GET-MEMBER-INFO-MUL-RESULT:" + String.valueOf(res));
            return res;
        } catch (Exception e) {
            logger.error("M_CLIENT_MEMBER_INFO_MUL_ERROR", e);
            return JsonUtil.convertObjToStr(new ClientResult<>("get_user_info_mul", "system error", false));
        }
    }

	@ResponseBody
	@PostMapping("/get-user-wxacode.htm")
	public Object getWxaCode(@RequestBody Map<String, String> request) {
		String code = request.get("code");
		if (StringUtils.isEmpty(code)) {
			return JsonUtil.convertObjToStr(new ClientResult<>("get_wxacode", "code can't be null", false));
		}
		String key = UUID.randomUUID().toString().replace("-", "");
		ResponseEntity<org.springframework.core.io.Resource> wxaCode = weChatSupport.getWxaCode(key,true);
		if (HttpStatus.OK == wxaCode.getStatusCode() || wxaCode.getHeaders().getContentDisposition() == null) {
			stringRedisTemplate.opsForValue().set(String.format(CODE_CACHE_KEY, key), code, 5, TimeUnit.MINUTES);
			return wxaCode;
		} else {
			return JsonUtil.convertObjToStr(new ClientResult<>("get_wxacode", "system error", false));
		}
	}

	@PostMapping("/scenecode")
	@ResponseBody
	public Object getCodeByScene(@RequestBody Map<String, String> request) {
		String scene = request.get("scene");
		if (StringUtils.isEmpty(scene)) {
			return Response.fail("scene can't be null");
		}
		String code = stringRedisTemplate.opsForValue().get(String.format(CODE_CACHE_KEY, scene));
		if (code == null) {
			return Response.fail("已过期!");
		} else {
			JSONObject result = new JSONObject();
			result.put("code", code);
			return Response.succ(result);
		}
	}


}
