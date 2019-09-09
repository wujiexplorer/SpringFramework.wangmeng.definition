package com.lx.benefits.bean.util;

import com.aliyun.mns.common.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.lx.benefits.bean.base.dto.FailInfo;
import com.lx.benefits.bean.base.dto.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;

public class SmsAli {

	private static final Logger logger = LoggerFactory.getLogger(SmsAli.class);

	/**访问阿里云API的密钥对id*/
	protected static String accessId = "FECNgUq0ldVAfOMG";

	/**访问阿里云API的密钥对key*/
	protected static String accessKey = "MQkiiDt2OxezSPTc4nU6uDmfwrMAZa";

	/**访问MNS的接入地址，登陆MNS控制台 单击右上角 获取Endpoint 查看*/
	protected String endpoint;

	/**设置发送短信的签名（signName）*/
	protected static String signName="福粒科技";

	/**短信模板code */
	protected static String smsTempateCode = "SMS_76950031";

	public static ResultInfo<SendSmsResponse> sendSmsd(String mobile, String content, String messageId) throws ClientException {
		ResultInfo<SendSmsResponse> result = new ResultInfo<SendSmsResponse>();
		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessId, accessKey);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
		} catch (com.aliyuncs.exceptions.ClientException e) {
			return new ResultInfo<>(new FailInfo("【阿里云】"+e.getMessage()));
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
		//组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		//必填:待发送手机号
		request.setPhoneNumbers(mobile);
		//必填:短信签名-可在短信控制台中找到
		request.setSignName(signName);
		//必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(smsTempateCode);
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam("{\"content\":\""+content+"\"}");
		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId(messageId);
		//hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = null;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
			result.setData(sendSmsResponse);
			result.setSuccess(true);
			logger.info("短信发送成功手机号{},短信内容{}",mobile,content);
			return result;
		} catch (ServerException e) {
			logger.error("发短信异常{}",e.getMessage());
			return new ResultInfo<>(new FailInfo("【阿里云】"+e.getMessage()));
		} catch (com.aliyuncs.exceptions.ClientException e) {
			logger.error("发短信异常{}",e.getMessage());
			return new ResultInfo<>(new FailInfo("【阿里云】"+e.getMessage()));
		} catch (Exception e) {
			logger.error("发短信异常Exception{}",e.getMessage());
			return new ResultInfo<>(new FailInfo("【阿里云】"+e.getMessage()));
		}

	}

	public static void main(String[] args) {
		sendSmsd("13585676215","短信内容普通积分  亲爱的用户，您将收到普通积分提醒祝您愉快","");
		sendSmsd("13585676215","短信内容普通积分  亲爱的用户，您将收到节日积分提醒祝您愉快","");
	}
}

