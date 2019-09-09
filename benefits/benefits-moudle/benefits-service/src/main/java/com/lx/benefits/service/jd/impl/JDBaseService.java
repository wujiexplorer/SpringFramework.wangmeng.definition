package com.lx.benefits.service.jd.impl;

import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import com.lx.benefits.bean.util.BigDecimalUtil;
import com.lx.benefits.bean.util.HttpProtocolUtil;
import com.lx.benefits.service.jd.IJDAccessTokenService;
import com.lx.benefits.service.jd.JDConfigService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ldr on 2017/3/2.
 */
@Service
public class JDBaseService extends JDConfigService {

    private static Logger logger = LoggerFactory.getLogger(JDBaseService.class);

    @Autowired
    private IJDAccessTokenService jdAccessTokenService;

    public String getToken() {
        return jdAccessTokenService.getToken();
    }

    public Map<String, String> getParam() {
        Map<String, String> param = new HashMap<>();
        param.put("token", getToken());
        return param;
    }

    public String getString(List<String> skus) {
        if (CollectionUtils.isEmpty(skus)) {
            return StringUtils.EMPTY;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < skus.size(); i++) {
            if (StringUtils.isBlank(skus.get(i))) {
                continue;
            }
            stringBuilder.append(skus.get(i));
            if (i < skus.size() - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    public static String postData(String url, Map<String, String> params, String charset) {
        long start = System.currentTimeMillis();
        String res  = postDataWithoutLock(url, params, charset);
        logger.info("JD_ITEM_BASE_HTTP_POST_1: url={}, cost={}ms", url,
                System.currentTimeMillis() - start);
        return res;
    }

    public static String postDataWithoutLock(String url, Map<String, String> params, String charset) {
        try {
            return doPostData(url, params, charset);
        } catch (Exception e) {
            try {
                return doPostData(url, params, charset);
            } catch (Exception es) {
                try {
                    return doPostData(url, params, charset);
                } catch (Exception ess) {
                    throw new ServiceException("请求失败:" + ess.getMessage());
                }
            }
        }
    }
    private static String doPostData(String url, Map<String, String> params, String charset) throws Exception{
        return HttpProtocolUtil.postData(url,params,charset);
    }




}
