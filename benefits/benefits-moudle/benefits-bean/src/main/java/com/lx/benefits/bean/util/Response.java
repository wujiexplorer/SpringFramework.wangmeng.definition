package com.lx.benefits.bean.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;


public class Response {

    public static final int SUCC = 1001;//请求成功
    public static final int FAIL = 1002;//请求失败
    public static final int NOT_AUTH = 1003;//未授权，header中没有授权信息
    public static final int ERROR_TOKEN = 1004;//授权码错误或已过期
    public static final int IP_DENNY = 1005;//IP地址错误，需要配置服务器IP白名单
    public static final int REQUEST_LIMIT = 1006;//请求次数受限

    public static JSONObject succ(Object result) {
        JSONObject response = new JSONObject();
        response.put("result", result);
        response.put("code", SUCC);
        response.put("msg", "succ");
        return response;
    }

    public static JSONObject succ() {
        JSONObject response = new JSONObject();
        response.put("code", SUCC);
        response.put("msg", "succ");
        return response;
    }

    public static JSONObject fail(int code, String error) {
        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("msg", error);
        return response;
    }
    
    public static JSONObject fail(String error) {
    	JSONObject response = new JSONObject();
    	response.put("code", FAIL);
    	response.put("msg", error);
    	return response;
    }

    public static JSONObject fail() {
        JSONObject response = new JSONObject();
        response.put("code", FAIL);
        response.put("msg", "system busy");
        return response;
    }

    public static JSONObject notAuth() {
        JSONObject response = new JSONObject();
        response.put("code", NOT_AUTH);
        response.put("msg", "not auth");
        return response;
    }

    public static JSONObject errorToken(){
        JSONObject response=new JSONObject();
        response.put("code",ERROR_TOKEN);
        response.put("msg","error token");
        return response;
    }

    /**
     * 判断数据是否返回失败
     * @param jsonObject
     * @return
     */
    public static boolean isSuccess(JSONObject jsonObject) {
        return null != jsonObject && String.valueOf(SUCC).equals(jsonObject.getString("code"));
    }

    /**
     * 
     * @param jsonObject   jsonObject
     * @param typeReference   typeReference
     * @param <T> 类型
     * @return 需要的对象
     */
    public static <T> T getData(JSONObject jsonObject, TypeReference<T> typeReference) {
        if (isSuccess(jsonObject)) {
            return JsonUtils.getObj(jsonObject.getString("result"), typeReference);    
        }
        return null;
    }
}