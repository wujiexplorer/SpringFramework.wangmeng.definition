package com.lx.benefits.service.yx.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.yx.result.YXResult;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.yx.IYXItemInfoService;
import com.lx.benefits.service.yx.IYXItemService;
import com.lx.benefits.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: fan
 * Date: 2019/02/27
 * Time: 17:58
 */
@Service
public class IYXItemInfoServiceImpl implements IYXItemInfoService {

    private static final Logger logger = LoggerFactory.getLogger(IYXItemInfoServiceImpl.class);

    public static final String SYN_YX_ITEM_CACHE_KEY_PROCESS = "syn_yx_item_cache_key_proceing";

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    IYXItemService iyxItemService;

    @Override
    public JSONObject statistics() {
        JSONObject jsonObject;
        //加锁
        boolean lock = redisUtils.lock(SYN_YX_ITEM_CACHE_KEY_PROCESS, 1024 * 1024);
        if (lock) {
            try {
                YXResult<List<Long>> yxResult = iyxItemService.getItemIds();
                if (yxResult.success()) {
                    iyxItemService.synItem(yxResult.getResult());
                }
                jsonObject = Response.succ("操作成功");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                logger.error(e.getMessage(), e);
                jsonObject = Response.fail("操作失败，请稍后重试");
            } finally {
                redisUtils.unLock(SYN_YX_ITEM_CACHE_KEY_PROCESS);
            }
        } else {
            jsonObject = Response.succ("数据同步中，请稍后……");
        }
        return jsonObject;
    }

}