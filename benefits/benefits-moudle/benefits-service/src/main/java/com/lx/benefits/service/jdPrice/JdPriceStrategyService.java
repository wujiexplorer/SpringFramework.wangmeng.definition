package com.lx.benefits.service.jdPrice;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.jdPrice.JdPriceStrategyDto;
import com.lx.benefits.bean.dto.jdPrice.JdPriceStrategyReq;
import com.lx.benefits.bean.entity.jdPrice.JdPriceStrategy;

/**
 * User: fan
 * Date: 2019/03/04
 * Time: 23:27
 */
public interface JdPriceStrategyService {

    JSONObject delete(Long id);

    JSONObject insert(JdPriceStrategyDto record,String userName);

    JSONObject getStrategyById(Long id);

    JSONObject getStrategyList(JdPriceStrategyReq record);

    JSONObject update(JdPriceStrategyDto record,String userName);

}
