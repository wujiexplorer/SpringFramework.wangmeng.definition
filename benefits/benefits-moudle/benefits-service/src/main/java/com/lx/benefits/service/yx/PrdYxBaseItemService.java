package com.lx.benefits.service.yx;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.yx.PrdYxBaseItemReq;
import com.lx.benefits.bean.dto.yx.YxSkuImportReq;

/**
 * User: fan
 * Date: 2019/02/28
 * Time: 00:36
 */
public interface PrdYxBaseItemService {

    /**
     * 查询商品信息
     *
     * @param record
     * @return
     */
    JSONObject getYxItemList(PrdYxBaseItemReq record);
    /**
     * 商品导入
     * @return
     */
    JSONObject goodsImport(YxSkuImportReq record);

}
