package com.lx.benefits.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author unknow on 2018-12-17 23:20.
 */
public class JsonUtils {
    private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 转换对象为字符串
     *
     * @param t 待转换的对象
     * @return json字符串
     */
    public static <T> String getJsonString(T t) {
        String jsonStr = null;
        try {
            jsonStr = JSON.toJSONString(t);
        } catch (Exception e) {
            logger.error("JsonUtils error, obj=" + t, e);
        }
        return jsonStr;
    }

    /**
     * 转换对象为字符串 unicode格式 兼容emoji表情
     *
     * @param t 待转换的对象
     * @return json字符串
     */
    public static <T> String getUnicodeJsonString(T t) {
        String jsonStr = null;
        try {
            jsonStr = JSON.toJSONString(t, SerializerFeature.BrowserCompatible);
        } catch (Exception e) {
            logger.error("JsonUtils error, obj=" + t, e);
        }
        return jsonStr;
    }

    /**
     * 转换对象为字符串，保留 null 值
     *
     * @param t 待转换的对象
     * @return json字符串
     */
    public static <T> String getJsonStringWriteMapNullValue(T t) {
        String jsonStr = null;
        try {
            jsonStr = JSON.toJSONString(t, SerializerFeature.WriteMapNullValue);
        } catch (Exception e) {
            logger.error("JsonUtils error, obj=" + t, e);
        }
        return jsonStr;
    }

    /**
     * 解析字符串为指定的javabean数据类型
     * @param json 待解析的字符串
     * @param typeReference TypeReference
     * @param <T> javabean的数据类型
     * @return T
     */
    public static <T> T getObj(String json, TypeReference<T> typeReference) {
        T t = null;
        if (checkBlankJsonString(json)) {
            return null;
        }
        try {
            t = JSON.parseObject(json, typeReference);
        } catch (Exception e) {
            logger.error("JsonUtils error, json=" + json, e);
        }
        return t;
    }

    /**
     * 解析字符串为Lsit数据
     * @param json 待解析的字符串
     * @param typeReference TypeReference
     * @param <T> List中元素的数据类型
     * @return List
     */
    public static <T> List<T> getList(String json, TypeReference<List<T>> typeReference) {
        List<T> list = new ArrayList<>();
        if (checkBlankJsonString(json)) {
            return list;
        }
        try {
            list = JSON.parseObject(json, typeReference);
        } catch (Exception e) {
            logger.error("JsonUtils error, json=" + json, e);
        }
        return list;
    }

    /**
     * 解析json字符串为Map数据
     * @param json 待解析的字符串
     * @param typeReference TypeReference
     * @param <K> Map的key类型
     * @param <V> Map的val类型
     * @return Map数据
     */
    public static <K, V> Map<K, V> getMap(String json, TypeReference<Map<K, V>> typeReference) {
        Map<K, V> map = new HashMap<>();
        if (checkBlankJsonString(json)) {
            return map;
        }
        try {
            map = JSON.parseObject(json, typeReference);
        } catch (Exception e) {
            logger.error("JsonUtils error, json=" + json, e);
        }
        return map;
    }

    /**
     * 添加入参校验，防止一般空字符串或[]情况下的解析异常
     *
     * @param json 待check的字符串
     * @return boolean
     */
    public static boolean checkBlankJsonString(String json) {
        return (StringUtils.isBlank(json) || "[]".equals(json) || "{}".equals(json));
    }
}
