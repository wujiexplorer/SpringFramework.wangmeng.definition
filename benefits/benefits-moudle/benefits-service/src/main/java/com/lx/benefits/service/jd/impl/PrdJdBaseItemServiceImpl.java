package com.lx.benefits.service.jd.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.jd.JDSkuImportReq;
import com.lx.benefits.bean.dto.jd.PrdJdBaseItemReq;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.mapper.jd.PrdJdBaseItemMapper;
import com.lx.benefits.service.jd.IJDItemStatisticsService;
import com.lx.benefits.service.jd.PrdJdBaseItemService;
import com.lx.benefits.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 15:42
 */
@Service
public class PrdJdBaseItemServiceImpl implements PrdJdBaseItemService {

    private static final Logger logger = LoggerFactory.getLogger(PrdJdBaseItemServiceImpl.class);

    public static final String SYN_JD_ITEM_CACHE_KEY_PROCESS = "syn_jd_item_cache_key_proceing";

    @Autowired
    PrdJdBaseItemMapper jdBaseItemMapper;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    IJDItemStatisticsService ijdItemStatisticsService;

    @Override
    public JSONObject getJdItemList(PrdJdBaseItemReq record) {
        record.setPage(record.getPage() > 0 ? (record.getPage() - 1) * record.getPageSize() : 0);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("list", jdBaseItemMapper.getJdItemList(record));
        jsonObject.put("count", jdBaseItemMapper.getJdItemListCount(record));
        return jsonObject;
    }

    @Override
    public JSONObject synItemFromJd() {
        JSONObject jsonObject;
        //加锁
        boolean lock = redisUtils.lock(SYN_JD_ITEM_CACHE_KEY_PROCESS, 1024 * 1024);
        if (lock) {
            try {
                ijdItemStatisticsService.statistics();
                jsonObject = Response.succ("操作成功");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                logger.error(e.getMessage(), e);
                jsonObject = Response.fail("操作失败，请稍后重试");
            } finally {
                redisUtils.unLock(SYN_JD_ITEM_CACHE_KEY_PROCESS);
            }
        } else {
            jsonObject = Response.succ("数据同步中，请稍后……");
        }
        return jsonObject;
    }

    @Override
    public JSONObject goodsImport(JDSkuImportReq record) {
        try {
            ijdItemStatisticsService.goodsImport(record);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Response.succ();
    }


}
