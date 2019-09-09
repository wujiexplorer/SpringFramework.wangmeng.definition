package com.lx.benefits.service.search;

import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.sch.SearchQuery;
import com.lx.benefits.bean.dto.sch.result.Aggregate;
import com.lx.benefits.bean.dto.sch.result.ItemResult;
import com.lx.benefits.bean.dto.sch.result.ShopResult;
import com.lx.benefits.bean.dto.yianapi.PageInfo;

import java.io.IOException;
import java.util.List;

public interface SearchService {

    /**
     * 搜索和分类导航 获取商品信息
     * @param query
     * @return 商品信息
     * @throws IOException
     */
    ResultInfo<PageInfo<ItemResult>> search(SearchQuery query) throws  Exception;

    /**
     * 搜索和分类导航 获取统计信息
     * @param query
     * @return 统计信息
     * @throws IOException
     */
    ResultInfo<List<Aggregate>> aggregate(SearchQuery query) throws IOException;

    /**
     * 搜索和分类导航 获取统计信息
     * @param query
     * @return 统计信息
     * @throws IOException
     */
    ResultInfo<List<Aggregate>> aggregateNav(SearchQuery query) throws IOException;

    /**
     * 搜索店铺，只返回一个最精确的店铺信息
     * @param query
     * @return
     */
    ResultInfo<ShopResult> searchShop(SearchQuery query) throws IOException;
}
