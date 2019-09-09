package com.lx.benefits.service.yx;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.yx.result.YXResult;

import java.util.List;

/**
 * User: fan
 * Date: 2019/02/27
 * Time: 16:53
 */
public interface IYXItemService {

    /**
     * 拉取 sku
     *
     * @return
     */
    YXResult<List<Long>> getItemIds();

    /**
     * 保存严选数据到我方表中
     *
     * @param ids
     */
    void synItem(List<Long> ids) throws InterruptedException;

    JSONObject getYxItem(List<Long> subIds);

}
