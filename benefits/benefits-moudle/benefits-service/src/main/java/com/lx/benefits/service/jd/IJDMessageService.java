package com.lx.benefits.service.jd;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.jd.JdMessageLine;
import com.lx.benefits.bean.dto.jd.JdMessageLineReq;

import java.util.List;

/**
 * User: fan
 * Date: 2019/03/17
 * Time: 21:36
 */
public interface IJDMessageService {

    JSONObject getMessageList(JdMessageLineReq req);

    JSONObject process(Long id);

    List<JdMessageLine> get() throws Exception;

    Integer doMessageProcess();

}
