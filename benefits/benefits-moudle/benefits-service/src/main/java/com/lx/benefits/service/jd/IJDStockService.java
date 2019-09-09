package com.lx.benefits.service.jd;

import com.lx.benefits.bean.dto.jd.JDNewStock;
import com.lx.benefits.bean.dto.jd.JDSSkuNums;
import com.lx.benefits.bean.dto.jd.JDStock;

import java.util.List;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 22:25
 * JD API库存相关接口
 */
public interface IJDStockService {

    /**
     * 批量获取库存接口（建议订单详情页、下单使用）
     * @param skus 商品和数量  [{skuId: 569172,num:101}]
     * @param area 格式：1_0_0 (分别代表1、2、3级地址)
     * @return
     */
    List<JDNewStock> newStock(List<JDSSkuNums> skus, String area) throws Exception;

    /**
     *6.3 批量获取库存接口(建议商品列表页使用)
     * @param skus
     * @param area
     * @return
     */
    List<JDStock> stock(List<String> skus, String area) throws Exception;


    Integer stock(String sku,Long jdProvinceId,Long jdCityId,Long jdCountyId) throws Exception;

}
