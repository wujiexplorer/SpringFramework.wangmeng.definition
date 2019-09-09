package com.lx.benefits.web.ao;


import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.base.dto.PageForSearch;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.query.QuerySearch;
import com.lx.benefits.bean.dto.sch.Nav;
import com.lx.benefits.bean.dto.sch.SearchQuery;
import com.lx.benefits.bean.dto.sch.result.Aggregate;
import com.lx.benefits.bean.dto.sch.result.ItemResult;
import com.lx.benefits.bean.dto.sch.result.ShopResult;
import com.lx.benefits.bean.dto.yianapi.PageInfo;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.bean.vo.query.NavigationVO;
import com.lx.benefits.bean.vo.query.SearchConditionVO;
import com.lx.benefits.bean.vo.query.SearchItemVO;
import com.lx.benefits.bean.vo.query.SearchShopVO;
import com.lx.benefits.convert.SearchConvert;
import com.lx.benefits.service.navigation.NavigationCategoryService;
import com.lx.benefits.service.search.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 搜索业务层
 *
 * @author zhuss
 * @2016年3月2日 上午11:42:27
 */
@Service
public class SearchAO {

    private final Logger log = LoggerFactory.getLogger(SearchAO.class);

    @Resource
    private NavigationCategoryService navigationCategoryService;


    @Resource
    private SearchService searchService;

    /**
     * 搜索-导航(分类和品牌)
     *
     * @param
     * @return
     */
    public MResultVO<NavigationVO> navigation() {
        try {
            ResultInfo<Nav> result = new ResultInfo<>();
            Nav nav = navigationCategoryService.getNav();
            result.setData(nav);
            if (result.isSuccess())
                return new MResultVO<>(MResultInfo.SUCCESS, SearchConvert.convertNavigation(result.getData()));
            return new MResultVO<>(result.getMsg().getMessage());
        } catch (Exception e) {
            log.error("[API接口 - 搜索导航 Exception] = {}", e);
            return new MResultVO<>(MResultInfo.CONN_ERROR);
        }
    }

    /**
     * 执行搜索
     *
     * @param
     * @return
     */
    public MResultVO<PageForSearch<SearchItemVO, List<SearchShopVO>>> search(QuerySearch search) {
        try {
            SearchQuery query = SearchConvert.convertSearchParam(search);

            if(StringUtil.isNotBlank(search.getSuppliertag())){
                if(search.getSuppliertag().equalsIgnoreCase("jd")){
                   //query.setSupplierId(jdItemProxy.getJdSupplier().getData().getId());
                }else if(search.getSuppliertag().equalsIgnoreCase("yx")) {
                    //query.setSupplierId(yxOrderProxy.getYXSupplier().getData().getId());
                }
            }
            ResultInfo<PageInfo<ItemResult>> result = null;
            //ResultInfo<PageInfo<ItemResult>> result = searchProxy.search(query);
            ResultInfo<ShopResult> shopResult = new ResultInfo<>();
//            if (result.isSuccess()) {
//                PageForSearch<SearchItemVO, List<SearchShopVO>> pageForSearch = SearchConvert.convertSearchItem(result.getData(), shopResult.getData(), null);
//                pageForSearch.setPagesize(search.getSize());
//                return new MResultVO<>(MResultInfo.SUCCESS, pageForSearch);
//            }

            return new MResultVO<>(result.getMsg().getMessage());
        } catch (Exception e) {
            log.error("[API接口 - 执行搜索 Exception] = {}", e);
            return new MResultVO<>(MResultInfo.CONN_ERROR);
        }
    }

    /**
     * 搜索结果 - 筛选
     *
     * @param
     * @return
     */
    public MResultVO<List<SearchConditionVO>> condition(QuerySearch search) {
        try {
            ResultInfo<List<Aggregate>> result = searchService.aggregate(SearchConvert.convertSearchParam(search));
            if (result.isSuccess())
                return new MResultVO<>(MResultInfo.SUCCESS, SearchConvert.convertSearchCondition(result.getData()));
            return new MResultVO<>(result.getMsg().getMessage());
        } catch (Exception e) {
            log.error("[API接口 - 搜索结果 - 筛选 Exception] = {}", e);
            return new MResultVO<>(MResultInfo.CONN_ERROR);
        }
    }

    /**
     * 搜索结果 - 筛选
     *
     * @param
     * @return
     */
    public MResultVO<List<SearchConditionVO>> conditionNav(QuerySearch search) {
        try {
            ResultInfo<List<Aggregate>> result = searchService.aggregateNav(SearchConvert.convertSearchParam(search));
            if (result.isSuccess())
                return new MResultVO<>(MResultInfo.SUCCESS, SearchConvert.convertSearchCondition(result.getData()));
            return new MResultVO<>(result.getMsg().getMessage());
        } catch (Exception e) {
            log.error("[API接口 - 搜索结果 - 筛选 Exception] = {}", e);
            return new MResultVO<>(MResultInfo.CONN_ERROR);
        }
    }
}
