package com.lx.benefits.bean.dto.yx.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public final class YXOpenApiUtils {

    private static Logger logger = LoggerFactory.getLogger(YXOpenApiUtils.class);

    /**
     * 组装参数，包括sign值的生成
     * @param appKey appKey
     * @param appSecret appSecret
     * @param timestamp timestamp
     * @param method method
     * @param paramMap paramMap
     * @return treeMap
     */
    public static TreeMap<String, String> packageParams(String appKey, String appSecret, String timestamp, String method,
                                                  Map<String, String> paramMap) {
        //将请求参数按名称排序
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("appKey", appKey);
        treeMap.put("method", method);
        treeMap.put("timestamp", timestamp);
        if (null != paramMap) {
            treeMap.putAll(paramMap);
        }

        //遍历treeMap，将参数值进行拼接
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = treeMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            sb.append(key).append("=");
            sb.append(treeMap.get(key));
        }

        //参数值拼接的字符串收尾添加appSecret值
        String waitSignStr = appSecret + sb.toString() + appSecret;

        //获取MD5加密后的字符串
        String sign = ParseMD5.parseStrToMd5U32(waitSignStr);

        if (logger.isInfoEnabled()) {
            logger.info(".getSign() param={} sign={}", treeMap, sign);
        }

        treeMap.put("sign", sign);

        return treeMap;
    }

}
