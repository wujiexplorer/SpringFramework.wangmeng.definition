package com.lx.benefits.service.jd.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.dto.jd.*;
import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import com.lx.benefits.bean.dto.jd.res.JDResult;
import com.lx.benefits.bean.dto.jd.utils.AssertUtil;
import com.lx.benefits.service.jd.IJDAccessTokenService;
import com.lx.benefits.service.jd.IJDItemService;
import com.lx.benefits.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 18:24
 */
@Service
public class IJDItemServiceImpl extends JDBaseService  implements IJDItemService {

    private static final Logger logger = LoggerFactory.getLogger(IJDItemServiceImpl.class);

    private static final String JD_ITEM_POOL_SKUS_KEY_PREFIX = "jd:itempool:skus:poolid:";

    private static final Integer JD_ITEM_POOL_SKUS_EXPIRE_SPAN = 15*60;

    @Autowired
    private IJDAccessTokenService tokenService;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public List<JDItemPool> poolList() {
        String token = tokenService.getToken();
        AssertUtil.notBlank(token, "TOKEN为空");
        Map<String, String> param = new HashMap<>();
        param.put("token", token);

        String res = postData(getItem_pool_url(), param, "UTF-8");
        logger.info("JD_GET_ITEM_POOL_RESULT=" + res);
        JDResult<List<JDItemPool>> result = JSON.parseObject(res, new TypeReference<JDResult<List<JDItemPool>>>() {
        });

        if (!result.isSuccess()) {
            logger.error("JD_GET_ITEM_POOL_ERROR.RESULT=" + res);
        }
        return result.getResult();
    }

    @Override
    public List<String> pollSkuList(String pageNum) {
        List<String> cacheSkuList = getCache(getItemPoolSkusCacheKey(pageNum));
        if(!CollectionUtils.isEmpty(cacheSkuList)){
            logger.info("JD_ITEM_SERVICE:POLL_SKU_LIST:HIT_CACHE:pagenum={}",
                    pageNum);
            return cacheSkuList;
        }
        try {
            Long a = 0L;
            String token = tokenService.getToken();
            List<String> skuList = new ArrayList<>();
            Map<String, String> param = new HashMap<>();
            param.put("token", token);
            param.put("pageNum", pageNum);
            String res = postData(getItem_pool_sku_url(), param, "UTF-8");
            JDResult<String> skus = JSON.parseObject(res, new TypeReference<JDResult<String>>() {
            });
            if (skus.isSuccess()) {
                a = a + skus.getResult().split(",").length;
                skuList.addAll(Arrays.asList(skus.getResult().split(",")));
                logger.info("JD_ITEM_SERVICE:POLL_SKU_LIST:GET_FROM_JD_SOURCE:sku_count={}",
                        skuList.size());


            } else {
                logger.error("JD_ITEM_SERVICE:POLL_SKU_LIST:GET_FROM_JD_SOURCE:fail,pagenum={}, error_msg={}",
                        pageNum, skus.getResultMessage());
            }
            if(!CollectionUtils.isEmpty(skuList)){
                setCache(getItemPoolSkusCacheKey(pageNum), skuList,JD_ITEM_POOL_SKUS_EXPIRE_SPAN);
            }
            return skuList;
        } catch (Exception e) {
            logger.error("JD_ITEM_POOL_LIST_ERROR", e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<JDItemState> itemState(List<String> skus) {
        try {
            if (CollectionUtils.isEmpty(skus)) return Collections.EMPTY_LIST;
            String skuStr = getString(skus);
            Map<String, String> param = getParam();
            param.put("sku", skuStr);
            String res = postData(getItem_state_url(), param, CHARSET);
            JDResult<List<JDItemState>> jdItemStates = JSON.parseObject(res, new TypeReference<JDResult<List<JDItemState>>>() {
            });
            if (!jdItemStates.isSuccess()) {
                logger.error("GET_JD_ITEM_STATE_ERROR,RESULT=" + res + " PARAM=" + skus);
                return Collections.EMPTY_LIST;
            }
            return jdItemStates.getResult();
        } catch (Exception e) {
            logger.error("GET_JD_ITEM_STATE_ERROR_PARAM=" + skus);
            logger.error("GET_JD_ITEM_STATE_ERROR", e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public JDItemDetail itemDetail(String sku) {
        Map<String, String> param = getParam();
        param.put("sku", sku);
        param.put("isShow", "true");
        String res = postData(getItem_detail_url(), param, CHARSET);
        JDResult<JDItemDetail> result = JSON.parseObject(res, new TypeReference<JDResult<JDItemDetail>>() {
        });
        if (!result.isSuccess()) {
            logger.error("GET_JD_ITEM_DETAIL_ERROR,RESULT=" + res + " PARAM=" + sku);
            return null;
        }
        return result.getResult();
    }

    @Override
    public List<JDItemImage> itemImages(String sku) {
        Map<String, List<JDItemImage>> stringListMap = itemImages(Arrays.asList(sku));
        return CollectionUtils.isEmpty(stringListMap) ? Collections.EMPTY_LIST : stringListMap.get(sku);
    }

    @Override
    public Map<String, List<JDItemImage>> itemImages(List<String> skus) {
        Map<String, String> param = getParam();
        param.put("sku", getString(skus));
        String res = postData(getItem_image_url(), param, CHARSET);
        JDResult<Map<String, List<JDItemImage>>> result = JSON.parseObject(res, new TypeReference<JDResult<Map<String, List<JDItemImage>>>>() {
        });
        if (!result.isSuccess()) {
            logger.error("GET_JD_ITEM_IMAGE_ERROR,RESULT=" + res + " PARAM=" + skus);
            return Collections.EMPTY_MAP;
        }
        return result.getResult();
    }

    @Override
    public List<JDAreaLimit> checkAreaLimit(QueryAreaLimit limit) {
        Map<String, String> param = getParam();
        param.put("skuIds", getString(limit.getSkus()));
        param.put("province", String.valueOf(limit.getProvince()));
        param.put("city", String.valueOf(limit.getCity()));
        param.put("county", String.valueOf(limit.getCounty()));
        param.put("town", String.valueOf(limit.getTown()));

        String resStr = postData(getItem_check_area_limit_url(), param, CHARSET);
        JDResult<String> result = JSON.parseObject(resStr, new TypeReference<JDResult<String>>() {
        });
        logger.info("JD_CHECK_AREA_LIMIT_res=" + resStr + " PARAM=" + JSON.toJSONString(limit));
        if (!result.isSuccess()) {
            logger.error("JD_CHECK_AREA_LIMIT_ERROR_RESULT=" + resStr + " PARAM=" + JSON.toJSONString(limit));
            return Collections.EMPTY_LIST;
        }
        List<JDAreaLimit> jdAreaLimits = JSON.parseObject(result.getResult(), new TypeReference<List<JDAreaLimit>>() {
        });
        return jdAreaLimits;
    }

    @Override
    public List<JDItemCheck> check(List<String> skus) {
        if (CollectionUtils.isEmpty(skus)) return Collections.EMPTY_LIST;
        Map<String, String> map = getParam();
        map.put("skuIds", getString(skus));
        String res = postData(getItem_check_url(), map, CHARSET);
        logger.info("JD_CHECK_ITEM_RESULT_" + res);

        JDResult<List<JDItemCheck>> jdResult = JSON.parseObject(res, new TypeReference<JDResult<List<JDItemCheck>>>() {
        });

        if (!jdResult.isSuccess()) {
            logger.error("JD_CHECK_ITEM_ERROR_RESULT:" + res);
            logger.error("JD_CHECK_ITEM_ERROR_PARAM:" + skus);
            throw new ServiceException("获取商品可售状态失败");
        }
        return jdResult.getResult();
    }

    @Override
    public JDItemCheck check(String sku) {
        List<JDItemCheck> jdItemChecks = check(Arrays.asList(sku));
        if (CollectionUtils.isEmpty(jdItemChecks)) {
            throw new ServiceException("获取商品可售状态失败");
        }
        return jdItemChecks.get(0);
    }

    private String getItemPoolSkusCacheKey(String pageNum){
        return JD_ITEM_POOL_SKUS_KEY_PREFIX+pageNum;
    }

    private  List<String> getCache(String key){
        return JSON.parseObject(redisUtils.get(key), new TypeReference<List<String>>() {
        });
    }
    private void setCache(String key, Object o, Integer expire){
        redisUtils.set(key, o, expire);
    }

}
