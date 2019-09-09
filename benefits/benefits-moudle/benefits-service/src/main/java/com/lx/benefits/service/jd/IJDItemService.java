package com.lx.benefits.service.jd;

import com.lx.benefits.bean.dto.jd.*;

import java.util.List;
import java.util.Map;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 18:22
 * JD API商品相关接口
 */
public interface IJDItemService {

    /**
     * 获取商品池编号接口
     *
     * @return
     * @throws Exception
     */
    List<JDItemPool> poolList();

    /**
     * 获取池内商品编号
     *
     * @param pageNum
     * @return
     * @throws Exception
     */
    List<String> pollSkuList(String pageNum);

    /**
     * 商品状态
     *
     * @param skus
     * @return
     * @throws Exception
     */
    List<JDItemState> itemState(List<String> skus);

    /**
     * 商品详情
     *
     * @param sku
     * @return
     * @throws Exception
     */
    JDItemDetail itemDetail(String sku);

    /**
     * 商品图片
     *
     * @param sku
     * @return
     * @throws Exception
     */
    List<JDItemImage> itemImages(String sku);

    /**
     * 商品图片
     *
     * @param skus
     * @return
     * @throws Exception
     */
    Map<String, List<JDItemImage>> itemImages(List<String> skus);


    /**
     * 区域限购信息
     *
     * @param limit
     * @return
     * @throws Exception
     */
    List<JDAreaLimit> checkAreaLimit(QueryAreaLimit limit);

    /**
     * 商品是否可售
     *
     * @param skus
     * @return
     */
    List<JDItemCheck> check(List<String> skus);

    /**
     * 商品是否可售
     *
     * @param sku
     * @return
     */
    JDItemCheck check(String sku);

}
