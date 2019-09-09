package com.lx.benefits.service.jd.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.dto.jd.JDPrice;
import com.lx.benefits.bean.dto.jd.res.JDResult;
import com.lx.benefits.service.jd.IJDPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 22:22
 */
@Service
public class IJDPriceServiceImpl extends JDBaseService implements IJDPriceService {

    private static final  Logger logger = LoggerFactory.getLogger(IJDPriceServiceImpl.class);

    @Override
    public JDPrice getPriceBySku(String sku) throws Exception {
        List<JDPrice> jdPrices = getPriceBySkus(sku);
        return CollectionUtils.isEmpty(jdPrices) ? null : jdPrices.get(0);
    }

    @Override
    public List<JDPrice> getPriceBySkus(String skus) throws Exception {
        try {
            Map<String, String> param = getParam();
            param.put("sku", skus);
            param.put("queryExts", "marketPrice");
            String salePriceStr = postData(getPrice_sale_price_url(), param, CHARSET);
            JDResult<List<JDPrice>> jdResultSalePrice = JSON.parseObject(salePriceStr, new TypeReference<JDResult<List<JDPrice>>>() {
            });
            if (!jdResultSalePrice.isSuccess()) {
                logger.error("JD_GET_SALE_PRICE_ERROR_RESULT_" + salePriceStr + " PARAM=" + skus);
                return Collections.emptyList();
            }
            return jdResultSalePrice.getResult();
        } catch (Exception e) {
            logger.error("JD_GET_PRICE_ERROR,", e);
            logger.error("JD_GET_PRICE_ERROR_PARAM" + skus);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Double balance(Integer payType) throws Exception {
        Map<String,String> param = getParam();
        param.put("payType" ,payType==null? "4": payType.toString());
        String res = postData(getPrice_balance_url(),param,CHARSET);

        logger.info("JD_GET_BALANCE_RESULT_"+res);
        JDResult<Double> jdResult = JSON.parseObject(res,new TypeReference<JDResult<Double>>(){});
        if(!jdResult.isSuccess()){
            logger.error("JD_GET_BALANCE_ERROR_RESULT_"+res);
        }

        return  jdResult.getResult();
    }
}
