package com.lx.benefits.web.controller.enterprise;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.wechat.Code2SessionDTO;
import com.lx.benefits.bean.entity.card.CardStorage;
import com.lx.benefits.bean.enums.card.MemberCardStatus;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.JsonUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.bean.util.SmsAli;
import com.lx.benefits.bean.vo.card.MemberCardBindRequest;
import com.lx.benefits.bean.vo.card.MemberCardMobileBean;
import com.lx.benefits.constant.PlatformConstatnts;
import com.lx.benefits.service.card.MemberCardService;
import com.lx.benefits.utils.ImageCheckCodeUtil;
import com.lx.benefits.web.support.WeChatSupport;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/enterprise/cards")
@Slf4j
public class MemberCardController {

	@Autowired
	private MemberCardService memberCardService;
	@Autowired
	private WeChatSupport weChatSupport;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Value("${benefits.istest:false}")
	private boolean isTest;
	@Value("${benefits.codeCheck:true}")
	private boolean codeCheck;
	@Autowired
	private ImageCheckCodeUtil imageCheckCodeUtil;

	private static final String MOBILE_SMSCODE_CACH = "cardcode_%s_%s";
	private static final String CARD_MOBILE_BIND_CACH = "cardbind_%s_%s";
	private static final String MOBILE_SMSCODE_INTERVAL_CACH = "cardbind_interval_%s";
	private static final String SMS_CONTENT = "尊敬的用户，您正在激活专属会员卡，验证码为：%s。15分钟有效，请妥善保管！";

	@PostMapping("/verify")
	public Object check(@RequestBody CardStorage request) {
		String verifyNumber = request.getVerifyNumber();
		if (verifyNumber == null || (verifyNumber = verifyNumber.trim()).length() == 0) {
			return Response.fail("校验码不能为空!");
		}
		CardStorage cardStorage = memberCardService.getCardByVerifyNumber(verifyNumber);
		if (cardStorage == null) {
			return Response.fail("无效的校验码!");
		} else if (MemberCardStatus.ACTIVE.status.equals(cardStorage.getStatus())) {
			return Response.succ();
		} else if (MemberCardStatus.USED.status.equals(cardStorage.getStatus())) {
			return Response.fail("该会员卡已被使用!");
		} else {
			return Response.fail("无效的校验码!");
		}
	}

	@PostMapping("/smscode")
	public Object getCardSmsCode(@Validated @RequestBody MemberCardMobileBean request) {
		if (codeCheck && !imageCheckCodeUtil.checkImageCode(request.getCodeKey(), request.getImageCode())) {
			return Response.fail("图片验证码验证失败");
		}
		String mobile = request.getMobile().trim();
		String verifyNumber = request.getVerifyNumber().trim();
		String cacheKey = String.format(MOBILE_SMSCODE_CACH, mobile, verifyNumber);
		String intervalCach = String.format(MOBILE_SMSCODE_INTERVAL_CACH, mobile);
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		// 判断该手机号码是否在1分钟内发送过
		String value = opsForValue.get(intervalCach);
		if (value != null) {
			return Response.fail("获取验证码间隔时间太短");
		}
		// 将生成的验证码缓存
		String code = RandomStringUtils.randomNumeric(6);
		opsForValue.set(intervalCach, "1", 1, TimeUnit.MINUTES);// 缓存验证码获取间隔1分钟
		opsForValue.set(cacheKey, code, 15, TimeUnit.MINUTES);// 缓存验证码信息15分钟
		if (isTest) {// 测试环境不真正发验证码
			return Response.succ(Collections.singletonMap("smsCode", code));
		} else {
			ResultInfo<SendSmsResponse> sendSmsd = SmsAli.sendSmsd(mobile, String.format(SMS_CONTENT, code), "");// 发送短信
			log.info("send smscode, mobile={}, code={}, result={}", mobile, code, JSON.toJSONString(sendSmsd));
			return Response.succ();
		}
	}

	@PostMapping("/smsCodeCheck")
	public Object smsCodeCheck(@Validated @RequestBody MemberCardMobileBean request) {
		String smsCode = request.getSmsCode();
		if (smsCode == null || "".equals(smsCode.trim())) {
			return Response.fail("验证码不能为空");
		}
		String mobile = request.getMobile().trim();
		String verifyNumber = request.getVerifyNumber().trim();
		String cacheKey = String.format(MOBILE_SMSCODE_CACH, mobile, verifyNumber);
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		String cacheCode = opsForValue.get(cacheKey);
		if (!smsCode.equals(cacheCode)) {// 通过缓存进行校验
			return Response.fail("验证码错误");
		}
		stringRedisTemplate.delete(cacheKey);
		opsForValue.set(String.format(CARD_MOBILE_BIND_CACH, mobile, verifyNumber), "1", 30, TimeUnit.MINUTES);
		return Response.succ();
	}

	@RequestMapping("/bind")
	public Object memberCardBind(@Validated @RequestBody MemberCardBindRequest bindRequest, HttpServletResponse httpServletResponse) {
		String mobile = bindRequest.getMobile().trim();
		String verifyNumber = bindRequest.getVerifyNumber().trim();
		String bindCach = String.format(CARD_MOBILE_BIND_CACH, mobile, verifyNumber);
		String value = stringRedisTemplate.opsForValue().get(bindCach);
		if (value == null) {
			return Response.fail("操作超时，请重新开始操作!");
		}
		if (StringUtils.isEmpty(bindRequest.getWxOpenId()) && !StringUtils.isEmpty(bindRequest.getCode())) {
			Code2SessionDTO code2SessionDTO = weChatSupport.code2Session(bindRequest.getCode());
			if (code2SessionDTO != null) {
				bindRequest.setWxOpenId(code2SessionDTO.getOpenId());
			}
		}
		SessionShopInfo sessionShopInfo = memberCardService.bindEmployee(bindRequest);
		String tokenSecret = EncryptUtil.encryptByMd5(UUID.randomUUID().toString());
		String token = EncryptUtil.aesEncrypt(JsonUtils.getJsonString(sessionShopInfo), tokenSecret);
		String viewToken = EncryptUtil.encodeToken(token, tokenSecret);
		// 写header头信息
		httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
		httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
		httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_TOKEN, viewToken);
		httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_NAME, bindRequest.getLoginName());
		stringRedisTemplate.delete(bindCach);
		return Response.succ(sessionShopInfo);
	}

	@RequestMapping("/reward")
	public Object memberCardReward() {
		SessionShopInfo employeeInfo = SessionContextHolder.getSessionEmployeeInfo();
		BigDecimal rewardAmount = memberCardService.memberCardReward(employeeInfo.getEmployeeId().intValue());
		Map<String, Object> response = new HashMap<>();
		response.put("rewardAmount", rewardAmount);
		if (rewardAmount.compareTo(BigDecimal.ZERO) > 0) {
			response.put("rewardMesage", "恭喜您获得" + rewardAmount + "积分");
		}
		return Response.succ(response);
	}
}
