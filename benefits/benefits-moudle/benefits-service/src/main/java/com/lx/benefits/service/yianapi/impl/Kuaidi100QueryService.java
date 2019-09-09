package com.lx.benefits.service.yianapi.impl;

import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.dto.jd.utils.MD5Util;
import com.lx.benefits.bean.enums.yianapi.model.kuaidi100query.Kuaidi100Line;
import com.lx.benefits.bean.enums.yianapi.model.kuaidi100query.Kuaidi100Result;
import com.lx.benefits.bean.util.HttpClientUtil;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.service.yianapi.IKuaidi100QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Kuaidi100QueryService implements IKuaidi100QueryService {


    private String url = "https://poll.kuaidi100.com/poll/query.do";

    private String customer = "2F8B26422F6C1D07C437453991B646BA";

    @Value("${kuaidi100.key}")
    private String key;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Kuaidi100Result<Kuaidi100Line> query(String com, String num) {
        try {
            Map<String,String> meta = new HashMap<>();
            meta.put("com",com);
            meta.put("num",num);
            String md = JsonUtil.toString(meta);
            String sign = MD5Util.MD5(md+key+customer);
            Map<String,String> param = new HashMap<>();
            param.put("param",md);
            param.put("sign",sign.toUpperCase());
            param.put("customer",customer);
            String res = HttpClientUtil.postData(url,param,"UTF-8");
            Kuaidi100Result<Kuaidi100Line> result = JsonUtil.parseObject(res,new TypeReference<Kuaidi100Result<Kuaidi100Line>>(){});
            logger.info("KUAIDI100-RES:"+ res);
            return result;

        }catch (Exception e){
            logger.error("KUAIDI100-QUERY-ERROR",e);
            return null;
        }
    }
}
