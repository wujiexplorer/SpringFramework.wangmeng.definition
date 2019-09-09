package com.lx.benefits.utils;


import com.lx.benefits.bean.enums.PlatformEnum;
import com.lx.benefits.bean.util.StringUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 请求的工具类
 * @author zhuss
 * @2016年1月3日 下午2:36:28
 */
public class RequestHelper {

	private static Logger log = LoggerFactory.getLogger(RequestHelper.class);
	
	public static final String DETAIL_IP = "127.0.0.1";
	
	public static final String POST_PAREMENTS_JSON_KEY = "POST_PAREMENTS_JSON_KEY";
	
	/**
	 * 请求Request转换成JSON格式字符串
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getJsonStrByIO(HttpServletRequest request){
		//如果经过拦截器的直接从request中读取JSON格式数据
		String jsonStr = (String)request.getAttribute(POST_PAREMENTS_JSON_KEY);
		if(StringUtils.isNotBlank(jsonStr)){
			return jsonStr;
		}
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (Exception ex) {
			log.error("[请求Request转换成JSON格式字符串 Exception]", ex);
		}finally{
			IOUtils.closeQuietly(bufferedReader);
		}
		if(StringUtils.isNotBlank(stringBuilder)){
			request.setAttribute(RequestHelper.POST_PAREMENTS_JSON_KEY, stringBuilder.toString());
		}
		return stringBuilder.toString();
	}


	/**
	 * 校验平台是否是APP
	 * @param appType
	 * @return
	 */
	public static boolean isAPP(String appType){
		if(StringUtil.isBlank(appType))return false;
		if(StringUtil.equals(appType, PlatformEnum.IOS.name()))return true;
		if(StringUtil.equals(appType, PlatformEnum.ANDROID.name()))return true;
		return false;
	}

}
