package com.lx.benefits.service.jd;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.jd.JDSkuImportReq;
import com.lx.benefits.bean.dto.jd.PrdJdBaseItemReq;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 15:41
 */
public interface PrdJdBaseItemService {

    /**
     * 查询商品信息
     *
     * @param record
     * @return
     */
    JSONObject getJdItemList(PrdJdBaseItemReq record);

    /**
     * 数据同步
     *
     * @return
     */
    JSONObject synItemFromJd();

    /**
     * 商品导入
     * @return
     */
    JSONObject goodsImport(JDSkuImportReq record);

}
