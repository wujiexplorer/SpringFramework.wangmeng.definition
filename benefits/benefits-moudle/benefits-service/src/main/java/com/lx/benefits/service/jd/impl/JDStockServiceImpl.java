package com.lx.benefits.service.jd.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.dto.jd.JDNewStock;
import com.lx.benefits.bean.dto.jd.JDSSkuNums;
import com.lx.benefits.bean.dto.jd.JDStock;
import com.lx.benefits.bean.dto.jd.res.JDResult;
import com.lx.benefits.service.jd.IJDStockService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 22:28
 */
@Service
public class JDStockServiceImpl extends JDBaseService implements IJDStockService {

    private static final Logger logger = LoggerFactory.getLogger(JDStockServiceImpl.class);

    @Override
    public List<JDNewStock> newStock(List<JDSSkuNums> skus, String area) throws Exception {
        Map<String,String> param = getParam();
        param.put("skuNums", JSON.toJSONString(skus));
        param.put("area",area);
        String res = postData(getStock_new_stock_url(),param,CHARSET);
        logger.info("JD_NEW_STOCK_RESULT_"+res);
        JDResult<String> jdResult = JSON.parseObject(res,new TypeReference<JDResult<String>>(){});
        if(!jdResult.isSuccess()){
            logger.error("JD_NEW_STOCK_ERROR_RESULT_"+ res);
            logger.error("JD_NEW_STOCK_ERROR_PARAM_"+ skus+ area);
            return Collections.EMPTY_LIST;
        }
        List<JDNewStock> jdNewStocks = JSON.parseObject(jdResult.getResult(),new TypeReference<List<JDNewStock>>(){});
        return jdNewStocks;
    }

    @Override
    public List<JDStock> stock(List<String> skus, String area) throws Exception {
        Map<String,String> param = getParam();
        param.put("sku", getString(skus));
        param.put("area",area);
        String res = postData(getStock_stock_url(),param,CHARSET);
        logger.info("JD_STOCK_RESULT_"+res);
        JDResult<String> jdResult = JSON.parseObject(res,new TypeReference<JDResult<String>>(){});
        if(!jdResult.isSuccess()){
            logger.error("JD_STOCK_ERROR_RESULT_"+ res);
            logger.error("JD_STOCK_ERROR_PARAM_"+ skus+ area);
            return Collections.EMPTY_LIST;
        }
        return JSON.parseObject( jdResult.getResult(),new TypeReference<List<JDStock>>(){});
    }

    @Override
    public Integer stock(String sku,Long jdProvinceId,Long jdCityId,Long jdCountyId) throws Exception {
        if (StringUtils.isBlank(sku)) {
            return -1;
        }
        if (jdProvinceId == null && jdCityId == null || jdCountyId == null) {
            return -1;
        }
        List<JDStock> jdStocks = stock(Arrays.asList(sku),jdProvinceId+"_"+jdCityId+"_"+ jdCountyId);
        if (CollectionUtils.isEmpty(jdStocks)) {
            return 0;
        }
        JDStock jdStock = jdStocks.get(0);
        if(jdStock.getState() != null && jdStock.getState() == 33){
            return 1;
        }
        return 0;
    }
}
