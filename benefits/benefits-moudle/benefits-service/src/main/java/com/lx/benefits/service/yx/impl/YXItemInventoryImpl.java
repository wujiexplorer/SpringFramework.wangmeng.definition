package com.lx.benefits.service.yx.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.dto.yx.YXItemInventoryDto;
import com.lx.benefits.bean.dto.yx.result.YXResult;
import com.lx.benefits.bean.util.RetryUtil;
import com.lx.benefits.service.yx.YXBaseService;
import com.lx.benefits.service.yx.YXItemInventory;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: fan
 * Date: 2019/02/27
 * Time: 17:23
 */
@Service
public class YXItemInventoryImpl extends YXBaseService implements YXItemInventory {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public YXResult<List<YXItemInventoryDto>> inventory(List<Long> skuIds) {
        Map<String, String> param = new HashMap<>();
        param.put("skuIds", JSON.toJSONString(skuIds));
        String res = handle(param, getInventoryGet());
        logger.info("YX_INVENTORY_RESULT:" + res);
        YXResult<String> result = JSON.parseObject(res, new TypeReference<YXResult<String>>() {
        });
        if (res == null || !result.success()) {
            logger.info("YX_INVENTORY_ERROR: res={},skuIds={}", res, ArrayUtils.toString(skuIds));
            RetryUtil.setDelayRetryTimes(3, 1200).retry(skuIds);
            return new YXResult<>(-1, "获取库存信息失败");
        }
        List<YXItemInventoryDto> inventoryList = null;
        if (result.getResult() != null) {
            inventoryList = JSON.parseObject(result.getResult(), new TypeReference<List<YXItemInventoryDto>>() {
            });
        } else {
            logger.info("YX_INVENTORY_ERROR: 没有取到库存数据， res={},skuIds={}", res, ArrayUtils.toString(skuIds));
            RetryUtil.setDelayRetryTimes(3, 1200).retry(skuIds);
        }
        YXResult<List<YXItemInventoryDto>> re = new YXResult<>(result.getCode(), result.getMsg());
        re.setResult(inventoryList);
        return re;
    }

    @Override
    public YXResult<YXItemInventoryDto> inventory(Long skuId) {
        YXResult<List<YXItemInventoryDto>> result = inventory(Arrays.asList(skuId));
        if (!result.success()) {
            return new YXResult<>(result.getCode(), result.getMsg());
        }
        if (CollectionUtils.isEmpty(result.getResult())) {
            return new YXResult<>();
        }
        return new YXResult<>(result.getResult().get(0));
    }

    @Override
    public YXResult testCallback() {
        return null;
    }
}
